package com.alissonferreira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alissonferreira.enums.StatesOfBrazil;
import com.alissonferreira.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	City findByName(String name);

	List<City> findByState(@Param(value = "state") StatesOfBrazil state);

}
