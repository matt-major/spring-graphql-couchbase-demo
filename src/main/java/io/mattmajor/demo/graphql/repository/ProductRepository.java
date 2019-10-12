package io.mattmajor.demo.graphql.repository;

import graphql.schema.DataFetcher;
import io.mattmajor.demo.graphql.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> products = Arrays.asList(
            new Product(1001, "Test Product 1", 95),
            new Product(1002, "Test Product 2", 0),
            new Product(1003, "Test Product 3", 1),
            new Product(1004, "Test Product 4", 214),
            new Product(1005, "Test Product 5", 30),
            new Product(1006, "Test Product 6", 6)
    );

    public DataFetcher getAllProducts() {
        return dataFetchingEnvironment -> products;
    }

    public DataFetcher getProductById() {
        return dataFetchingEnvironment -> {
            final Integer id = Integer.valueOf(dataFetchingEnvironment.getArgument("id"));
            return products.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        };
    }
}
