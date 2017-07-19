package com.backroads.atlas.testcases.calculation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.backroads.common.util.ExelUtils;
import com.backroads.common.util.QueryGenerator;
import com.backroads.payroll.dataprovider.PayrollDataProviderSource;


public class PayrollCalculationTests_US {
	private Connection connection = null;
	private static Statement statement = null;
	private static ResultSet rs = null;
	private int partyId;
//	private int payPeriod;
//	private String activityDateField = "dActivityFromDate";
	
	
	@BeforeClass
	public void setUp() {
		String databaseURL = "jdbc:sqlserver://172.18.1.60\\ibis6stage:1433";
	    String user = "appuser";
	    String password = "appuser";
	    try {
	    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        System.out.println("Connecting to Database...");
	        connection = DriverManager.getConnection(databaseURL, user, password);
	        if (connection != null) {
	        	System.out.println("Connected to the Database...");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } catch (ClassNotFoundException ex) {
	        ex.printStackTrace();
	    }
    }
	/*
	@Test (dataProvider = "TS73US_Data", dataProviderClass=PayrollDataProviderSource.class)	
	public void TS73US_test(String actCode, double partyId, String date, double iActualUnits, double iOTUnits, double yGratuities, double yRate, double yRegPay, double yOTPay, double iDTUnits, double yDTPay) {
		testCalculationTable(actCode, partyId, date, iActualUnits, iOTUnits, yGratuities, yRate, yRegPay, yOTPay, iDTUnits, yDTPay);
	}
	*/	
	@Test (dataProvider = "TS80_83_89US_Data", dataProviderClass=PayrollDataProviderSource.class)	
	public void TS80_83_89US_test(String actCode, double partyId, String date, double iActualUnits, double iOTUnits, double yGratuities, double yRate, double yRegPay, double yOTPay, double iDTUnits, double yDTPay) {
		testCalculationTable(actCode, partyId, date, iActualUnits, iOTUnits, yGratuities, yRate, yRegPay, yOTPay, iDTUnits, yDTPay);
	}
	/*
	
	@Test (dataProvider = "TS85US_Data", dataProviderClass=PayrollDataProviderSource.class)	
	public void TS85US_test(String actCode, double partyId, String date, double iActualUnits, double iOTUnits, double yGratuities, double yRate, double yRegPay, double yOTPay, double iDTUnits, double yDTPay) {
		testCalculationTable(actCode, partyId, date, iActualUnits, iOTUnits, yGratuities, yRate, yRegPay, yOTPay, iDTUnits, yDTPay);
	}
	
	@Test (dataProvider = "TS76_77US_Data", dataProviderClass=PayrollDataProviderSource.class)	
	public void TS76_77US_test(String actCode, double partyId, String date, double iActualUnits, double iOTUnits, double yGratuities, double yRate, double yRegPay, double yOTPay, double iDTUnits, double yDTPay) {
		testCalculationTable(actCode, partyId, date, iActualUnits, iOTUnits, yGratuities, yRate, yRegPay, yOTPay, iDTUnits, yDTPay);
	}
	
	@Test (dataProvider = "TS79US_Data", dataProviderClass=PayrollDataProviderSource.class)	
	public void TS79US_test(String actCode, double partyId, String date, double iActualUnits, double iOTUnits, double yGratuities, double yRate, double yRegPay, double yOTPay, double iDTUnits, double yDTPay) {
		testCalculationTable(actCode, partyId, date, iActualUnits, iOTUnits, yGratuities, yRate, yRegPay, yOTPay, iDTUnits, yDTPay);
	}

	@Test (dataProvider = "TS78_81US_Data", dataProviderClass=PayrollDataProviderSource.class)	
	public void TS78_81US_test(String actCode, double partyId, String date, double iActualUnits, double iOTUnits, double yGratuities, double yRate, double yRegPay, double yOTPay, double iDTUnits, double yDTPay) {
		testCalculationTable(actCode, partyId, date, iActualUnits, iOTUnits, yGratuities, yRate, yRegPay, yOTPay, iDTUnits, yDTPay);
	}	

	*/
	public void testCalculationTable(String actCode, double partyId, String date, double iActualUnits, double iOTUnits, double yGratuities, double yRate, double yRegPay, double yOTPay, double iDTUnits, double yDTPay) {
		try {
			String query = QueryGenerator.usPayrollQuery(partyId, date, actCode);	
	        statement = connection.createStatement();  
	        rs = statement.executeQuery(query);
	        //System.out.println(query);
	       
            Assert.assertTrue(rs.next());
	        while(rs.next()){
	            Assert.assertEquals(rs.getString("cActivityCode"), actCode);
	            Assert.assertEquals(rs.getDouble("iActualUnits"), iActualUnits);
	            Assert.assertEquals(rs.getDouble("yGratuities"), yGratuities);
	            Assert.assertEquals(rs.getDouble("iOTUnits"), iOTUnits);
	            Assert.assertEquals(rs.getDouble("yRate"), yRate);
	            Assert.assertEquals(rs.getDouble("yRegPay"), yRegPay);
	            Assert.assertEquals(rs.getDouble("yOTPay"), yOTPay);
	            Assert.assertEquals(rs.getDouble("iDTUnits"), iDTUnits);
	            Assert.assertEquals(rs.getDouble("yDTPay"), yDTPay);
	        }
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    }
    }
	
	@AfterClass
	public void tearDown() {
		if (connection != null) {
			try {
				System.out.println("Closing Database Connection...");
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
