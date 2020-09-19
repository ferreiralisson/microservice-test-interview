package com.alissonferreira.serviceclient.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "dateofbirth")
	private Date dateOfBirth;

	@Column(name = "age")
	private Integer age;

	@Column(name = "city")
	private Long cityId;

	@Transient
	private City city;

	public Client() {
		// Constructor default
	}

	

	public Client(String name, Gender gender, Date dateOfBirth, Integer age, Long cityId, City city) {
		super();
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.cityId = cityId;
		this.city = city;
	}



	public Client(Long id, String name, Gender gender, Date dateOfBirth, Integer age, Long cityId, City city) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.cityId = cityId;
		this.city = city;
	}
	
	

	public Client(Long id, String name, Gender gender, Date dateOfBirth, Integer age, Long cityId) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.cityId = cityId;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public static class ClientBuilder implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long id;
		private String name;
		private Gender gender;
		private Date dateOfBirth;
		private Integer age;
		private Long cityId;

		public ClientBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public ClientBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ClientBuilder gender(Gender gender) {
			this.gender = gender;
			return this;
		}

		public ClientBuilder dateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		public ClientBuilder age(Integer age) {
			this.age = age;
			return this;
		}

		public ClientBuilder cityId(Long cityId) {
			this.cityId = cityId;
			return this;
		}

		public Client build() {
			return new Client(id, name, gender, dateOfBirth, age, cityId);
		}

	}

}
