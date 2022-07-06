package com.ecommerce.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.model.Address;
import com.ecommerce.userservice.model.User;
import com.ecommerce.userservice.repository.UserRepo;
import com.ecommerce.userservice.serviceimplementation.UserServiceImplementation;

@SpringBootTest
class UserServiceApplicationTests {

	@Mock
	UserRepo userRepo;
	@InjectMocks
 UserServiceImplementation userServiceImplementation;
	
	
	@Test
	@Order(1)
	 void test_getAllUser() {
		List<User> user=new ArrayList<User>();
		user.add(new User("1223ndsjd","abcd","efg","abcdefg","abcdefg@gmail.com","abcde",
					new Address("zzz","zzz","zzz","zzz",680683,812966354)));
		user.add(new User("123dhfjdfj","samuvel","joseph","samuvel joseph","samuveljoseph@gmail.com","samuvel",
				new Address("abcd house","kallettumkara","thrissur","kerala",680683,812966354)));
		when(userRepo.findAll()).thenReturn(user);//mocking 
		assertEquals(2,userServiceImplementation.getAllUserDetails().size());
	}
	@Test
	@Order(2)
	 void signUpTest() {
		User user=new User("123dhfjdfj","samuvel","joseph","samuvel joseph","samuveljoseph@gmail.com","samuvel",
				new Address("abcd house","kallettumkara","thrissur","kerala",680683,812966354));
		Mockito.when(userRepo.save(user)).thenReturn(user);
		assertEquals(user, userServiceImplementation.signUp(user));
	}
	@Test
	@Order(3)
	 void userDetailsTest () {
		User user =new User("1223ndsjd","abcd","efg","abcdefg","abcdefg@gmail.com","abcde",new Address("zzz","zzz","zzz","zzz",680683,812966354));
		//Optional<User> userData=userRepo.findById(user.getId());
		Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.ofNullable(user));
		assertEquals(userServiceImplementation.userDetails("1223ndsjd").get(),user);
	}
	@Test
	@Order(4)
	 void deleteUserDetailsTest () {
		User user =new User("1223ndsjd","abcd","efg","abcdefg","abcdefg@gmail.com","abcde",new Address("zzz","zzz","zzz","zzz",680683,812966354));
		userServiceImplementation.deleteUserDetails(user.getId());
		verify(userRepo,times(1)).deleteById(user.getId());
	}
	@Test
	@Order(5)
	 void editUserDetailsTest() {
		User user=new User("123dhfjdfj","samuvel","joseph","samuvel joseph","samuveljoseph@gmail.com","samuvel",
				new Address("abcd house","kallettumkara","thrissur","kerala",680683,812966354));
		UserDto userDto=new UserDto("samuvel","joseph",new Address("abcd house","kallettumkara","thrissur","kerala",680683,812966354));
		Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.ofNullable(user));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setAddress(userDto.getAddress());
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user, userServiceImplementation.editUserDetails(user.getId(), userDto));
	}
	@Test
	@Order(6)
	 void existUserNameTest () {
		User user =new User();
		Address address=new Address();
		address.setAddressLine1("abcd house");
		address.setAddressLine2("kallettumkara");
		address.setCity("thrissur");
		address.setState("kerala");
		address.setPincode(680683);
		address.setMobileNumber(812966354);
		user.setId("123dhfjdfj");
		user.setFirstName("samuvel");
		user.setLastName("joseph");
		user.setUserName("samuvel joseph");
		user.setEmailId("samuveljoseph@gmail.com");
		user.setPassword("samuvel");
		user.setAddress(address);
		//Optional<User> userData=userRepo.findById(user.getId());
		Mockito.when(userRepo.existByUserName("abcdefg")).thenReturn(Optional.ofNullable(user));
		assertEquals(true,userServiceImplementation.existUserName("abcdefg"));
	}
	@Test
	@Order(7)
	 void existEmailIdTest () {
		//Optional<User> userData=userRepo.findById(user.getId());
		Address address=new Address();
		address.setAddressLine1("abcd house");
		address.setAddressLine2("kallettumkara");
		address.setCity("thrissur");
		address.setState("kerala");
		address.setPincode(680683);
		address.setMobileNumber(812966354);
		User user =new User("1223ndsjd","abcd","efg","abcdefg","abcdefg@gmail.com","abcde",new Address(address.getAddressLine1(),address.getAddressLine2(),address.getCity(),address.getState(),address.getPincode(),address.getMobileNumber()));
		Mockito.when(userRepo.existByEmailId("abcdefg@gmail.com")).thenReturn(Optional.ofNullable(user));
		assertEquals(true,userServiceImplementation.existEmailId("abcdefg@gmail.com"));
	}
	@Test
	@Order(8)
	 void readUserByIdTest () {
		User user =new User("1223ndsjd","abcd","efg","abcdefg","abcdefg@gmail.com","abcde",new Address("zzz","zzz","zzz","zzz",680683,812966354));
		//Optional<User> userData=userRepo.findById(user.getId());
		Mockito.when(userRepo.findById("1223ndsjd")).thenReturn(Optional.ofNullable(user));
		assertEquals(true,userServiceImplementation.readUserById("1223ndsjd"));
	}

}
