package com.mka.service;

import java.util.List;

import com.mka.models.PingResult;

public interface PingService {

	public PingResult storePing(String ip, String message);
	public List<PingResult> getPings();
	
}
