package com.mka.service;

import java.util.List;
import java.util.UUID;

import com.mka.models.PingResult;

public interface PingService {

	public PingResult storePing(UUID id, String ip, String message);
	public List<PingResult> getPings();
	public List<PingResult> getClientPings(UUID id);
	
	
}
