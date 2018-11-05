package com.petProject.productsStore;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.productDAO.ProductDAO;
import com.petProject.productsStore.productDAO.ProductDAOImpl;
import com.petProject.productsStore.service.ProductService;
import com.petProject.productsStore.service.ProductServiceImpl;
import com.petProject.productsStore.utils.DBTemplate;

import java.util.List;

public class ProductsStoreApp {
    public static void main(String[] args) {
        //ProductDAO productDAO = new ProductDAOImpl(DBTemplate.getInstance());
        ProductService productService = new ProductServiceImpl();
        List<Product> productList = productService.findAll();
    }
}
