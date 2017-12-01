package com.mka.mapper;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.mka.controller.AbstractApiTest;
import com.mka.entity.Client;
import com.mka.entity.PingRow;
import com.mka.mapping.PingResultMapper;
import com.mka.models.PingResult;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PingResultMapperTest extends AbstractApiTest {

	@InjectMocks
	public PingResultMapper pingResultMapper;
	

	@Before
	public void before() {
		
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testCreateResult() {
		PingRow row = new PingRow()
				.setClientId(Client.generateId())
				.setId("id").setIpAddress("0.0.0.0")
				.setMessage("message")
				.setTime(System.currentTimeMillis());
		
		PingResult result = pingResultMapper.createResult(row);
		
		assertEquals(row.getClientId(), result.getClientId());
		assertEquals(row.getId(), result.getId());
		assertEquals(row.getMessage(), result.getMessage());
		assertNotNull(result.getTime());
		log.info(result.getTime());
		
		
	}
	
	@Test
	public void testCreate() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
		
		PingResult result = new PingResult()
				.setClientId(Client.generateId())
				.setId("id").setIpAddress("0.0.0.0")
				.setMessage("message");
				
		
		PingRow row = pingResultMapper.create(result);
		
		assertEquals(result.getClientId(), row.getClientId());
		assertEquals(result.getId(), row.getId());
		assertEquals(result.getMessage(), row.getMessage());
		assertNotNull(row.getTime());
		
		
	}
	
}
