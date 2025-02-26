package ru.alkv.springrecipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.alkv.springrecipes.recipes.rcp_2_2.Battery;
import ru.alkv.springrecipes.recipes.rcp_2_2.Product;

@Configuration
@Profile("prod")
public class ProdConfiguration {
    @Bean
    public Product super_battery() {
        Battery superBattery = new Battery();
        superBattery.setName("super");
        superBattery.setPrice(100_000);
        superBattery.setRechargeable(true);
        superBattery.setDiscount(0);

        return superBattery;
    }
}
