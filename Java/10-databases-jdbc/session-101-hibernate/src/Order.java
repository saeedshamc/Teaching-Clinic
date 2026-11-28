// Order.java — Hibernate entity برای orders
// نیازمند: jakarta.persistence-api + hibernate-core jars برای کامپایل
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String productName;
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Order() {}
    public Order(User user, String productName, BigDecimal amount) {
        this.user = user; this.productName = productName; this.amount = amount;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getProductName() { return productName; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return String.format("Order{id=%d, product='%s', amount=%s}", id, productName, amount);
    }
}
