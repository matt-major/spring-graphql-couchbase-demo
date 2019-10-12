package io.mattmajor.demo.graphql.service;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import io.mattmajor.demo.graphql.datafetchers.ProductDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {
    private final ProductDataFetcher productDataFetcher;
    private final Resource resource;

    private GraphQL graphQL;

    @Autowired
    public GraphQLService(@Value("classpath:schema.graphqls") final Resource resource,
                          final ProductDataFetcher productDataFetcher) {
        this.productDataFetcher = productDataFetcher;
        this.resource = resource;
    }

    @PostConstruct
    private void initialiseGraphQL() throws IOException {
        final File schemaFile = resource.getFile();
        final TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        final RuntimeWiring runtimeWiring = buildRuntimeWiring();
        final GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query")
                        .dataFetcher("product", productDataFetcher.getById()))
                .build();
    }

    public ExecutionResult executeQuery(final String query) {
        return graphQL.execute(query);
    }
}
