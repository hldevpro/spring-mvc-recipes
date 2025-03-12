package ru.alkv.springrecipes.recipes.rcp_2_17_1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class OtherCalculatorLoggingAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(
		pointcut = "OtherCalculatorPointcuts.annotationLoggingOperation()",
		returning = "result"
	)
	public void logByAnnotationAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("@ Using annotation logging: The method {}() returns with {}",
				joinPoint.getSignature().getName(), result);
	}

	@AfterThrowing(
			pointcut = "OtherCalculatorPointcuts.arithmeticOperation()",
			throwing = "e"
	)
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		logger.info("!! An exception {} has been thrown in {}()",
				e, joinPoint.getSignature().getName());
	}


	@Before("OtherCalculatorPointcuts.unitOperation()")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("# The unit method {}() begins with {}",
				joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(
			pointcut = "OtherCalculatorPointcuts.ultimateLoggingOperation()",
			returning = "result"
	)
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("-> The method {}() completed", joinPoint.getSignature().getName());
	}

	@Before("OtherCalculatorPointcuts.parameterPointcut(target, a, b)")
	public void logBeforePow(Object target, double a, double b) {
		logger.info("** Target class : {}", target.getClass().getName());
		logger.info("** Arguments : {}, {}", a, b);
	}
}
