package com.petProject.productsStore.service.impl;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.entity.ShoppingCart;
import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.dao.ProductDAO;
import com.petProject.productsStore.dao.ShoppingCartDAO;
import com.petProject.productsStore.dao.UserDao;
import com.petProject.productsStore.dao.impl.ProductDAOImpl;
import com.petProject.productsStore.dao.impl.ShoppingCartDAOImpl;
import com.petProject.productsStore.dao.impl.UserDaoImpl;
import com.petProject.productsStore.service.ProductService;
import com.petProject.productsStore.service.ShoppingCartService;
import com.petProject.productsStore.service.UserService;
import com.petProject.productsStore.utils.DBTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ShoppingCartServiceImplTest {

    ShoppingCartService shoppingCartService;
    UserService userService;
    ProductService productService;

    @Test
    public void testAddProduct() {
        User user = userService.get(3);
        Product product = productService.get(3);
        shoppingCartService.addProduct(user, product);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        int result = shoppingCart.getProducts().size();
        assertEquals(result, 4);
    }

    @Test
    public void testRemoveProduct() {
        User user = userService.get(2);
        shoppingCartService.removeProduct(user, 3);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        int result = shoppingCart.getProducts().size();
        assertEquals(result, 0);
    }

    @Test
    public void testRemoveAllProducts() {
        User user = userService.get(3);
        shoppingCartService.removeAllProducts(user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        int result = shoppingCart.getProducts().size();
        assertEquals(result, 0);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void negativeTestRemoveProductNonexistentId() {
        User user = userService.get(2);
        shoppingCartService.removeProduct(user, 8);
    }

    @BeforeMethod
    public void beforeMethod() {
        DBTemplate dbTemplate = DBTemplate.getInstance();
        mockProductsDB();
        mockUsersDB();

        UserDao userDao = new UserDaoImpl(dbTemplate);
        userService = new UserServiceImpl(userDao);

        ProductDAO productDAO = new ProductDAOImpl(dbTemplate);
        productService = new ProductServiceImpl(productDAO);

        mockShoppingCartDB();
        ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAOImpl(dbTemplate);
        shoppingCartService = new ShoppingCartServiceImpl(shoppingCartDAO);
    }

    private void mockShoppingCartDB() {
        DBTemplate dbTemplate = DBTemplate.getInstance();
        dbTemplate.getShoppingCarts().clear();
        List<Product> products = productService.findAll();

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setUser(userService.get(1));
        shoppingCart1.getProducts().add(products.get(1));

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setUser(userService.get(2));
        shoppingCart2.getProducts().add(products.get(2));

        ShoppingCart shoppingCart3 = new ShoppingCart();
        shoppingCart3.setUser(userService.get(3));
        shoppingCart3.getProducts().addAll(Arrays.asList(products.get(0), products.get(1), products.get(2)));


        dbTemplate.getShoppingCarts().addAll(Arrays.asList(shoppingCart1, shoppingCart2, shoppingCart3));
    }

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

    private void mockUsersDB() {
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