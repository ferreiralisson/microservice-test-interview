package com.alissonferreira.serviceclient.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alissonferreira.serviceclient.dto.ClientDTO;
import com.alissonferreira.serviceclient.exception.ResourceNotFoundException;
import com.alissonferreira.serviceclient.model.City;
import com.alissonferreira.serviceclient.model.Client;
import com.alissonferreira.serviceclient.reposistory.ClientRepository;
import com.alissonferreira.serviceclient.service.IClientService;
import com.alissonferreira.serviceclient.utilities.Constantes;
import com.alissonferreira.serviceclient.utilities.Util;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private ClientRepository clienteRepository;

	@Override
	@Transactional
	public Client addClient(ClientDTO clientDTO) {
		return clienteRepository.save(convertClientDTOInObject(clientDTO));
	}

	@Override
	public Client findByName(String name) {
		Client client = clienteRepository.findByName(name);
		findClientCity(client);
		return client;
	}

	@Override
	public Client findById(Long id) {
		Client client = clienteRepository.findById(id).orElse(null);
		findClientCity(client);
		return client;
	}

	@Override
	public void removeClient(Long id) {
		clienteRepository.delete(clientFound(id));
	}

	@Override
	public Client updateClient(Long id, ClientDTO clientDTO) {

		BeanUtils.copyProperties(convertClientDTOInObject(clientDTO), clientFound(id));
		return clienteRepository.save(clientFound(id));

	}

	private Client clientFound(Long id) {
		Client client = findById(id);
		if (client == null) {
			throw new ResourceNotFoundException("Client not found");
		}
		return client;
	}

	private Client convertClientDTOInObject(ClientDTO clientDTO) {

		return new Client.ClientBuilder()
				.name(clientDTO.getName())
				.gender(clientDTO.getGender())
				.dateOfBirth(clientDTO.getDateOfBirth())
				.age(clientDTO.getAge())
				.cityId(clientDTO.getCityId())
				.build();
	}
	
	private void findClientCity(Client client) {
		if(client != null) {
			client.setCity((City) Util.getRestTamplate(Constantes.BASE_URL_MICROSERVIE_CITY + client.getCityId(), City.class));
		}
	}

}
