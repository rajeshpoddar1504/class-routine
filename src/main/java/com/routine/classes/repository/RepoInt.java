package com.routine.classes.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface RepoInt{
	
	public List<Map<String, Object>> getStudentRoutine();
	String updateStudentRoutine(List<Map<String, Object>> data);
	public void updateFacultytRoutine(List<Map<String, Object>> fileData, String select_faculty);
	public List<Map<String, Object>> getFacultyRoutine();
}
