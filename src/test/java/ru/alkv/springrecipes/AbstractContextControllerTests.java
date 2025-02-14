package ru.alkv.springrecipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import ru.alkv.springrecipes.config.WebConfig;

@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class AbstractContextControllerTests {
    @Autowired
    protected WebApplicationContext wac;
}
