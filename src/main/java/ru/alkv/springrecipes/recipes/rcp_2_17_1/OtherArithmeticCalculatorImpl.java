package ru.alkv.springrecipes.recipes.rcp_2_17_1;

import org.springframework.stereotype.Component;

import ru.alkv.springrecipes.recipes.rcp_2_13_1.ArithmeticCalculator;

@Component("otherArithmeticCalculator")
public class OtherArithmeticCalculatorImpl implements ArithmeticCalculator {
    @LoggingRequired
	public double add(double a, double b) {
        double result = a + b;
        System.out.println("Add: " + a + " + " + b + " = " + result);
        return result;
    }

    @LoggingRequired
    public double sub(double a, double b) {
        double result = a - b;
        System.out.println("Sub: " + a + " - " + b + " = " + result);
        return result;
    }

    @LoggingRequired
    public double mul(double a, double b) {
        double result = a * b;
        System.out.println("Mul: " + a + " * " + b + " = " + result);
        return result;
    }

    @LoggingRequired
    public double div(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        double result = a / b;
        System.out.println("Div: " + a + " / " + b + " = " + result);
        return result;
    }
}
