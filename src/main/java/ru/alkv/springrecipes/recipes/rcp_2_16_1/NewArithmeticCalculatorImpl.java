package ru.alkv.springrecipes.recipes.rcp_2_16_1;

import org.springframework.stereotype.Component;

@Component("newArithmeticCalculator")
public class NewArithmeticCalculatorImpl implements NewArithmeticCalculator {
	public double add(double x, double y)
    {
        double result = x + y;
        System.out.println(x + " + " + y + " = " + result);

        return result;
    }

    public double sub(double x, double y)
    {
        double result = x - y;
        System.out.println(x + " - " + y + " = " + result);

        return result;
    }

    public double mul(double x, double y)
    {
        double result = x * y;
        System.out.println(x + " * " + y + " = " + result);

        return result;
    }

    public double div(double x, double y)
    {
        if (y == 0) {
            throw  new IllegalArgumentException("Division by zero!");
        }

        double result = x / y;
        System.out.println(x + " / " + y + " = " + result);

        return result;
    }
}
