package ru.alkv.springrecipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import ru.alkv.springrecipes.recipes.rcp_2_1.SequenceGenerator;
import ru.alkv.springrecipes.recipes.rcp_2_2.Battery;
import ru.alkv.springrecipes.recipes.rcp_2_2.Disc;
import ru.alkv.springrecipes.recipes.rcp_2_2.Product;
import ru.alkv.springrecipes.recipes.rcp_2_3_1.DatePrefixGenerator;
import ru.alkv.springrecipes.recipes.rcp_2_3_1.NumberPrefixGenerator;
import ru.alkv.springrecipes.recipes.rcp_2_3_1.SequenceGenerator2;

@Configuration
@ComponentScan(
	includeFilters = {
		@ComponentScan.Filter(
			type = FilterType.REGEX,
			pattern = { 
				"ru.alkv.springrecipes.recipes.rcp_2_1_1.*Dao",
				"ru.alkv.springrecipes.recipes.rcp_2_1_1.*Service",
				"ru.alkv.springrecipes.recipes.rcp_2_2.*"
			}
		)
	}
)
public class RecipesConfiguration {

	@Bean
	public SequenceGenerator sequenceGenerator() {
		SequenceGenerator seqgen = new SequenceGenerator();
		seqgen.setPrefix("30");
		seqgen.setSuffix("A");
		seqgen.setInitial(100000);

		return seqgen;
	}

	@Bean
	public Product aaa() {
		Battery p1 = new Battery("AAA", 2.5);
		p1.setRechargeable(true);

		return p1;
	}

	@Bean
	public Product cdrw() {
		Disc p2 = new Disc("CD-RW", 1.5);
		p2.setCapacity(700);

		return p2;
	}

	@Bean
	public Product dvdrw() {
		Disc p2 = new Disc("DVD-RW", 3.0);
		p2.setCapacity(700);
		return p2;
	}

	@Bean
	public DatePrefixGenerator datePrefixGenerator() {
		DatePrefixGenerator dpg = new DatePrefixGenerator();
		dpg.setPattern("ddMMyyyy");

		return dpg;
	}

	@Bean
    public NumberPrefixGenerator numberPrefixGenerator() {
        return new NumberPrefixGenerator();
    }

	@Bean
	public SequenceGenerator2 sequenceGenerator2() {
		SequenceGenerator2 sequence = new SequenceGenerator2();

		sequence.setInitial(100000);
		sequence.setSuffix("A");

		return sequence;
	}
}
