package com.petProject.productsStore;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.service.ProductService;
import com.petProject.productsStore.service.ShoppingCartService;
import com.petProject.productsStore.service.UserService;
import com.petProject.productsStore.service.impl.ProductServiceImpl;
import com.petProject.productsStore.service.impl.ShoppingCartServiceImpl;
import com.petProject.productsStore.service.impl.UserServiceImpl;
import com.petProject.productsStore.utils.DBTemplate;

public class ProductsStoreApp {

    private boolean isAlive = true;

    private ProductService productService;
    private ShoppingCartService shoppingCartService;
    private UserService userService;

    public static void main(String[] args) {
        ProductsStoreApp app = new ProductsStoreApp();
        app.init();
        app.runApp();
    }

    public void init() {
        //init DB
        DBTemplate.getInstance();
        //init services
        productService = new ProductServiceImpl();
        shoppingCartService = new ShoppingCartServiceImpl();
        userService = new UserServiceImpl();

        User user1 = userService.get(1);
        User user2 = userService.get(2);
        User user3 = userService.get(3);

        Product product1 = productService.get(1);
        Product product2 = productService.get(2);
        Product product3 = productService.get(3);

        Product product4 = productService.get(4);
        Product product5 = productService.get(2);
        Product product6 = productService.get(7);

        Product product7 = productService.get(5);
        Product product8 = productService.get(6);

        shoppingCartService.addProduct(user1, product1);
        shoppingCartService.addProduct(user1, product2);
        shoppingCartService.addProduct(user1, product3);

        shoppingCartService.addProduct(user2, product6);
        shoppingCartService.addProduct(user2, product4);
        shoppingCartService.addProduct(user1, product7);

        shoppingCartService.addProduct(user3, product5);
        shoppingCartService.addProduct(user3, product8);

    }

    public synchronized void runApp() {
        logger("App started");
        //just for example
        //cut, read, update, do whatever you want
        while (isAlive()) {
            try {
                //logger("sleep");
                wait(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized boolean isAlive() {
        return isAlive;
    }

    public synchronized void terminate() {
        isAlive = false;
        logger("App exit 0");
    }

    private void logger(String s) {
        System.out.println(s);
    }

    public ProductService getProductService() {
        return productService;
    }

    public ShoppingCartService getShoppingCartService() {
        return shoppingCartService;
    }

    public UserService getUserService() {
        return userService;
    }
}
