package com.routine.classes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.routine.classes.models.TimeSlotBean;

@Service
public interface TimeDateRoomServ {

	int addTimeSlot(String timeSlot);

	List<TimeSlotBean> getTimeSlots();

}
