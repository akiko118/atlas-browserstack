package com.backroads.common.util;

public class QueryGenerator {
	
	public static String usPayrollQuery(double partyId, String date, String cActivityCode){
		String query = "select convert(varchar(19), dActivityFromDate, 10) date, "
				+ "iActualUnits, iOTUnits, yRate, yGratuities, yRegPay, yOTPay, iDTUnits, yDTPay "
				+ "from LeaderPayRollInterfaceus "
				+ "where  iLeaderPartyId = " + partyId
				+ " and dActivityFromDate = " + "'" + date + "'"
				+ " and cActivityCode = " + "'" + cActivityCode + "'"
				+ "order by dActivityFromDate asc;";
		System.out.println(query);
		return query;
	}
	
	public static String usPayrollQuery(double partyId, String date){
		String query = "select convert(varchar(19), dActivityFromDate, 10) date, "
				+ " iActualUnits, iOTUnits, yGratuities, yRate, yRegPay, yOTPay, iDTUnits, yDTPay "
				+ " from LeaderPayRollInterfaceus "
				+ " where  iLeaderPartyId = " + partyId
				+ " and dActivityFromDate = " + "'" + date + "' "
				+ " order by dActivityFromDate asc;";
		System.out.println(query);
		return query;
	}
	
	public static String usPayrollQuery(int payrollId, double partyId, String date, String cActivityCode){
		String query = "select convert(varchar(19), dActivityFromDate, 10) date, "
				+ "iActualUnits, iOTUnits, yRate, yGratuities, yRegPay, yOTPay, iDTUnits, yDTPay "
				+ "from LeaderPayRollInterfaceus "
				+ "where iLeaderPayRollInterfaceUSId = " + payrollId
				+ " and iLeaderPartyId = " + partyId
				+ " and dActivityFromDate = " + "'" + date + "'"
				+ " and cActivityCode = " + "'" + cActivityCode + "'"
				+ "order by dActivityFromDate asc;";
		
		System.out.println(query);
		return query;
	}
	
	public static String usPayrollQuery(int payrollId){
		String query = "select iPayPeriodID, convert(varchar(19), dActivityFromDate, 10) date, "
				+ "iActualUnits, iOTUnits, yRate, yGratuities, yRegPay, yOTPay, iDTUnits, yDTPay "
				+ "from LeaderPayRollInterfaceus "
				+ "where iLeaderPayRollInterfaceUSId = " + payrollId
				+ "order by dActivityFromDate asc;";
		
		System.out.println(query);
		return query;
	}
}
