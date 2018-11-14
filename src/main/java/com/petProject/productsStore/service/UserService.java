package com.petProject.productsStore.service;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.User;

import java.util.List;

public interface UserService {
    void create(User user);
    void update(User user);
    void delete(int id);
    User get(int id);
    List<User> findAll();
    List<User> getUsersByName(String name);
}
