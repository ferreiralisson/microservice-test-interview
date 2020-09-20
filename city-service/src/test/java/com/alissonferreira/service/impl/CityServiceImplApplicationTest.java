package com.alissonferreira.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.alissonferreira.enums.StatesOfBrazil;
import com.alissonferreira.model.City;
import com.alissonferreira.repository.CityRepository;
import com.alissonferreira.service.ICityService;

@RunWith(SpringRunner.class)
public class CityServiceImplApplicationTest {

	@TestConfiguration
	static class CityServiceImplTestContextConfiguration {
		
		@Bean
		public ICityService cityService() {
			return new CityServiceImpl();
		}
	}
	
	@Autowired
	private ICityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@Before
	public void setUp() {
		City recife = new City(1l, "Recife", StatesOfBrazil.PE);
		City caruaru = new City(2l, "Caruaru", StatesOfBrazil.PE);
		City garanhuns = new City(1l, "Garanhuns", StatesOfBrazil.PE);
		
		List<City> pernambucoCities = Arrays.asList(
				recife, caruaru, garanhuns
		); 
		
		when(cityRepository.findByName(recife.getName()))
			.thenReturn(recife);
		
		when(cityRepository.findByState(StatesOfBrazil.PE))
			.thenReturn(pernambucoCities);
		
		City saoPaulo = new City(null, "São Paulo", StatesOfBrazil.SP);
		
		when(cityRepository.save(saoPaulo))
			.thenReturn(new City(1l, "São Paulo", StatesOfBrazil.SP));
	}
	
	@Test
	public void whenValidName_thenCityShouldBeFound() {
		String name = "Recife";
		City found = cityService.findByName(name);
		
		assertThat(found.getName()).isEqualTo(name);
	}
	
	@Test
	public void whenValidState_thenThatStateCitiesShouldBeFound() {
		StatesOfBrazil state = StatesOfBrazil.PE;
		List<City> foundCities = cityService.findByState(state);

		assertThat(foundCities.size()).isEqualTo(3);
	}
	
	@Test
	public void whenCreateCity_thenReturnCreatedCity() {
		
		City city = new City("São Paulo", StatesOfBrazil.SP);
		when(cityRepository.save(city)).thenReturn(new City("São Paulo", StatesOfBrazil.SP));
		
		City created = cityService.addCity(city);
		
		try {
			assertThat(created.getName()).isEqualTo(city.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
