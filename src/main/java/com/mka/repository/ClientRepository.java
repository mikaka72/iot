package com.mka.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mka.entity.Client;

public interface ClientRepository extends MongoRepository<Client, String>{

	Client findByClientId(UUID id);
	long deleteByClientId(UUID id);
	
	
}
