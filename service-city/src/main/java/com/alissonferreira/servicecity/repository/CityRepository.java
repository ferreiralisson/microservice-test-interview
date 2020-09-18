package com.alissonferreira.servicecity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alissonferreira.servicecity.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	City findByName(String name);

	@Query(value = "select * from city where state = :state", nativeQuery = true)
	List<City> findByState(@Param(value = "state") String state);

}
