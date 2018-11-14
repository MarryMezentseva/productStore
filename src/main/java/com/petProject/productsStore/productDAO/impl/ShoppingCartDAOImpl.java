package com.petProject.productsStore.productDAO.impl;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.ShoppingCart;
import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.productDAO.ShoppingCartDAO;
import com.petProject.productsStore.utils.DBTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ShoppingCartDAOImpl implements ShoppingCartDAO {

    protected DBTemplate dbTemplate;
    protected List<ShoppingCart> shoppingCarts;

    public ShoppingCartDAOImpl(DBTemplate dbTemplate) {
        this.dbTemplate = dbTemplate;
        this.shoppingCarts = dbTemplate.getShoppingCarts();
    }

    @Override
    public void create(ShoppingCart shoppingCart) {
        if (shoppingCart == null){
            throw new RuntimeException("Can't add null.");
        }

        User user = shoppingCart.getUser();
        boolean isShCartPresent = shoppingCarts.stream()
                .anyMatch(shoppingCart1 -> user.getId() == shoppingCart1.getUser().getId());
        if (!isShCartPresent){
            shoppingCarts.add(shoppingCart);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        if (shoppingCart == null){
            throw new RuntimeException("Can't add null.");
        }
        boolean res = shoppingCarts.removeIf(s-> s.getUser().getId() == shoppingCart.getUser().getId());
        if (!res){
            shoppingCarts.add(shoppingCart);
        }else {
            throw new RuntimeException("Can't find cart with user id=" + shoppingCart.getUser().getId());
        }
    }

    @Override
    public ShoppingCart get(int userId) {
        return shoppingCarts.stream()
                .filter(shoppingCart -> shoppingCart.getUser().getId() == userId)
                .findFirst().get();

    }

    @Override
    public void delete(int userId) {
        boolean res = shoppingCarts.removeIf(shoppingCart -> shoppingCart.getUser().getId() == userId);
        if (res){
            throw new RuntimeException("Can't find cart with user id=" + userId);
        }
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCarts;
    }
}
