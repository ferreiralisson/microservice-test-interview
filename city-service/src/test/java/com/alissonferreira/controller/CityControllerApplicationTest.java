package com.alissonferreira.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.alissonferreira.enums.StatesOfBrazil;
import com.alissonferreira.model.City;
import com.alissonferreira.service.ICityService;
import com.alissonferreira.util.Constantes;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerApplicationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ICityService cityService;
	
	@Test
	public void givenCity_whenSearchCityByName_thenReturnJson() throws Exception {
		
		City recife = new City(1l, "Recife", StatesOfBrazil.PE);
		given(cityService.findByName("Recife")).willReturn(recife);
		
		mockMvc.perform(get(Constantes.BASE_URL_REST_CITY + "/name")
				.param("name", "Recife")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Recife")));
	}
	
	@Test
	public void givenState_whenSearchCityByState_thenReturnJsonArray() throws Exception {
		
		City recife = new City(1l, "Recife", StatesOfBrazil.PE);
		City caruaru = new City(1l, "Recife", StatesOfBrazil.PE);
		City garanhuns = new City(1l, "Recife", StatesOfBrazil.PE);
		
		List<City> pernambucoCities = Arrays.asList(recife, caruaru, garanhuns);

		given(cityService.findByState(StatesOfBrazil.PE)).willReturn(pernambucoCities);
		
		mockMvc.perform(get(Constantes.BASE_URL_REST_CITY + "/state")
				.param("state", "PE")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
			    .andExpect(jsonPath("$.cityList", hasSize(3)))
				.andExpect(jsonPath("$.cityList[0].state", is("PE")))
				.andExpect(jsonPath("$.cityList[1].state", is("PE")))
				.andExpect(jsonPath("$.cityList[2].state", is("PE")));
	}
	
}
