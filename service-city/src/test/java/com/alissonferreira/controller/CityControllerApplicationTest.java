package com.alissonferreira.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.alissonferreira.servicecity.enums.StatesOfBrazil;
import com.alissonferreira.servicecity.model.City;
import com.alissonferreira.servicecity.repository.CityRepository;
import com.alissonferreira.servicecity.service.ICityService;
import com.alissonferreira.servicecity.utilities.Constantes;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerApplicationTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;
    @MockBean
    private CityRepository cityRepository;
	
	@MockBean
	private ICityService citytService;
	
	@Before
    public void setup() {
		City recife = new City(1l, "Recife", StatesOfBrazil.PE);
        BDDMockito.when(cityRepository.findById(recife.getId())).thenReturn(createCityOptional(recife));
    }
	
	@Test
	public void givenCity_whenSearchCityByName_thenReturn200() throws Exception {
		ResponseEntity<City> response = restTemplate.getForEntity(Constantes.BASE_URL_REST_CITY + "/name?name=Vitoria", City.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void givenCity_whenSearchCityBystate_thenReturn200() throws Exception {
		ResponseEntity<City[]> response = restTemplate.getForEntity(Constantes.BASE_URL_REST_CITY + "/state?state=PE", City[].class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void givenCity_whenSearchCityById_thenReturn200() throws Exception {
		ResponseEntity<City> response = restTemplate.getForEntity(Constantes.BASE_URL_REST_CITY + "/{id}", City.class, 1l);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void whenCreateCity_thenReturnCreatedCity() throws Exception {

		City city = new City("São Paulo", StatesOfBrazil.SP);
        BDDMockito.when(cityRepository.save(city)).thenReturn(city);
        ResponseEntity<City> response = restTemplate.postForEntity(Constantes.BASE_URL_REST_CITY, city, City.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
	}
	
	
	private Optional<City> createCityOptional(City city) {
		return Optional.of(city);
	}

}
