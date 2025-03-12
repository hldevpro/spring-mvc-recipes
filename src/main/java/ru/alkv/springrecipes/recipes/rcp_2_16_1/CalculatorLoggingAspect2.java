package ru.alkv.springrecipes.recipes.rcp_2_16_1;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class CalculatorLoggingAspect2 {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* NewArithmeticCalculator.*u*(..))")
	private void loggingOperation() {
	}

	@Before("CalculatorLoggingAspect2.loggingOperation()")
	public void logBefore(JoinPoint joinPoint) {
        logger.info("The method " + joinPoint.getSignature().getName()
                + "() begins with " + Arrays.toString(joinPoint.getArgs()));

		System.out.printf("The new calc method %s() begins with %s\n",
						joinPoint.getSignature().getName(),
						Arrays.toString(joinPoint.getArgs()));
	}

	@After("CalculatorLoggingAspect2.loggingOperation()")
	public void logAfter(JoinPoint joinPoint) {
        logger.info("The method " + joinPoint.getSignature().getName()
                + "() ends.");

		System.out.printf("The new calc method %s() ends\n",
						joinPoint.getSignature().getName());
	}

	@AfterReturning(
		pointcut = "CalculatorLoggingAspect2.loggingOperation()",
		returning = "result"
	)
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("The method " + joinPoint.getSignature().getName() + "() returns with " + result);

		System.out.printf("The method %s() returns with %s\n", 
						joinPoint.getSignature().getName(),
						result);
	}

	@AfterThrowing(
		pointcut = "CalculatorLoggingAspect2.loggingOperation()",
		throwing = "e"
	)
	public void logAfterThrowing(JoinPoint joinPoint, IllegalArgumentException e) {
		logger.info("Illegal argument among " + Arrays.toString(joinPoint.getArgs()) + 
					" in " + joinPoint.getSignature().getName() + "()");

		System.out.printf("Illegal argument among %s in %s()\n", 
							Arrays.toString(joinPoint.getArgs()),
							joinPoint.getSignature().getName());
	}

	@Pointcut("execution(* NewArithmeticCalculator.div(..))")
	private void loggingDivOperation() {
	}

	@Around("CalculatorLoggingAspect2.loggingDivOperation()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("The new method " + joinPoint.getSignature().getName()
                + "() begins with " + Arrays.toString(joinPoint.getArgs()));

		System.out.printf("The new calc method %s() begins with %s\n",
						joinPoint.getSignature().getName(),
						Arrays.toString(joinPoint.getArgs()));
						
		try {
			Object result = joinPoint.proceed();

			logger.info("The method " + joinPoint.getSignature().getName() + 
						"() after around ends with " + result);

			System.out.printf("The method %s() after around ends with %s\n",
								joinPoint.getSignature().getName(), result);

			return result;
		}
		catch(IllegalArgumentException e) {
			logger.error("Caught illegal argument exception for " + Arrays.toString(joinPoint.getArgs()) + 
						" in " + joinPoint.getSignature().getName() + "()");

			System.out.printf("Caught illegal argument exception for %s in %s\n",
								Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());

			throw e;
		}
	}
}
