package com.alissonferreira.servicecity.service;

import java.util.List;

import com.alissonferreira.servicecity.enums.StatesOfBrazil;
import com.alissonferreira.servicecity.model.City;

public interface ICityService {

	City addCity(City city);

	City findByName(String name);

	List<City> findByState(StatesOfBrazil state);

	City findById(Long id);

}
