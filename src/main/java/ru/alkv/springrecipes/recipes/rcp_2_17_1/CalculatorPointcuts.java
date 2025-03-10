package ru.alkv.springrecipes.recipes.rcp_2_17_1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CalculatorPointcuts {
	@Pointcut("@annotation(ru.alkv.springrecipes.recipes.rcp_2_17_1.LoggingRequired)")
	public void annotationLoggingOperation() {

	}
}
