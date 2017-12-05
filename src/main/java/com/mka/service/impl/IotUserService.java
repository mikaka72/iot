package com.mka.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mka.entity.User;
import com.mka.models.CreateUserModel;
import com.mka.models.UserModel;
import com.mka.repository.UserRepository;
import com.mka.service.UserService;

@Service

public class IotUserService implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public IotUserService( final UserRepository userRepository, final BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.modelMapper = new ModelMapper();
	}
	
	@Override
	public UserModel createUser(CreateUserModel createUserModel) {
		createUserModel.setPassword(bCryptPasswordEncoder.encode(createUserModel.getPassword()));
		User user = userRepository.save(modelMapper.map(createUserModel, User.class));
		return modelMapper.map(user, UserModel.class);
	}

	@Override
	public UserModel updateUser(UserModel userModel) {
		
		// Check that user exists so save will not create new one by mistake as this is update
		// Also take the password and set it again so it will not be lost on update..
		String password = Optional.ofNullable(userRepository.findOne(userModel.getId()))
			.orElseThrow( () -> new RuntimeException("User does not exist")).getPassword();
		
		User user =  modelMapper.map(userModel, User.class);
		user.setPassword(password);
		user = userRepository.save(user);
	
		return modelMapper.map(user, UserModel.class);
		
	}

	@Override
	public void deleteUser(String userId) {
		userRepository.delete(userId);
	}

	@Override
	public UserModel getUser(String name) {
		
		Optional.ofNullable(userRepository.findByUsername(name))
		.orElseThrow( () -> new RuntimeException("User does not exist"));
		
		return modelMapper.map(userRepository.findByUsername(name), UserModel.class);
	}

}
