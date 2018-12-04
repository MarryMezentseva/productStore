package com.petProject.productsStore.service.impl;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.exceptions.ProductServiceException;
import com.petProject.productsStore.dao.ProductDAO;
import com.petProject.productsStore.dao.impl.ProductDAOImpl;
import com.petProject.productsStore.service.ProductService;
import com.petProject.productsStore.utils.DBTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    protected ProductDAO productDAO;

    public ProductServiceImpl() {
        this.productDAO = new ProductDAOImpl(DBTemplate.getInstance());
    }

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product create(Product product) {
        return productDAO.create(product);
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
        if (startFrom < 0 || endWith < 0){
            throw new ProductServiceException("Uf uffff uffff.");
        }
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
