package com.routine.classes.models;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class RoomBean {
	@JsonProperty("id")
	private int id;
	@JsonProperty("room_abbr")
	private String roomAbbr;
	
	@JsonProperty("room_desc")
	private String roomDesc;

	public RoomBean(int id, String roomAbbr, String roomDesc) {
		super();
		this.id = id;
		this.roomAbbr = roomAbbr;
		this.roomDesc = roomDesc;
	}

	public RoomBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomAbbr() {
		return roomAbbr;
	}

	public void setRoomAbbr(String roomAbbr) {
		this.roomAbbr = roomAbbr;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}
	
}
