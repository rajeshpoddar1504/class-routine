package com.routine.classes.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.routine.classes.models.TimeSlotBean;

@Repository
public interface TimeDateRoomInt {

	int addTimeSlot(String timeSlot);

	List<Map<String, Object>> getTimeSlots();

}
