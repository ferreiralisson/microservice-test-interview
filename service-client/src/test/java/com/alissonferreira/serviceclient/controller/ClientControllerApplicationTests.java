package com.alissonferreira.serviceclient.controller;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.alissonferreira.serviceclient.model.Client;
import com.alissonferreira.serviceclient.model.Gender;
import com.alissonferreira.serviceclient.reposistory.ClientRepository;
import com.alissonferreira.serviceclient.service.IClientService;
import com.alissonferreira.serviceclient.utilities.Constantes;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerApplicationTests {
	
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;
    @MockBean
    private ClientRepository clientRepository;
	
	@MockBean
	private IClientService clientService;
	
	@Before
    public void setup() {
		Client client = new Client(1l, "João", Gender.M, new Date(), 30, 1l, null);
        BDDMockito.when(clientRepository.findById(client.getId())).thenReturn(createClientOptional(client));
    }
	
	@Test
	public void givenClient_whenSearchClientByName_thenReturnStatusCode200() throws Exception {
		ResponseEntity<Client> response = restTemplate.getForEntity(Constantes.BASE_URL_REST_CLIENT + "?name=Alisson", Client.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void givenClient_whenSearchClientById_thenReturnStatusCode200() throws Exception {
		ResponseEntity<Client> response = restTemplate.getForEntity(Constantes.BASE_URL_REST_CLIENT + "/{id}", Client.class, 1L);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void createShouldPersistDataAndReturnStatusCode200() throws Exception {

		Client client = new Client("João", Gender.M, new Date(), 30, 1l, null);
        BDDMockito.when(clientRepository.save(client)).thenReturn(client);
        ResponseEntity<Client> response = restTemplate.postForEntity(Constantes.BASE_URL_REST_CLIENT, client, Client.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}
	
	@Test
	public void updateShouldPersistDataAndReturnStatusCode200() throws Exception {

		Client client = new Client(1l, "João", Gender.M, new Date(), 30, 1l, null);
		BDDMockito.when(clientRepository.save(client)).thenReturn(client);
		
		Map<String, Client> param = new HashMap<String, Client>();
	    param.put("client", client);
	    HttpEntity<Client> requestEntity = new HttpEntity<Client>(client);
	    
		ResponseEntity<String> exchange = restTemplate.exchange(Constantes.BASE_URL_REST_CLIENT + "/{id}", PUT, requestEntity, String.class, 1L);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);

	}
	
	@Test
    public void deleteWhenUserHasAndStudentExistsShouldReturnStatusCode200() {
        BDDMockito.doNothing().when(clientRepository).deleteById(1L);
        ResponseEntity<String> exchange = restTemplate.exchange(Constantes.BASE_URL_REST_CLIENT + "/{id}", DELETE, null, String.class, 1L);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
    }
	
	private Optional<Client> createClientOptional(Client client) {
		return Optional.of(client);
	}
	
	
}
