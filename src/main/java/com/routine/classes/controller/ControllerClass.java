package com.routine.classes.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;
import com.routine.classes.services.StudentRoutineServiceImpl;
import com.routine.classes.utility.UserPDFExporter;

@Controller
public class ControllerClass {
	@Autowired
	StudentRoutineServiceImpl studentServImpl;

	@GetMapping(value = {"/class-routine","/"})
	public ModelAndView getStudentSchedule() {

		ModelAndView mv = new ModelAndView();
		mv.addObject("routine_data", studentServImpl.getStudentRoutine());
		mv.addObject("download_url","/download/student/routine");
		mv.setViewName("home");
		return mv;
	}
	@GetMapping("/class-routine/faculty")
	public ModelAndView getFacultySchedule() {

		ModelAndView mv = new ModelAndView();
		mv.addObject("routine_data", studentServImpl.getFacultyRoutine());
		mv.addObject("download_url","/download/faculty/routine");
		mv.setViewName("home");
		return mv;
	}

	@PostMapping("/admin/class-routine/update")
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
	
	public String getAdminLogin() {

		try {
			studentServImpl.getStudentRoutine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin";
	}
	@GetMapping("/admin/class-routine/update")
	public String getAfterRoutine() {
		return "admin";
	}

	@GetMapping("/download/student/routine")
	public void downloadRoutine(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/pdf");

		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

		// studentServImpl.getStudentRoutine();
		UserPDFExporter pdfExp = new UserPDFExporter(studentServImpl.getStudentRoutine());
		try {
			pdfExp.export(response);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("call through ajax");

	}
	@GetMapping("/download/faculty/routine")
	public void downloadFacultyRoutine(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/pdf");

		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

		// studentServImpl.getStudentRoutine();
		UserPDFExporter pdfExp = new UserPDFExporter(studentServImpl.getFacultyRoutine());
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
