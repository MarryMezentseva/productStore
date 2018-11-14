package com.petProject.productsStore;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.service.ProductService;
import com.petProject.productsStore.service.impl.ProductServiceImpl;

import java.util.List;

public class ProductsStoreApp {
    public static void main(String[] args) {
        //ProductDAO productDAO = new ProductDAOImpl(DBTemplate.getInstance());
        ProductService productService = new ProductServiceImpl();
        List<Product> productList = productService.findAll();
    }
}
