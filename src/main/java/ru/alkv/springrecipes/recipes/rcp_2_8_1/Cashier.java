package ru.alkv.springrecipes.recipes.rcp_2_8_1;

import ru.alkv.springrecipes.recipes.rcp_2_5_1.ShoppingCart;

import java.io.*;
import java.util.Date;

public class Cashier {
    private String fileName;
    private String path;
    private BufferedWriter fileWriter;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }

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
        fileWriter.write(new Date() + "\t" + cart.getItems() + "\r\n");
        fileWriter.flush();
    }

    public void closeFile() throws IOException {
        fileWriter.close();
    }
}
