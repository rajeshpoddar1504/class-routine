package com.routine.classes.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.routine.classes.models.FacultyBean;

@Repository
public interface FacultyRepoInt {


	int addNewUser(FacultyBean faculty);

	List<Map<String, Object>> geUsers();

	int deleteUsers(String empId);
}
