// ProductRepository.java — Spring Data repository برای محصولات
// نیازمند: spring-data-jpa jar برای کامپایل
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);
    List<Product> findByActiveTrue();
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceBetween(java.math.BigDecimal min, java.math.BigDecimal max);
    List<Product> findByStockGreaterThan(int minStock);

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.stock > 0 ORDER BY p.price ASC")
    List<Product> findAvailableProducts();

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.active = true")
    List<Product> findByCategoryAndActive(@Param("category") String category);
}
