package com.petProject.productsStore.integrationTest;

import com.petProject.productsStore.ProductsStoreApp;
import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.ShoppingCart;
import com.petProject.productsStore.entity.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.testng.Assert.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class UserProductAddingScenarioIT {

    private ProductsStoreApp storeApp;
    private User persistedUser;

    @BeforeClass
    public void init(){
        storeApp = new ProductsStoreApp();
        storeApp.init();

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
        Product product1 = storeApp.getProductService().get(1);
        Product product2 = storeApp.getProductService().get(2);
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
        Product product1 = storeApp.getProductService().get(3);
        Product product2 = storeApp.getProductService().get(4);
        Product product3 = storeApp.getProductService().get(5);
        Product product4 = storeApp.getProductService().get(6);
        Product product5 = storeApp.getProductService().get(7);
        Product product6 = storeApp.getProductService().get(8);
        Product product7 = storeApp.getProductService().get(9);
        Product product8 = storeApp.getProductService().get(10);
        Product product9 = storeApp.getProductService().get(11);
        Product product10 = storeApp.getProductService().get(12);

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

    @Test(priority = 40)
    public void delete_1_ProductFromShoppingCart(){

    }

    @Test(priority = 50)
    public void delete_5_ProductFromShoppingCart(){

    }

    @AfterClass
    public void terminate(){
        storeApp.terminate();
    }

}
