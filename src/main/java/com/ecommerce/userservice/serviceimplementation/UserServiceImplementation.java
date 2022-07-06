package com.ecommerce.userservice.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.model.User;
import com.ecommerce.userservice.repository.UserRepo;
import com.ecommerce.userservice.service.UserService;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepo userRepo;
	public User signUp(User user) {
		User userData=new User();
		userData.setAddress(user.getAddress());
		userData.setFirstName(user.getFirstName());
		userData.setLastName(user.getLastName());
		userData.setUserName(user.getUserName());
		userData.setEmailId(user.getEmailId());
		userData.setPassword(user.getPassword());
		return userRepo.save(user);
	}
	public boolean existUserName(String userName) {
		return userRepo.existByUserName(userName).isPresent();
	}
	public boolean existEmailId(String emailId) {
		return userRepo.existByEmailId(emailId).isPresent();
	}
	public boolean readUserById(String id) {
		return userRepo.findById(id).isPresent();
	}
	public User editUserDetails(String id,UserDto userDto) {
		Optional<User> userData=userRepo.findById(id);
		User user =userData.get();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setAddress(userDto.getAddress());
		return userRepo.save(user);
	}
	public void deleteUserDetails(String id) {
		userRepo.deleteById(id);
	}
	public Optional<User> userDetails(String id){
		return userRepo.findById(id);
	}
	public List<User> getAllUserDetails(){
		return userRepo.findAll();
	}
}
