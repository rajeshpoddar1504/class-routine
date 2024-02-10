package com.routine.classes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.routine.classes.models.DayBean;
import com.routine.classes.models.RoomBean;
import com.routine.classes.models.TimeSlotBean;

@Service
public interface TimeDateRoomServ {

	int addTimeSlot(String timeSlot);

	List<TimeSlotBean> getTimeSlots();

	int modifyTimeSlot(String olderTsId, String newTs);

	int deleteTimeSlot(String timeSlot);

	List<RoomBean> getRooms();

	int addRoom(String room, String newRoomDesc);

	int modifyRoom(String roomId, String newRoom, String newRoomDesc);

	int deleteRoom(String roomId);

	List<DayBean> getDays();

	int addDays(String day);

	int modifyDays(String dayToModify, String newDay);

	int deleteDays(String daysId);

	List<TimeSlotBean> getTimeSlots(String facultyAbbr, String selectedDay, String selectedBatch);

	List<RoomBean> getRooms(String facultyAbbr, String selectedDay, String selectedBatch, String selectedTime);

	int bookSchedule(String facultyAbbr, String selectedDay, String selectedBatch, String selectedTime,
			String selectedRoom, String courseCode);

	int addBatch(String newBatchAbbr, String newBatchDesc);

	int deleteBatch(String batchAbbr);

}
