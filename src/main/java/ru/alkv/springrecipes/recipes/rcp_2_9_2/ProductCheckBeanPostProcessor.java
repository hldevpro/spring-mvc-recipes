package ru.alkv.springrecipes.recipes.rcp_2_9_2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.alkv.springrecipes.recipes.rcp_2_2.Product;

public class ProductCheckBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Product) {
            String productName = ((Product) bean).getName();

            System.out.println("In ProductCheckBeanPostProcessor.postProcessBeforeInitialization," +
                    "processing product: " + productName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Product) {
            String productName = ((Product) bean).getName();

            System.out.println("In ProductCheckBeanPostProcessor.postProcessAfterInitialization," +
                    "processing product: " + productName);
        }
        return bean;
    }
}
