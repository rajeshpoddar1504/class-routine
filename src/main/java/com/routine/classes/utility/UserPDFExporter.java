package com.routine.classes.utility;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

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
    private List<Map<String,Object>> scheduleMapList;
    Stream stream;
    int columnsNm;
    List<Object> headerValues;
     
    public UserPDFExporter(List<Map<String,Object>> scheduleMap) {
        this.scheduleMapList = scheduleMap;
        headerValues=scheduleMap.get(0).values().stream().filter(e->!(e.toString().equals(""))).collect(Collectors.toList());
        columnsNm= headerValues.size();
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
    	
    	for (int i=1;i<scheduleMapList.size();i++) {
    		ArrayList list=new ArrayList<>(scheduleMapList.get(i).values());
    		
    		for (int j=0;j<columnsNm;j++) {
    			cell.setPhrase(new Phrase(list.get(j).toString(),font));
    			table.addCell(cell);
    		}
    		
    	}
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Class Schedule", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(columnsNm);
        
        table.setWidthPercentage(110f);
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
    		if(i==0||i==1) {
    			widthArr[i]=2.2f;
    		}else {
    			widthArr[i]=3.5f;
    		}
    	}
    	return widthArr;
    	
    }
}
