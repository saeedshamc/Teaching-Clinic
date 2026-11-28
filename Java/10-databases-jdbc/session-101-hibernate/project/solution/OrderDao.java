// OrderDao.java — Hibernate DAO برای orders
// نیازمند: hibernate-core + h2 database jars برای اجرا
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

public class OrderDao {

    private final SessionFactory sessionFactory;

    public OrderDao() {
        Configuration config = new Configuration()
            .addAnnotatedClass(OrderUser.class)
            .addAnnotatedClass(OrderItem.class)
            .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
            .setProperty("hibernate.connection.url", "jdbc:h2:mem:order_db")
            .setProperty("hibernate.connection.username", "sa")
            .setProperty("hibernate.hbm2ddl.auto", "create-drop");
        sessionFactory = config.buildSessionFactory();
    }

    public void close() { sessionFactory.close(); }

    public void saveUserWithOrders(OrderUser user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(user);
            tx.commit();
        } finally {
            session.close();
        }
    }

    public OrderUser findUserById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(OrderUser.class, id);
        } finally {
            session.close();
        }
    }

    public List<OrderItem> findOrdersByUser(Long userId) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery(
                "FROM OrderItem o WHERE o.user.id = :userId", OrderItem.class)
                .setParameter("userId", userId)
                .list();
        } finally {
            session.close();
        }
    }

    public List<OrderItem> findExpensiveOrders(BigDecimal minAmount) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery(
                "FROM OrderItem o WHERE o.amount > :min", OrderItem.class)
                .setParameter("min", minAmount)
                .list();
        } finally {
            session.close();
        }
    }

    public void deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            OrderUser user = session.get(OrderUser.class, id);
            if (user != null) session.delete(user);
            tx.commit();
        } finally {
            session.close();
        }
    }
}
