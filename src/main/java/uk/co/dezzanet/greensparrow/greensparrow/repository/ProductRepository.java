package uk.co.dezzanet.greensparrow.greensparrow.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.dezzanet.greensparrow.greensparrow.model.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);
}
