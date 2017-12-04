package com.mka.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.mka.AppConfig;
import com.mka.TestConfig;
import com.mka.controller.ClientController;
import com.mka.entity.ClientType;
import com.mka.models.ClientModel;
import com.mka.models.CreateClientModel;



@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestConfig.class)
@TestPropertySource("classpath:test.properties")
public class ClientIntegrationTest {
	
	@Autowired
	private ClientController clientController;
	
	@Test
	public void testClientCrud() {
		
		// create client
		
		CreateClientModel model = new CreateClientModel().setClientType(ClientType.PING).setDescription("Integration test client");
		
		ClientModel clientModel = clientController.createClient(model);
		
		assertEquals(ClientType.PING, clientModel.getClientType());
		assertNotNull(clientModel.getClientId());
		assertEquals("Integration test client", clientModel.getDescription());
		
		// check that it can be found 
		UUID createdClientUUID = clientModel.getClientId();
		
		clientModel = clientController.getClient(clientModel.getClientId());
		
		assertEquals(ClientType.PING, clientModel.getClientType());
		assertNotNull(clientModel.getClientId());
		assertEquals("Integration test client", clientModel.getDescription());
		
		// Check that all clients return one 
		
		assertEquals(1, clientController.getClients().size());
		
		clientController.deleteClient(clientModel.getClientId());
		 
		assertEquals(0, clientController.getClients().size());
		
		try {
			ClientModel deleted = clientController.getClient(createdClientUUID);
			assertTrue(deleted == null);
		} catch(RuntimeException e) {
			// this is what currently happens..
		}
		
		
	}
	
	

}
