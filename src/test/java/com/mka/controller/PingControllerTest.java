package com.mka.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasKey;
import org.springframework.http.MediaType;

import com.mka.models.PingResult;
import com.mka.service.PingService;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.time.LocalDateTime;

public class PingControllerTest extends AbstactControllerTest {

	@InjectMocks
	private PingController pingController;
	
	@Mock
	private PingService pingService;
	
	@Before
	public void before() {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(pingController).build();
		
	}
	
	@Test
	public void testPing() throws Exception{
		//when(this.schemaDao.createNewSchemaEntry(any(PackSchema.class))).thenReturn(SchemaMocks.getPackCloneSchemaMock());
		 when(pingService.storePing(any(String.class), any(String.class))).thenReturn(new PingResult()
					.setIpAddress("1.1.1.1")
					.setMessage("testing")
					.setTime(LocalDateTime.now()));
		
		mockMvc.perform(get("/ping/testing"))
		.andExpect(status().isOk());
		/*
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", hasKey("ipAddress")))
		.andExpect(jsonPath("$", hasKey("message")))
		.andExpect(jsonPath("$", hasKey("time")));*/
				
	}
	
	
}
