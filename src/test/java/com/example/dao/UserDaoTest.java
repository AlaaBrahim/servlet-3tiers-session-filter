package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.models.User;

public class UserDaoTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private UserDao userDao;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        userDao = new UserDao(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testGetUser() throws Exception {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Test User");
        when(resultSet.getString("email")).thenReturn("test@example.com");
        when(resultSet.getString("password")).thenReturn("password");

        User user = userDao.getUser(1);
        Boolean passwordTest;

        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("Test User", user.getName());
        assertEquals("test@example.com", user.getEmail());

        passwordTest = user.getPassword().startsWith("$2a$10$");
        assertTrue(passwordTest);
    }

    @Test
    public void testGetUserNotFound() throws Exception {
        when(resultSet.next()).thenReturn(false);

        User user = userDao.getUser(1);

        assertNull(user);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(resultSet.next()).thenReturn(true, true, false); // Simulate a result set with two users
        when(resultSet.getInt("id")).thenReturn(1, 2);
        when(resultSet.getString("name")).thenReturn("Test User 1", "Test User 2");
        when(resultSet.getString("email")).thenReturn("test1@example.com",
                "test2@example.com");
        when(resultSet.getString("password")).thenReturn("password1", "password2");

        List<User> users = userDao.getAllUsers();
        Boolean passwordTest;

        assertNotNull(users);
        assertEquals(2, users.size());

        User user1 = users.get(0);
        assertEquals(1, user1.getId());
        assertEquals("Test User 1", user1.getName());
        assertEquals("test1@example.com", user1.getEmail());
        passwordTest = user1.getPassword().startsWith("$2a$10$");
        assertTrue(passwordTest);

        User user2 = users.get(1);
        assertEquals(2, user2.getId());
        assertEquals("Test User 2", user2.getName());
        assertEquals("test2@example.com", user2.getEmail());
        passwordTest = user1.getPassword().startsWith("$2a$10$");
        assertTrue(passwordTest);
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("password");

        userDao.addUser(user);

        verify(preparedStatement, times(1)).execute();
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("Updated User");
        user.setEmail("updated@example.com");
        user.setPassword("updated_password");

        userDao.updateUser(user);

        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteUser() throws Exception {
        userDao.deleteUser(1);

        verify(preparedStatement, times(1)).executeUpdate();
    }
}
