package com.petProject.productsStore.integrationTest;

import com.petProject.productsStore.ProductsStoreApp;
import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.ShoppingCart;
import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.utils.DBTemplate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class UserProductAddingScenarioIT {

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
    public void addNewUser(){
        User user = new User();
        user.setName("Jhon");
        user.setLastName("Petruchi");
        user.setPassword("1234567");
        persistedUser = storeApp.getUserService().create(user);
        assertNotNull(storeApp.getUserService().get(persistedUser.getId()), "New User registration fail.");
    }

    @Test(priority = 20, dependsOnMethods = "addNewUser")
    public void add_2_ProductsToShoppingCart(){
        Product product1 = storeApp.getProductService().get(100);
        Product product2 = storeApp.getProductService().get(200);
        storeApp.getShoppingCartService().addProduct(persistedUser, product1);
        storeApp.getShoppingCartService().addProduct(persistedUser, product2);

        ShoppingCart shoppingCart = storeApp.getShoppingCartService().getByUser(persistedUser);
        List<Product> products = shoppingCart.getProducts();

        assertThat(products, containsInAnyOrder(
                product1,
                product2
        ));
    }

    @Test(priority = 30, dependsOnMethods = {"addNewUser", "add_2_ProductsToShoppingCart"})
    public void add_10_ProductsToShoppingCart(){
        Product product1 = storeApp.getProductService().get(300);
        Product product2 = storeApp.getProductService().get(400);
        Product product3 = storeApp.getProductService().get(500);
        Product product4 = storeApp.getProductService().get(600);
        Product product5 = storeApp.getProductService().get(700);
        Product product6 = storeApp.getProductService().get(800);
        Product product7 = storeApp.getProductService().get(900);
        Product product8 = storeApp.getProductService().get(1000);
        Product product9 = storeApp.getProductService().get(1100);
        Product product10 = storeApp.getProductService().get(1200);

        storeApp.getShoppingCartService().addProduct(persistedUser, product1);
        storeApp.getShoppingCartService().addProduct(persistedUser, product2);
        storeApp.getShoppingCartService().addProduct(persistedUser, product3);
        storeApp.getShoppingCartService().addProduct(persistedUser, product4);
        storeApp.getShoppingCartService().addProduct(persistedUser, product5);
        storeApp.getShoppingCartService().addProduct(persistedUser, product6);
        storeApp.getShoppingCartService().addProduct(persistedUser, product7);
        storeApp.getShoppingCartService().addProduct(persistedUser, product8);
        storeApp.getShoppingCartService().addProduct(persistedUser, product9);
        storeApp.getShoppingCartService().addProduct(persistedUser, product10);

        ShoppingCart shoppingCart = storeApp.getShoppingCartService().getByUser(persistedUser);
        List<Product> products = shoppingCart.getProducts();

        assertThat(products, hasItems(
                product1,
                product2,
                product3,
                product4,
                product5,
                product6,
                product7,
                product8,
                product9,
                product10
        ));
        assertEquals(products.size(), 12);
    }

    @Test(priority = 40, dependsOnMethods = {"addNewUser", "add_2_ProductsToShoppingCart"})
    public void delete_1_ProductFromShoppingCart(){
        Product product = storeApp.getProductService().get(100);

        storeApp.getShoppingCartService().removeProduct(persistedUser, product.getId());

        ShoppingCart shoppingCart = storeApp.getShoppingCartService().getByUser(persistedUser);
        List<Product> products = shoppingCart.getProducts();

        assertThat(products,not(hasItem(product)));
    }

    @Test(priority = 50, dependsOnMethods = {"addNewUser", "add_2_ProductsToShoppingCart", "add_10_ProductsToShoppingCart", "delete_1_ProductFromShoppingCart"})
    public void delete_5_ProductFromShoppingCart(){
        Product product1 = storeApp.getProductService().get(200);
        Product product2 = storeApp.getProductService().get(300);
        Product product3 = storeApp.getProductService().get(400);
        Product product4 = storeApp.getProductService().get(500);
        Product product5 = storeApp.getProductService().get(600);
        Product product6 = storeApp.getProductService().get(700);
        Product product7 = storeApp.getProductService().get(800);
        Product product8 = storeApp.getProductService().get(900);
        Product product9 = storeApp.getProductService().get(1000);
        Product product10 = storeApp.getProductService().get(1100);

        storeApp.getShoppingCartService().removeProduct(persistedUser, product1.getId());
        storeApp.getShoppingCartService().removeProduct(persistedUser, product2.getId());
        storeApp.getShoppingCartService().removeProduct(persistedUser, product3.getId());
        storeApp.getShoppingCartService().removeProduct(persistedUser, product4.getId());
        storeApp.getShoppingCartService().removeProduct(persistedUser, product5.getId());
        storeApp.getShoppingCartService().removeProduct(persistedUser, product6.getId());
        storeApp.getShoppingCartService().removeProduct(persistedUser, product7.getId());
        storeApp.getShoppingCartService().removeProduct(persistedUser, product8.getId());
        storeApp.getShoppingCartService().removeProduct(persistedUser, product9.getId());
        storeApp.getShoppingCartService().removeProduct(persistedUser, product10.getId());

        ShoppingCart shoppingCart = storeApp.getShoppingCartService().getByUser(persistedUser);
        List<Product> products = shoppingCart.getProducts();

        assertThat(products,not(hasItems(
                product1,
                product2,
                product3,
                product4,
                product5,
                product6,
                product7,
                product8,
                product9,
                product10
        )));
    }

    @Test(priority = 60, dependsOnMethods = {"addNewUser", "add_2_ProductsToShoppingCart", "add_10_ProductsToShoppingCart"})
    public void deleteAllProductsFromShoppingCart(){
        storeApp.getShoppingCartService().removeAllProducts(persistedUser);
        int size = storeApp.getShoppingCartService().getByUser(persistedUser).getProducts().size();
        assertEquals(size, 0, "Not valid products size.");
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
