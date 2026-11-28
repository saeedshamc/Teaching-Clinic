// OrderItem.java — Hibernate entity برای order
// نیازمند: jakarta.persistence-api + hibernate-core jars برای کامپایل
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private OrderUser user;

    private String productName;
    private BigDecimal amount;

    public OrderItem() {}
    public OrderItem(String productName, BigDecimal amount) {
        this.productName = productName; this.amount = amount;
    }

    public Long getId() { return id; }
    public OrderUser getUser() { return user; }
    public void setUser(OrderUser user) { this.user = user; }
    public String getProductName() { return productName; }
    public BigDecimal getAmount() { return amount; }

    @Override
    public String toString() {
        return String.format("Order{id=%d, product='%s', amount=%s}", id, productName, amount);
    }
}
