package com.routine.classes.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.routine.classes.models.TimeSlotBean;

@Repository
public interface TimeDateRoomInt {

	int addTimeSlot(String timeSlot);

	List<Map<String, Object>> getTimeSlots();

	int modifyTimeSlot(String olderTsId, String newTs);

	int deleteTimeSlot(String timeSlot);

	List<Map<String, Object>> getRooms();

	int addRoom(String room, String newRoomDesc);

	int modifyRoom(String roomId, String newRoom, String newRoomDesc);

	int deleteRoom(String roomId);

	List<Map<String, Object>> getDays();

	int addDay(String day);

	int modifyDay(String dayToModify, String newDay);

	int deleteDay(String daysId);

	List<Map<String, Object>> getTimeSlots(String facultyAbbr, String selectedDay, String selectedBatch);

	List<Map<String, Object>> getRooms(String facultyAbbr, String selectedDay, String selectedBatch,
			String selectedTime);

	int bookSchedule(String facultyAbbr, String selectedDay, String selectedBatch, String selectedTime,
			String selectedRoom, String courseCode);

	int addBatch(String newBatchAbbr, String newBatchDesc);

	int deleteBatch(String batchAbbr);

}
