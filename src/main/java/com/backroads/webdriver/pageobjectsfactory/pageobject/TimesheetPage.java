package com.backroads.webdriver.pageobjectsfactory.pageobject;



import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.backroads.common.util.StringUtils;
import com.backroads.webdriver.common.url.UrlBuilder;
import com.backroads.webdriver.common.util.TableHandler;
import com.backroads.webdriver.common.util.WebElementUtils;
import com.backroads.webdriver.pageobjectsfactory.pageobject.AddActPage;

public class TimesheetPage extends BasePageObject{
	
	private int regDayColLoc = 5; // radio button column location for Regular Day
	private int dinDayColLoc = 6; // radio button column location for Dinner Day
	private int actualColLoc = 9;
	private int activityColLoc = 0;
	
	@FindBy(id = "cmdSubmit")
	WebElement toPayrollPageBtn;
	@FindBy(css = "#grdPayroll > tbody > tr:nth-child(1)")
	WebElement lTableColHeader;
	@FindBy(css = "#GridViewOther > tbody > tr:nth-child(1)")
	WebElement oTableColHeader;
	@FindBy(linkText = "here")
	WebElement termsLink;
	@FindBy(css = "#cmdSubmit")
	WebElement sbmtTimeSheetBtn;
	@FindBy(id = "cmdAdd")
	WebElement addActBtn;
	@FindBy(id = "cmdAddTip")
	WebElement addTip;
	@FindBy(css = "body > h1")
	WebElement termsWindowHeader;
	@FindBy(css = "body > ol")
	WebElement termsWindowText;
	@FindBy(css = "#frmPayroll > div > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td:nth-child(1)")
	WebElement payPeriodLabel;
    @FindBy(css = "#frmPayroll > div > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td:nth-child(2)")
    WebElement payPeriod;
    @FindBy(css = "#frmPayroll > div > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td:nth-child(1)")
    WebElement payDateLabel;
    @FindBy(id = "lblPayDate")
    WebElement payDate;
    @FindBy(css = "#grdPayroll > tbody")
    WebElement leaderActTable;
    @FindBy(id = "lblMessageTop")
    WebElement errorLocTop;
    @FindBy(id = "lblMessageBottom")
    WebElement errorLocBottom;
    @FindBy(id = "grdPay2")
    WebElement submittedTable;
   
	public TimesheetPage(WebDriver driver) {
		super(driver);
	}
	
	public void toTimeSheetPage (){
		wait.until(ExpectedConditions.elementToBeClickable(toPayrollPageBtn));
		if (driver.getCurrentUrl().equals(UrlBuilder.PAYROLL_DELINQUENT_DEV)){
			toPayrollPageBtn.click();
		}		
	}
	
	public void clickTermsLink(){
		this.wait.until(ExpectedConditions.elementToBeClickable(termsLink));
		termsLink.click();
	}
	
	// Return Terms window header in [0] and terms text in [1] - for getting a pop up 
	public String [] getTermsWindowTextsFromPopUp(){
		String parentHandle = driver.getWindowHandle(); 
		String [] windowTexts = new String[2];
		clickTermsLink(); // click some link that opens a new window

		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}

	    windowTexts[0] = termsWindowHeader.getText();
	    windowTexts[1] = termsWindowText.getText();
		driver.close(); // close newly opened window when done with it
		driver.switchTo().window(parentHandle); // switch back to the original window'
		
		return windowTexts;
	}
	// Return Terms window header in [0] and terms text in [1] - for getting a window
	public String [] getTermsWindowTextsFromWindow(){
		String parentHandle = driver.getWindowHandle(); 
		String [] windowTexts = new String[2];
		Set s = driver.getWindowHandles();
		Iterator ite = s.iterator();
		

		while(ite.hasNext()){
			String anotherWinHandle = ite.next().toString();
			if(!anotherWinHandle.contains(parentHandle)) {
				driver.switchTo().window(anotherWinHandle);				
			}
		}
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    windowTexts[0] = termsWindowHeader.getText();
	    windowTexts[1] = termsWindowText.getText();
 		
		driver.close(); // close newly opened window when done with it
		driver.switchTo().window(parentHandle); // switch back to the original window'
		
		return windowTexts;				
	}
	
	
	public <E> List<WebElement> getLeaderTableHeaderTexts(){
		return lTableColHeader.findElements(By.tagName("th"));		
	}
	
	public <E> List<WebElement> getOtherActHeaderTexts(){
		return TableHandler.getHiddenColRemovedList(oTableColHeader, "th");		
	}
	
	public String getPayPeriod(){
		return payPeriod.getText();
	}
	
	public String getPayDate(){
		return payDate.getText();
	}
	
	public String getPayPeriodLabel(){
		return payPeriodLabel.getText();
	}
	
	public String getPayDateLabel(){
		return payDateLabel.getText();
	}
	
	public boolean isLeaderActDatesAsc(){
	    List<WebElement> dates = TableHandler.getColumnsAsList(leaderActTable, "td", 3);    
	    
	    for (int i = 0; i < dates.size()-2; i++){
	    	Date date1 = StringUtils.toStringMMddyyyWithSlash(dates.get(i).getText());
	    	Date date2 = StringUtils.toStringMMddyyyWithSlash(dates.get(i+1).getText());	    	
	    	if (date1.after(date2)){
	    		return false;
	    	}
	    }
	    return true;
	}
	
	//colNum1 is the column number of Regular Day or Dinner Day.
	//colNum2 is the column number of Expected Hours for Regular Day or Expected Hours for Dinner Day.
	//WORKS WITH AtlasLeaderOnlyUSTest011_Regular() AND AtlasLeaderOnlyUSTest011_Dinner()
	public boolean isDayColRepresentExpHoursColmn(int colNum1, int colNum2){
		String expectedText = "NA";
		String gratuitiesOrRadioText = "";
		List <WebElement> regDayColmns = TableHandler.getColumnsAsList(leaderActTable, "td", colNum1);
		List <WebElement> expectedColumnTexts = TableHandler.getColumnsAsList(leaderActTable, "td", colNum2);
		for (int i = 0; i < regDayColmns.size(); i++) {		
			//String expectedColumnText = TableHandler.getColumnsAsStrArr(leaderActTable, "td", colNum2)[i];
			String expectedColumnText = expectedColumnTexts.get(i).getText();
			String regDayColumnText = regDayColmns.get(i).getText();
			WebElement regDayColumn = regDayColmns.get(i);
			
			if (regDayColumnText.equals(gratuitiesOrRadioText)) {
				try {
					if(regDayColumn.findElement(By.tagName("input")) != null) {
						if(!StringUtils.isNumeric(expectedColumnText)) {
							return false;
						}
					}
				} catch (Exception e) {
					if(!expectedColumnText.equals(expectedText)){
						return false;
					}
				}
			} else if (regDayColumnText.equals(expectedText)) {
				if(!expectedColumnText.equals(expectedText)) {
					return false;
				}
			} else {
				System.out.println("Regular Day column doesn't have expected values");
				return false;
			}
		}
		return true;
	}
	
	
	/*
	tableElm is a table element.
	colNum1 is the column number of Regular Day column.
	colNum2 is the column number of Dinner Day column.
	htmlAttr is the html attributes used to distinguish the radio button from other attributes within a column.
	radioValue is to store the columns' html attribute name.  radioValue1 corresponds with colNum1, and so forth.
	tagName should be td or th.  the assumption here is the same tag name is used for organizing the two columns.
	*/
	public boolean isRadioWorking(){

		String regRadioValue = "RadioButtonRegular";
		String dinRadioValue = "RadioButtonExtended";
		String tagName = "td";
		String htmlAttr = "value";

		List <WebElement> regDayColmns = TableHandler.getColumnsAsList(leaderActTable, tagName, regDayColLoc);
		List <WebElement> dinDayColmns = TableHandler.getColumnsAsList(leaderActTable, tagName, dinDayColLoc);
		int rowNum = regDayColmns.size();
		
		for (int i = 0; i < rowNum; i++) {	

			try {
				WebElement regDayColElm = regDayColmns.get(i).findElement(By.tagName("input"));
				WebElement dinDayColElm = dinDayColmns.get(i).findElement(By.tagName("input"));
				
				if(regDayColElm.getAttribute(htmlAttr).equals(regRadioValue) && dinDayColElm.getAttribute(htmlAttr).equals(dinRadioValue)){

					if (!WebElementUtils.isRadioMutuallyExclusive(regDayColElm, dinDayColElm)){
						return false;
					}
				}
			} catch (NoSuchElementException e) {
				continue;
			}
		}
	    return true;
	}
	
    // the array size needs to be the same as the number of the input table.
	public void updateNumFields(String [] inputs){
		String tagName = "td";	
		List <WebElement> actualColumns = TableHandler.getColumnsAsList(leaderActTable, tagName, actualColLoc);			
		TableHandler.fillAllInputFieldInColumn(actualColumns, inputs);
	}
	
	public void updateNumFields(double increaseBy) {
		String [] colValues = getActualColmnValues();
		System.out.println("updateNumFields colValues: " +colValues.length);
		String [] newColValues = StringUtils.increasedNumValues(increaseBy, colValues);
		System.out.println("updateNumFields newColValues: " +newColValues.length);
		updateNumFields(newColValues);
	}
	
	//returns 
	public String [] getOneRowData(String searchStr) {
		List<WebElement> rowElements = TableHandler.getARowElements(submittedTable, searchStr, "td", activityColLoc);
		String [] rowStrings = new String [rowElements.size()];
		for (int i = 0; i < rowElements.size(); i++){
			rowStrings[i] = rowElements.get(i).getText();
		}
		return rowStrings;
	}
	
		
    public String [] getActualColmnValues() {
    	return TableHandler.getColumnsAsStrArr(leaderActTable, "td", actualColLoc);    	
    }
    
    public String getTopError(){
    	return errorLocTop.getText();
    }
    
    public String getBottomError() {
    	return errorLocBottom.getText();
    }
    
    public void clickSubmit() {
    	sbmtTimeSheetBtn.click();
    }
    
    public AddActPage clickAddActButton(){
    	addActBtn.click();
    	return PageFactory.initElements(driver, AddActPage.class);
    }
	
}
