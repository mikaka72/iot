package com.mka.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mka.models.ClientModel;
import com.mka.models.CreateClientModel;
import com.mka.service.ClientService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@PostMapping("/client")
	@ApiOperation(value = "Creates new client with given type and returns the UUID", response = ClientModel.class)
	public ClientModel createClient( @RequestBody final CreateClientModel clientModel) {
		
		return clientService.createClient(clientModel);
		
    }
	
	@GetMapping("/client/{id}")
	@ApiOperation(value = "get Client with id")
	public ClientModel getClient(@PathVariable final UUID id) {
		
		return clientService.getClient(id);
		
    }
	
	
	@GetMapping("/clients")
	@ApiOperation(value = "get all Clients ")
	public List<ClientModel> getClients() {
		
		return clientService.getClients();
		
    }
	
	@DeleteMapping("/client/{id}")
	public long deleteClient(@PathVariable final UUID id) {
		
		return clientService.deleteClient(id);
		
	}
}
