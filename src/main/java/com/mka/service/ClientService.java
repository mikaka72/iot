package com.mka.service;

import java.util.List;
import java.util.UUID;

import com.mka.models.ClientModel;
import com.mka.models.CreateClientModel;

public interface ClientService {

	public ClientModel createClient(CreateClientModel clientModel);
	public ClientModel getClient(UUID id);
	public List<ClientModel> getClients();
	public long deleteClient(UUID id);
	
	
}
