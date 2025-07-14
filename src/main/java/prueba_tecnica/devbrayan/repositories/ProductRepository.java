package prueba_tecnica.devbrayan.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba_tecnica.devbrayan.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
