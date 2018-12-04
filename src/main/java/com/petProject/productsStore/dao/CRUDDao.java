package com.petProject.productsStore.dao;

import java.util.List;

public interface CRUDDao <T> {
    T create(T t);
    void update(T t);
    void delete(int id);
    T get(int id);
    List<T> findAll();
}
