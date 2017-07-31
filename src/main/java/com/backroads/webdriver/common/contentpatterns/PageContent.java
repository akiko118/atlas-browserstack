package com.backroads.webdriver.common.contentpatterns;


public class PageContent {
	
	//For AtlasLeaderOnlyUSTest005()
	public static final String [] LEADING_COLUMN_HEADER_TEXTS = {"Activity", "Trip", "Type", "Date", "Day", "Regular Day", "Dinner Day",
			"Expected Hours for Regular Day", "Expected Hours for Dinner Day", "Actual", "Notes", "Submission Deadline", "Accept"};

	//For AtlasLeaderOnlyUSTest007()
	public static final String PAY_PERIOD_LABEL = "Pay Period:";
	public static final String PAY_DATE_LABEL = "Pay Date:";	
	public static final String PAY_PERIOD = "  7/16/2017  to  7/31/2017";
	public static final String PAY_DATE = "8/7/2017";
	
	//For AtlasLeaderOnlyUSTest008()
	public static final String TERMS_HEADER = "Atlas Payroll Terms";
	public static final String TERMS_TEXT_US = "I have reviewed my time records for the week/pay period noted. The time records accurately reflect the total hours worked in the work week, and, for work done in the State of California, for the date noted. For work outside of California, I understand that as long as the total time I worked for the week is accurate, I need not make adjustments that would otherwise cancel each other out in that workweek although I can if I want (e.g., add 1 hour to Wednesday and subtract 1 hour from Thursday).\n"
                                             + "Where necessary to conform to the actual hours worked, I have adjusted the estimated times up or down to reflect those hours I actually worked.\n"
                                             + "I have recorded all time worked, and I understand that it is against the policy of Backroads Utah to work off the clock; and I have not done so.\n"
                                             + "I am familiar with Backroads Utah\'s meal and rest period policy. I have been provided meal and rest periods on a daily basis in this pay period that are consistent with that policy. I understand the procedure for reporting when I did not receive a meal or rest period, and I have utilized that procedure to report any such situation on any particular day.";

	//AtlasLeaderOnlyUSTest012_13
	public static final String DIFFERENT_ACTUAL_ERROR = "Regular hours selected but the Actual hours differ. Please provide a note to explain.";
	
	//AtlasLeaderOnlyUSTest016
	public static final String ACTIVITY = "Assigned TE - hourly";
	//public static final String 
	
	//For AtlasLeaderOnlyUSTest022()
	public static final String [] OTHER_COLUMN_HEADER_TEXTS = {"Activity", "Trip", "Type", "Date", "Day", "Expected Hours", "Actual", 
			"Notes", "Submission Deadline", "Accept"};
			

}
