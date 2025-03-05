package ru.alkv.springrecipes.recipes.rcp_2_13_1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CalculatorLoggingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* *.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("The method {}() begins with {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

/*
    @Before("execution(* ArithmeticCalculator.add(..))")
    public void logBefore() {
        logger.info("The method add() begins");
    }
  */
}
