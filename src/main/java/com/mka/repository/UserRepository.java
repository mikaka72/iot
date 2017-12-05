package com.mka.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mka.entity.User;

public interface UserRepository extends MongoRepository<User, String>{
	User findByUsername(String username);
}
