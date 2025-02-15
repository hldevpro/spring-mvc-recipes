package ru.alkv.springrecipes.recipes.rcp_2_3_1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePrefixGenerator implements PrefixGenerator {

	private DateFormat formater;

	public void setPattern(String pattern) {
		this.formater = new SimpleDateFormat(pattern);
	}

	@Override
	public String getPrefix() {
		return formater.format(new Date());
	}
	
}
