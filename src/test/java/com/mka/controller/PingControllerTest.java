package com.mka.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mka.AppConfig;
import com.mka.SecurityConfiguration;
import com.mka.models.PingResult;
import com.mka.service.PingService;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureWebClient
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        AppConfig.class,
        SecurityConfiguration.class
})
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
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
		//when(this.schemaDao.createNewSchemaEntry(any(PackSchema.class))).thenReturn(SchemaMocks.getPackCloneSchemaMock());
		 when(pingService.storePing(any(String.class), any(String.class))).thenReturn(new PingResult()
					.setIpAddress("1.1.1.1")
					.setMessage("testing")
					.setTime(formatter.format(Date.from(Instant.now()))));
		
		mockMvc.perform(get("/ping/testing"))
		.andExpect(status().isOk());
		/*
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", hasKey("ipAddress")))
		.andExpect(jsonPath("$", hasKey("message")))
		.andExpect(jsonPath("$", hasKey("time")));*/
				
	}
	

	
	
}
