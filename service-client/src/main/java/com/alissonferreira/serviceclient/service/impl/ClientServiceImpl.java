package com.alissonferreira.serviceclient.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alissonferreira.serviceclient.exception.ResourceNotFoundException;
import com.alissonferreira.serviceclient.model.City;
import com.alissonferreira.serviceclient.model.Client;
import com.alissonferreira.serviceclient.reposistory.ClientRepository;
import com.alissonferreira.serviceclient.service.IClientService;
import com.alissonferreira.serviceclient.utilities.Constantes;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	@Transactional
	public Client addClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client findByName(String name) {
		Client client = clientRepository.findByName(name);
		getRestTamplateCityService(client);
		return client;
	}

	@Override
	public Client findById(Long id) {
		Client client = clientRepository.findById(id).orElse(null);
		getRestTamplateCityService(client);
		return client;
	}

	@Override
	public void removeClient(Long id) {
		clientRepository.delete(clientFound(id));
	}

	@Override
	public Client updateClient(Long id, Client client) {
		Client clientBeforeUpdate = findById(id);
		
		if (clientBeforeUpdate == null) {
			throw new ResourceNotFoundException("Resource [Client] not Found");
		}
		
		BeanUtils.copyProperties(client, clientBeforeUpdate);
		
		return clientRepository.save(clientBeforeUpdate);
	}

	private Client clientFound(Long id) {
		Client client = findById(id);
		if (client == null) {
			throw new ResourceNotFoundException("Client not found");
		}
		return client;
	}


	private void getRestTamplateCityService(Client client) {
		if (client != null) {
			RestTemplate restTemplate = new RestTemplate();
			client.setCity(restTemplate.getForObject(Constantes.BASE_URL_MICROSERVICE_CITY + client.getCityId(), City.class));
		}
	}

}
