package com.mka.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mka.entity.PingRow;

public interface PingRepository extends MongoRepository<PingRow, String>{
	List<PingRow> findByClientId(UUID id);
}
