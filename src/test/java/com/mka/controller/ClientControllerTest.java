package com.mka.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasKey;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mka.entity.ClientType;
import com.mka.models.ClientModel;
import com.mka.models.CreateClientModel;
import com.mka.service.ClientService;

public class ClientControllerTest /*extends AbstractApiTest*/ {

	protected MockMvc mockMvc;
	protected ObjectMapper objectMapper;
	@InjectMocks
	private ClientController clientController;
	
	@Mock
	private ClientService clientService;
	
	@Before
	public void before() {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(clientController).build();
		objectMapper = new ObjectMapper();
		
	}
	
	@Test
	public void testCreateClient() throws Exception{
		
		CreateClientModel model = new CreateClientModel().setClientType(ClientType.PING).setDescription("Test ping client");
		
		when(clientService.createClient(any(CreateClientModel.class)))
		.thenReturn(new ClientModel());
				
		
		mockMvc.perform(post("/api/v1/client")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(model)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasKey("clientId")))
				.andExpect(jsonPath("$", hasKey("clientType")))
				.andExpect(jsonPath("$", hasKey("description")));
		
	}
	
	@Test
	public void testGetClient() throws Exception{
		when(clientService.getClient(any(UUID.class)))
		.thenReturn(new ClientModel());
		
		mockMvc.perform(get("/api/v1/client/29f3c684-7629-472e-aab1-305e662aaae0")
		.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", hasKey("clientId")))
		.andExpect(jsonPath("$", hasKey("clientType")))
		.andExpect(jsonPath("$", hasKey("description")));
		
		
	}
	
	@Test
	public void testGetClients() throws Exception{
		when(clientService.getClients())
		.thenReturn(getClientModelList());
		
		mockMvc.perform(get("/api/v1/clients")
		.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0]", hasKey("clientId")))
		.andExpect(jsonPath("$[0]", hasKey("clientType")))
		.andExpect(jsonPath("$[0]", hasKey("description")));
		
		
	}
	
	private List<ClientModel> getClientModelList(){
		List<ClientModel> clients = new ArrayList<>();
		
		clients.add(new ClientModel());
		return clients;
	}
	
	
	
}
