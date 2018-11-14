package com.petProject.productsStore.productDAO;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartDAO {
    void create(ShoppingCart shoppingCart);
    void update(ShoppingCart shoppingCart);
    ShoppingCart get(int id);
    void delete(int id);
    List<ShoppingCart> findAll();
}
