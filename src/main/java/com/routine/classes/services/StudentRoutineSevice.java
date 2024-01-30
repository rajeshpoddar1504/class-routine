package com.routine.classes.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StudentRoutineSevice {
	public List<Map<String, Object>> getStudentRoutine();

	public List<Map<String, Object>> getFacultyRoutine();

	String updateStudentRoutine(MultipartFile miltPrtFile);
	void updateFacultyRoutine(MultipartFile miltPrtFile, String select_faculty);



}
