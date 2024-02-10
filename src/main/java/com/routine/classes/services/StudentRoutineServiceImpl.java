package com.routine.classes.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.routine.classes.repository.RepoInt;
import com.routine.classes.utility.ExcelFileReader;
import com.routine.classes.utility.FileOperations;

@Service
public class StudentRoutineServiceImpl implements StudentRoutineSevice{
	@Autowired
	RepoInt repoInt;
	@Autowired
	ExcelFileReader exeFileReader;
	@Autowired
	FileOperations fileOperations;
	
	
	@Override
	public List<Map<String, Object>> getStudentRoutine() {
		//System.out.println(repoInt.getStudentRoutine());
		
		return repoInt.getStudentRoutine();
	}
	
	
	@Override
	public String updateStudentRoutine(MultipartFile miltPrtFile) {
		
		try {
			
			List<Map<String, Object>> fileData=exeFileReader.getExcelData(convert(miltPrtFile));
			
			repoInt.updateStudentRoutine(fileData);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		return "Success";
		
	}
	@Override
	public void updateFacultyRoutine(MultipartFile miltPrtFile, String select_faculty) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> fileData=exeFileReader.getExcelData(convert(miltPrtFile));
		repoInt.updateFacultytRoutine(fileData,select_faculty);
		
	}
	
	public File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close(); //IOUtils.closeQuietly(fos);
        } catch (IOException e) {
            convFile = null;
        }

        return convFile;
    }

	@Override
	public List<Map<String, Object>> getFacultyRoutine() {
		// TODO Auto-generated method stub
		return repoInt.getFacultyRoutine();
	}

	@Override
	public List<Map<String, Object>> getStudentRoutineByBatch(String batchId) {
		
		
		
		return repoInt.getStudentRoutineByBatch(batchId);
	}
}
