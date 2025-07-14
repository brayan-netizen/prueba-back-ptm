package prueba_tecnica.devbrayan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba_tecnica.devbrayan.dto.CombinationDTO;
import prueba_tecnica.devbrayan.models.Product;
import prueba_tecnica.devbrayan.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> responseDB = productRepository.findById(id);

        return responseDB.map(p -> {
            if (product.getName() != null) {
                p.setName(product.getName());
            }
            if (product.getDescription() != null) {
                p.setDescription(product.getDescription());
            }
            if (product.getPrice() != null) {
                p.setPrice(product.getPrice());
            }
            if (product.getStock() != null) {
                p.setStock(product.getStock());
            }
            return productRepository.save(p);
        });
    }

    @Override
    public boolean delete(Long id) {
        return productRepository.findById(id).map(p -> {
            productRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public double getInventoryTotal() {
        return productRepository.findAll().stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }

    @Override
    public List<CombinationDTO> getCombinations(double max) {
        List<Product> products = productRepository.findAll();
        List<CombinationDTO> result = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            for (int j = i + 1; j < products.size(); j++) {
                Product p1 = products.get(i);
                Product p2 = products.get(j);
                double sum2 = p1.getPrice() + p2.getPrice();
                if (sum2 <= max) {
                    result.add(new CombinationDTO(
                            List.of(p1.getName(), p2.getName()), sum2));
                }

                for (int k = j + 1; k < products.size(); k++) {
                    Product p3 = products.get(k);
                    double sum3 = p1.getPrice() + p2.getPrice() + p3.getPrice();
                    if (sum3 <= max) {
                        result.add(new CombinationDTO(
                                List.of(p1.getName(), p2.getName(), p3.getName()), sum3));
                    }
                }
            }
        }

        return result.stream()
                .sorted((a, b) -> Double.compare(b.getTotal(), a.getTotal()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
