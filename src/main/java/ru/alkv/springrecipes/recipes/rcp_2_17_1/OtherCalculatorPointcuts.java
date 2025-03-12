package ru.alkv.springrecipes.recipes.rcp_2_17_1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class OtherCalculatorPointcuts {
    @Pointcut("@annotation(ru.alkv.springrecipes.recipes.rcp_2_17_1.LoggingRequired)")
    public void annotationLoggingOperation() {

    }

    // Matches all classes that implement the OtherArithmeticCalculator interface
    @Pointcut("within(OtherArithmeticCalculator+)")
    public void arithmeticOperation() {
    }

    @Pointcut("within(OtherUnitCalculator+)")
    public void unitOperation() {
    }

    @Pointcut("arithmeticOperation() || unitOperation()")
    public void ultimateLoggingOperation() {
    }

    @Pointcut("execution(* *.pow(..)) && target(target) && args(a,b)")
    public void parameterPointcut(Object target, double a, double b) {
    }
}