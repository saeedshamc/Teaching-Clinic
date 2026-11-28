// WarehouseFull.java — پاسخ کامل پروژهٔ جلسه ۱۰۲
// نیازمند SQLite JDBC driver در runtime
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseFull {

    private static final String URL = "jdbc:sqlite:warehouse_full.db";

    public static class Product {
        int id; String sku, name, category; double price; int stock;
    }
    public static class Order {
        int id, productId, quantity; double totalPrice; String status;
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
                    sku TEXT UNIQUE NOT NULL, name TEXT NOT NULL,
                    price REAL NOT NULL, stock INTEGER DEFAULT 0, category TEXT)""");
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS orders (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    product_id INTEGER NOT NULL, quantity INTEGER NOT NULL,
                    total_price REAL NOT NULL, status TEXT DEFAULT 'PENDING',
                    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (product_id) REFERENCES products(id))""");
        }
    }

    static void demo() throws SQLException {
        System.out.println("=== Warehouse Management System ===\n");

        addProduct("SKU001", "Laptop", 1200.50, 10, "Electronics");
        addProduct("SKU002", "Mouse", 25.99, 50, "Electronics");
        addProduct("SKU003", "Book", 15.00, 100, "Books");

        System.out.println("\n--- Products ---");
        listProducts().forEach(p -> System.out.println(
            String.format("[%s] %s — %.2f — stock:%d", p.sku, p.name, p.price, p.stock)));

        System.out.println("\n--- Orders ---");
        System.out.println("Order 1 (2× Laptop): " + (createOrder(1, 2) ? "SUCCESS" : "FAILED"));
        System.out.println("Order 2 (30× Mouse): " + (createOrder(2, 30) ? "SUCCESS" : "FAILED"));
        System.out.println("Order 3 (100× Laptop): " + (createOrder(1, 100) ? "SUCCESS" : "FAILED — insufficient"));

        listOrders().forEach(o -> System.out.println(
            String.format("Order#%d — product:%d — qty:%d — %.2f — %s",
                o.id, o.productId, o.quantity, o.totalPrice, o.status)));

        System.out.println("\n--- Restock ---");
        restock(1, 20);
        System.out.println("Laptop restocked +20");

        System.out.println("\n--- Final Stock ---");
        listProducts().forEach(p -> System.out.println(
            String.format("[%s] %s — stock:%d", p.sku, p.name, p.stock)));
    }

    // ---------- Product CRUD ----------
    static void addProduct(String sku, String name, double price, int stock, String category) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO products(sku, name, price, stock, category) VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, sku); ps.setString(2, name); ps.setDouble(3, price);
            ps.setInt(4, stock); ps.setString(5, category);
            ps.executeUpdate();
        }
    }

    static List<Product> listProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products ORDER BY id")) {
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

    // ---------- Order ----------
    static boolean createOrder(int productId, int quantity) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);
            try {
                int stock = getInt(conn, "SELECT stock FROM products WHERE id = ?", productId);
                double price = getDouble(conn, "SELECT price FROM products WHERE id = ?", productId);
                if (stock < quantity) { conn.rollback(); return false; }

                updateStock(conn, productId, stock - quantity);
                insertOrder(conn, productId, quantity, price * quantity);
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    static List<Order> listOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM orders ORDER BY id")) {
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

    static void restock(int productId, int quantity) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                 "UPDATE products SET stock = stock + ? WHERE id = ?")) {
            ps.setInt(1, quantity); ps.setInt(2, productId);
            ps.executeUpdate();
        }
    }

    // ---------- Helpers ----------
    static void updateStock(Connection conn, int productId, int newStock) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE products SET stock = ? WHERE id = ?")) {
            ps.setInt(1, newStock); ps.setInt(2, productId);
            ps.executeUpdate();
        }
    }

    static void insertOrder(Connection conn, int productId, int quantity, double totalPrice) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO orders(product_id, quantity, total_price, status) VALUES (?, ?, ?, 'COMPLETED')")) {
            ps.setInt(1, productId); ps.setInt(2, quantity); ps.setDouble(3, totalPrice);
            ps.executeUpdate();
        }
    }

    static int getInt(Connection conn, String sql, int param) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, param);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? rs.getInt(1) : 0; }
        }
    }

    static double getDouble(Connection conn, String sql, int param) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, param);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? rs.getDouble(1) : 0; }
        }
    }
}
