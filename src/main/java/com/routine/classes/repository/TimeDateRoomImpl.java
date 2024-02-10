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

	@Override
	public List<Map<String, Object>> getTimeSlots(String facultyAbbr, String selectedDay, String selectedBatch) {
		//String sql="select time_slot from batch_time_day_details  where "+selectedDay+" is null and batch_abbr=?";
		
		String sql="select distinct(batch.time_slot) from batch_time_day_details batch join faculty_time_day_details faculty on batch.time_slot=faculty.time_slot where batch."+selectedDay+" is null and batch.batch_abbr=?";
		
		return jdbc.queryForList(sql,selectedBatch);
	}

	@Override
	public List<Map<String, Object>> getRooms(String facultyAbbr, String selectedDay, String selectedBatch,
			String selectedTime) {
		
		String sql="select * from room_details where room_abbr in ( select room_abbr from room_time_day_details where "+selectedDay +" is null and time_slot=?)";
		
		//System.out.println(sql+" "+selectedTime);
		//System.out.println(jdbc.queryForList(sql,selectedTime));
		return jdbc.queryForList(sql,selectedTime);
	}

	@Override
	public int bookSchedule(String facultyAbbr, String selectedDay, String selectedBatch, String selectedTime,
			String selectedRoom, String courseCode) {
		
		String batchTimeDaySQL="update batch_time_day_details set "+selectedDay+"=? where batch_abbr=? and time_slot=?";
		int rowsInserted =jdbc.update(batchTimeDaySQL,courseCode+" ("+ facultyAbbr+") "+selectedRoom,selectedBatch,selectedTime);
		
		String facultyTimeDaySQL="update faculty_time_day_details set "+selectedDay+"=? where faculty_abbr=? and time_slot=?";
		int facultyDayRows =jdbc.update(facultyTimeDaySQL,selectedBatch+" ("+ courseCode+") "+selectedRoom,facultyAbbr,selectedTime);
		
		
		String roomTimeDaySQL="update room_time_day_details set "+selectedDay+"=? where room_abbr=? and time_slot=?";
		int roomDayRows =jdbc.update(roomTimeDaySQL,selectedBatch+" ("+ courseCode+") "+facultyAbbr,selectedRoom,selectedTime);
		
		return rowsInserted;
	}

	@Override
	public int addBatch(String newBatchAbbr, String newBatchDesc) {
		String sql="insert into batch_details (batch_abbr,batch_desc) values(?,?)";
		return jdbc.update(sql,newBatchAbbr,newBatchDesc);
	}

	@Override
	public int deleteBatch(String batchAbbr) {
		System.out.println(batchAbbr);
		String sql="delete from batch_details where batch_abbr=?";
		return jdbc.update(sql,batchAbbr);
	} 

}
