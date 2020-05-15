package uk.co.dezzanet.greensparrow.greensparrow.graphql;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;
import uk.co.dezzanet.greensparrow.greensparrow.model.Product;

import java.util.Arrays;
import java.util.List;

@Component
public class DataFetchers {
    private static final List<Product> products = Arrays.asList(
            new Product("1", "product1", 114),
            new Product("2", "product2", 70)
    );

    public DataFetcher<List<Product>> getAllProducts() {
        return dataFetchingEnvironment -> products;
    }
}
