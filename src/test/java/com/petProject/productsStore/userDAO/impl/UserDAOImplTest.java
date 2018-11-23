package com.petProject.productsStore.userDAO.impl;


import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.productDAO.UserDao;
import com.petProject.productsStore.productDAO.impl.UserDaoImpl;
import com.petProject.productsStore.utils.DBTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserDAOImplTest {
    UserDao userDao;

    @Test
    public void testCreate() {
        User user = new User(6, "Troy", "Tttt", "125689774");
        userDao.create(user);
        int userListSizeAfter = userDao.findAll().size();
        assertEquals(6, userListSizeAfter);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testNegativeUpdate(){
        userDao.update(null);
    }

    @Test
    public void testUpdate() {
        User user = userDao.get(2);//from db
        user.setName("Fisha");

        userDao.update(user);

        User result = userDao.get(2);
        assertEquals(result.getId(), user.getId());
        assertEquals(result.getName(), user.getName());
        assertEquals(result.getLastName(), user.getLastName());
        assertEquals(result.getPassword(), user.getPassword());
    }

    @Test
    public void testNegativeDeleteWithNotExistingId(){
        userDao.delete(6);
        int userListAfter = userDao.findAll().size();
        assertEquals(5, userListAfter);
    }

    @Test
    public void testDelete(){
        userDao.delete(3);
        int userListAfter = userDao.findAll().size();
        assertEquals(4, userListAfter);
    }

    @Test
    public void testGet() {
        User user = userDao.get(1);
        assertNotNull(user);
    }

    @Test
    public void testNegativeGetNotExistingId() {
        User user = userDao.get(8);
        assertNotNull(user);
    }

    @Test
    public void testFindAll() {
        List<User> userList = userDao.findAll();
        assertEquals(5, userList);
    }

    @BeforeMethod
    public void beforeMethod() {
        DBTemplate dbTemplate = mockDB();
        userDao = new UserDaoImpl(dbTemplate);
    }

    private DBTemplate mockDB() {
        DBTemplate dbTemplate = DBTemplate.getInstance();
        dbTemplate.getUsers().clear();
        User user1 = new User(1, "Bob", "Bbbb", "1210345u");
        User user2 = new User(2, "Billy", "Llll", "687o87ol");
        User user3 = new User(3, "Garry", "Gggg", "78dfr5t2");
        User user4 = new User(4, "Olly", "Oooo", "h24gfh02");
        User user5 = new User(5, "Clod", "Cccc", "1871rg798");
        dbTemplate.getUsers().addAll(Arrays.asList(user1, user2, user3, user4, user5));
        return dbTemplate;
    }
}
