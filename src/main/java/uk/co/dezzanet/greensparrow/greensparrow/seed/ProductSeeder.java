package uk.co.dezzanet.greensparrow.greensparrow.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.dezzanet.greensparrow.greensparrow.model.Product;
import uk.co.dezzanet.greensparrow.greensparrow.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductSeeder {
    private final ProductRepository repo;

    public void seed() {
        repo.save(new Product(1L, "product1", 114));
        repo.save(new Product(2L, "product2", 70));
        repo.save(new Product(3L, "product3", 666));
    }
}
