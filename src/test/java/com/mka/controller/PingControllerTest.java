package com.mka.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mka.AppConfig;
import com.mka.SecurityConfiguration;
import com.mka.service.PingService;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class PingControllerTest {

	@InjectMocks
	private PingController pingController;
	
	@Mock
	private PingService pingService;
	
	protected MockMvc mockMvc;
	
	@Before
	public void before() {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(pingController).build();
		
	}
	
	@Test
	public void testPing() throws Exception{
		
		mockMvc.perform(get("/api/v1/ping/d82370db-4425-4665-848b-24ce23563527/testing"))
		.andExpect(status().isOk());
				
	}
	

	
	
}
