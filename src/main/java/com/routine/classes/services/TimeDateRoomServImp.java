package com.routine.classes.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	public List<TimeSlotBean> getTimeSlots() {
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> tsList=dateRoomInt.getTimeSlots();
		List<TimeSlotBean> avTs=tsList.stream().map(e->mapper.convertValue(e, TimeSlotBean.class)).collect(Collectors.toList());
		return avTs;
	}

}
