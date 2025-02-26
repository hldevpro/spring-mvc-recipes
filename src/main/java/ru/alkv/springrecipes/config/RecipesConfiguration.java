package ru.alkv.springrecipes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import ru.alkv.springrecipes.recipes.rcp_2_1.SequenceGenerator;
import ru.alkv.springrecipes.recipes.rcp_2_10_1.ProductCreator;
import ru.alkv.springrecipes.recipes.rcp_2_10_2.ProductCreatorFactory;
import ru.alkv.springrecipes.recipes.rcp_2_10_3.DiscountFactoryBean;
import ru.alkv.springrecipes.recipes.rcp_2_11.ProductDumper;
import ru.alkv.springrecipes.recipes.rcp_2_12.CashierWithName;
import ru.alkv.springrecipes.recipes.rcp_2_2.Battery;
import ru.alkv.springrecipes.recipes.rcp_2_2.Disc;
import ru.alkv.springrecipes.recipes.rcp_2_2.Product;
import ru.alkv.springrecipes.recipes.rcp_2_3_1.DatePrefixGenerator;
import ru.alkv.springrecipes.recipes.rcp_2_3_1.NumberPrefixGenerator;
import ru.alkv.springrecipes.recipes.rcp_2_3_1.SequenceGenerator2;
import ru.alkv.springrecipes.recipes.rcp_2_6.BannerLoader;
import ru.alkv.springrecipes.recipes.rcp_2_8_1.Cashier;
import ru.alkv.springrecipes.recipes.rcp_2_9_2.ProductCheckBeanPostProcessor;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(
	includeFilters = {
		@ComponentScan.Filter(
			type = FilterType.REGEX,
			pattern = { 
				"ru.alkv.springrecipes.recipes.rcp_2_1_1.*Dao",
				"ru.alkv.springrecipes.recipes.rcp_2_1_1.*Service",
				"ru.alkv.springrecipes.recipes.rcp_2_2.*",
				"ru.alkv.springrecipes.recipes.rcp_2_8.*",
				"ru.alkv.springrecipes.recipes.rcp_2_9.*"
			}
		)
	}
)
@PropertySource("classpath:shoppingcart.properties")
public class RecipesConfiguration {

	@Bean
	public static ProductCheckBeanPostProcessor productCheckBeanPostProcessor() {
		return new ProductCheckBeanPostProcessor();
	}

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
		//Battery p1 = new Battery("AAA", 2.5);
		Battery p1 = new Battery();
		p1.setName("AAA");
		p1.setPrice(2.5);
		p1.setRechargeable(true);
		p1.setDiscount(specialCustomerDiscountField);

		return p1;
	}

	@Bean
	public Product cdrw() {
		Disc p2 = new Disc("CD-RW", 1.5, summerDiscountField);
		p2.setCapacity(700);

		return p2;
	}

	@Bean
	public Product dvdrw() {
		Disc p2 = new Disc("DVD-RW", 3.0, endofyearDiscountField);
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

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${specialcustomer.discount:0}")
	private double specialCustomerDiscountField;

	@Value("${summer.discount:0}")
	private double summerDiscountField;

	@Value("${endofyear.discount:0}")
	private double endofyearDiscountField;

	@Value("classpath:banner.txt")
	private Resource banner;

	@Bean
	public BannerLoader bannerLoader() {
		BannerLoader b1 = new BannerLoader();
		b1.setBanner(banner);

		return b1;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource src = new ReloadableResourceBundleMessageSource();

		src.setBasenames("classpath:messages");
		src.setCacheSeconds(1);

		return src;
	}

	@Bean(initMethod = "openFile", destroyMethod = "closeFile")
	public Cashier cashier() {
		String path = System.getProperty("java.io.tmpdir") + "/cahier";

		Cashier cashier = new Cashier();
		cashier.setFileName("checkout");
		cashier.setPath(path);

		return cashier;
	}

	@Bean
	public Product aaa_nd() {
		return ProductCreator.createProduct("aaa_nd");
	}

	@Bean
	public Product cdrw_nd() {
		return ProductCreator.createProduct("cdrw_nd");
	}

	@Bean
	public Product dvdrw_nd() {
		return ProductCreator.createProduct("dvdrw_nd");
	}

	@Bean
	public ProductCreatorFactory productCreatorFactory() {
		ProductCreatorFactory factory = new ProductCreatorFactory();

		Map<String, Product> products = new HashMap<>();

		products.put("aa", new Battery("AA", 3.0, 0));
		products.put("blueray", new Disc("BD-RW", 5.0, 0));

		factory.setProducts(products);

		return factory;
	}


	@Bean
	public Product aa() {
		return productCreatorFactory().createProduct("aa");
	}

	@Bean
	public Product bdrw() {
		return productCreatorFactory().createProduct("blueray");
	}

	@Bean
	public Product cd() {
		Disc cd = new Disc("CD", 1.5, 0);
		cd.setCapacity(700);

		return cd;
	}

	@Bean
	public Product dvd() {
		Disc dvd = new Disc("DVD", 2, 0);
		dvd.setCapacity(1400);

		return dvd;
	}

	@Bean
	public DiscountFactoryBean discountFactoryBeanCD() {
		DiscountFactoryBean factory = new DiscountFactoryBean();
		factory.setProduct(cd());
		factory.setDiscount(0.2);

		return factory;
	}

	@Bean
	public DiscountFactoryBean discountFactoryBeanDVD() {
		DiscountFactoryBean factory = new DiscountFactoryBean();
		factory.setProduct(dvd());
		factory.setDiscount(0.3);
		return factory;
	}

	@Bean(initMethod = "openFile", destroyMethod = "closeFile")
	@Profile("local")
	public ProductDumper dumper() {
		final String path = System.getProperty("java.io.tmpdir") + "dumper";

		ProductDumper dumper = new ProductDumper();
		dumper.setFileName("dump");
		dumper.setPath(path);

		return dumper;
	}

	@Bean(initMethod = "openFile", destroyMethod = "closeFile")
	public CashierWithName cashierWithName() {
		final String path = System.getProperty("java.io.tmpdir") + "cashierWithName";

		CashierWithName c1 = new CashierWithName();

		c1.setPath(path);

		return c1;
	}
}
