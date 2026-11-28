// ProductManager.java — پاسخ کامل پروژهٔ جلسه ۱۰۰
// نیازمند: jakarta.persistence-api + Hibernate + SQLite driver jars
public class ProductManager {

    public static void main(String[] args) {
        ProductDao dao = new ProductDao();

        try {
            System.out.println("=== Product Manager (JPA) ===");

            // ---------- Add ----------
            Long id1 = dao.save(new Product("Laptop", 1200.0, "Electronics"));
            Long id2 = dao.save(new Product("Mouse", 25.99, "Electronics"));
            Long id3 = dao.save(new Product("Book", 15.0, "Books"));
            System.out.println("Added products: " + id1 + ", " + id2 + ", " + id3);

            // ---------- Find ----------
            Product found = dao.findById(id1);
            System.out.println("Found: " + found);

            // ---------- Query ----------
            System.out.println("All products: " + dao.findAll().size());
            System.out.println("Electronics: " + dao.findByCategory("Electronics").size());
            System.out.println("Price 10-100: " + dao.findByPriceRange(10, 100).size());

            // ---------- Update ----------
            found.setPrice(1100.0);
            dao.update(found);
            System.out.println("Updated: " + dao.findById(id1));

            // ---------- Delete ----------
            dao.delete(id3);
            System.out.println("Deleted product " + id3);
            System.out.println("Remaining: " + dao.findAll().size());

        } finally {
            dao.close();
        }
    }
}
