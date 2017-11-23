package com.mka.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mka.entity.PingRow;

public interface PingRepository extends MongoRepository<PingRow, String>{

}
