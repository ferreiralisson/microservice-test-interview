package com.alissonferreira.servicecity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alissonferreira.servicecity.enums.StatesOfBrazil;
import com.alissonferreira.servicecity.model.City;
import com.alissonferreira.servicecity.repository.CityRepository;
import com.alissonferreira.servicecity.service.ICityService;

@Service
public class CityServiceImpl implements ICityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public City addCity(City city) {
		return cityRepository.save(city);
	}

	@Override
	public City findByName(String name) {
		return cityRepository.findByName(name);
	}

	@Override
	public List<City> findByState(StatesOfBrazil state) {
		return cityRepository.findByState(state.name());
	}

	@Override
	public City findById(Long id) {
		return cityRepository.findById(id).orElse(null);
	}

}
