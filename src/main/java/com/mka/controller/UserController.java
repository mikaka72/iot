package com.mka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mka.models.CreateUserModel;
import com.mka.models.UserModel;
import com.mka.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	@ApiOperation(value = "Creates new user", response = UserModel.class)
	public UserModel createUser( @RequestBody final CreateUserModel createUserModel) {
		
		return userService.createUser(createUserModel);
		
    }
	
	@PutMapping("/user")
	@ApiOperation(value = "Updates existing user ", response = UserModel.class)
	public UserModel updateUser( @RequestBody final UserModel createUserModel) {
		
		return userService.updateUser(createUserModel);
		
    }
	// Regex postfix :.+ is needed so that last.xxx is not considered as file extension by spring
	@GetMapping("/user/{username:.+}")
	@ApiOperation(value = "get user by username")
	public UserModel getUser(@PathVariable final String username) {
		
		return userService.getUser(username);
		
    }
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable final String id) {
		
		userService.deleteUser(id);
		
	}
	
}
