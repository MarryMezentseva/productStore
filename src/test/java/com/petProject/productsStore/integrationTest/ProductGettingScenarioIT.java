package com.petProject.productsStore.integrationTest;

import com.petProject.productsStore.ProductsStoreApp;
import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.ShoppingCart;
import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.utils.DBTemplate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
    public void getProductsByName() {
        final String PRODUCT_NAME = "prodName600";
        List<Product> productList = storeApp.getProductService().getProductsByName(PRODUCT_NAME);
        for (Product product : productList) {
            storeApp.getShoppingCartService().addProduct(persistedUser, product);
        }
        ShoppingCart shoppingCart = storeApp.getShoppingCartService().getByUser(persistedUser);
        List<Product> allUserProducts = shoppingCart.getProducts();

        Product[] productsArr = new Product[allUserProducts.size()];

        assertThat(allUserProducts, hasItems(allUserProducts.toArray(productsArr)));
    }

    @Test(priority = 20, dependsOnMethods = "getUser")
    public void getProductByPriceRange() {
        final double START_PRICE = 50.50;
        final double END_PRICE = 400.50;
        List<Product> products = storeApp.getProductService().getByPriceRange(START_PRICE, END_PRICE);
        for (Product product : products) {
            storeApp.getShoppingCartService().addProduct(persistedUser, product);
        }
        ShoppingCart shoppingCart = storeApp.getShoppingCartService().getByUser(persistedUser);
        List<Product> allUsersProducts = shoppingCart.getProducts();
        Product[] arrayProducts = new Product[allUsersProducts.size()];
        assertThat(allUsersProducts, hasItems(allUsersProducts.toArray(arrayProducts)));
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
        Product product3001 = new Product(3001,"prodName600","600 too", 10.50);
        Product product3002 = new Product(3002,"prodName600","600 too", 120.10);
        Product product3003 = new Product(3003,"prodName600","600 too", 120.10);
        products.addAll(Arrays.asList(product3001, product3002, product3003));
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
