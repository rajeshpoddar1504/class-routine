package com.routine.classes.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.routine.classes.models.DayBean;
import com.routine.classes.models.RoomBean;
import com.routine.classes.models.TimeSlotBean;
import com.routine.classes.repository.TimeDateRoomInt;

@Service
public class TimeDateRoomServImp implements TimeDateRoomServ{
@Autowired
TimeDateRoomInt dateRoomInt;
	@Override
	public int addTimeSlot(String timeSlot) {
		int rowsUpdated=dateRoomInt.addTimeSlot(timeSlot);
		return rowsUpdated;
	}
	@Override
	public int modifyTimeSlot(String olderTsId, String newTs) {
		int rowsUpdated=dateRoomInt.modifyTimeSlot(olderTsId,newTs);
		return rowsUpdated;
	}
	
	@Override
	public List<TimeSlotBean> getTimeSlots() {
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> tsList=dateRoomInt.getTimeSlots();
		List<TimeSlotBean> avTs=tsList.stream().map(e->mapper.convertValue(e, TimeSlotBean.class)).collect(Collectors.toList());
		return avTs;
	}
	@Override
	public int deleteTimeSlot(String timeSlot) {
		int rowsUpdated=dateRoomInt.deleteTimeSlot(timeSlot);
		return rowsUpdated;
	}
	@Override
	public List<RoomBean> getRooms() {
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> roomList=dateRoomInt.getRooms();
		List<RoomBean> avRooms=roomList.stream().map(e->mapper.convertValue(e, RoomBean.class)).collect(Collectors.toList());
		return avRooms;
	}
	@Override
	public int addRoom(String room, String newRoomDesc) {
		int rowsRooms=dateRoomInt.addRoom(room,newRoomDesc);
		return rowsRooms;
	}
	@Override
	public int modifyRoom(String roomId, String newRoom, String newRoomDesc) {
		int rowsUpdated=dateRoomInt.modifyRoom(roomId,newRoom,newRoomDesc);
		return rowsUpdated;
	}
	@Override
	public int deleteRoom(String roomId) {
		int rowsUpdated=dateRoomInt.deleteRoom(roomId);
		return rowsUpdated;
	}
	@Override
	public List<DayBean> getDays() {
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> tsList=dateRoomInt.getDays();
		List<DayBean> avDays=tsList.stream().map(e->mapper.convertValue(e, DayBean.class)).collect(Collectors.toList());
		return avDays;
	}
	@Override
	public int addDays(String day) {
		int rowsDays=dateRoomInt.addDay(day);
		return rowsDays;
	}
	@Override
	public int modifyDays(String dayToModify, String newDay) {
		int rowsUpdated=dateRoomInt.modifyDay(dayToModify,newDay);
		return rowsUpdated;
	}
	@Override
	public int deleteDays(String daysId) {
		int rowsUpdated=dateRoomInt.deleteDay(daysId);
		return rowsUpdated;
	}
	

}
