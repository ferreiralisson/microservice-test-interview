package com.alissonferreira.servicecity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alissonferreira.servicecity.enums.StatesOfBrazil;
import com.alissonferreira.servicecity.model.City;

@Service
public interface ICityService {

	City addCity(City city);

	City findByName(String name);

	List<City> findByState(StatesOfBrazil state);

}
