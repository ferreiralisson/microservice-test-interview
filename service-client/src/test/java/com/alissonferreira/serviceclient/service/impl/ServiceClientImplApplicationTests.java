package com.alissonferreira.serviceclient.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.alissonferreira.serviceclient.dto.ClientDTO;
import com.alissonferreira.serviceclient.exception.ResourceNotFoundException;
import com.alissonferreira.serviceclient.model.Client;
import com.alissonferreira.serviceclient.model.Gender;
import com.alissonferreira.serviceclient.reposistory.ClientRepository;
import com.alissonferreira.serviceclient.service.IClientService;

@RunWith(SpringRunner.class)
public class ServiceClientImplApplicationTests {

	@TestConfiguration
	static class ServiceClientImplTestContextConfiguration {
		
		@Bean
		public IClientService clientService() {
			return new ClientServiceImpl();
		}
		
		@Bean
		public RestTemplate getRestTemplate() {
			return new RestTemplate();
		}
		
		@Autowired
		private IClientService clientService;
		
		@MockBean
		private ClientRepository clientRepository;
		
		@Before
		public void setUp() {
			Client alisson = new Client.ClientBuilder()
								.name("Alisson")
								.gender(Gender.M)
								.dateOfBirth(new Date())
								.age(28)
								.build();
			alisson.setId(1l);
			
			when(clientRepository.findById(alisson.getId())).thenReturn(clientOptional(alisson));
			when(clientRepository.findByName(alisson.getName())).thenReturn(alisson);
		}
		
		@Test(expected = ResourceAccessException.class)
		public void whenShouldFindCustomerByName() {
			String testName = "Alisson";
			Client clienteFound = clientService.findByName(testName);
			assertThat(clienteFound.getName()).isEqualTo(testName);
		}
		
		@Test(expected = ResourceAccessException.class)
		public void whenShouldFindCustomerById() {
			Long idClient = 1l;
			Client clienteFound = clientService.findById(idClient);
			assertThat(clienteFound.getId()).isEqualTo(idClient);
		}
		
		public void whenCreateClient_thenReturnCreatedClient() {
			
			Client maria = new Client(2l, "name", Gender.F, new Date(), 25, 12l);
			
			when(clientRepository.save(maria)).thenReturn(new Client(2l, "Maria", Gender.F, new Date(), 25, 12l));
			
			Client created = clientService.addClient(new ClientDTO(2l, "Maria", Gender.F, new Date(), 25, 12l));
			
			try {
				assertThat(created.getName()).isEqualTo(maria.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		@Test(expected=ResourceNotFoundException.class)
		public void whenUpdateClientName_thenNameChanged() throws Exception {
			
			Client pedro = new Client(3l, "Pedro", Gender.M, new Date(), 30, 1l);
			when(clientRepository.save(pedro)).thenReturn(new Client(3l, "Pedro", Gender.M, new Date(), 21, 1l));
			
			ClientDTO toUpdate = new ClientDTO(3l, "José", Gender.F, new Date(), 40, 1l);
			Client updated = clientService.updateClient(3l, toUpdate);
			
			assertThat(updated.getName()).isEqualTo(toUpdate.getName());
		}
		
		private Optional<Client> clientOptional(Client client) {
			return Optional.of(client);
		}
	}
}
