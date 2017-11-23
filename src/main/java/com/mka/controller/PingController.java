package com.mka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mka.models.PingResult;
import com.mka.service.PingService;

import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PingController {

	final PingService pingService;
	
	public PingController(final PingService pingService) {
		this.pingService= pingService;
	}
	
	@GetMapping("/ping/{message}")
	@ApiOperation(value = "Ping with message that is stored with ip and timestamp, returns Ping result.", response = PingResult.class)
	PingResult ping(@PathVariable final String message, HttpServletRequest request) {
		log.info("Ping request from: " + request.getRemoteAddr() + " with message " + message);
		return pingService.storePing(request.getRemoteAddr(), message);
		
    }
	
	@GetMapping("/ping")
	@ApiOperation(value = "get all Ping results")
	List<PingResult> pings() {
		
		return pingService.getPings();
		
    }
	
	
}
