package com.mka.service;

import com.mka.models.CreateUserModel;
import com.mka.models.UserModel;

public interface UserService {
	public UserModel createUser(CreateUserModel createUserModel);
	public UserModel updateUser(UserModel userModel);
	public void deleteUser(String userId);
	public UserModel getUser(String name);
	
}
