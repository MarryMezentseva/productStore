package com.petProject.productsStore.service;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.productDAO.ProductDAO;
import com.petProject.productsStore.productDAO.ProductDAOImpl;
import com.petProject.productsStore.utils.DBTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService{

    protected ProductDAO productDAO;

    public ProductServiceImpl() {
        this.productDAO = new ProductDAOImpl(DBTemplate.getInstance());
    }

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void create(Product product) {
        productDAO.create(product);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
    }

    @Override
    public Product get(int id) {
        return productDAO.get(id);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> getByPriceRange(double startFrom, double endWith) {
        return productDAO.findAll().stream()
                .filter(product -> startFrom <= product.getPrice() && endWith >= product.getPrice())
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productDAO.findAll().stream()
                .filter(product -> name.equals(product.getName()))
                .collect(Collectors.toList());
    }
}
