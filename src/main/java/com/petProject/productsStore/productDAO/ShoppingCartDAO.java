package com.petProject.productsStore.productDAO;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartDAO {
    void create(ShoppingCart t);
    void update(ShoppingCart t);
    void delete(int id);
    ShoppingCart getByUserId(int id);
    List<ShoppingCart> findAll();
}
