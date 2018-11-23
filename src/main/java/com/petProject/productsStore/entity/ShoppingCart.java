package com.petProject.productsStore.entity;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private User user;
    private List<Product> products = new ArrayList<>();

    public ShoppingCart(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public ShoppingCart(User user) {
        this.user = user;
    }

    public ShoppingCart() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "user=" + user +
                ", products=" + products +
                '}';
    }
}
