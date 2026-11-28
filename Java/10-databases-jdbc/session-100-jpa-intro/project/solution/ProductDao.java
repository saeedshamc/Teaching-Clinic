// ProductDao.java — JPA DAO برای products
// نیازمند: jakarta.persistence-api + Hibernate + SQLite driver jars
import jakarta.persistence.*;
import java.util.List;

public class ProductDao {

    private final EntityManagerFactory emf;

    public ProductDao() {
        emf = Persistence.createEntityManagerFactory("myPU");
    }

    public void close() { emf.close(); }

    public Long save(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(product);
            tx.commit();
            return product.getId();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Product findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public List<Product> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Product> findByCategory(String category) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT p FROM Product p WHERE p.category = :cat", Product.class)
                .setParameter("cat", category)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Product> findByPriceRange(double min, double max) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max", Product.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(product);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Product p = em.find(Product.class, id);
            if (p != null) em.remove(p);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
