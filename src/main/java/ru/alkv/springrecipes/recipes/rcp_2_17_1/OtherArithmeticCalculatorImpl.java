package ru.alkv.springrecipes.recipes.rcp_2_17_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ru.alkv.springrecipes.recipes.rcp_2_13_1.ArithmeticCalculator;

@Component("otherArithmeticCalculator")
public class OtherArithmeticCalculatorImpl implements OtherArithmeticCalculator {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @LoggingRequired
	public double add(double a, double b) {
        double result = a + b;

        logger.info("OtherArithmeticCalculatorImpl runs Add(): " + a + " + " + b + " = " + result);

        return result;
    }

    @LoggingRequired
    public double sub(double a, double b) {
        double result = a - b;

        logger.info("OtherArithmeticCalculatorImpl runs Sub(): " + a + " - " + b + " = " + result);

        return result;
    }

    @LoggingRequired
    public double mul(double a, double b) {
        double result = a * b;

        logger.info("OtherArithmeticCalculatorImpl runs Mul(): " + a + " * " + b + " = " + result);

        return result;
    }

    @LoggingRequired
    public double div(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        double result = a / b;

        logger.info("OtherArithmeticCalculatorImpl runs Div(): " + a + " / " + b + " = " + result);

        return result;
    }

    public double pow(double a, double b) {
        double result = Math.pow(a, b);

        logger.info("OtherArithmeticCalculatorImpl runs Pow(): " + a + " / " + b + " = " + result);

        return result;
    }
}
