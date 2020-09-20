package com.alissonferreira.service;

import java.util.List;

import com.alissonferreira.enums.StatesOfBrazil;
import com.alissonferreira.model.City;

public interface ICityService {

	City addCity(City city);

	City findByName(String name);

	List<City> findByState(StatesOfBrazil state);

	City findById(Long id);

}
