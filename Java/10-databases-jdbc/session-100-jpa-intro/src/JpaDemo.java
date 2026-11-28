// JpaDemo.java — JPA CRUD با EntityManager
// نیازمند: jakarta.persistence-api + Hibernate + SQLite driver jars
// با javac بدون jarها کامپایل نمی‌شود — نیازمند dependencies
import jakarta.persistence.*;
import java.util.List;

public class JpaDemo {

    public static void main(String[] args) {
        // ---------- EntityManagerFactory ----------
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // ---------- CREATE ----------
            tx.begin();
            User user1 = new User("Ali", "ali@mail.com", 25);
            User user2 = new User("Sara", "sara@mail.com", 30);
            em.persist(user1);   // INSERT
            em.persist(user2);
            tx.commit();
            System.out.println("Users persisted: " + user1.getId() + ", " + user2.getId());

            // ---------- READ ----------
            tx.begin();
            User found = em.find(User.class, user1.getId());  // SELECT by id
            System.out.println("Found: " + found);

            // JPQL query
            List<User> all = em.createQuery("SELECT u FROM User u", User.class).getResultList();
            System.out.println("Total users: " + all.size());
            tx.commit();

            // ---------- UPDATE ----------
            tx.begin();
            found.setName("Ali Updated");
            found.setAge(26);
            em.merge(found);   // UPDATE
            tx.commit();
            System.out.println("User updated.");

            // ---------- DELETE ----------
            tx.begin();
            em.remove(found);  // DELETE
            tx.commit();
            System.out.println("User deleted.");

        } finally {
            em.close();
            emf.close();
        }
    }
}
