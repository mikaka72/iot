package com.mka.integration;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.mka.TestConfig;
import com.mka.controller.UserController;
import com.mka.entity.User;
import com.mka.models.CreateUserModel;
import com.mka.models.UserModel;
import com.mka.repository.UserRepository;


public class UserIntegrationTest extends AbstractIntegrationTest  {

	@Autowired
	private UserController userController;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Test
	public void testUserCrud() {
		
		CreateUserModel createUserModel = new CreateUserModel()
						.setEmail("john.doe@mailserver.net")
						.setForeName("Jonhny")
						.setSurName("Doe")
						.setPassword("password")
						.setUsername("john.doe@mailserver.net");
		
		UserModel createdUser = userController.createUser(createUserModel);
		assertEquals("john.doe@mailserver.net", createdUser.getEmail());
		assertEquals("Jonhny", createdUser.getForeName());
		assertEquals("Doe", createdUser.getSurName());
		assertEquals("john.doe@mailserver.net", createdUser.getUsername());
		assertNotNull(createdUser.getId());		
		assertEquals(Long.valueOf(0), createdUser.getVersion());
		
		UserModel foundUser = userController.getUser(createdUser.getUsername());
		assertEquals("john.doe@mailserver.net", foundUser.getEmail());
		assertEquals("Jonhny", foundUser.getForeName());
		assertEquals("Doe", foundUser.getSurName());
		assertEquals("john.doe@mailserver.net", foundUser.getUsername());
		assertNotNull(foundUser.getId());	
		
		foundUser.setEmail("johnnyboy@mailserver.net");
		foundUser.setForeName("Jon");
		foundUser.setSurName("Doup");
		foundUser.setUsername("johnnyboy@mailserver.net");
		
		
		UserModel updatedUser = userController.updateUser(foundUser);
		assertEquals("johnnyboy@mailserver.net", updatedUser.getEmail());
		assertEquals("Jon", updatedUser.getForeName());
		assertEquals("Doup", updatedUser.getSurName());
		assertEquals("johnnyboy@mailserver.net", updatedUser.getUsername());
		assertNotNull(updatedUser.getId());	
		assertEquals(Long.valueOf(1), updatedUser.getVersion());
		
		try {
			UserModel updatedUser2 = userController.updateUser(foundUser);
			fail("Expected OptimisticLockingFailureException..");
		} catch(OptimisticLockingFailureException e) {
			// ok..
		}
		List<User> users = userRepository.findAll();
		userController.deleteUser(updatedUser.getId());
		
		try {
			userController.getUser(updatedUser.getUsername());
			fail("Expected RuntimeException..");
		} catch(RuntimeException e) {
			assertThat(e.getMessage(), is("User does not exist"));
		}
		
		// Just check that there are no extra users created into the db during test..
		 users = userRepository.findAll();
		assertEquals(0, userRepository.findAll().size());
		
	}
	
}
