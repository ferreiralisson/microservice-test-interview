package com.alissonferreira.serviceclient.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.alissonferreira.serviceclient.model.Gender;

public class ClientDTO {

    private Long id;

    @NotNull(message = "The field 'Name' is mandatory")
    private String name;

    @NotNull(message = "The field 'Gender' is mandatory")
    private Gender gender;

    @NotNull(message = "The field 'Date of Birth' is mandatory")
    private Date dateOfBirth;

    @NotNull(message = "The field 'Age' is mandatory")
    private Integer age;

    @NotNull(message = "The field 'City' is mandatory")
    private Long cityId;
    
    
    public ClientDTO() {}

    
	public ClientDTO(Long id, @NotNull(message = "The field 'Name' is mandatory") String name,
			@NotNull(message = "The field 'Gender' is mandatory") Gender gender,
			@NotNull(message = "The field 'Date of Birth' is mandatory") Date dateOfBirth,
			@NotNull(message = "The field 'Age' is mandatory") Integer age,
			@NotNull(message = "The field 'City' is mandatory") Long cityId) {
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
    
    
    


}
