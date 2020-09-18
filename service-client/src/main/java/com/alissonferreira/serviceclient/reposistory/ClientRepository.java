package com.alissonferreira.serviceclient.reposistory;

import com.alissonferreira.serviceclient.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

	Client findByName(String name);
}
