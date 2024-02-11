package com.routine.classes.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.routine.classes.models.BatchBean;
import com.routine.classes.models.FacultyBean;
import com.routine.classes.repository.FacultyRepoImpl;
import com.routine.classes.repository.FacultyRepoInt;

@Service
public class FacultyServiceImpl implements FacultyService {
	@Autowired
	FacultyRepoInt fauRepoInt;

	@Override
	public int addNewUser(FacultyBean faculty) {
		
		return fauRepoInt.addNewUser(faculty);
	}

	@Override
	public List<FacultyBean> getUsers() {
		ObjectMapper mapper = new ObjectMapper();
		List<FacultyBean> facultyList = fauRepoInt.geUsers().stream()
				.map(e -> mapper.convertValue(e, FacultyBean.class)).collect(Collectors.toList());
		return facultyList;
	}

	@Override
	public String deleteUser(String empId) {
		int deletedFac = fauRepoInt.deleteUsers(empId);
		return String.valueOf(deletedFac);
	}

	@Override
	public List<BatchBean> getBatches() {
		ObjectMapper mapper = new ObjectMapper();
		List<BatchBean> batchList = fauRepoInt.getBatch().stream()
				.map(e -> mapper.convertValue(e, BatchBean.class)).collect(Collectors.toList());
		return batchList;
	}

	@Override
	public List<Map<String, Object>> getRoutineByFaculty(String faculty) {
		return fauRepoInt.getRoutineByFaculty(faculty);
	}

}
