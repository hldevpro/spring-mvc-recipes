package ru.alkv.springrecipes.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ru.alkv.springrecipes.recipes.rcp_2_1.SequenceGenerator;
import ru.alkv.springrecipes.recipes.rcp_2_1_1.SequenceDao;
import ru.alkv.springrecipes.recipes.rcp_2_2.Product;
import ru.alkv.springrecipes.recipes.rcp_2_3_1.SequenceGenerator2;
import ru.alkv.springrecipes.recipes.rcp_2_5_1.ShoppingCart;

@RestController
public class PojoController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ApplicationContext context;
	
	@GetMapping("/pojo")
    public String simple() {
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

        return "Squence: " + 
				seqGen.getSequence() + "</hr>" +
				" SequenceGenerator2: " + 
				generator.getSequence() + "</hr>" +
				" SequenceDao: " + 
				sequenceDao.getNextValue("IT") + "</hr>" +
				" Product battery: " + aaa.toString() + "</hr>" +
				" Product disc: " + cdrw.toString() + "</hr>" +
				"Shopping cart 1 contains: " + cart1.getItems() + "</hr>" +
				"Shopping cart 2 contains: " + cart2.getItems() + "</hr>";
    }
}
