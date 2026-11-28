# راهنمای پاسخ — Store Backend توسعه‌یافته

## Cart
```java
@Entity
public class Cart {
    @Id @GeneratedValue Long id;
    @OneToOne User user;
    @OneToMany(mappedBy="cart", cascade=ALL, orphanRemoval=true)
    List<CartItem> items = new ArrayList<>();
    public void addItem(Product p, int qty) {
        items.stream().filter(i -> i.getProduct().getId().equals(p.getId()))
            .findFirst()
            .ifPresentOrElse(i -> i.setQuantity(i.getQuantity()+qty),
                () -> { CartItem ci = new CartItem(); ci.setProduct(p); ci.setQuantity(qty); items.add(ci); ci.setCart(this); });
    }
}
```
Checkout: سفارش را از آیتم‌های سبد بساز و سبد را خالی کن — داخل `@Transactional`.

## Coupon
```java
@Entity
public class Coupon {
    String code;              // unique
    BigDecimal percentOff;    // یا amountOff
    LocalDateTime expiresAt;
    int maxUses;
    int usedCount;
    public boolean isValid() { return expiresAt.isAfter(LocalDateTime.now()) && usedCount < maxUses; }
}
```
در checkout: `total = total * (1 - percentOff/100)` یا `total - amountOff`.

## docker-compose
```yaml
services:
  app:
    build: .
    ports: ["8080:8080"]
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/store
      SPRING_DATASOURCE_USERNAME: store
      SPRING_DATASOURCE_PASSWORD: secret
    depends_on: [db]
  db:
    image: postgres:15-alpine
    environment:
      POSTGRES_DB: store
      POSTGRES_USER: store
      POSTGRES_PASSWORD: secret
```
application.yml را طوری کن که با env override شود (`${SPRING_DATASOURCE_URL:jdbc:h2:mem:store...}`).
