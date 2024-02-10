package com.routine.classes.utility;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;
 

public class UserPDFExporter {
	
	@Value("${pdf.main.heading}")
	String mainHead;
	@Value("${pdf.sub.heading.department}")
	String subHeadDepart;
	@Value("${pdf.sub.heading.university}")
	String subHeadUniversity;
	
	@Value("${pdf.sub.heading.validity}")
	String subHeadValidity;
	
	
    private List<Map<String,Object>> scheduleMapList;
    Stream stream;
    int columnsNm;
    List<Object> headerValues;
     String batchDesc;
    public UserPDFExporter(List<Map<String,Object>> scheduleMap, String batchDesc) {
    	System.out.println(scheduleMap);
        this.scheduleMapList = scheduleMap;
        headerValues=scheduleMap.get(0).keySet().stream().filter(e->!(e.toString().equals("day"))).collect(Collectors.toList());
        columnsNm= headerValues.size()+1;
        this.batchDesc=batchDesc;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPaddingLeft(2);
        cell.setPaddingRight(2);
        cell.setPaddingTop(10);
        cell.setPaddingBottom(10);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);
        cell.setPhrase(new Phrase("Day/Time", font));
        table.addCell(cell);
        headerValues.forEach(e->{
        	
        	cell.setPhrase(new Phrase(e.toString(), font));
            
            table.addCell(cell);
        });    
    }
     
    private void writeTableData(PdfPTable table) {
    	PdfPCell cell = new PdfPCell();
    	cell.setPadding(5);
    	Font font = FontFactory.getFont(FontFactory.COURIER);
    	font.setSize(12);
        font.setColor(Color.BLACK);
        
        for (Map<String,Object> map: scheduleMapList) {
        	String currentDay=map.get("day").toString();
        	cell.setPhrase(new Phrase(map.get("day").toString(),font));
        	table.addCell(cell);
        	map.values().stream().filter(e->!e.equals(currentDay)).forEach(e->{
        	cell.setPhrase(new Phrase(e==null?"":e.toString(),font));
        	table.addCell(cell);
        	}
        			);
        	
        	
			
		}
    	
		/*
		 * for (int i=1;i<scheduleMapList.size();i++) { ArrayList list=new
		 * ArrayList<>(scheduleMapList.get(i).values());
		 * 
		 * for (int j=0;j<columnsNm;j++) {
		 * 
		 * cell.setPhrase(new Phrase(list.get(j).toString(),font)); table.addCell(cell);
		 * 
		 * }
		 * 
		 * }
		 */
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        
        //System.out.println();
        
        document.open();
        Font mainHFont = FontFactory.getFont(FontFactory.TIMES);
        mainHFont.setSize(15);
        mainHFont.setColor(Color.BLACK);
        mainHFont.setStyle(Font.UNDERLINE);
         
        Font subHDFont = FontFactory.getFont(FontFactory.TIMES);
        subHDFont.setSize(13);
        subHDFont.setColor(Color.BLACK);
        
       
        Font effectFromFont = FontFactory.getFont(FontFactory.TIMES);
        effectFromFont.setSize(10);
        effectFromFont.setColor(Color.BLACK);
        
        Paragraph mainHP = new Paragraph("Class Routine 2024", mainHFont);
        mainHP.setAlignment(Paragraph.ALIGN_CENTER);
         
        Paragraph subDepart = new Paragraph("Department of Computer Science and Engineering", subHDFont);
        subDepart.setAlignment(Paragraph.ALIGN_CENTER);
        
        Paragraph subUni = new Paragraph("Bangamata Sheikh Fojilatunnesa Mujib Science and Technology University", subHDFont);
        subUni.setAlignment(Paragraph.ALIGN_CENTER);
        
        Paragraph subEffe = new Paragraph("Year/Semester: "+batchDesc, subHDFont);
        subEffe.setAlignment(Paragraph.ALIGN_CENTER);
        
        
        mainHP.setAlignment(Paragraph.ALIGN_CENTER);
        subDepart.setAlignment(Paragraph.ALIGN_CENTER);
        subUni.setAlignment(Paragraph.ALIGN_CENTER);
        subEffe.setAlignment(Paragraph.ALIGN_CENTER);
        
        
        document.add(mainHP);
        document.add(subDepart);
        document.add(subUni);
        document.add(subEffe);
        
         
        PdfPTable table = new PdfPTable(columnsNm);
        
        table.setWidthPercentage(100f);
       // table.setWidths();
        table.setWidths(getWidthArray());
        table.setSpacingBefore(5);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
    private float[] getWidthArray(){
    	float[] widthArr=new float[columnsNm];
    	for(int i=0;i<columnsNm;i++) {
    		if(i==0) {
    			widthArr[i]=2.2f;
    		}else {
    			widthArr[i]=3.5f;
    		}
    	}
    	return widthArr;
    	
    }
}
