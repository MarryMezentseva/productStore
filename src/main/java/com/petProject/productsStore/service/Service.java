package com.petProject.productsStore.service;

import com.petProject.productsStore.entity.Product;

import java.util.List;

public interface Service <T> {
    T create(T t);
    void update(T t);
    void delete(int id);
    T get(int id);
    List<T> findAll();
}
