package com.routine.classes.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;
import com.routine.classes.models.BatchBean;
import com.routine.classes.models.DayBean;
import com.routine.classes.models.FacultyBean;
import com.routine.classes.models.TimeSlotBean;
import com.routine.classes.services.FacultyService;
import com.routine.classes.services.StudentRoutineServiceImpl;
import com.routine.classes.services.TimeDateRoomServ;
import com.routine.classes.utility.UserPDFExporter;

@Controller
public class ControllerClass {
	@Autowired
	StudentRoutineServiceImpl studentServImpl;
	
	@Autowired
	TimeDateRoomServ dateRoomServ;
	
	@Autowired
	FacultyService facultyServ;
	
	@GetMapping(value = {"/class-routine","/"})
	public ModelAndView getStudentSchedule() {

		ModelAndView mv = new ModelAndView();
		
		mv.addObject("routine_batches", facultyServ.getBatches());
		mv.setViewName("home");
		return mv;
	}
	
	@GetMapping(value = {"/class-routine/{batchId}"})
	public ModelAndView getStudentScheduleByBatch(@PathVariable("batchId") String batchId) {
		
		ModelAndView mv = new ModelAndView();

		List<BatchBean> batches=facultyServ.getBatches();
		List<TimeSlotBean> slots= dateRoomServ.getTimeSlots();
		List<DayBean> days=dateRoomServ.getDays();
		
		//slots.stream().forEach(e->System.out.println( e.getTimeSlots()));
		List<Map<String, Object>> routineList=studentServImpl.getStudentRoutineByBatch(batchId);
		
		//System.out.println(routineList);
		
		List<Map<String, Object>> convertedRoutine=convertToHorizontal(days,slots,routineList);
		
		//System.out.println("converted routine=="+convertedRoutine);
		
		mv.addObject("routine_day", days);
		mv.addObject("routine_data", convertedRoutine);
		mv.addObject("routine_batches", batches);
		mv.addObject("routine_timeslot", slots);
		
		mv.addObject("download_url","/download/student/routine/"+batchId);
		mv.setViewName("home");
		return mv;
	}
	
	private List<Map<String, Object>> convertToHorizontal(List<DayBean> days, List<TimeSlotBean> slots,
			List<Map<String, Object>> routineList) {
		//Stream<Map<String, Object>> routineListStreamString=routineList.stream();
		List<Map<String, Object>> mapList=new ArrayList<>();
		 
		for(DayBean day:days) {
			Map<String, Object> newMap=new HashedMap<>();
			newMap.put("day", day.getDayAbbr());
			for (TimeSlotBean tsBean: slots) {
				Map<String, Object> filteredMap=routineList.stream().filter(e->e.containsValue(tsBean.getTimeSlots())).findAny().get();
				newMap.put(tsBean.getTimeSlots(), filteredMap.get(day.getDayAbbr())==null?"":filteredMap.get(day.getDayAbbr()).toString());
			}
			mapList.add(newMap);
			
		}
		//System.out.println("vertical map "+mapList);
		return mapList;
		
	}

	@GetMapping("/class-routine/faculty")
	public ModelAndView getFacultySchedule() {

		ModelAndView mv = new ModelAndView();
		mv.addObject("routine_data", studentServImpl.getFacultyRoutine());
		mv.addObject("download_url","/download/faculty/routine");
		mv.setViewName("home");
		return mv;
	}

	//@PostMapping("/admin/class-routine/update")
	public ModelAndView getRoutineUpdate(@RequestParam("miltPrtFile") MultipartFile miltPrtFile,
			@RequestParam("upload-category") String upload_category,
			@RequestParam(name = "select-faculty", required = false) String select_faculty) {
		ModelAndView mv = new ModelAndView();
		
		try {
			if(upload_category.equals("student")) {
				studentServImpl.updateStudentRoutine(miltPrtFile);
				mv.addObject("routine_data", studentServImpl.getStudentRoutine());
				mv.addObject("download_url","/download/student/routine");
			}else if(upload_category.equals("faculty")) {
				studentServImpl.updateFacultyRoutine(miltPrtFile,select_faculty);
				mv.addObject("download_url","/download/faculty/routine");
				mv.addObject("routine_data", studentServImpl.getFacultyRoutine());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("home");
		return mv;
	}

	@GetMapping("/admin/login")
	public ModelAndView getAdminLogin() {

		ModelAndView mv=new ModelAndView();
		try {
			List<FacultyBean> faculties=facultyServ.getUsers();
			
			//System.out.println(faculties.get(0).getfName());
			mv.addObject("faculties",faculties);
			mv.setViewName("NewAdmin");
			
			List<BatchBean> batches=facultyServ.getBatches();
			mv.addObject("batches",batches);
			
			//studentServImpl.getStudentRoutine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
		//return "NewAdmin";
	}
	
	
	@GetMapping("/admin/new/features")
	public ModelAndView getAdminNewFeatres() {
		List<FacultyBean> faculties=facultyServ.getUsers();
		ModelAndView mv=new ModelAndView();
		mv.addObject("faculties",faculties);
		
		List<BatchBean> batches=facultyServ.getBatches();
		mv.addObject("batches",batches);
		
		mv.setViewName("NewAdmin");
		return mv;
	}

	@GetMapping("/admin/class-routine/update")
	public ModelAndView getAfterRoutine() {
		List<FacultyBean> faculties=facultyServ.getUsers();
		ModelAndView mv=new ModelAndView();
		mv.addObject("faculties",faculties);
		//System.out.println(faculties.get(0).getfName());
		mv.setViewName("NewAdmin");
		return mv;
	}

	@GetMapping("/download/student/routine/{batchId}")
	public void downloadRoutine(@PathVariable("batchId") String batchId, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/pdf");

		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		
		
		List<TimeSlotBean> slots= dateRoomServ.getTimeSlots();
		List<DayBean> days=dateRoomServ.getDays();
		List<BatchBean> batches=facultyServ.getBatches();
		
		List<BatchBean> batch=batches.stream().filter(e->e.getBatchAbbr().equals(batchId)).collect(Collectors.toList());
		List<Map<String, Object>> routineList=studentServImpl.getStudentRoutineByBatch(batchId);
		
		List<Map<String, Object>> convertedRoutine=convertToHorizontal(days,slots,routineList);
		//System.out.println("converted=="+convertedRoutine);
		
		UserPDFExporter pdfExp = new UserPDFExporter(convertedRoutine,slots,batch.get(0).getBatchDesc());
		
		
		//UserPDFExporter pdfExp = new UserPDFExporter(studentServImpl.getStudentRoutine());
		try {
			pdfExp.export(response);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("call through ajax");

	}
	@GetMapping("/download/faculty/routine" )
	public void downloadFacultyRoutine(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/pdf");

		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

		// studentServImpl.getStudentRoutine();
		
		//List<BatchBean> batches=facultyServ.getBatches();
		List<TimeSlotBean> slots= dateRoomServ.getTimeSlots();
		List<DayBean> days=dateRoomServ.getDays();
		List<Map<String, Object>> routineList=studentServImpl.getFacultyRoutine();
		
		List<Map<String, Object>> convertedRoutine=convertToHorizontal(days,slots,routineList);
		//System.out.println("converted=="+convertedRoutine);
		
		UserPDFExporter pdfExp = new UserPDFExporter(convertedRoutine,slots,"");
		
		try {
			pdfExp.export(response);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("call through ajax");
	}
	
}
