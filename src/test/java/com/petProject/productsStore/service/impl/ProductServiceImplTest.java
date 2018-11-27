package com.petProject.productsStore.service.impl;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.exceptions.ProductServiceException;
import com.petProject.productsStore.productDAO.ProductDAO;
import com.petProject.productsStore.productDAO.impl.ProductDAOImpl;
import com.petProject.productsStore.service.ProductService;
import com.petProject.productsStore.service.impl.ProductServiceImpl;
import com.petProject.productsStore.utils.DBTemplate;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ProductServiceImplTest {
    ProductService productService = new ProductServiceImpl();


    @BeforeClass
    public void init() {

        System.out.println("before class");
    }

    @Test
    public void testCreate() {
        Product product = new Product(3, "Tomato", "They are red", 21.20);
        productService.create(product);


    }

    @Test
    public void testUpdate() {
        Product product = productService.get(3);
        productService.update(product);
        Product result = productService.get(3);
        assertEquals(result.getDescription(), product.getDescription());
        assertEquals(result.getName(), product.getName());
        assertEquals(result.getPrice(), product.getPrice());
    }

    @Test
    public void testDelete() {
        productService.delete(1);
        int productListSizeAfter = productService.findAll().size();
        assertEquals(5, productListSizeAfter);
    }

    @Test
    public void testGgetById() {
        Product product = productService.get(3);
        assertNotNull(product);
    }

    @Test
    public void testFindAll() {
        List<Product> productList = productService.findAll();
        assertEquals(productList.size(), 6);
    }

    @Test
    public void testGetByPriceRange() {
        final double START_PRICE = 1.15;
        final double END_PRICE = 10.50;
        List<Product> productList = productService.getByPriceRange(START_PRICE, END_PRICE);
        for (Product product : productList) {
            double price = product.getPrice();
            boolean result = (price <= END_PRICE) && (price >= START_PRICE);
            assertTrue(result);
        }
    }

    @Test(expectedExceptions = ProductServiceException.class, expectedExceptionsMessageRegExp = "Uf uffff uffff.")
    public void testNegativePrice() {
        productService.getByPriceRange(-1.15, 10.50);
    }
    @Test(expectedExceptions = ProductServiceException.class, expectedExceptionsMessageRegExp = "Uf uffff uffff.")
    public void test2NegativePrice() {
        productService.getByPriceRange(1.15, -10.50);
    }
    @Test(expectedExceptions = ProductServiceException.class, expectedExceptionsMessageRegExp = "Uf uffff uffff.")
    public void test3NegativePrice() {
        productService.getByPriceRange(-1.15, -10.50);
    }

    @Test
    public void testGetProductsByName() {
        final String NAME = "pepper";
        List<Product> productList = productService.getProductsByName(NAME);
        for (Product product : productList) {
            String productName = product.getName();
            assertEquals(productName, NAME);
        }
    }

    @AfterClass
    public void after() {

        System.out.println("after class");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("after method");
    }

    @BeforeMethod
    public void beforeMethod() {
        DBTemplate dbTemplate = mockDB();
        ProductDAO productDAO = new ProductDAOImpl(dbTemplate);
        productService = new ProductServiceImpl(productDAO);
    }

    private DBTemplate mockDB() {
        DBTemplate dbTemplate = DBTemplate.getInstance();
        dbTemplate.getProducts().clear();
        Product product1 = new Product(1, "potatoes", "From farm", 3.45);
        Product product2 = new Product(2, "pepper", "This is not hot", 6.10);
        Product product3 = new Product(3, "chilly", "This is very hot", 12.00);
        Product product4 = new Product(4, "pumpkin", "For Halloween", 4.20);
        Product product5 = new Product(5, "nut", "This is peanut", 30.05);
        Product product6 = new Product(6, "nut", "This is chestnut", 104.50);
        dbTemplate.getProducts().addAll(Arrays.asList(product1, product2, product3, product4, product5, product6));
        return dbTemplate;
    }
}
