package ru.alkv.springrecipes.recipes.rcp_2_3_1;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

public class SequenceGenerator2 {

    @Autowired
	private PrefixGenerator[] prefixGenerators;
	private String suffix;
	private int initial;
	private final AtomicInteger counter  = new AtomicInteger();

	public SequenceGenerator2() {
	}

	public SequenceGenerator2(PrefixGenerator[] prefixGenerator, String suffix, int initial) {
		this.prefixGenerators = prefixGenerator;
        this.suffix = suffix;
        this.initial = initial;
	}

	public void setPrefixGenerators(PrefixGenerator[] prefixGenerator) {
        this.prefixGenerators = prefixGenerator;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public String getSequence() {
        StringBuilder builder = new StringBuilder();

        for(PrefixGenerator prefix: prefixGenerators) {
            builder.append(prefix.getPrefix());
            builder.append("-");
        }
        
        builder.append(initial).append(counter.getAndIncrement()).append(suffix);
				
        return builder.toString();
    }
}
