package com.alissonferreira.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.alissonferreira.enums.Gender;
import com.alissonferreira.model.Client;
import com.alissonferreira.reposistory.ClientRepository;

@RunWith (SpringRunner.class)
@DataJpaTest
public class ClientRepositoryApplicationTests {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Test
	public void whenFindByName_thenReturnClient() {
		
		Client jessica = new Client("Jessica", Gender.F, new Date(), 22, 1l, null);
		
		entityManager.persist(jessica);
		entityManager.flush();
		
		Client found = clientRepository.findByName(jessica.getName());
		
		assertThat(found.getName()).isEqualTo(found.getName());
	}
	
	@Test
	public void whenSave_thenReturnSavedClient() {
		
		Client alisson = new Client("Alison", Gender.M, new Date(), 28, 1l, null);
		Client saved = clientRepository.save(alisson);
		
		assertThat(saved.getName())
		.isEqualTo(alisson.getName());
	}
	
	@Test
	public void whenUpdate_thenNameChanged() {
		
		Client joao = new Client("João", Gender.M, new Date(), 40, 1l, null);
		
		Client saved = entityManager.persist(joao);
		entityManager.flush();
		
		saved.setName("Daniel");
		Client daniel = clientRepository.save(saved);
		
		assertThat(daniel.getName())
		.isEqualTo(saved.getName());
	}
	
	@Test
	public void whenUpdate_thenIdIsUnChanged() {
		
		Client alisson = new Client("Alisson", Gender.M, new Date(), 30, 1l, null);
		
		Client saved = entityManager.persist(alisson);
		entityManager.flush();
		
		saved.setName("Bruno");
		Client daniel = clientRepository.save(saved);
		
		assertThat(daniel.getId())
		.isEqualTo(saved.getId());
	}
	
	@Test
	public void whenFindById_thenResourceIsFound() {
		Client joao = new Client("João", Gender.M, new Date(), 30, 1l, null);
		
		Client saved = entityManager.persist(joao);
		entityManager.flush();
		
		Long id = saved.getId();
		
		Client found = clientRepository.findById(id).orElse(null);
		
		assertThat(found.getId()).isEqualTo(id);
	}
	
	@Test
	public void whenDelete_thenResourceShouldNotExist() {
		
		Client bruno = new Client("Bruno", Gender.M, new Date(), 30, 1l, null);
		
		Client saved = entityManager.persist(bruno);
		entityManager.flush();
		
		Long id = saved.getId();
		clientRepository.deleteById(id);
		
		Client found = clientRepository.findById(id).orElse(null);
		
		assertNull(found);
	}

}
