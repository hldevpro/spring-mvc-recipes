package ru.alkv.springrecipes.recipes.rcp_2_19;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction2 {

    @DeclareParents(
            value = "ru.alkv.springrecipes.recipes.*.*CalculatorImpl",
            defaultImpl = CounterImpl.class
    )
    public Counter counter;

    @After("execution(* ru.alkv.springrecipes.recipes.*.*Calculator.*(..)) && this(counter)")
    public void increaseCount(Counter counter) {
        counter.increase();
    }
}
