package ru.alkv.springrecipes.recipes.rcp_2_18;

public class MaxCalculatorImpl implements MaxCalculator {
    public double max(double x, double y) {
        double result = (x >= y) ? x : y;

        System.out.println("max(" + x + ", " + y + ") = " + result);

        return result;
    }
}
