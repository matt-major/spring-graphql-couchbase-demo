package io.mattmajor.demo.graphql.domain;

public class Product {
    private final Integer id;
    private final String title;
    private final Integer stock;

    public Product(final Integer id,
                   final String title,
                   final Integer stock) {
        this.id = id;
        this.title = title;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStock() {
        return stock;
    }
}
