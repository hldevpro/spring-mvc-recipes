package ru.alkv.springrecipes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alkv.springrecipes.recipes.rcp_2_13_1.ArithmeticCalculator;
import ru.alkv.springrecipes.recipes.rcp_2_13_1.UnitCalculator;
import ru.alkv.springrecipes.recipes.rcp_2_16_1.NewArithmeticCalculator;

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

        try {
            arithmeticCalculator.div(4, 0);
        }
        catch(Throwable e) {
            log.info("In the Aspects controller caught exception: " + e.getMessage());
        }

        UnitCalculator unitCalculator = context.getBean("unitCalculator", UnitCalculator.class);
        unitCalculator.kilogramToPound(10);
        unitCalculator.kilometerToMile(5);

        NewArithmeticCalculator newArithmeticCalculator = 
                context.getBean("newArithmeticCalculator", NewArithmeticCalculator.class);

        newArithmeticCalculator.add(1, 2);
        newArithmeticCalculator.sub(4, 3);
        newArithmeticCalculator.mul(2, 3);
        newArithmeticCalculator.div(4, 2);

        try {
            newArithmeticCalculator.div(4, 0);
        }
        catch(Throwable e) {
            log.info("In the Aspects controller caught exception: " + e.getMessage());
        }

        log.info("Trying otherArithmeticCalculator:");
        System.out.println("");

        ArithmeticCalculator otherArithmeticCalculator =
                (ArithmeticCalculator) context.getBean("otherArithmeticCalculator");

        otherArithmeticCalculator.add(1, 2);
        otherArithmeticCalculator.sub(4, 3);
        otherArithmeticCalculator.mul(2, 3);
        otherArithmeticCalculator.div(4, 2);

        return "Aspects are ready!";
    }
}
