package io.mattmajor.demo.graphql.datafetchers;

import graphql.schema.DataFetcher;
import io.mattmajor.demo.graphql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDataFetcher {
    private final ProductRepository productRepository;

    @Autowired
    public ProductDataFetcher(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public DataFetcher getById() {
        return dataFetchingEnvironment -> {
            return productRepository.getProductById(dataFetchingEnvironment.getArgument("id"));
        };
    }
}
