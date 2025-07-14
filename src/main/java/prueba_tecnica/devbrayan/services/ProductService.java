package prueba_tecnica.devbrayan.services;

import prueba_tecnica.devbrayan.dto.CombinationDTO;
import prueba_tecnica.devbrayan.models.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Product save(Product product);
    List<Product> saveAll(List<Product> products);
    Optional<Product> update(Long id, Product product);
    boolean delete(Long id);
    double getInventoryTotal();
    List<CombinationDTO> getCombinations(double max);
}
