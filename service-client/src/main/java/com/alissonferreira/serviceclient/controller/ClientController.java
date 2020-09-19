package com.alissonferreira.serviceclient.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alissonferreira.serviceclient.exception.ResourceNotFoundException;
import com.alissonferreira.serviceclient.model.Client;
import com.alissonferreira.serviceclient.service.IClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private IClientService clientService;

	@PostMapping
	public Client add(@RequestBody Client client, HttpServletResponse response) {

		Client added = clientService.addClient(client);

		if (added != null) {
			response.setStatus(HttpStatus.CREATED.value());
		}

		return added;
	}

	@GetMapping
	public Client findClientByName(@RequestParam(name = "name") String name) {
		try {
			return clientService.findByName(name);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

	}

	@GetMapping(path = "/{id}")
	public Client findClientById(@PathVariable(name = "id") Long id) {
		try {
			return clientService.findById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable(name = "id") Long id) {
		clientService.removeClient(id);
	}

	@PutMapping(value = "/{id}")
	public Client update(@PathVariable Long id, @RequestBody Client client) {

		try {
			return clientService.updateClient(id, client);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

}
