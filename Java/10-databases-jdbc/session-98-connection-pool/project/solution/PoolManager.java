// PoolManager.java — پاسخ کامل پروژهٔ جلسه ۹۸
// نیازمند: sqlite-jdbc + HikariCP jars در classpath
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import javax.sql.DataSource;
import java.sql.*;

public class PoolManager {

    private static final String URL = "jdbc:sqlite:pool_manager.db";
    private static HikariDataSource ds;

    public static void main(String[] args) throws Exception {
        // ---------- Init pool ----------
        initPool();
        Runtime.getRuntime().addShutdownHook(new Thread(PoolManager::shutdown));

        // ---------- Setup ----------
        setupTable();

        // ---------- Test connections ----------
        testConnections(20);

        // ---------- Monitor ----------
        monitorPool();

        // ---------- Cleanup ----------
        shutdown();
    }

    static void initPool() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        config.setPoolName("pool-manager");
        config.setLeakDetectionThreshold(60000);  // 60s
        ds = new HikariDataSource(config);
        System.out.println("Pool initialized: max=10, min=2");
    }

    static void setupTable() throws SQLException {
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS items (id INTEGER PRIMARY KEY, name TEXT)");
            stmt.execute("DELETE FROM items");
            for (int i = 0; i < 50; i++) {
                stmt.execute("INSERT INTO items VALUES (" + i + ", 'Item" + i + "')");
            }
        }
    }

    static void testConnections(int count) throws Exception {
        java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(5);
        java.util.concurrent.CountDownLatch latch = new java.util.concurrent.CountDownLatch(count);
        java.util.concurrent.atomic.AtomicInteger success = new java.util.concurrent.atomic.AtomicInteger(0);
        java.util.concurrent.atomic.AtomicInteger fail = new java.util.concurrent.atomic.AtomicInteger(0);

        for (int i = 0; i < count; i++) {
            executor.submit(() -> {
                try (Connection conn = ds.getConnection();
                     PreparedStatement ps = conn.prepareStatement("SELECT * FROM items LIMIT 1")) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) success.incrementAndGet();
                } catch (SQLException e) {
                    fail.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executor.shutdown();
        System.out.println("Connections tested: " + count + " — success=" + success.get() + ", fail=" + fail.get());
    }

    static void monitorPool() {
        HikariPoolMXBean pool = ds.getHikariPoolMXBean();
        System.out.println("\n--- Pool Monitor ---");
        System.out.println("Active: " + pool.getActiveConnections());
        System.out.println("Idle: " + pool.getIdleConnections());
        System.out.println("Total: " + pool.getTotalConnections());
    }

    static void shutdown() {
        if (ds != null && !ds.isClosed()) {
            ds.close();
            System.out.println("Pool closed.");
        }
    }
}
