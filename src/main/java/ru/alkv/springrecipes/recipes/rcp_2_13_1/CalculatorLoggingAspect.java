package ru.alkv.springrecipes.recipes.rcp_2_13_1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CalculatorLoggingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

/*
    @Before("execution(* *.*(..))")
    @Order(1)
    public void logBefore(JoinPoint joinPoint) {
        logger.info("The method {}() begins with {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }


    @Before("execution(* ArithmeticCalculator.add(..))")
    public void logBefore() {
        logger.info("The method add() begins");
    }
*/

    @After("execution(* *.kilo*(..))")
    @Order(2)
    public void logAfter(JoinPoint joinPoint) {
        logger.info("The method {}() completed", joinPoint.getSignature().getName());
        System.out.println("The method " + joinPoint.getSignature().getName() + "() completed.");
    }

    @AfterReturning(
        pointcut = "execution(* ArithmeticCalculator.*(..))",
        returning = "result"
    )
    @Order(3)
    public void logAfterReturniung(JoinPoint joinPoint, Object result) {
        logger.info("The method {}() completed with result = {}", 
                    joinPoint.getSignature().getName(),
                    result);

        System.out.println("The method " + joinPoint.getSignature().getName() + 
                            "() completed with result = " + result);
    }

    @AfterThrowing(
        pointcut = "execution(* *.*(..))",
        throwing = "e"
    )
    @Order(4)   
    public void logAfterThrowingIllegalArgs(JoinPoint joinPoint, IllegalArgumentException e) {
        logger.info("Illegal argument {} in {}()", 
                    Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());

        System.out.println("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in " + 
                            joinPoint.getSignature().getName() + "()");
    }
/*
    @AfterThrowing(
        pointcut = "execution(* *.*(..))",
        throwing = "e"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.info("An exception {} has been thrown in {}()", 
                    e, joinPoint.getSignature().getName());

        System.out.println("An exception " + e + " has been thrown in " + 
                            joinPoint.getSignature().getName() + "()");
    }
*/
    @Around("execution(* ArithmeticCalculator.div(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("The method {}() begins with {}", 
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        
        try {
            System.out.printf("The proceeding of method {}() begins with {}", 
                                joinPoint.getSignature().getName(),
                                Arrays.toString(joinPoint.getArgs()));
            
            Object result = joinPoint.proceed();

            logger.info("The method {}() ends with {}", 
                        joinPoint.getSignature().getName(),
                        result);

            System.out.printf("The method {}() ends with {}", 
                        joinPoint.getSignature().getName(),
                        result); 
                        
            return result;
        }
        catch(IllegalArgumentException e) {
            logger.error("IllegalArgumentException for arg {} in {}()",  
                        Arrays.toString(joinPoint.getArgs()),
                        joinPoint.getSignature().getName());

            System.out.printf("IllegalArgumentException for arg {} in {}()",  
                        Arrays.toString(joinPoint.getArgs()),
                        joinPoint.getSignature().getName());

            throw e;
        }
    }
}
