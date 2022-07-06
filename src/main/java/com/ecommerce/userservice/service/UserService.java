package com.ecommerce.userservice.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.model.User;

public interface UserService {
	public User signUp(User user);
	public boolean existUserName(String userName);
	public boolean existEmailId(String emailId);
	public boolean readUserById(String id);
	public User editUserDetails(String id,UserDto userDto);
	public void deleteUserDetails(String id);
	public Optional<User> userDetails(String id);
	public List<User> getAllUserDetails();
}
