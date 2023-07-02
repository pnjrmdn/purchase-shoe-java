package com.apifull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import com.apifull.Repository.ProductRepository;
import com.apifull.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Mendapatkan daftar semua produk
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Mendapatkan produk berdasarkan ID
    // @GetMapping("/{id}")
    // public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    //     Optional<Product> product = productRepository.findById(id);
    //     return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Data tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    // Membuat produk baru
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
       
        if (product.getName() == null || product.getName().isEmpty() || product.getDescription() == null || product.getDescription().isEmpty() ||
        product.getPrice() == 0 || product.getQuantity() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please check form name, description, quantity and price");
        }

        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    // Mengupdate produk
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            if (updatedProduct.getName() == null || updatedProduct.getName().isEmpty() ||
            updatedProduct.getDescription() == null || updatedProduct.getDescription().isEmpty() ||
            updatedProduct.getPrice() == 0 || updatedProduct.getQuantity() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please check form name, description, quantity and price");
            }

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            productRepository.save(existingProduct);

            return ResponseEntity.ok("Product updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Menghapus produk
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Product Not Exist");

        }
    }
}

