package com.mka.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mka.entity.Client;
import com.mka.models.ClientModel;
import com.mka.models.CreateClientModel;
import com.mka.repository.ClientRepository;
import com.mka.service.ClientService;

@Service
public class IotClientService implements ClientService {

	private final ModelMapper modelMapper;
	
	private final ClientRepository clientRepository;
	
	public IotClientService(final ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
		this.modelMapper = new ModelMapper();
	}
	
	@Override
	public ClientModel createClient(CreateClientModel clientModel) {
		Client client = modelMapper.map(clientModel,Client.class);
		client.setClientId(Client.generateId());
		return modelMapper.map(clientRepository.save(client), ClientModel.class);
	}

	@Override
	public ClientModel getClient(UUID id) {
		Client client = clientRepository.findByClientId(id);
		return modelMapper.map(client, ClientModel.class);
	}

	@Override
	public List<ClientModel> getClients() {
		List<ClientModel> clients = new ArrayList<>();
		clientRepository.findAll().forEach(client -> {
			clients.add(modelMapper.map(client, ClientModel.class));
		});
		
		return clients;
	}

}
