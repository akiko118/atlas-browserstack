package com.backroads.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExelUtils {
	private static XSSFSheet ExcelWSheet;	 
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
    
    public static Object[][] getTableArray(String filePath, String sheetName, int startCol, int colRange) throws Exception { 
	    Object[][] tabArray = null;
    	try {
    		FileInputStream ExcelFile = new FileInputStream(filePath);
    		// Access the required test data sheet
    		ExcelWBook = new XSSFWorkbook(ExcelFile);
    		ExcelWSheet = ExcelWBook.getSheet(sheetName);
    		int startRow = 1;
    		int ci,cj;    		
    		int totalRows;
            //this is to handle party ID and pay period 
//    		if(startCol <=1){
//    			totalRows = 1;
//    		} else {
    		totalRows = ExcelWSheet.getLastRowNum();
    		System.out.println("totalRows: " + totalRows);
//    		}
    		
    		// you can write a function as well to get Column count
    		int totalCols = startCol + colRange + 1;

    		tabArray = new Object[totalRows][totalCols-startCol];
    		ci=0;
    		for (int i = startRow; i <= totalRows;  i++, ci++) {  
    			cj=0;
    			for (int j = startCol; j < totalCols; j++, cj++){
    				tabArray[ci][cj]=getCellData(i,j);
//  				    System.out.print(" *" + ci + " " + cj + "**");
//    				System.out.print("    " + tabArray[ci][cj]);  
    			}
 //   			System.out.println("");
    		}
    	} catch (FileNotFoundException e){
    		System.out.println("Could not read the Excel sheet");
    		e.printStackTrace();
    	} catch (IOException e){
    		System.out.println("Could not read the Excel sheet");
    		e.printStackTrace();
    	}

    	return(tabArray);
    }


	@SuppressWarnings("deprecation")
	public static Object getCellData(int RowNum, int ColNum) throws Exception {
    	try{
    		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
    		//System.out.println(Cell.toString());
    		if  (Cell.getCellTypeEnum() == CellType.BLANK) {
    			return 0.0;
    		} else if (Cell.getCellTypeEnum() == CellType.NUMERIC) {
    			Object CellData = Cell.getNumericCellValue();
    			return CellData;
    		} else {
    			Object CellData = Cell.getStringCellValue();
    			return CellData;    			
    		}
    	} catch (Exception e){
    		System.out.println(e.getMessage());
    		throw (e);
    	}
	}
/*	
	public static int getTestPayPeriod(String filePath, String tabName) throws Exception{
		double payPeriod = (double) ExelUtils.getTableArray(filePath, tabName, 0, 0)[0][0];		
		return (int) payPeriod; 		
	}
	
	public static int getTestPartyId(String filePath, String tabName) throws Exception{	
		double partyId = (double) ExelUtils.getTableArray(filePath, tabName, 1, 0)[0][0];			
		return (int) partyId; 		
	}
*/	
	public static Object[][] getTestDates(String filePath, String tabName) throws Exception{	
		return ExelUtils.getTableArray(filePath, tabName, 2, 0); 		
	}
	public static Object[][] getTestData(String filePath, String tabName) throws Exception {	
		// to get date, iActualUnits	iOTUnits yGratuities yRate yRegPay	yOTPay	iDTUnits yDTPay
	/*	
		Object[][] o = ExelUtils.getTableArray(filePath, tabName, 1, 9);
		
		for (int i = 0; i < o.length; i++) {
			System.out.print("--" + i + "--");
			for (int j = 0; j< o[i].length; j++){
				System.out.print("-" + j + "-" );
				System.out.print(o[i][j] + " ");
			}
			System.out.println("\n");
		}
	   */ 
		return ExelUtils.getTableArray(filePath, tabName, 1, 10); 		
	}
/*	
	public static void main (String[] args) throws Exception{
		String testDataFilePath = "C:\\br_db\\DatabaseScripts\\AtlasCalcTestData.xlsx";
		String excelTabName = "TS73US";
		Object [][] td = getTestDates(testDataFilePath, excelTabName);
		System.out.println(td[0][0]);
		System.out.println(td[td.length-1][0]);
		System.out.println(getTestPartyId(testDataFilePath, excelTabName));
	}
*/
}

