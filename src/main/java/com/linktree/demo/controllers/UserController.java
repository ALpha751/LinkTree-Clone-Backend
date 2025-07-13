package com.linktree.demo.controllers;

import com.linktree.demo.models.UserModel;
import com.linktree.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // Marks this class as a Spring REST Controller
@RequestMapping("/users") // Base path for all endpoints in this controller
public class UserController {

    private final UserService userService; // Declare the UserService

    // Constructor injection for UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     * Maps to POST /users
     *
     * @param userModel The UserModel object sent in the request body.
     * @return A ResponseEntity containing the created UserModel and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        UserModel newUser = userService.addUser(userModel); // Calls the addUser method from UserService
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * Retrieves a user by their ID.
     * Maps to GET /users/{id}
     *
     * @param id The UUID of the user to retrieve.
     * @return A ResponseEntity containing the UserModel if found, or HTTP status 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable UUID id) {
        // This method would require a findById method in your UserService
        // For example:
        // return userService.findUserById(id)
        //         .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        //         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Placeholder
    }

    /**
     * Retrieves all users.
     * Maps to GET /users
     *
     * @return A ResponseEntity containing a list of all UserModels and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        // This method would require a findAll method in your UserService
        // For example:
        // List<UserModel> users = userService.findAllUsers();
        // return new ResponseEntity<>(users, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK); // Placeholder
    }

    // You can add other methods like PUT for update, DELETE for delete, etc.
}
