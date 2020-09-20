package com.alissonferreira.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.alissonferreira.enums.Gender;
import com.alissonferreira.model.Client;
import com.alissonferreira.service.IClientService;
import com.alissonferreira.util.Constantes;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerApplicationTests {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IClientService clientService;
	
	@Test
	public void givenClient_whenSearchClientByName_thenReturnJson() throws Exception {
		
		Client joao = new Client(1l, "João", Gender.M, new Date(), 30, 1l, null);
		
		given(clientService.findByName("João")).willReturn(joao);
		
		mockMvc.perform(get(Constantes.BASE_URL_REST_CLIENT)
				.param("name", "João")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("João")));
	}
	
	@Test
	public void givenClient_whenSearchClientById_thenReturnJson() throws Exception {
		
		Client joao = new Client(1l, "João", Gender.M, new Date(), 30, 1l, null);
		
		given(clientService.findById(1l)).willReturn(joao);
		
		mockMvc.perform(get(Constantes.BASE_URL_REST_CLIENT + "/1")
				.param("name", "João")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void whenDeleteClient_thenReturnDeletedClientIsOk() throws Exception {
		mockMvc.perform(delete(Constantes.BASE_URL_REST_CLIENT + "/1")).andExpect(status().isOk());
	}
	
}
