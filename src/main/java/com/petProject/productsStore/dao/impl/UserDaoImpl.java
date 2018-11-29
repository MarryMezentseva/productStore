package com.petProject.productsStore.dao.impl;

import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.dao.UserDao;
import com.petProject.productsStore.utils.DBTemplate;

import java.util.Comparator;
import java.util.List;

public class UserDaoImpl implements UserDao {
    protected DBTemplate dbTemplate;
    protected List<User> users;

    public UserDaoImpl(DBTemplate dbTemplate) {
        this.dbTemplate = dbTemplate;
        this.users = dbTemplate.getUsers();
    }

    @Override
    public void create(User user) {
        if (user == null){
            throw new RuntimeException("Can't add null.");
        }
        int id = users.stream()
                .map(User::getId)
                .max(Comparator.naturalOrder()).get();
        user.setId(id + 1);
        users.add(user);
    }

    @Override
    public void update(User user) {
        if (user == null){
            throw new RuntimeException("Cant add null.");
        }
        boolean res = users.removeIf(user1 -> user1.getId() == user.getId());
        if (!res){
            throw new RuntimeException("Can't find user with id=" + user.getId());
        }
        users.add(user);

    }

    @Override
    public void delete(int id) {
        boolean res = users.removeIf(user -> user.getId() == id);
        if (!res){
            throw new RuntimeException("Can't find user with id=" + id);
        }
    }

    @Override
    public User get(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst().orElseThrow(()-> new RuntimeException("User not found."));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

 }
