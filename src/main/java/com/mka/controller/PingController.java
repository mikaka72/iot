package com.mka.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mka.models.PingResult;
import com.mka.service.PingService;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class PingController {
	
	@Autowired
    private PingService pingService;

	
	@GetMapping("/ping/{id}/{message}")
	@ApiOperation(value = "Ping with id and message that is stored with ip and timestamp, returns Ping result.", response = PingResult.class)
	PingResult ping(@PathVariable final UUID id, final String message, HttpServletRequest request) {
		log.info("Ping request from: " + request.getRemoteAddr() + " with message " + message);
		return pingService.storePing(id, request.getRemoteAddr(), message);
		
    }
	
	@GetMapping("/pings")
	@ApiOperation(value = "get all Ping results")
	List<PingResult> pings() {
		
		return pingService.getPings();
		
    }
	
	@GetMapping("/pings/{id}")
	@ApiOperation(value = "get all Ping results from specific client")
	List<PingResult> getClientPings(@PathVariable final UUID id) {
		
		return pingService.getClientPings(id);
		
    }
	
	
	
}
