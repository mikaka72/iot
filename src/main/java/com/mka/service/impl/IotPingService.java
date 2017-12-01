package com.mka.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mka.entity.PingRow;
import com.mka.mapping.PingResultMapper;
import com.mka.models.PingResult;
import com.mka.repository.PingRepository;
import com.mka.service.PingService;

@Component
public class IotPingService implements PingService {

	private final PingRepository pingRepository;
	private final PingResultMapper pingResultMapper;
	
	public IotPingService(final PingRepository pingRepository, final PingResultMapper pingResultMapper) {
		this.pingRepository = pingRepository;
		this.pingResultMapper = pingResultMapper;
	}
	
	@Override
	public PingResult storePing(String ip, String message) {
		PingResult result = new PingResult()
		.setIpAddress(ip)
		.setMessage(message);
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
	
	

}
