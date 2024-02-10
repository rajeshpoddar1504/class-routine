package com.routine.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DayBean {
	@JsonProperty("id")
	private int id;
	@JsonProperty("day_abbr")
	private String dayAbbr;
	
	@JsonProperty("day_desc")
	private String dayDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDayAbbr() {
		return dayAbbr;
	}

	public void setDayAbbr(String dayAbbr) {
		this.dayAbbr = dayAbbr;
	}

	public String getDayDesc() {
		return dayDesc;
	}

	public void setDayDesc(String dayDesc) {
		this.dayDesc = dayDesc;
	}

	public DayBean(int id, String dayAbbr, String dayDesc) {
		super();
		this.id = id;
		this.dayAbbr = dayAbbr;
		this.dayDesc = dayDesc;
	}

	public DayBean() {
		super();
	}
	
	
	
}
