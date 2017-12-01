package com.mka.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mka.entity.PingRow;
import com.mka.mapping.PingResultMapper;
import com.mka.models.PingResult;
import com.mka.repository.ClientRepository;
import com.mka.repository.PingRepository;
import com.mka.service.PingService;

@Service
public class IotPingService implements PingService {

	private final PingRepository pingRepository;
	private final ClientRepository clientRepository;
	private final PingResultMapper pingResultMapper;
	
	public IotPingService(final PingRepository pingRepository, final PingResultMapper pingResultMapper
			, final ClientRepository clientRepository) {
		this.pingRepository = pingRepository;
		this.pingResultMapper = pingResultMapper;
		this.clientRepository = clientRepository;
	}
	
	@Override
	public PingResult storePing(UUID id, String ip, String message) {
		checkClientId(id);
		PingResult result = new PingResult()
		.setIpAddress(ip)
		.setMessage(message).setClientId(id);
	
		PingRow row = pingRepository.save(pingResultMapper.create(result));
		return pingResultMapper.createResult(row);
	}

	@Override
	public List<PingResult> getPings() {
		
		List<PingResult> results = new ArrayList<>(); 
		
		pingRepository.findAll().forEach(pingRow -> {
			
				results.add(pingResultMapper.createResult(pingRow));
			}
		);
		return results;
	}
	
	@Override
	public List<PingResult> getClientPings(UUID id) {
		List<PingResult> results = new ArrayList<>(); 
		pingRepository.findByClientId(id).forEach(pingRow -> {
			results.add(pingResultMapper.createResult(pingRow));
		});
		return results;
	}
	
	private void checkClientId(UUID id) {
		 Optional.ofNullable(clientRepository.findByClientId(id)).
				orElseThrow( () -> new RuntimeException("Client id not found. Create client and get id before ping request..."));
	}

	

}
