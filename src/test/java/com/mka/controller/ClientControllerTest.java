package com.mka.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import com.mka.entity.ClientType;
import com.mka.models.ClientModel;

public class ClientControllerTest extends AbstractApiTest {

	@InjectMocks
	private ClientController clientController;
	
	@Before
	public void before() {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(clientController).build();
		
	}
	
	@Test
	public void testCreateClient() throws Exception{
		
		ClientModel model = new ClientModel().setClientType(ClientType.PING).setDescription("Test ping client");
		
		mockMvc.perform(post("/client")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(model)))
				.andExpect(status().isOk());
		
	}
	
}
