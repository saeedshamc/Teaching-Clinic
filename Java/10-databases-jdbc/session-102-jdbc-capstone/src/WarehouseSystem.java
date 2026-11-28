// WarehouseSystem.java — سیستم مدیریت انبار با JDBC
// نیازمند SQLite JDBC driver در runtime برای اجرا
// با javac کامپایل می‌شود — java.sql بخشی از JDK است
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseSystem {

    private static final String URL = "jdbc:sqlite:warehouse.db";

    // ---------- Models ----------
    public static class Product {
        int id; String sku, name, category; double price; int stock;
        public String toString() {
            return String.format("[%s] %s — %.2f — stock:%d — %s", sku, name, price, stock, category);
        }
    }

    public static class Order {
        int id, productId, quantity; double totalPrice; String status;
        public String toString() {
            return String.format("Order#%d — product:%d — qty:%d — %.2f — %s",
                id, productId, quantity, totalPrice, status);
        }
    }

    public static void main(String[] args) {
        try {
            createTables();
            demo();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void createTables() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS products (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    sku TEXT UNIQUE NOT NULL,
                    name TEXT NOT NULL,
                    price REAL NOT NULL,
                    stock INTEGER DEFAULT 0,
                    category TEXT
                )
                """);
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS orders (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    product_id INTEGER NOT NULL,
                    quantity INTEGER NOT NULL,
                    total_price REAL NOT NULL,
                    status TEXT DEFAULT 'PENDING',
                    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (product_id) REFERENCES products(id)
                )
                """);
        }
    }

    static void demo() throws SQLException {
        System.out.println("=== Warehouse Management System ===\n");

        // ---------- Add products ----------
        addProduct("SKU001", "Laptop", 1200.50, 10, "Electronics");
        addProduct("SKU002", "Mouse", 25.99, 50, "Electronics");
        addProduct("SKU003", "Book", 15.00, 100, "Books");

        // ---------- List products ----------
        System.out.println("\n--- Products ---");
        listProducts().forEach(System.out::println);

        // ---------- Create orders ----------
        System.out.println("\n--- Orders ---");
        boolean o1 = createOrder(1, 2);   // 2 laptops
        boolean o2 = createOrder(2, 30);  // 30 mice
        boolean o3 = createOrder(3, 5);   // 5 books
        boolean o4 = createOrder(1, 100); // 100 laptops — insufficient stock!
        System.out.println("Order 1: " + (o1 ? "SUCCESS" : "FAILED"));
        System.out.println("Order 2: " + (o2 ? "SUCCESS" : "FAILED"));
        System.out.println("Order 3: " + (o3 ? "SUCCESS" : "FAILED"));
        System.out.println("Order 4 (insufficient): " + (o4 ? "SUCCESS" : "FAILED — rolled back"));

        // ---------- List orders ----------
        System.out.println("\n--- All Orders ---");
        listOrders().forEach(System.out::println);

        // ---------- Stock check ----------
        System.out.println("\n--- Final Stock ---");
        listProducts().forEach(System.out::println);
    }

    // ---------- CRUD ----------
    static void addProduct(String sku, String name, double price, int stock, String category) throws SQLException {
        String sql = "INSERT INTO products(sku, name, price, stock, category) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sku); ps.setString(2, name); ps.setDouble(3, price);
            ps.setInt(4, stock); ps.setString(5, category);
            ps.executeUpdate();
        }
    }

    static List<Product> listProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product p = new Product();
                p.id = rs.getInt("id"); p.sku = rs.getString("sku");
                p.name = rs.getString("name"); p.price = rs.getDouble("price");
                p.stock = rs.getInt("stock"); p.category = rs.getString("category");
                products.add(p);
            }
        }
        return products;
    }

    static List<Order> listOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY id";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Order o = new Order();
                o.id = rs.getInt("id"); o.productId = rs.getInt("product_id");
                o.quantity = rs.getInt("quantity"); o.totalPrice = rs.getDouble("total_price");
                o.status = rs.getString("status");
                orders.add(o);
            }
        }
        return orders;
    }

    // ---------- Create Order با Transaction ----------
    static boolean createOrder(int productId, int quantity) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);
            try {
                // ۱. چک موجودی
                int stock = getStock(conn, productId);
                double price = getPrice(conn, productId);
                if (stock < quantity) {
                    conn.rollback();
                    return false;
                }

                // ۲. کاهش موجودی
                try (PreparedStatement ps = conn.prepareStatement(
                        "UPDATE products SET stock = stock - ? WHERE id = ?")) {
                    ps.setInt(1, quantity);
                    ps.setInt(2, productId);
                    ps.executeUpdate();
                }

                // ۳. ثبت سفارش
                double totalPrice = price * quantity;
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO orders(product_id, quantity, total_price, status) VALUES (?, ?, ?, 'COMPLETED')")) {
                    ps.setInt(1, productId);
                    ps.setInt(2, quantity);
                    ps.setDouble(3, totalPrice);
                    ps.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    static int getStock(Connection conn, int productId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT stock FROM products WHERE id = ?")) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("stock") : 0;
            }
        }
    }

    static double getPrice(Connection conn, int productId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT price FROM products WHERE id = ?")) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getDouble("price") : 0;
            }
        }
    }
}
