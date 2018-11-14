package com.petProject.productsStore.service;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.User;

import java.util.List;

public interface UserService extends Service<User>{
    List<User> getUsersByName(String name);
}
