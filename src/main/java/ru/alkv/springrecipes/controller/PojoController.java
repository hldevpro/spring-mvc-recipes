package ru.alkv.springrecipes.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ru.alkv.springrecipes.recipes.rcp_2_1.SequenceGenerator;
import ru.alkv.springrecipes.recipes.rcp_2_1_1.SequenceDao;
import ru.alkv.springrecipes.recipes.rcp_2_2.Product;
import ru.alkv.springrecipes.recipes.rcp_2_3_1.SequenceGenerator2;
import ru.alkv.springrecipes.recipes.rcp_2_5_1.ShoppingCart;
import ru.alkv.springrecipes.recipes.rcp_2_8_1.Cashier;
import ru.alkv.springrecipes.recipes.rcp_2_8_2.Cashier2;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

@RestController
public class PojoController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ApplicationContext context;
	
	@GetMapping("/pojo")
    public String simple() throws IOException {
		log.info("In the POJO!");

		SequenceGenerator seqGen = (SequenceGenerator) context.getBean("sequenceGenerator");
		seqGen = context.getBean("sequenceGenerator", SequenceGenerator.class);

		SequenceGenerator2 generator = context.getBean(SequenceGenerator2.class);

		SequenceDao sequenceDao = context.getBean(SequenceDao.class);

		Product aaa = context.getBean("aaa", Product.class);
		Product cdrw = context.getBean("cdrw", Product.class);
		Product dvdrw = context.getBean("dvdrw", Product.class);

		ShoppingCart cart1 = context.getBean("shoppingCart", ShoppingCart.class);
		cart1.addItem(aaa);
		cart1.addItem(cdrw);

		ShoppingCart cart2 = context.getBean("shoppingCart", ShoppingCart.class);
		cart2.addItem(dvdrw);

		Resource resource = new ClassPathResource("shoppingcart.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);

		String alert = context.getMessage("alert.checkout", null, Locale.US);
		String alert_inventory = context.getMessage(
				"alert.inventory.checkout",
				new Object[]{"[DVD-RW 3.0]", new Date()},
				Locale.US);

		Cashier cashier = context.getBean("cashier", Cashier.class);
		cashier.checkout(cart1);

		Cashier2 cashier2 = context.getBean("cashier2", Cashier2.class);
		cashier2.checkout(cart2);

		Product aaa_nd = context.getBean("aaa_nd", Product.class);
		Product cdrw_nd = context.getBean("cdrw_nd", Product.class);
		Product dvdrw_nd = context.getBean("dvdrw_nd", Product.class);

		ShoppingCart cart_nd = context.getBean("shoppingCart", ShoppingCart.class);
		cart_nd.addItem(aaa_nd);
		cart_nd.addItem(cdrw_nd);
		cart_nd.addItem(dvdrw_nd);

		Product aa = context.getBean("aa", Product.class);
		Product bdrw = context.getBean("bdrw", Product.class);

		ShoppingCart cartNew = context.getBean("shoppingCart", ShoppingCart.class);
		cartNew.addItem(aa);
		cartNew.addItem(bdrw);

		Product cd = context.getBean("cd", Product.class);
		Product dvd = context.getBean("dvd", Product.class);

		ShoppingCart cartDiscs = context.getBean("shoppingCart", ShoppingCart.class);
		cartDiscs.addItem(cd);
		cartDiscs.addItem(dvd);

        return "<p>Squence: " +
				seqGen.getSequence() + "</p>" +
				"<p>SequenceGenerator2: " +
				generator.getSequence() + "</p>" +
				"<p>SequenceDao: " +
				sequenceDao.getNextValue("IT") + "</p>" +
				"<p>Product battery: " + aaa + "</p>" +
				"<p>Product disc: " + cdrw + "</p>" +
				"<p>Shopping cart 1 contains: " + cart1.getItems() + "</p>" +
				"<p>Shopping cart 2 contains: " + cart2.getItems() + "</p>" +
				"<p>And don't forget our discounts!" + "</hr>" +
				props + "</p>" +
				"<p>The I18N message for alert.checkout is: " + alert + "</p>" +
				"<p>The I18N message for alert.inventory.checkout is: " + alert_inventory + "</p>" +
				"<p>Shopping cart non discount contains: " + cart_nd.getItems() + "</p>" +
				"<p>Shopping cart from factory contains: " + cartNew.getItems() + "</p>" +
				"<p>Shopping cart from discount factory contains: " + cartDiscs.getItems() + "</p>";
    }
}
