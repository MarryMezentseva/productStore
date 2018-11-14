package com.petProject.productsStore.service;

import com.petProject.productsStore.entity.Product;

import java.util.List;

public interface ProductService extends Service<Product> {
    List<Product> getByPriceRange(double startFrom, double endWith);
    List<Product> getProductsByName(String name);
}
