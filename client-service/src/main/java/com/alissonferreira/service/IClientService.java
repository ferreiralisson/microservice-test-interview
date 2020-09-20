package com.alissonferreira.service;

import com.alissonferreira.model.Client;

public interface IClientService {
	
	Client addClient(Client client);
	Client findByName(String name);
	Client findById(Long id);
	void removeClient(Long id);
	Client updateClient(Long id, Client client);
	
}
