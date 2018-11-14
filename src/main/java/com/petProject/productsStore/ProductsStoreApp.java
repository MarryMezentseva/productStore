package com.petProject.productsStore;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.service.ProductService;
import com.petProject.productsStore.service.ShoppingCartService;
import com.petProject.productsStore.service.UserService;
import com.petProject.productsStore.service.impl.ProductServiceImpl;
import com.petProject.productsStore.service.impl.ShoppingCartServiceImpl;
import com.petProject.productsStore.service.impl.UserServiceImpl;

import java.util.List;

public class ProductsStoreApp {
    public static void main(String[] args) {
        //ProductDAO productDAO = new ProductDAOImpl(DBTemplate.getInstance());
        ProductService productService = new ProductServiceImpl();
        List<Product> productList = productService.findAll();
        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
        UserService userService = new UserServiceImpl();
        User user = userService.get(1);
        shoppingCartService.addProduct(user, productService.get(1));
        shoppingCartService.addProduct(user, productService.get(2));
        shoppingCartService.addProduct(user, productService.get(3));

        shoppingCartService.removeProduct(user, 2);
        shoppingCartService.removeProduct(user, 3);

        shoppingCartService.removeAllProducts(user);

        System.out.println();

    }
}
