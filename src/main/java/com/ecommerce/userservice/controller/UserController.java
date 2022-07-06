package com.ecommerce.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.userservice.common.ApiResponse;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.model.User;
import com.ecommerce.userservice.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> registerUser(@RequestBody User user){
		try {
			if(userService.existUserName(user.getUserName())) {
				throw new IllegalArgumentException("Error: user name exist!!" );
			}
			if(userService.existEmailId(user.getEmailId())) {
				throw new IllegalArgumentException("Error: emailId exist!!" );
			}
			return new ResponseEntity<>(new ApiResponse(true,userService.signUp(user)),HttpStatus.CREATED);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUserDetail(@PathVariable("userId") String id){
		try {
			if(!userService.readUserById(id)) {
				throw new IllegalArgumentException("user not found!!" );
			}
			return new ResponseEntity<User>(userService.userDetails(id).get(),HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("user/{userId}")
	public ResponseEntity<ApiResponse> editUser(@PathVariable("userId") String id,@RequestBody UserDto userDto){
	try {
		if(!userService.readUserById(id)) {
			throw new IllegalArgumentException("user not found" );
		}
		return new ResponseEntity<>(new ApiResponse(true,userService.editUserDetails(id, userDto)),HttpStatus.OK);
	}catch(IllegalArgumentException e) {
		return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.NOT_FOUND);
	}
	}
	@DeleteMapping("user/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") String id){
	try {
		if(!userService.readUserById(id)) {
			throw new IllegalArgumentException("user not found" );
		}
		userService.deleteUserDetails(id);
		return new ResponseEntity<>(new ApiResponse(true,"user is deleted successfully!!"),HttpStatus.OK);
	}catch(IllegalArgumentException e) {
		return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.NOT_FOUND);
	}
	}
	@GetMapping("/user")
	public ResponseEntity<ApiResponse> getAllUserDetail(){
		try {
			return new ResponseEntity<>(new ApiResponse(true,userService.getAllUserDetails()),HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.NOT_FOUND);
		}
	}
}
