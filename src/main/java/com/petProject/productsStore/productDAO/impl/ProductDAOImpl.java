package com.petProject.productsStore.productDAO.impl;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.productDAO.ProductDAO;
import com.petProject.productsStore.utils.DBTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductDAOImpl implements ProductDAO {

    protected DBTemplate dbTemplate;
    protected List<Product> productList;

    public ProductDAOImpl(DBTemplate dbTemplate) {
        this.dbTemplate = dbTemplate;
        this.productList = dbTemplate.getProducts();
    }

    @Override
    public void create(Product product) {
        if (product == null){
            throw new RuntimeException("Cant add null.");
        }
        int id = productList.stream()
                .map(product1 -> product1.getId())
                .max(Comparator.naturalOrder()).get();
        product.setId(id + 1);
        productList.add(product);
    }

    @Override
    public void update(Product product) {
        if (product == null){
            throw new RuntimeException("Cant add null.");
        }
        Product p = productList.get(product.getId());
        if (p == null){
            throw new RuntimeException("Can't find product with id=" + product.getId());
        }else {
            productList.add(product.getId(), product);
        }
    }

    @Override
    public void delete(int id) {
        boolean res = productList.removeIf(product -> product.getId() == id);
        if (!res){
            throw new RuntimeException("Can't find product with id=" + id);
        }
    }

    @Override
    public Product get(int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst().get();
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }


}
