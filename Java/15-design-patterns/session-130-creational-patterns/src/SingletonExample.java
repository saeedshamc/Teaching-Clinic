// SingletonExample.java — Singleton pattern demo
// قابل کامپایل با javac — بدون dependency
public class SingletonExample {

    public static void main(String[] args) {
        // Singleton — same instance
        DatabaseConnection conn1 = DatabaseConnection.getInstance();
        DatabaseConnection conn2 = DatabaseConnection.getInstance();

        System.out.println("Same instance: " + (conn1 == conn2));
        System.out.println("HashCode: " + conn1.hashCode());
        System.out.println("HashCode: " + conn2.hashCode());
    }

    static class DatabaseConnection {
        private static class Holder {
            static final DatabaseConnection INSTANCE = new DatabaseConnection();
        }

        private DatabaseConnection() {
            System.out.println("DatabaseConnection created");
        }

        public static DatabaseConnection getInstance() {
            return Holder.INSTANCE;
        }

        public void query(String sql) {
            System.out.println("Executing: " + sql);
        }
    }
}
