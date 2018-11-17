package com.petProject.productsStore.productDAO;

import com.petProject.productsStore.entity.User;

import java.util.List;

public interface CRUDDao <T> {
    void create(T t);
    void update(T t);
    void delete(int id);
    T get(int id);
    List<T> findAll();
}
