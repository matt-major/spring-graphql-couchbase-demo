package io.mattmajor.demo.graphql.repository;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.Document;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mattmajor.demo.graphql.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Bucket bucket;

    @Autowired
    public ProductRepository(final Bucket couchbaseBucket) {
        this.bucket = couchbaseBucket;
    }

    public Product getProductById(final String productId) throws Exception {
        final Document document = bucket.get(productId);
        return convertDocumentContents(document);
    }

    private Product convertDocumentContents(final Document document) throws Exception {
        return OBJECT_MAPPER.readValue(document.content().toString(), Product.class);
    }
}
