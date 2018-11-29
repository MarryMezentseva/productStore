package com.petProject.productsStore.service;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.ShoppingCart;
import com.petProject.productsStore.entity.User;

public interface ShoppingCartService {
    void addProduct(User user, Product product);
    void removeProduct(User user, int productId);
    void removeAllProducts(User user);
    ShoppingCart getByUser(User user);
}
