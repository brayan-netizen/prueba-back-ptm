package prueba_tecnica.devbrayan.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import prueba_tecnica.devbrayan.dto.CombinationDTO;
import prueba_tecnica.devbrayan.models.Product;
import prueba_tecnica.devbrayan.services.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //get all products
    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }

    //create product
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product created = productService.save(product);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Product>> createBatch(@Valid @RequestBody List<Product> products) {
        List<Product> savedProducts = productService.saveAll(products);
        return ResponseEntity.ok(savedProducts);
    }


    //update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> updated = productService.update(id, product);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = productService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // get all value total inventory (price * stock)
    @GetMapping("/inventory")
    public ResponseEntity<Double> getInventoryTotal() {
        double total = productService.getInventoryTotal();
        return ResponseEntity.ok(total);
    }

    // get combination products what sum price <= value max
    @GetMapping("/combinations")
    public ResponseEntity<List<CombinationDTO>> getCombinations(@RequestParam double max) {
        List<CombinationDTO> combinations = productService.getCombinations(max);
        return ResponseEntity.ok(combinations);
    }
}
