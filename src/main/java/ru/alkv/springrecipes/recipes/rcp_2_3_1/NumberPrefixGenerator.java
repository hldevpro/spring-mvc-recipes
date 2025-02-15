package ru.alkv.springrecipes.recipes.rcp_2_3_1;

import java.util.Random;

public class NumberPrefixGenerator implements PrefixGenerator {
	public String getPrefix() {

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);
		
        return String.format("%03d", randomInt);
    }
}
