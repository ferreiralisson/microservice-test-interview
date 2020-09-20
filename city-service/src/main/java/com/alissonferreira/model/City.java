package com.alissonferreira.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alissonferreira.enums.StatesOfBrazil;

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
	private StatesOfBrazil state;

	public City() {
		// Constructor default
	}
	
	

	public City(Long id, String name, StatesOfBrazil state) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
	}



	public City(String name, StatesOfBrazil state) {
		super();
		this.name = name;
		this.state = state;
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
