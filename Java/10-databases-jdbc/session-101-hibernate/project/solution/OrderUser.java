// OrderUser.java — Hibernate entity برای user با orders
// نیازمند: jakarta.persistence-api + hibernate-core jars برای کامپایل
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_users")
public class OrderUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orders = new ArrayList<>();

    public OrderUser() {}
    public OrderUser(String name, String email) {
        this.name = name; this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<OrderItem> getOrders() { return orders; }

    public void addOrder(OrderItem order) {
        orders.add(order);
        order.setUser(this);
    }
}
