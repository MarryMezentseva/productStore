package com.petProject.productsStore.service.impl;

import com.petProject.productsStore.entity.User;
import com.petProject.productsStore.dao.UserDao;
import com.petProject.productsStore.dao.impl.UserDaoImpl;
import com.petProject.productsStore.service.UserService;
import com.petProject.productsStore.utils.DBTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class UserServiceImplTest {
UserService userService;

    @Test
    public void testCreate() {
        User user = new User(10,"Antonio","Italiano","2222222");
        userService.create(user);
        int userListSizeAfter = userService.findAll().size();
        assertEquals(6, userListSizeAfter);
    }

    @Test
    public void testUpdate() {
        User user = new User(2,"Emma","Eeee","utyg785");
        userService.update(user);
        User result = userService.get(2);
    assertEquals(result.getId(), user.getId());
    assertEquals(result.getName(), user.getName());
    assertEquals(result.getLastName(), user.getLastName());
    assertEquals(result.getPassword(), user.getPassword());
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void testNegativeUpdate(){
        userService.update(null);
    }

    @Test
    public void testDelete() {
        userService.delete(5);
        int userListSizeAfterDelete = userService.findAll().size();
        assertEquals(4, userListSizeAfterDelete);
    }

    @Test
    public void testGetById() {
        User user = userService.get(1);
        assertNotNull(user);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testNegativeGetByNonExistingId() {
        User user = userService.get(8);
        assertNotNull(user);
    }

    @Test
    public void testFindAll() {
        List<User> userList = userService.findAll();
        assertEquals(userList.size(), 5);
    }

    @Test
    public void testGetUsersByName() {
        final String NAME = "Bim";
        List<User> users = userService.getUsersByName(NAME);
        for (User user:users) {
            String userName = user.getName();
            assertEquals(userName, NAME);
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        DBTemplate dbTemplate = mockDB();
        UserDao userDao = new UserDaoImpl(dbTemplate);
        userService = new UserServiceImpl(userDao);
        }

    private DBTemplate mockDB() {
        DBTemplate dbTemplate = DBTemplate.getInstance();
        dbTemplate.getUsers().clear();
        User user1 = new User(1, "Boby", "Bbbb", "1210345u");
        User user2 = new User(2, "Bil", "Llll", "687o87ol");
        User user3 = new User(3, "Jim", "Gggg", "78dfr5t2");
        User user4 = new User(4, "Bim", "Oooo", "h24gfh02");
        User user5 = new User(5, "Kler", "Cccc", "1871rg798");
        dbTemplate.getUsers().addAll(Arrays.asList(user1, user2, user3, user4, user5));
        return dbTemplate;
    }
}