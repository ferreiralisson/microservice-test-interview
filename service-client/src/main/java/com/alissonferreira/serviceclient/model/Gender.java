package com.alissonferreira.serviceclient.model;

public enum Gender {

    M("Male"),
    F("Female"),
    U("Uninformed");

    private String detailGender;
    
    private Gender(String detailGender) {
        this.detailGender = detailGender;
    }

	public String getDetailGender() {
		return detailGender;
	}
    
    
}
