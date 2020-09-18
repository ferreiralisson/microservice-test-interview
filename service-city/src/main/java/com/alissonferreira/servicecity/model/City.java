package com.alissonferreira.servicecity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alissonferreira.servicecity.enums.StatesOfBrazil;

@Entity
@Table(name = "city")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "state")
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
