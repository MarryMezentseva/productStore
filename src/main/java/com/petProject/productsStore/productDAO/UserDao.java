package com.petProject.productsStore.productDAO;

import com.petProject.productsStore.entity.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    void update(User user);
    void delete(int id);
    User get(int id);
    List<User> findAll();
}
