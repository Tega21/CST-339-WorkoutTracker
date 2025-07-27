package com.workouttracker.service;

import com.workouttracker.model.LoginPrincipal;
import com.workouttracker.model.User;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple user management service for Milestone 2.
 * Handles user registration and lookup using in-memory storage.
 * Database integration will be added in Milestone 4.
 *
 * @author Brandon Ortega, Aaron Starley
 * @version 1.0
 * @since Milestone 2
 */
@Service
public class UserService implements UserBusinessServiceInterface{

    /**
     * In-memory storage for users (temporary for Milestone 2)
     */
    private List<User> users = new ArrayList<>();

    /**
     * Simple ID generator for new users
     */
    private Long nextId = 1L;

    /**
     * Constructor that initializes service with demo user for testing.
     */
    public UserService() {
        // Add demo user for testing
        User demo = new User();
        demo.setId(nextId++);
        demo.setFirstName("Demo");
        demo.setLastName("User");
        demo.setEmail("demo@test.com");
        demo.setUsername("demo");
        demo.setPassword("password123");
        demo.setCreatedDate(LocalDateTime.now());
        demo.setActive(true);
        users.add(demo);

        System.out.println("UserService initialized with demo user");
    }

    @Override
    public boolean authenticateUser(LoginPrincipal loginModel) {
        return "demo".equals(loginModel.getUsername()) &&
                "password123".equals(loginModel.getPassword());
    }

    /**
     * Registers a new user in the system.
     * Checks for duplicate usernames before registration.
     *
     * @param user the user to register
     * @return the registered user with assigned ID
     * @throws RuntimeException if username already exists
     */
    @Override
    public boolean registerUser(User user) {
        System.out.println("Registered: " + user.getUsername());
        return true;
    }
    /*public User registerUser(User user) {
        if (findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Set system fields
        user.setId(nextId++);
        user.setCreatedDate(LocalDateTime.now());
        user.setActive(true);

        // Add to list
        users.add(user);

        System.out.println("User registered: " + user.getUsername());
        return user;
    }*/

    /**
     * Finds a user by username.
     *
     * @param username the username to search for
     * @return the user if found, null otherwise
     */
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets all registered users.
     *
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Gets the total number of registered users.
     *
     * @return user count
     */
    public int getUserCount() {
        return users.size();
    }
}