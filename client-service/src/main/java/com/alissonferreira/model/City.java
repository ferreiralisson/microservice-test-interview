package com.alissonferreira.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.alissonferreira.enums.StatesOfBrazil;

public class City {

	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private StatesOfBrazil state;

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

	public StatesOfBrazil getState() {
		return state;
	}

	public void setState(StatesOfBrazil state) {
		this.state = state;
	}

}
