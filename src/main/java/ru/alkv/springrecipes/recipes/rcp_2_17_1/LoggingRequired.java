package ru.alkv.springrecipes.recipes.rcp_2_17_1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(
	 {
		ElementType.METHOD,
		ElementType.TYPE
	 } 
)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggingRequired {

}
