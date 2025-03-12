package ru.alkv.springrecipes.recipes.rcp_2_18;

public class MinCalculatorImpl implements MinCalculator {
    public double min(double x, double y) {
        double result = (x <= y) ? x : y;

        System.out.println("min(" + x + ", " + y + ") = " + result);

        return result;
    }
}
