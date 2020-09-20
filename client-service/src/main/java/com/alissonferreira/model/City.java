package com.alissonferreira.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.alissonferreira.enums.StatesOfBrazil;

public class City {

	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private StatesOfBrazil stateOfBrasil;

	public City() {
		// Constructor default
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StatesOfBrazil getStateOfBrasil() {
		return stateOfBrasil;
	}

	public void setStateOfBrasil(StatesOfBrazil stateOfBrasil) {
		this.stateOfBrasil = stateOfBrasil;
	}

}
