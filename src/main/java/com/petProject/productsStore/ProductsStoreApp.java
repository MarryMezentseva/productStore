package com.petProject.productsStore;

import com.petProject.productsStore.productDAO.ProductDAO;
import com.petProject.productsStore.productDAO.ProductDAOImpl;
import com.petProject.productsStore.utils.DBTemplate;

public class ProductsStoreApp {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAOImpl(DBTemplate.getInstance());
    }
}
