package com.routine.classes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.routine.classes.models.DayBean;
import com.routine.classes.models.FacultyBean;
import com.routine.classes.models.RoomBean;
import com.routine.classes.models.TimeSlotBean;
import com.routine.classes.repository.TimeDateRoomInt;
import com.routine.classes.services.FacultyService;
import com.routine.classes.services.TimeDateRoomServ;

@RestController
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	FacultyService facultyServ;
	@Autowired
	TimeDateRoomServ dateRoomServ;

	@PostMapping(value = "add/user")
	@ResponseBody
	public String addUser(FacultyBean faculty) {

		facultyServ.addNewUser(faculty);
		// System.out.println(faculty.getfName());
		return "Success";

	}

	@PostMapping(value = "delete/user")
	@ResponseBody
	public String deleteUser(String empId) {
		// System.out.println(empId);
		facultyServ.deleteUser(empId);
		// System.out.println(faculty.getfName());
		return "Success";
	}

	@GetMapping("get/user")
	@ResponseBody
	public List<FacultyBean> getUser() {
		// ModelAndView mv=new ModelAndView();
		List<FacultyBean> faculties = facultyServ.getUsers();
		// System.out.println(faculties.get(0).getfName());
		// mv.addObject("faculties",faculties);
		// mv.setViewName("NewAdmin");
		return faculties;
	}

	@GetMapping("timeslot/get")
	public List<TimeSlotBean> getTimeSlots() {

		List<TimeSlotBean> tsList = dateRoomServ.getTimeSlots();
		return tsList;
	}

	@PostMapping("timeslot/add")
	public int addTimeSlot(String timeSlot) {

		int rowsUpdated = dateRoomServ.addTimeSlot(timeSlot);
		return rowsUpdated;
	}

	@PostMapping("timeslot/modify")
	public int modifyTimeSlot(String timeSlot, String newTimeSlot) {

		int rowsUpdated = dateRoomServ.modifyTimeSlot(timeSlot, newTimeSlot);
		return rowsUpdated;
	}

	@PostMapping("timeslot/delete")
	public int deleteTimeSlot(String timeSlot) {
		int rowsUpdated = dateRoomServ.deleteTimeSlot(timeSlot);
		return rowsUpdated;
	}

	@GetMapping("rooms/get")
	public List<RoomBean> getRooms() {
		List<RoomBean> tsList = dateRoomServ.getRooms();
		return tsList;
	}

	@PostMapping("rooms/add")
	public int addRooms(String newRoomAbbr,String newRoomDesc) {
		int rowsUpdated = dateRoomServ.addRoom(newRoomAbbr,newRoomDesc);
		return rowsUpdated;
	}

	@PostMapping("room/modify")
	public int modifyRoom(String roomId, String newRoomAbbr,String newRoomDesc) {
		int rowsUpdated = dateRoomServ.modifyRoom(roomId, newRoomAbbr,newRoomDesc);
		return rowsUpdated;
	}

	@PostMapping("room/delete")
	public int deleteRoom(String roomId) {
		int rowsUpdated = dateRoomServ.deleteRoom(roomId);
		return rowsUpdated;
	}

	@GetMapping("days/get")
	public List<DayBean> getDays() {
		List<DayBean> dayList = dateRoomServ.getDays();
		return dayList;
	}

	@PostMapping("day/add")
	public int addDays(String day) {
		int rowsUpdated = dateRoomServ.addDays(day);
		return rowsUpdated;
	}

	@PostMapping("day/modify")
	public int modifyDays(String dayToModify, String newDay) {
		int rowsUpdated = dateRoomServ.modifyDays(dayToModify, newDay);
		return rowsUpdated;
	}

	@PostMapping("day/delete")
	public int deleteDays(String daysId) {
		int rowsUpdated = dateRoomServ.deleteDays(daysId);
		return rowsUpdated;
	}

}
