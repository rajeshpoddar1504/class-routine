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

	@Override
	public int modifyTimeSlot(String olderTsId, String newTs) {
	//	System.out.println("time updates: "+olderTsId+" "+newTs);
		String sql="update class_time_slots set time_slot=? where id=?";
		return jdbc.update(sql,newTs,Integer.valueOf(olderTsId) );
	}

	@Override
	public int deleteTimeSlot(String timeSlot) {
		//System.out.println("time updates: "+timeSlot);
		String sql="delete from class_time_slots where id=?";
		return jdbc.update(sql,Integer.valueOf(timeSlot) );
	}

	@Override
	public List<Map<String, Object>> getRooms() {
		String sql="select * from room_details";
		return jdbc.queryForList(sql);
	}

	@Override
	public int addRoom(String room,String newRoomDesc) {
		String sql="insert into room_details (room_abbr,room_desc) values(?,?)";
		return jdbc.update(sql,room,newRoomDesc);
	}

	@Override
	public int modifyRoom(String roomId, String newRoom,String newRoomDesc ) {
		String sql="update room_details set room_abbr=?, room_desc=? where id=?";
		return jdbc.update(sql,newRoom,newRoomDesc,Integer.valueOf(roomId));
	}

	@Override
	public int deleteRoom(String roomId) {
		String sql="delete from room_details where id=?";
		return jdbc.update(sql,Integer.valueOf(roomId) );
	}

	@Override
	public List<Map<String, Object>> getDays() {
		String sql="select * from day_details";
		return jdbc.queryForList(sql);
	}

	@Override
	public int addDay(String day) {
		String sql="insert into day_details (day_abbr) values(?)";
		return jdbc.update(sql,day);
	}

	@Override
	public int modifyDay(String dayToModify, String newDay) {
		String sql="update day_details set day_abbr=? where id=?";
		return jdbc.update(sql,newDay,Integer.valueOf(dayToModify));
	}

	@Override
	public int deleteDay(String daysId) {
		String sql="delete from day_details where id=?";
		return jdbc.update(sql,Integer.valueOf(daysId) );
	}

}
