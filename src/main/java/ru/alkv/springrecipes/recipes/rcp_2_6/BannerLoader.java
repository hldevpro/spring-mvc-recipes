package ru.alkv.springrecipes.recipes.rcp_2_6;

import javax.annotation.PostConstruct;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BannerLoader {
    private Resource banner;

    public void setBanner(Resource banner) {
        this.banner = banner;
    }

    @PostConstruct
    public void showBanner() throws IOException {
        Files.lines(Paths.get(banner.getURI()), Charset.forName("UTF-8"))
                .forEachOrdered(System.out::println);
    }
}
