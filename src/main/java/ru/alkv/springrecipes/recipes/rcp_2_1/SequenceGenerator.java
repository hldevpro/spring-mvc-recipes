package ru.alkv.springrecipes.recipes.rcp_2_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.alkv.springrecipes.recipes.rcp_2_3_1.PrefixGenerator;

//import javax.annotation.Resource;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceGenerator {
	private String prefix;
    private String suffix;
    private int initial;

    private final AtomicInteger counter = new AtomicInteger();

    private PrefixGenerator prefixGenerator;

	public SequenceGenerator() {
    }

    @Autowired(required = false)
    @Qualifier("numberPrefixGenerator")
    //@Resource
    public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public String getSequence() {
        String builder = prefixGenerator.getPrefix()  +
                initial +
                counter.getAndIncrement() +
                suffix;
				
        return builder;
    }

}
