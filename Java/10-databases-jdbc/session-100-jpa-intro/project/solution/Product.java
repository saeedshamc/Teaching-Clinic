// Product.java — JPA entity برای product
// نیازمند: jakarta.persistence-api jar در classpath برای کامپایل
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Double price;
    private String category;

    public Product() {}
    public Product(String name, Double price, String category) {
        this.name = name; this.price = price; this.category = category;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', price=%.2f, category='%s'}",
            id, name, price, category);
    }
}
