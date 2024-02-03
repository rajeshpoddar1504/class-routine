package com.routine.classes.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.routine.classes.models.TimeSlotBean;

@Repository
public class TimeDateRoomImpl implements TimeDateRoomInt{

	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public int addTimeSlot(String timeSlot) {
		String sql="insert into class_time_slots (time_slot) values(?)";
		
		return jdbc.update(sql,timeSlot);
	}

	@Override
	public List<Map<String, Object>> getTimeSlots() {
		String sql="select * from class_time_slots";
		return jdbc.queryForList(sql);
	}

}
