package com.backroads.webdriver.common.dataprovider;


import org.testng.annotations.DataProvider;

public class DataProviderSource {
	private DataProviderSource() {
	}

	@DataProvider(name = "AtlasLeaderOnlyUSTest014")
	public static final Object [][] getUSActualData() {
	
		return new Object[][]{ 
			new String [] {"10.5", "13.5", "9.00", "8.5", "11.00", "10.5", "8.5", "10.5", "8.5", "0.5"},
			new String [] {"9.5", "7.5", "9.5", "7.5", "0", "9.5", "7.5", "9.5", "7.5", "0" }
		};
		
	}
	
	public static final String [] getUSIncreasedActual(){
		return new String [] {"10.5", "8.5", "10.5", "8.5", "0.5", "10.5", "8.5", "10.5", "8.5", "0.5"};
	}
	
	public static final String [] getUSDecreasedActual(){
		return new String [] {"9.5", "7.5", "9.5", "7.5", "0", "9.5", "7.5", "9.5", "7.5", "0"};
	}
	
	public static final String [][] forAtlasLeaderOnlyUSTest015(){
		return new String [][] {
			new String [] {"Lead Inn Trip", "WPJI", "R1B", "02/12/2017"},
			new String [] {"", "WPJI", "R1B", "02/13/2017"},
			new String [] {"", "WPJI", "R1B", "02/14/2017"},
			new String [] {"", "WPJI", "R1B", "02/15/2017"},
			new String [] {"Gratuities", "WPJI", "R1B", "02/15/2017"},
			new String [] {"Lead Inn Trip", "WPJI", "R1B", "02/19/2017"},
			new String [] {"", "WPJI", "R1B", "02/20/2017"},
			new String [] {"", "WPJI", "R1B", "02/21/2017"},
			new String [] {"", "WPJI", "R1B", "02/22/2017"},
			new String [] {"Gratuities", "WPJI", "R1B", "02/22/2017"}
		};
	}
	
	@DataProvider(name = "forAtlasLeaderOnlyUSTest012_13")
	public static final String [][] forAtlasLeaderOnlyUSTest012_13(){
		return new String [][] {
			new String [] {"10.50", "12.00", "10.00", "11.50", "9.25", "0", "10.00", "11.00", "13.00", "9.75", "0"},
			new String [] {"9.00", "12.00", "8.00", "10.00", "8.50", "0", "8.50", "8.50", "8.50", "8.50", "0"}
		};
	}
	
	
	@DataProvider(name = "forAtlasLeaderOnlyUSTest014")
	public static Double [][] forAtlasLeaderOnlyUSTest014(){		
		return new Double [][] {
			new Double [] {0.5},
			new Double [] {-0.5}
		};
	}
	
	@DataProvider(name = "AtlasLeaderOnlyUSTest016_Non_Trip")
	public static final String [][] AtlasLeaderOnlyUSTest016_Non_Trip(){
		return new String [][] {
			new String [] {"Fam", "(Global)            ", "automation", "automation", "3"},
			new String [] {"GRAF", "(Global)            ", "automation", "automation", "2"},
			new String [] {"Euro Drive School                       ", "(Global)            ", "automation", "automation", "2"}
		};
	}
}
