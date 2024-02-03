package com.routine.classes.models;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class FacultyBean {
	@JsonProperty("id")
	private int id;
public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@JsonProperty("faculty_fname")
private String fName;
public FacultyBean(int id, String fName, String lName, String abbrevation) {
	super();
	this.id = id;
	this.fName = fName;
	this.lName = lName;
	this.abbrevation = abbrevation;
}
@JsonProperty("faculty_lname")
private String lName;
@JsonProperty("faculty_abbre")
private String abbrevation;
public FacultyBean() {
	super();
}

public String getfName() {
	return fName;
}
public void setfName(String fName) {
	this.fName = fName;
}
public String getlName() {
	return lName;
}
public void setlName(String lName) {
	this.lName = lName;
}
public String getAbbrevation() {
	return abbrevation;
}
public void setAbbrevation(String abbrevation) {
	this.abbrevation = abbrevation;
}


}
