package com.petProject.productsStore.utils;

import com.petProject.productsStore.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DBTemplate {
    private List<Product> products = new ArrayList<>();
    private static DBTemplate instance;

    private DBTemplate() {
    }

    public static DBTemplate getInstance(){
        if (instance == null){
            instance = new DBTemplate();
            instance.initProducts();
        }
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }

    private void initProducts(){
        double min = 1.0;
        double max = 1000.0;
        for (int i = 0; i < 100; i++){
            Product product = new Product(
                    i,
                    "prodName" + i,
                    "Here is a description for product " + i,
                    ThreadLocalRandom.current().nextDouble(min, max + 1));
            instance.products.add(product);
        }
    }
}
