package ru.alkv.springrecipes.recipes.rcp_2_17_1;

import org.springframework.stereotype.Component;
import ru.alkv.springrecipes.recipes.rcp_2_13_1.UnitCalculator;

@Component("otherUnitCalculator")
public class OtherUnitCalculatorImpl implements OtherUnitCalculator {
    public double kilogramToPound(double killogram) {
        double pound = killogram * 2.2;

        System.out.println(killogram + " killogram = " + pound + " pound");

        return pound;
    }

    public double kilometerToMile(double kilometer) {
        double mile = kilometer * 0.62;

        System.out.println(kilometer + " kilometer = " + mile + " mile");

        return mile;
    }
}
