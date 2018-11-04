package com.petProject.productsStore.productDAO;

import com.petProject.productsStore.entity.Product;

import java.util.List;

public interface ProductDAO {
    void create(Product product);
    void update(Product product);
    void delete(int id);
    Product get(int id);
    List<Product> findAll();
}
