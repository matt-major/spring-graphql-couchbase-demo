package io.mattmajor.demo.graphql.domain;

public class Product {
    private Integer id;
    private String title;
    private Integer stock;

    public Product() {
    }

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
