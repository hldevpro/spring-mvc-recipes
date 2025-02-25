package ru.alkv.springrecipes.recipes.rcp_2_10_1;

import ru.alkv.springrecipes.recipes.rcp_2_2.Battery;
import ru.alkv.springrecipes.recipes.rcp_2_2.Disc;
import ru.alkv.springrecipes.recipes.rcp_2_2.Product;

public class ProductCreator {
    public static Product createProduct(String productId) {
        if ("aaa_nd".equals(productId)) {
            return new Battery("Battery AAA non discount", 2.5, 0);
        } else if ("cdrw_nd".equals(productId)) {
            return new Disc("Disk CD-RW non discount", 1.5, 0);
        } else if ("dvdrw_nd".equals(productId)) {
            return new Disc("Disk DVD-RW non discount", 3.0, 0);
        }
        throw new IllegalArgumentException("Unknown product");
    }
}
