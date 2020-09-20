package com.alissonferreira.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alissonferreira.enums.StatesOfBrazil;
import com.alissonferreira.model.City;
import com.alissonferreira.repository.CityRepository;
import com.alissonferreira.service.ICityService;

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
		return cityRepository.findByState(state);
	}

	@Override
	public City findById(Long id) {
		return cityRepository.findById(id).orElse(null);
	}

}
