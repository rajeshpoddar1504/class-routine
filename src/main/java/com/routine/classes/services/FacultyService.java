package com.routine.classes.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.routine.classes.models.BatchBean;
import com.routine.classes.models.FacultyBean;

@Service
public interface FacultyService {

	public int addNewUser(FacultyBean faculty);

	public List<FacultyBean> getUsers();

	public String deleteUser(String empId);

	public List<BatchBean> getBatches();

	public List<Map<String, Object>> getRoutineByFaculty(String facultyId);
}
