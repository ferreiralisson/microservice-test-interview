package com.alissonferreira.serviceclient.service;

import com.alissonferreira.serviceclient.model.Client;

public interface IClientService {
	
	Client addClient(Client client);
	Client findByName(String name);
	Client findById(Long id);
	void removeClient(Long id);
	Client updateClient(Long id, Client client);
	
}
