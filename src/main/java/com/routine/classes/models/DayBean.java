package com.routine.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DayBean {
	@JsonProperty("id")
	private int id;
	@JsonProperty("day_abbr")
	private String dayAbbr;
	public DayBean(int id, String dayAbbr) {
		super();
		this.id = id;
		this.dayAbbr = dayAbbr;
	}
	public DayBean() {
		super();
	}
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
	
}
