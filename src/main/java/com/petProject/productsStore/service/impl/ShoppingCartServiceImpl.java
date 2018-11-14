package com.petProject.productsStore.service.impl;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.ShoppingCart;
import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.productDAO.ShoppingCartDAO;
import com.petProject.productsStore.productDAO.impl.ShoppingCartDAOImpl;
import com.petProject.productsStore.service.ShoppingCartService;
import com.petProject.productsStore.utils.DBTemplate;

import java.util.List;
import java.util.Optional;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    protected ShoppingCartDAO shoppingCartDAO;

    public ShoppingCartServiceImpl(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    public ShoppingCartServiceImpl() {
        this.shoppingCartDAO = new ShoppingCartDAOImpl(DBTemplate.getInstance());
    }

    @Override
    public void addProduct(User user, Product product) {
        if (user == null) {
            throw new RuntimeException("User is null.");
        }

        if (product == null) {
            throw new RuntimeException("Product is null.");
        }

        List<ShoppingCart> shoppingCarts = shoppingCartDAO.findAll();
        Optional<ShoppingCart> shoppingCart = shoppingCarts.stream()
                .filter(s-> s.getUser().getId() == user.getId())
                .findFirst();
        if (!shoppingCart.isPresent()){
            ShoppingCart shC = new ShoppingCart();
            shC.setUser(user);
            shC.getProducts().add(product);
            shoppingCartDAO.create(shC);
        }else {
            shoppingCart.get().getProducts().add(product);
        }
    }

    @Override
    public void removeProduct(User user, int productId) {
        if (user == null) {
            throw new RuntimeException("User is null.");
        }

        List<ShoppingCart> shoppingCarts = shoppingCartDAO.findAll();
        Optional<ShoppingCart> shoppingCart = shoppingCarts.stream()
                .filter(s-> s.getUser().getId() == user.getId())
                .findFirst();
        if (!shoppingCart.isPresent()){
            throw new RuntimeException("User is null.");
        }else {
            shoppingCart.get().getProducts().removeIf(p -> p.getId() == productId);
        }
    }

    @Override
    public void removeAllProducts(User user) {
        if (user == null) {
            throw new RuntimeException("Cart is null.");
        }
        List<ShoppingCart> shoppingCarts = shoppingCartDAO.findAll();
        Optional<ShoppingCart> shoppingCart = shoppingCarts.stream()
                .filter(s-> s.getUser().getId() == user.getId())
                .findFirst();
        if (!shoppingCart.isPresent()){
            throw new RuntimeException("Cart is null.");
        }else {
            shoppingCart.get().getProducts().clear();
        }
    }
}
