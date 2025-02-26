package ru.alkv.springrecipes.recipes.rcp_2_10_2;

import ru.alkv.springrecipes.recipes.rcp_2_2.Product;

import java.util.Map;

public class ProductCreatorFactory {
    private Map<String, Product> products;

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public Product createProduct(String productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Unknown product Id");
        }

        return product;
    }
}
