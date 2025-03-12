package ru.alkv.springrecipes.recipes.rcp_2_18;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction {
    @DeclareParents(
            value = "ru.alkv.springrecipes.recipes.rcp_2_17_1.OtherArithmeticCalculatorImpl",
            defaultImpl = MaxCalculatorImpl.class
    )
    public MaxCalculator maxCalculator;

    @DeclareParents(
            value = "ru.alkv.springrecipes.recipes.rcp_2_17_1.OtherArithmeticCalculatorImpl",
            defaultImpl = MinCalculatorImpl.class
    )
    public MinCalculator minCalculator;
}
