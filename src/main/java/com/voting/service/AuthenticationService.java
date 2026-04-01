package com.voting.service;

import com.voting.model.User;
import com.voting.repository.UserRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AuthenticationService {

    private final UserRepository userRepository;
    
    // Store simple logged in session state
    private static User loggedInUser;

    public AuthenticationService() {
        this.userRepository = new UserRepository();
    }

    public boolean register(String name, String studentId, String password, String role) {
        if (userRepository.getUserByStudentId(studentId) != null) {
            return false; // User already exists
        }
        
        String hashedPassword = hashPassword(password);
        User user = new User(0, name, studentId, hashedPassword, role, false, null);
        return userRepository.registerUser(user);
    }

    public User login(String studentId, String password) {
        User user = userRepository.getUserByStudentId(studentId);
        if (user != null && user.getPassword().equals(hashPassword(password))) {
            loggedInUser = user;
            return user;
        }
        return null; // Login failed
    }
    
    public static User getLoggedInUser() {
        return loggedInUser;
    }
    
    public static void logout() {
        loggedInUser = null;
    }
    
    public static void updateCurrentUserHasVoted() {
        if(loggedInUser != null) {
            loggedInUser.setHasVoted(true);
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password; // Fallback, not for production beyond this scope
        }
    }
}
