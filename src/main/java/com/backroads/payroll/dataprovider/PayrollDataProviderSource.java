package com.backroads.payroll.dataprovider;

import org.testng.annotations.DataProvider;

import com.backroads.common.util.ExelUtils;

public class PayrollDataProviderSource {
	
	public static final String testDataFilePath = "C:\\br_db\\DatabaseScripts\\AtlasCalcTestData.xlsx";
	
	private PayrollDataProviderSource() {
		
	}
	
	@DataProvider
	public static Object[][] TS73US_Data() throws Exception{
		String excelTabName = "TS73US";
		Object [][] testObjArray = ExelUtils.getTestData(testDataFilePath, excelTabName); 
		for (int i=0; i < testObjArray.length; i++){
			for (int j=0; j < testObjArray[i].length; j++){
				System.out.println(testObjArray[i][j]);
			}
		}
	    return (testObjArray);
	}	
	
	@DataProvider
	public static Object[][] TS74US_Data() throws Exception{
		String excelTabName = "TS74US";
		Object [][] testObjArray = ExelUtils.getTestData(testDataFilePath, excelTabName); 
	    return (testObjArray);
	}
	
	@DataProvider
	public static Object[][] TS77_91US_Data() throws Exception{
		String excelTabName = "TS77_91US";
		Object [][] testObjArray = ExelUtils.getTestData(testDataFilePath, excelTabName); 
	    return (testObjArray);
	}
	
	@DataProvider
	public static Object[][] TS78_81US_Data() throws Exception{
		String excelTabName = "TS78_81US";
		Object [][] testObjArray = ExelUtils.getTestData(testDataFilePath, excelTabName); 
	    return (testObjArray);
	}	
	
	@DataProvider
	public static Object[][] TS84US_Data() throws Exception{
		String excelTabName = "TS84US";
		Object [][] testObjArray = ExelUtils.getTestData(testDataFilePath, excelTabName); 
	    return (testObjArray);
	}
	
	@DataProvider
	public static Object[][] TS80_83_89US_Data() throws Exception{
		String excelTabName = "TS80_83_89US";
		Object [][] testObjArray = ExelUtils.getTestData(testDataFilePath, excelTabName); 
	    return (testObjArray);
	}
	
	@DataProvider
	public static Object[][] TS85US_Data() throws Exception{
		String excelTabName = "TS85US";
		Object [][] testObjArray = ExelUtils.getTestData(testDataFilePath, excelTabName); 
	    return (testObjArray);
	}
	
	@DataProvider
	public static Object[][] TS76_77US_Data() throws Exception{
		String excelTabName = "TS76_77US";
		Object [][] testObjArray = ExelUtils.getTestData(testDataFilePath, excelTabName); 
	    return (testObjArray);
	}
	
	@DataProvider
	public static Object[][] TS79US_Data() throws Exception{
		String excelTabName = "TS79US";
		Object [][] testObjArray = ExelUtils.getTestData(testDataFilePath, excelTabName); 
	    return (testObjArray);
	}
}
