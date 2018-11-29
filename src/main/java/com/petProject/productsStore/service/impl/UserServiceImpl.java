package com.petProject.productsStore.service.impl;

import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.dao.UserDao;
import com.petProject.productsStore.dao.impl.UserDaoImpl;
import com.petProject.productsStore.service.UserService;
import com.petProject.productsStore.utils.DBTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    protected UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl(DBTemplate.getInstance());
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User get(int id) {
        return userDao.get(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userDao.findAll().stream()
                .filter(user -> name.equals(user.getName()))
                .collect(Collectors.toList());
    }
}
