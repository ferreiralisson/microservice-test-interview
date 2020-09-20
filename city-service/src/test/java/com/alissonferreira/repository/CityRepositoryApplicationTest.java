package com.alissonferreira.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.alissonferreira.enums.StatesOfBrazil;
import com.alissonferreira.model.City;

@RunWith (SpringRunner.class)
@DataJpaTest
public class CityRepositoryApplicationTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Test
	public void whenFindByName_thenReturnCity() {
		City recife = new City("Recife", StatesOfBrazil.PE);
		
		entityManager.persist(recife);
		entityManager.flush();
		
		City found = cityRepository.findByName(recife.getName());
		
		assertThat(found.getName())
			.isEqualTo(found.getName());
	}
	
	@Test
	public void whenFindByState_thenReturnThatStateCities() {
		City recife = new City("Recife", StatesOfBrazil.PE);
		City caruaru = new City("Caruaru", StatesOfBrazil.PE);
		City garahuns = new City("Garanhuns", StatesOfBrazil.PE);
		
		entityManager.persist(recife);
		entityManager.persist(caruaru);
		entityManager.persist(garahuns);
		
		entityManager.flush();
		
		List<City> found = cityRepository.findByState(recife.getState());
		
		assertThat(found.size()).isEqualTo(3);
	}
	
	@Test
	public void whenSave_thenReturnSavedCity() {
		
		City recife = new City("Recife", StatesOfBrazil.PE);
		City saved = cityRepository.save(recife);
		
		assertThat(saved.getName())
		.isEqualTo(recife.getName());
	}
}
