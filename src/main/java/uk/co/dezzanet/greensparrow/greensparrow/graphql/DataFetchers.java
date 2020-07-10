package uk.co.dezzanet.greensparrow.greensparrow.graphql;

import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.co.dezzanet.greensparrow.greensparrow.model.Product;
import uk.co.dezzanet.greensparrow.greensparrow.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataFetchers {
    private final ProductRepository productRepo;

    public DataFetcher<List<Product>> getAllProducts() {
        return dataFetchingEnvironment -> {
            List<Product> products = new ArrayList<>();
            productRepo.findAll().forEach(products::add);
            return products;
        };
    }
}
