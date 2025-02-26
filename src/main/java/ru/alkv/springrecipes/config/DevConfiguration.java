package ru.alkv.springrecipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.alkv.springrecipes.recipes.rcp_2_2.Battery;
import ru.alkv.springrecipes.recipes.rcp_2_2.Product;

@Configuration
@Profile({"dev", "local"})
public class DevConfiguration {
    @Bean
    public Product super_battery() {
        Battery superBattery = new Battery();
        superBattery.setName("SUPER");
        superBattery.setPrice(1000);
        superBattery.setRechargeable(true);
        superBattery.setDiscount(0.5);

        return superBattery;
    }
}
