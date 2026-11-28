// ProductService.java — service layer برای محصولات
// نیازمند: spring-context jar برای کامپایل
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getActiveProducts() {
        return productRepository.findByActiveTrue();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productData) {
        Product product = productRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Product not found: " + id));
        product.setName(productData.getName());
        product.setPrice(productData.getPrice());
        product.setStock(productData.getStock());
        product.setCategory(productData.getCategory());
        product.setDescription(productData.getDescription());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryAndActive(category);
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findAvailableProducts();
    }
}
