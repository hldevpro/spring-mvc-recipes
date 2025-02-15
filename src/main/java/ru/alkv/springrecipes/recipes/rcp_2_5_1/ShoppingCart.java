package ru.alkv.springrecipes.recipes.rcp_2_5_1;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.alkv.springrecipes.recipes.rcp_2_2.Product;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class ShoppingCart {
    private List<Product> items = new ArrayList<>();

    public void addItem(Product item) {
        items.add(item);
    }

    public List<Product> getItems() {
        return items;
    }
}
