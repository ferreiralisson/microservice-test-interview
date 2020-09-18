package com.alissonferreira.servicecity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alissonferreira.servicecity.enums.StatesOfBrazil;
import com.alissonferreira.servicecity.model.City;
import com.alissonferreira.servicecity.repository.CityRepository;
import com.alissonferreira.servicecity.service.ICityService;

@Service
public class CityServiceImpl implements ICityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	@Transactional
	public City addCity(City city) {
		if(validationDataCity(city)) {
			return cityRepository.save(city);
		}
		return new City();
	}

	@Override
	public City findByName(String name) {
		return cityRepository.findByName(name);
	}

	@Override
	public List<City> findByState(StatesOfBrazil state) {
		return cityRepository.findByState(state.name());
	}

	private static boolean validationDataCity(City city) {
		return city != null && !city.getName().equals("") && city.getStateOfBrasil().getName() != null;
	}

}
