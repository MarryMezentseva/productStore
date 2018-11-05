package com.petProject.productsStore.service;

import com.petProject.productsStore.entity.Product;

import java.util.List;

public interface ProductService {
    void create(Product product);
    void update(Product product);
    void delete(int id);
    Product get(int id);
    List<Product> findAll();
    List<Product> getByPriceRange(double startFrom, double endWith);
    List<Product> getProductsByName(String name);
}
