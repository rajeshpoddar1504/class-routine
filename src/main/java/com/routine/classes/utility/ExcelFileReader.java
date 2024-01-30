package com.routine.classes.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelFileReader {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getExcelData(File file) {
		List<Map<String, String>> rowList = new ArrayList<>();
		try {

			// Reading file from local directory
			FileInputStream fileIS = new FileInputStream(file);

			// Create Workbook instance holding reference to
			// .xlsx file

			XSSFWorkbook workbook = new XSSFWorkbook(fileIS);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			// Till there is an element condition holds true
			int j = 0;
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				
				//System.out.println("rownum=="+row.getRowNum());

				Map<String, String> rowMapper = new LinkedHashMap<>();
				 
				int i = 0;

				boolean rowListSize = rowList.isEmpty();
				//System.out.println("rowListSize==" + rowListSize);
				for (Iterator<Cell> cellIterator = row.cellIterator();(rowListSize?cellIterator.hasNext():i<rowList.get(0).size()); i++) {
					
					  if (cellIterator.hasNext()) {
						//System.out.println("in if case i==" + i);
						Cell cell = cellIterator.next();

						// Checking the cell type and format
						// accordingly
						switch (cell.getCellType()) {

						// Case 1
						case Cell.CELL_TYPE_NUMERIC:
							
							//  System.out.print( cell.getNumericCellValue() + "\t");
							 
							rowMapper.put("col" + i, cell.toString());
							break;

						// Case 2
						case Cell.CELL_TYPE_STRING:
							
							 // System.out.print( cell.getStringCellValue() + "\t");
							 
							rowMapper.put("col" + i, cell.toString());
							break;
						}
					} else {
						//System.out.println("in else case i==" + i);
						
						rowMapper.put("col" + i, "");
						
					}
				}
					 rowList.add(rowMapper);
			}
			//System.out.println("hjfuyf======" + rowList);
			
			fileIS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowList;
	}

}
