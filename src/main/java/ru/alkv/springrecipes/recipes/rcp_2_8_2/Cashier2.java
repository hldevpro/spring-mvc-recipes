package ru.alkv.springrecipes.recipes.rcp_2_8_2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.alkv.springrecipes.recipes.rcp_2_5_1.ShoppingCart;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.Date;

@Component
public class Cashier2 {
    @Value("checkout2")
    private String fileName;
    @Value("c:\\Windows\\Temp")
    private String path;
    private BufferedWriter fileWriter;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @PostConstruct
    public void openFile() throws IOException {
        File targetDir = new File(path);

        if (!targetDir.exists()) {
            targetDir.mkdir();
        }

        File checkoutFile = new File(path, fileName + ".txt");
        if (!checkoutFile.exists()) {
            checkoutFile.createNewFile();
        }

        fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(checkoutFile)));
    }

    public void checkout(ShoppingCart cart) throws IOException {
        fileWriter.write("Cashier2 \r\n");
        fileWriter.write(new Date() + "\t" + cart.getItems() + "\r\n");
        fileWriter.flush();
    }

    @PreDestroy
    public void closeFile() throws IOException {
        fileWriter.close();
    }
}
