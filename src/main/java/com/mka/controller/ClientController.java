package com.mka.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mka.entity.Client;
import com.mka.models.ClientModel;
import com.mka.models.CreateClientModel;
import com.mka.models.PingResult;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
public class ClientController {

	@PostMapping("/client")
	@ApiOperation(value = "Creates new client with given type and returns the UUID", response = ClientModel.class)
	ClientModel createClient( @RequestBody final CreateClientModel clientModel) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		Client client = modelMapper.map(clientModel,Client.class);
		client.setClientId(Client.generateId());
		
		return modelMapper.map(client, ClientModel.class);
		
    }
	
}
