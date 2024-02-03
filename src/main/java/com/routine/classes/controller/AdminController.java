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

import com.routine.classes.models.FacultyBean;
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
		//System.out.println(faculty.getfName());
		return "Success";
		
	}
	@PostMapping(value = "delete/user")
	@ResponseBody
	public String deleteUser(String empId) {
		//System.out.println(empId);
		facultyServ.deleteUser(empId);
		//System.out.println(faculty.getfName());
		return "Success";
	}
	
	
	@GetMapping("get/user")
	@ResponseBody
   public  List<FacultyBean> getUser() {
		//ModelAndView mv=new ModelAndView();
		List<FacultyBean> faculties=facultyServ.getUsers();
		//System.out.println(faculties.get(0).getfName());
	//	mv.addObject("faculties",faculties);
	//	mv.setViewName("NewAdmin");
		return faculties;
	}
	
	@GetMapping("timeslot/get")
	   public  List<TimeSlotBean> getTimeSlots() {
			
			List<TimeSlotBean> tsList=dateRoomServ.getTimeSlots();
			return tsList;
		}
	@PostMapping("timeslot/add")
   public  int addTimeSlot(String timeSlot) {
		
		int rowsUpdated=dateRoomServ.addTimeSlot(timeSlot);
		return rowsUpdated;
	}
	
}
