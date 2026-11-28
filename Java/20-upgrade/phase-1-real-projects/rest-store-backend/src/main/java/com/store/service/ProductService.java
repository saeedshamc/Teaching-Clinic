package com.store.service;

import com.store.entity.*;
import com.store.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findByActiveTrue(pageable);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
            .filter(Product::isActive)
            .orElseThrow(() -> new IllegalArgumentException("محصول پیدا نشد"));
    }

    public List<Product> search(String name) {
        return productRepository.findByNameContainingIgnoreCaseAndActiveTrue(name);
    }

    public List<Product> byCategory(String category) {
        return productRepository.findByCategoryAndActiveTrue(category);
    }

    public Product create(Product p) { p.setId(null); return productRepository.save(p); }

    public Product update(Long id, Product data) {
        Product p = getById(id);
        p.setName(data.getName());
        p.setPrice(data.getPrice());
        p.setStock(data.getStock());
        p.setCategory(data.getCategory());
        p.setDescription(data.getDescription());
        return productRepository.save(p);
    }

    public void delete(Long id) {
        Product p = getById(id);
        p.setActive(false);   // حذف نرم
        productRepository.save(p);
    }
}
