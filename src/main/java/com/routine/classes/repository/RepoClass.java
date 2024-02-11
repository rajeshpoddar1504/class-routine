package com.routine.classes.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class RepoClass implements RepoInt {

	@Autowired
	JdbcTemplate jdbcTemp;

	@Override
	public List<Map<String, Object>> getStudentRoutine() {
		String sqlForStudentRountine = "SELECT * FROM batch_time_day_details";
		return jdbcTemp.queryForList(sqlForStudentRountine);
	}
	
	@Override
	public List<Map<String, Object>> getFacultyRoutine() {
		String sqlForFacultyRountine = "SELECT * FROM faculty_time_table";
		return jdbcTemp.queryForList(sqlForFacultyRountine);
	}
	@Override
	public String updateStudentRoutine(List<Map<String, Object>> data) {
		int columnNum = data.get(0).size();
		int rowsUpdated = 0;

		try {
			String deleteStudentRecordSql = "delete from student_time_table";
			jdbcTemp.update(deleteStudentRecordSql);

			String sqlForStudentRountine = "insert into student_time_table (column_names)  values (question_mark)";

			sqlForStudentRountine = getStudentPreparedStatement(columnNum, sqlForStudentRountine);

			for (int i = 0; i < data.size(); i++) {

				Object[] dataMap = data.get(i).values().toArray();

				rowsUpdated = jdbcTemp.update(sqlForStudentRountine, dataMap);
				//System.out.println(rowsUpdated);
				
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return "Success";
	}

	@Override
	public void updateFacultytRoutine(List<Map<String, Object>> fileData, String select_faculty) {

		int columnNum = fileData.get(0).size();
		int rowsUpdated = 0;
		try {
			String sqlForFacultyRountine = "";

			if (select_faculty.equalsIgnoreCase("bulk_update")) {
				String deleteFacultyRecordSql = "delete from faculty_time_table";
				jdbcTemp.update(deleteFacultyRecordSql);
				sqlForFacultyRountine = "insert into faculty_time_table (column_names)  values (question_mark)";
				sqlForFacultyRountine = getFacultyPreparedStatement(columnNum, sqlForFacultyRountine);

			} else {
				String deleteFacultyRecordSql = "delete from faculty_time_table where faculty=" + select_faculty;
				jdbcTemp.update(deleteFacultyRecordSql);

				sqlForFacultyRountine = "insert into faculty_time_table (column_names)  values (question_mark)";
				sqlForFacultyRountine = getFacultyPreparedStatement(columnNum, sqlForFacultyRountine);

			}

			for (int i = 0; i < fileData.size(); i++) {
				Object[] dataMap = fileData.get(i).values().toArray();
				rowsUpdated = jdbcTemp.update(sqlForFacultyRountine, dataMap);

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private String getFacultyPreparedStatement(int size, String sql) {

		String columnNm = "";
		String values = "";

		for (int i = 0; i < size; i++) {
			if (i == 0) {
				columnNm = columnNm + "faculty,";
				values = values + "?,";
			} else if (i == 1) {
				columnNm = columnNm + "day,";
				values = values + "?,";
			} else {
				columnNm = columnNm + "ts" + (i - 1) + ",";
				values = values + "?,";
			}
		}
		sql = sql.replace("column_names", columnNm.substring(0, columnNm.length() - 1));

		sql = sql.replace("question_mark", values.substring(0, values.length() - 1));
		return sql;
	}

	private String getStudentPreparedStatement(int size, String sql) {

		String columnNm = "";
		String values = "";

		for (int i = 0; i < size; i++) {
			if (i == 0) {
				columnNm = columnNm + "day,";
				values = values + "?,";
			} else if (i == 1) {
				columnNm = columnNm + "year,";
				values = values + "?,";
			} else {
				columnNm = columnNm + "ts" + (i - 1) + ",";
				values = values + "?,";
			}
		}
		sql = sql.replace("column_names", columnNm.substring(0, columnNm.length() - 1));

		sql = sql.replace("question_mark", values.substring(0, values.length() - 1));
		return sql;
	}

	@Override
	public List<Map<String, Object>> getStudentRoutineByBatch(String batchId) {
		String sqlForStudentRountine = "SELECT * FROM batch_time_day_details where batch_abbr=?";
		//System.out.println(jdbcTemp.queryForList(sqlForStudentRountine,batchId));
		return jdbcTemp.queryForList(sqlForStudentRountine,batchId);
	}

}
