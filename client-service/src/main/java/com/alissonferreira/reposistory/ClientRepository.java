package com.alissonferreira.reposistory;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.alissonferreira.model.Client;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

	Client findByName(String name);
}
