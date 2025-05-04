package com.mst.AlertHub.controller;

import com.mst.AlertHub.beans.User;
import com.mst.AlertHub.exceptions.UserExceptions;
import com.mst.AlertHub.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {
	
	
	 @Autowired
	 private UserServiceImpl userService;

	    @PostMapping("/create_user")
	    public ResponseEntity<User> createUser(@RequestBody User user) {
	        User createdUser = userService.createUser(user);
	        return ResponseEntity.ok(createdUser);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable int id) {
	        User user = userService.getUserById(id);
	        if (user == null) {
	            return ResponseEntity.notFound().build(); 
	        }
	        return ResponseEntity.ok(user);
	    }

	    @PutMapping("/update/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
	        try {
	            User updatedUser = userService.updateUser(id, user);
	            return ResponseEntity.ok(updatedUser);
	        } catch (UserExceptions.UserNotFoundException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
	        boolean isDeleted = userService.deleteUser(id);
	        if (!isDeleted) {
	            return ResponseEntity.notFound().build(); 
	        }
	        return ResponseEntity.noContent().build(); 
	    }

	    @GetMapping("/all_users")
	    public ResponseEntity<List<User>> getAllUsers() {
	        List<User> users = userService.getAllUser();
	        return ResponseEntity.ok(users);
	    }
	    
	    
	    @PostMapping("/{userId}/assign-role")
	    public ResponseEntity<User> assignRoleToUser(@RequestParam int userId, @RequestParam int roleId) {
	        User updatedUser = userService.assignRoleToUser(userId, roleId);
	        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	    } 
	
	

}
