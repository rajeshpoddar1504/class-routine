package com.routine.classes.models;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class TimeSlotBean {
	@JsonProperty("id")
	private int id;
	@JsonProperty("time_slot")
	private String timeSlots;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTimeSlots() {
		return timeSlots;
	}
	public void setTimeSlots(String timeSlots) {
		this.timeSlots = timeSlots;
	}
	public TimeSlotBean(int id, String timeSlots) {
		super();
		this.id = id;
		this.timeSlots = timeSlots;
	}
	public TimeSlotBean() {
		super();
	}
	
}
