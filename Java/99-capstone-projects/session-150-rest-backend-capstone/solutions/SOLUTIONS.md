# پاسخ تمرین‌های جلسه ۱۵۰

## آسان

**۱ و ۲.**
```java
@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Size(min=2, max=100)
    private String name;
    @DecimalMin("0.01")
    private BigDecimal price;
}

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByActiveTrue();
}
```

## متوسط

**۳ و ۴.**
```java
@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    public Product createProduct(Product product) { return productRepository.save(product); }
}

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @GetMapping
    public List<Product> getAll() { return productService.getAllProducts(); }
}
```

## سخت

**۵–۷.**
```java
String token = jwtUtil.generateToken(username);

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults());
    return http.build();
}
```
