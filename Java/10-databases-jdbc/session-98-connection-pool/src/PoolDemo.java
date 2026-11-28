// PoolDemo.java — مقایسهٔ DriverManager vs Connection Pool
// نیازمند: sqlite-jdbc + HikariCP jars در classpath برای اجرا
// با javac کامپایل می‌شود — java.sql و javax.sql بخشی از JDK هستند
import javax.sql.DataSource;
import java.sql.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PoolDemo {

    private static final String URL = "jdbc:sqlite:pool_demo.db";

    public static void main(String[] args) throws Exception {
        setupTable();

        // ---------- Benchmark: بدون pool ----------
        System.out.println("=== Without pool (DriverManager) ===");
        long start1 = System.nanoTime();
        runWithoutPool(50);
        long elapsed1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("50 operations took: " + elapsed1 + " ms");

        // ---------- Benchmark: با pool ----------
        System.out.println("\n=== With pool (HikariCP) ===");
        DataSource ds = createPool();
        long start2 = System.nanoTime();
        runWithPool(ds, 50);
        long elapsed2 = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("50 operations took: " + elapsed2 + " ms");

        // ---------- Pool stats ----------
        printPoolStats(ds);

        // ---------- Cleanup ----------
        if (ds instanceof com.zaxxer.hikari.HikariDataSource) {
            ((com.zaxxer.hikari.HikariDataSource) ds).close();
        }
    }

    // ---------- Setup ----------
    static void setupTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS items");
            stmt.execute("CREATE TABLE items (id INTEGER PRIMARY KEY, name TEXT)");
            for (int i = 0; i < 100; i++) {
                stmt.execute("INSERT INTO items VALUES (" + i + ", 'Item" + i + "')");
            }
        }
    }

    // ---------- بدون pool ----------
    static void runWithoutPool(int iterations) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(iterations);
        AtomicInteger errors = new AtomicInteger(0);

        for (int i = 0; i < iterations; i++) {
            executor.submit(() -> {
                try (Connection conn = DriverManager.getConnection(URL);
                     PreparedStatement ps = conn.prepareStatement("SELECT * FROM items WHERE id = ?")) {
                    ps.setInt(1, ThreadLocalRandom.current().nextInt(100));
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) { /* consume */ }
                } catch (SQLException e) {
                    errors.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executor.shutdown();
        System.out.println("Errors: " + errors.get());
    }

    // ---------- با pool ----------
    static void runWithPool(DataSource ds, int iterations) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(iterations);
        AtomicInteger errors = new AtomicInteger(0);

        for (int i = 0; i < iterations; i++) {
            executor.submit(() -> {
                try (Connection conn = ds.getConnection();
                     PreparedStatement ps = conn.prepareStatement("SELECT * FROM items WHERE id = ?")) {
                    ps.setInt(1, ThreadLocalRandom.current().nextInt(100));
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) { /* consume */ }
                } catch (SQLException e) {
                    errors.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executor.shutdown();
        System.out.println("Errors: " + errors.get());
    }

    // ---------- Pool creation (HikariCP) ----------
    static DataSource createPool() {
        com.zaxxer.hikari.HikariConfig config = new com.zaxxer.hikari.HikariConfig();
        config.setJdbcUrl(URL);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        config.setPoolName("demo-pool");
        return new com.zaxxer.hikari.HikariDataSource(config);
    }

    // ---------- Pool stats ----------
    static void printPoolStats(DataSource ds) {
        if (ds instanceof com.zaxxer.hikari.HikariDataSource) {
            com.zaxxer.hikari.HikariDataSource hikari = (com.zaxxer.hikari.HikariDataSource) ds;
            com.zaxxer.hikari.HikariPoolMXBean pool = hikari.getHikariPoolMXBean();
            System.out.println("\n--- Pool Stats ---");
            System.out.println("Active: " + pool.getActiveConnections());
            System.out.println("Idle: " + pool.getIdleConnections());
            System.out.println("Total: " + pool.getTotalConnections());
        }
    }
}
