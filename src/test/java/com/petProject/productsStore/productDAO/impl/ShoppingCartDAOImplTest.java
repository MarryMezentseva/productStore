package com.petProject.productsStore.productDAO.impl;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.ShoppingCart;
import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.productDAO.ProductDAO;
import com.petProject.productsStore.productDAO.ShoppingCartDAO;
import com.petProject.productsStore.productDAO.UserDao;
import com.petProject.productsStore.utils.DBTemplate;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ShoppingCartDAOImplTest {

    ShoppingCartDAO shoppingCartDAO;
    UserDao userDAO;
    ProductDAO productDAO;

    @Test
    public void testCreate() {
        User user = userDAO.get(1);
        List<Product> productList = Arrays.asList(productDAO.get(2), productDAO.get(1), productDAO.get(3));

        ShoppingCart shoppingCart = new ShoppingCart(user, productList);
        shoppingCartDAO.create(shoppingCart);
        int shoppingCartListSizeAfter = shoppingCartDAO.findAll().size();
        assertEquals(4, shoppingCartListSizeAfter);
    }

    @Test
    public void testUpdate() {
        ShoppingCart shoppingCart = shoppingCartDAO.getByUserId(2);
        Product product = productDAO.get(3);
        shoppingCart.getProducts().add(product);

        shoppingCartDAO.update(shoppingCart);
        ShoppingCart result = shoppingCartDAO.getByUserId(2);

        assertEquals(result.getProducts().size(), 2);
    }

    @Test
    public void testGet() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testFindAll() {
    }

    @BeforeMethod
    public void beforeMethod() {
        DBTemplate dbTemplate = DBTemplate.getInstance();
        mockProductsDB();
        mockUserDB();
        userDAO = new UserDaoImpl(dbTemplate);
        productDAO = new ProductDAOImpl(dbTemplate);
        mockShoppingCartDB();
        shoppingCartDAO = new ShoppingCartDAOImpl(dbTemplate);
    }

    private void mockShoppingCartDB() {
        DBTemplate dbTemplate = DBTemplate.getInstance();
        dbTemplate.getShoppingCarts().clear();
        // for (int i = 0, i <= 10, i++){

        List<Product> products = productDAO.findAll();

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setUser(userDAO.get(1));
        shoppingCart1.getProducts().add(products.get(3));

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setUser(userDAO.get(4));
        shoppingCart2.getProducts().add(products.get(4));

        ShoppingCart shoppingCart3 = new ShoppingCart();
        shoppingCart3.setUser(userDAO.get(2));
        shoppingCart3.getProducts().add(products.get(2));


        dbTemplate.getShoppingCarts().addAll(Arrays.asList(shoppingCart1, shoppingCart2, shoppingCart3));
    }

    // }
    private void mockProductsDB() {
        DBTemplate dbTemplate = DBTemplate.getInstance();
        dbTemplate.getProducts().clear();
        Product product1 = new Product(1, "orange1", "This orange is very tasty1", 121.02);
        Product product2 = new Product(2, "orange2", "This orange is very tasty2", 122.02);
        Product product3 = new Product(3, "orange3", "This orange is very tasty3", 123.02);
        Product product4 = new Product(4, "orange4", "This orange is very tasty4", 124.02);
        Product product5 = new Product(5, "orange5", "This orange is very tasty5", 125.02);
        dbTemplate.getProducts().addAll(Arrays.asList(product1, product2, product3, product4, product5));

    }

    private void mockUserDB() {
        DBTemplate dbTemplate = DBTemplate.getInstance();
        dbTemplate.getUsers().clear();
        User user1 = new User(1, "Bob", "Bbbb", "1210345u");
        User user2 = new User(2, "Billy", "Llll", "687o87ol");
        User user3 = new User(3, "Garry", "Gggg", "78dfr5t2");
        User user4 = new User(4, "Olly", "Oooo", "h24gfh02");
        User user5 = new User(5, "Clod", "Cccc", "1871rg798");
        dbTemplate.getUsers().addAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
}