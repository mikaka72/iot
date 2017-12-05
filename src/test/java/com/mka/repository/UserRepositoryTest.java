package com.mka.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.mka.TestConfig;
import com.mka.entity.User;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestConfig.class)
@TestPropertySource("classpath:test.properties")
@Slf4j
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void crudTestUserRepository(){
		User user = new User()
				.setEmail("john.doe@mail.net")
				.setForeName("John")
				.setSurName("Doe")
				.setUsername("john.doe@mail.net")
				.setPassword("PassWord");
										
		User savedUser = userRepository.save(user);
		assertEquals("john.doe@mail.net", savedUser.getEmail());
		assertEquals("john.doe@mail.net", savedUser.getUsername());
		assertEquals("John", savedUser.getForeName());
		assertEquals("Doe", savedUser.getSurName());
		assertNotNull(savedUser.getId());
		log.info(savedUser.getId());
		
		User foundUser = userRepository.findByUsername("john.doe@mail.net");
		
		assertEquals("john.doe@mail.net", foundUser.getEmail());
		assertEquals("john.doe@mail.net", foundUser.getUsername());
		assertEquals("John", foundUser.getForeName());
		assertEquals("Doe", foundUser.getSurName());
	
		
		
	}
	
}
