// HibernateDemo.java — CRUD با Hibernate native API
// نیازمند: hibernate-core + h2 database jars برای اجرا
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

public class HibernateDemo {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        // ---------- Configuration ----------
        Configuration config = new Configuration()
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(Order.class)
            .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
            .setProperty("hibernate.connection.url", "jdbc:h2:mem:hibernate_demo")
            .setProperty("hibernate.connection.username", "sa")
            .setProperty("hibernate.connection.password", "")
            .setProperty("hibernate.hbm2ddl.auto", "create-drop")
            .setProperty("hibernate.show_sql", "true");

        sessionFactory = config.buildSessionFactory();

        try {
            demoCrud();
            demoHql();
            demoCriteria();
            demoRelationships();
        } finally {
            sessionFactory.close();
        }
    }

    // ---------- CRUD ----------
    static void demoCrud() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            // INSERT
            User user = new User("Ali", "ali@mail.com", 25);
            session.persist(user);
            tx.commit();
            System.out.println("User persisted: " + user.getId());

            // SELECT
            tx = session.beginTransaction();
            User found = session.get(User.class, user.getId());
            System.out.println("Found: " + found);

            // UPDATE
            found.setName("Ali Updated");
            found.setAge(26);
            session.update(found);
            tx.commit();
            System.out.println("User updated.");

            // DELETE
            tx = session.beginTransaction();
            session.delete(found);
            tx.commit();
            System.out.println("User deleted.");

        } finally {
            session.close();
        }
    }

    // ---------- HQL ----------
    static void demoHql() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            // Insert test data
            session.persist(new User("Sara", "sara@mail.com", 30));
            session.persist(new User("Reza", "reza@mail.com", 22));
            session.persist(new User("Maryam", "maryam@mail.com", 35));
            tx.commit();

            // HQL query
            tx = session.beginTransaction();
            List<User> users = session.createQuery(
                "FROM User WHERE age > :minAge ORDER BY name", User.class)
                .setParameter("minAge", 25)
                .list();
            System.out.println("\nUsers older than 25:");
            users.forEach(u -> System.out.println("  " + u));

            // Count query
            Long count = session.createQuery(
                "SELECT COUNT(u) FROM User u", Long.class).uniqueResult();
            System.out.println("Total users: " + count);
            tx.commit();

        } finally {
            session.close();
        }
    }

    // ---------- Criteria API ----------
    static void demoCriteria() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            var cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(User.class);
            var root = cq.from(User.class);

            cq.select(root).where(
                cb.and(
                    cb.gt(root.get("age"), 20),
                    cb.like(root.get("name"), "%a%")
                )
            ).orderBy(cb.asc(root.get("name")));

            List<User> users = session.createQuery(cq).list();
            System.out.println("\nCriteria query results:");
            users.forEach(u -> System.out.println("  " + u));
            tx.commit();

        } finally {
            session.close();
        }
    }

    // ---------- Relationships ----------
    static void demoRelationships() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            // Create user with orders
            User user = new User("OrderUser", "order@mail.com", 28);
            session.persist(user);

            Order order1 = new Order(user, "Laptop", new BigDecimal("1200"));
            Order order2 = new Order(user, "Mouse", new BigDecimal("25.99"));
            session.persist(order1);
            session.persist(order2);
            tx.commit();

            // Query user with lazy orders — باید در session باز access شود
            tx = session.beginTransaction();
            User found = session.get(User.class, user.getId());
            System.out.println("\nUser orders (lazy loaded):");
            found.getOrders().forEach(o -> System.out.println("  " + o));
            tx.commit();

        } finally {
            session.close();
        }
    }
}
