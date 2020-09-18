package com.alissonferreira.servicecity.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alissonferreira.servicecity.enums.StatesOfBrazil;
import com.alissonferreira.servicecity.model.City;
import com.alissonferreira.servicecity.service.ICityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

	@Autowired
	private ICityService cityService;

	@PostMapping
	public City add(@RequestBody City city, HttpServletResponse response) {

		City added = cityService.addCity(city);

		if (added != null) {
			response.setStatus(HttpStatus.CREATED.value());
		}

		return added;
	}

	@GetMapping(path = "/name")
	public City findCityByName(@RequestParam(name = "name") String name) {
		return cityService.findByName(name);
	}

	@GetMapping(path = "/state")
	public List<City> findCityByState(@RequestParam(name = "state") StatesOfBrazil state) {
		return cityService.findByState(state);
	}

}
