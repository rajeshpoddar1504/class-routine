package com.routine.classes.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.routine.classes.models.FacultyBean;

@Repository
public class FacultyRepoImpl implements FacultyRepoInt{
	@Autowired
	JdbcTemplate jdbc;

	
	@Override
	public int addNewUser(FacultyBean faculty) {
		String addUserSql="insert into faculty_details (faculty_fName,faculty_lName,faculty_abbre) values (?,?,?)";
		int rowsupdated=jdbc.update(addUserSql, faculty.getfName(),faculty.getlName(),faculty.getAbbrevation());
		//System.out.println(rowsupdated);
		return rowsupdated;
	}


	@Override
	public List<Map<String, Object>> geUsers() {
		String getUserSql="select * from faculty_details";
		List<Map<String, Object>> users=jdbc.queryForList(getUserSql);
		//System.out.println(users);
		return users;
	}

	@Override
	public int deleteUsers(String empId) {
		String deleteUserSql="delete from faculty_details where id=?";
		int users=jdbc.update(deleteUserSql,Integer.valueOf(empId));
		//System.out.println(empId);
		return users;
	}

}
