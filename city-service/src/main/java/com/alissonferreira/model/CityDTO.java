package com.alissonferreira.model;


import java.util.List;

public class CityDTO {

	private Long searchTimestamp;
	private List<City> cityList;

	public CityDTO() {
		super();
	}

	public CityDTO(Long searchTimestamp, List<City> cityList) {
		super();
		this.searchTimestamp = searchTimestamp;
		this.cityList = cityList;
	}

	public Long getSearchTimestamp() {
		return searchTimestamp;
	}

	public void setSearchTimestamp(Long searchTimestamp) {
		this.searchTimestamp = searchTimestamp;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

}
