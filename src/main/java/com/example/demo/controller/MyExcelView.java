package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.example.demo.dto.CalcReport;

public class MyExcelView extends AbstractXlsView
{
    @SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		 String sheetName = (String)model.get("sheetname");
	        List<String> headers = (List<String>)model.get("headers");
	        List<List<CalcReport>> results = (List<List<CalcReport>>)model.get("results");
	        List<String> numericColumns = new ArrayList<String>();
	        if (model.containsKey("numericcolumns"))
	            numericColumns = (List<String>)model.get("numericcolumns");
	        //BUILD DOC
	        Sheet sheet = workbook.createSheet(sheetName);
	        sheet.setDefaultColumnWidth((short) 12);
	        int currentRow = 0;
	        short currentColumn = 0;
	        //CREATE STYLE FOR HEADER
	        CellStyle headerStyle = workbook.createCellStyle();
	        Font headerFont = workbook.createFont();
	        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	        headerStyle.setFont(headerFont); 
	        //POPULATE HEADER COLUMNS
	        Row headerRow = sheet.createRow(currentRow);
	        for(String header:headers){
	        	HSSFRichTextString text = new HSSFRichTextString(header);
	            Cell cell = headerRow.createCell(currentColumn); 
	            cell.setCellStyle(headerStyle);
	            cell.setCellValue(text);            
	            currentColumn++;
	        }
	        //POPULATE VALUE ROWS/COLUMNS
	        currentRow++;//exclude header
	        for(List<CalcReport> result: results){
	            currentColumn = 0;
	            Row row = sheet.createRow(currentRow);
	            for(CalcReport value : result){//used to count number of columns
	                Cell cell = row.createCell(currentColumn);
	                if (numericColumns.contains(headers.get(currentColumn))){
	                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	                    cell.setCellValue(value.getSiteId());
	                    cell.setCellValue(value.getExpression());
	                } else {
	                    HSSFRichTextString text = new HSSFRichTextString(value.getSiteId());                
	                    cell.setCellValue(text); 
	   //                 HSSFRichTextString text2 = new HSSFRichTextString(value.getTagname()); 
	    //                cell.setCellValue(text2);
	                }
	                currentColumn++;
	                cell = row.createCell(currentColumn);
	                HSSFRichTextString text2 = new HSSFRichTextString(value.getExpression());                
                    cell.setCellValue(text2);
                    
                    currentColumn++;
	                cell = row.createCell(currentColumn);
	                HSSFRichTextString text3 = new HSSFRichTextString(value.getTagname());                
                    cell.setCellValue(text3); 
                    
                    currentColumn++;
                    for(String str: value.getTagVal()) {
                    	cell = row.createCell(currentColumn);
    	                HSSFRichTextString text4 = new HSSFRichTextString(str);                
                        cell.setCellValue(text4);
                        currentColumn++;
                    }
	            }
	            currentRow++;
	        }
		
	}
}
