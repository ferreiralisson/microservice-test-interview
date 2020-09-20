package com.alissonferreira.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alissonferreira.enums.StatesOfBrazil;
import com.alissonferreira.exception.ResourceNotFoundException;
import com.alissonferreira.model.City;
import com.alissonferreira.model.CityDTO;
import com.alissonferreira.service.ICityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

	@Autowired
	private ICityService cityService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> add(@RequestBody City city) {

		City added = cityService.addCity(city);
		ResponseEntity<City> responseEntity = new ResponseEntity<>(HttpStatus.OK);

		if (added != null) {
			responseEntity = new ResponseEntity<>(added, HttpStatus.CREATED);
		}

		return responseEntity;
	}

	@GetMapping(path = "/name")
	public City findCityByName(@RequestParam(name = "name") String name) {
		return cityService.findByName(name);
	}
	
	@GetMapping(path = "/state")
	public CityDTO findCityByState(@RequestParam(name = "state") StatesOfBrazil state) {
		List<City> foundCities = cityService.findByState(state);
		return new CityDTO(new Date().getTime(), foundCities);
	}
	
	@GetMapping(path = "/{id}")
	public City findCityById(@PathVariable(name = "id") Long id) {
		try {
			return cityService.findById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
		
	}

}
