package com.petProject.productsStore.productDAO.impl;

import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.productDAO.UserDao;
import com.petProject.productsStore.utils.DBTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        User u = users.get(user.getId());
        if (u == null){
            throw new RuntimeException("Can't find product with id=" + user.getId());
        }else {
            users.add(user.getId(), user);
        }
    }

    @Override
    public void delete(int id) {
        boolean res = users.removeIf(user -> user.getId() == id);
        if (res){
            throw new RuntimeException("Can't find user with id=" + id);
        }
    }

    @Override
    public User get(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst().get();
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
