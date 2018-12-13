package com.petProject.productsStore.integrationTest;

import com.petProject.productsStore.ProductsStoreApp;
import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.utils.DBTemplate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.testng.Assert.assertNotNull;

public class ProductGettingScenarioIT {

    private ProductsStoreApp storeApp;
    private User persistedUser;

    @BeforeClass
    public void init(){
        storeApp = new ProductsStoreApp();
        storeApp.init();
        mockDB();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                storeApp.runApp();
            }
        });
        thread.start();
    }

    @Test(priority = 10)
    public void getUser() {
        persistedUser = storeApp.getUserService().get(600);
        assertNotNull(persistedUser);
    }
    @Test(priority = 20, dependsOnMethods = "getUser")
    public void getProductByName(){

    }

    @Test
    public void getProductByPriceRange(){

    }
    @AfterClass
    public void terminate(){
        storeApp.terminate();
    }

    private void mockDB(){
        DBTemplate.getInstance().getUsers().addAll(generateUsers());
        DBTemplate.getInstance().getProducts().addAll(generateProducts());
    }

    private List<Product> generateProducts(){
        List<Product> products = new ArrayList<>();
        double min = 1.0;
        double max = 1000.0;
        for (int i = 100; i < 3000; i++){
            Product product = new Product(
                    i,
                    "prodName" + i,
                    "Here is a description for product " + i,
                    ThreadLocalRandom.current().nextDouble(min, max + 1));
            products.add(product);
        }
        return products;
    }

    private List<User> generateUsers(){
        List<User> users = new ArrayList<>();
        for (int i = 300; i < 4000; i++){
            User user = new User(i, "UserName_" + i, "LastName_" + i, UUID.randomUUID().toString());
            users.add(user);
        }
        return users;
    }
}
