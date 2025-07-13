package com.linktree.demo.services;

import com.linktree.demo.models.UserModel;
import com.linktree.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional; // Changed from javax.transaction.Transactional

@Service // Marks this class as a Spring Service component
public class UserService {

    private final UserRepository userRepository; // Declare the UserRepository

    @Autowired // Automatically injects an instance of UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adds a new user to the database.
     *
     * @param userModel The UserModel object containing the user's details.
     * @return The saved UserModel object, including the generated ID.
     */
    @Transactional // Ensures the entire method runs as a single database transaction
    public UserModel addUser(UserModel userModel) {
        // You might add validation logic here before saving, e.g.,
        // if (userModel.getEmail() == null || !isValidEmail(userModel.getEmail())) {
        //     throw new IllegalArgumentException("Invalid email");
        // }

        // Save the user model to the database using the repository
        return userRepository.save(userModel);
    }

    // You can add other methods here like findUserById, updateUser, deleteUser, etc.
}
