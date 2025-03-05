package ru.alkv.springrecipes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alkv.springrecipes.recipes.rcp_2_13_1.ArithmeticCalculator;
import ru.alkv.springrecipes.recipes.rcp_2_13_1.UnitCalculator;

import java.io.IOException;

@RestController
public class ApectsController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationContext context;

    @GetMapping("/aspects")
    public String simple() throws IOException {
        log.info("In the Aspects controller!");

        ArithmeticCalculator arithmeticCalculator =
                context.getBean("arithmeticCalculator", ArithmeticCalculator.class);

        arithmeticCalculator.add(1, 2);
        arithmeticCalculator.sub(4, 3);
        arithmeticCalculator.mul(2, 3);
        arithmeticCalculator.div(4, 2);

        UnitCalculator unitCalculator = context.getBean("unitCalculator", UnitCalculator.class);
        unitCalculator.kilogramToPound(10);
        unitCalculator.kilometerToMile(5);

        return "Aspects are ready!";
    }
}
