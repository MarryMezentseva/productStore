package com.petProject.productsStore.productDAO.impl;

import com.petProject.productsStore.entity.Product;
import com.petProject.productsStore.productDAO.ProductDAO;
import com.petProject.productsStore.productDAO.impl.ProductDAOImpl;
import com.petProject.productsStore.service.impl.ProductServiceImpl;
import com.petProject.productsStore.utils.DBTemplate;
import org.testng.annotations.*;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ProductDAOImplTest {

    ProductDAO productDAO;

    @BeforeClass
    public void init() {

        System.out.println("before class");
    }

    @Test
    public void testCreate() {
        Product product = new Product(1, "orange", "This orange is very tasty", 12.02);
        productDAO.create(product);
        int productListSizeAfter = productDAO.findAll().size();
        assertEquals(6, productListSizeAfter);
    }

    @Test
    public void testDelete() {
        productDAO.delete(1);
        int productListSizeAfter = productDAO.findAll().size();
        assertEquals(4, productListSizeAfter);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testUpdateNegative()
    {
        productDAO.update(null);
    }

    @Test
    public void testUpdate() {
        Product product = new Product(2, "Banana", "They are yellow", 14.0);
        productDAO.update(product);
        Product result = productDAO.get(2);
        assertEquals(result.getDescription(), product.getDescription() );
        assertEquals(result.getName(), product.getName());
        assertEquals(result.getPrice(), product.getPrice());
        //1. Create new Product with existed id
        //2. call dao.update( new_product )
        //3. get by id (id from new_product)
        //4. compare by: name, description, price
    }


    @Test
    public void testGet() {
        Product product = productDAO.get(4);
        assertNotNull(product);
        //1. get(id)
        //2. assertNotNull(product)
    }

    @Test
    public void testFindAll() {
        List<Product> productList = productDAO.findAll();
        assertEquals(productList.size(), 5);
        //1. call findAll()
        //2. check size > 0 (or == 5)
    }
    @Test
    public void testNoSuchId() {
        Product product = productDAO.get(8);
        assertNull(product);
        ProductServiceImpl productService = new ProductServiceImpl();
    }

    @AfterClass
    public void after() {
        System.out.println("after class");
    }

    @BeforeMethod
    public void beforeMethod() {
        DBTemplate dbTemplate = mockDB();
        productDAO = new ProductDAOImpl(dbTemplate);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("after method");
    }

    private DBTemplate mockDB() {
        DBTemplate dbTemplate = DBTemplate.getInstance();
        dbTemplate.getProducts().clear();
        Product product1 = new Product(1, "orange1", "This orange is very tasty1", 121.02);
        Product product2 = new Product(2, "orange2", "This orange is very tasty2", 122.02);
        Product product3 = new Product(3, "orange3", "This orange is very tasty3", 123.02);
        Product product4 = new Product(4, "orange4", "This orange is very tasty4", 124.02);
        Product product5 = new Product(5, "orange5", "This orange is very tasty5", 125.02);
        dbTemplate.getProducts().addAll(Arrays.asList(product1, product2, product3, product4, product5));
        return dbTemplate;
    }
}