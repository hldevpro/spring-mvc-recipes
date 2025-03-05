package ru.alkv.springrecipes.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcShowcaseAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                RootConfig.class,
                RecipesConfiguration.class,
                DevConfiguration.class,
                ProdConfiguration.class,
                AopConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new DelegatingFilterProxy("csrfFilter") };
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        // Set active profile
        //servletContext.setInitParameter("spring.profiles.active", "prod");
        //servletContext.setInitParameter("spring.profiles.active", "dev, local");
    }

    //If the @Profile beans are loaded via root context
    @Override
    protected WebApplicationContext createRootApplicationContext() {

        WebApplicationContext context = (WebApplicationContext) super.createRootApplicationContext();

        ((ConfigurableEnvironment)context.getEnvironment()).setDefaultProfiles("dev");

        //Set multiple active profiles
        //((ConfigurableEnvironment)context.getEnvironment()).setActiveProfiles(new String[]{"dev", "local"});

        return context;

    }

    //If the @Profile beans are loaded via servlet context
	@Override
	protected WebApplicationContext createServletApplicationContext() {

		WebApplicationContext context = (WebApplicationContext) super.createServletApplicationContext();

        // ((ConfigurableEnvironment)context.getEnvironment()).setActiveProfiles("dev");

        return context;
	}
}
