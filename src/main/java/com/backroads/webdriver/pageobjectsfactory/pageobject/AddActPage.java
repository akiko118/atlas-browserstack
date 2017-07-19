package com.backroads.webdriver.pageobjectsfactory.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.backroads.common.util.StringUtils;

public class AddActPage extends BasePageObject {

	@FindBy(id = "cboActivityCode")
	WebElement actCodeDropDown;
	@FindBy(id = "cboTripDeparture")
	WebElement tripDropDown;
	@FindBy(id = "cboArea")
	WebElement areaDropDown;
	@FindBy(id = "txtNotes")
	WebElement notesField;
	@FindBy(id = "txtAuthorizedBy")
	WebElement authByField;
	@FindBy(id = "txtFromDate1")
	WebElement date1Field;
	@FindBy(id = "txtFromDate2")
	WebElement date2Field;
	@FindBy(id = "txtFromDate3")
	WebElement date3Field;
	@FindBy(css = "#Table1 > tbody > tr:nth-child(10) > td:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(2) > a")
	WebElement date1CalButton;
	@FindBy(css = "#Table1 > tbody > tr:nth-child(10) > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(2) > a")
	WebElement date2CalButton;
	@FindBy(css = "#Table1 > tbody > tr:nth-child(10) > td:nth-child(2) > table > tbody > tr:nth-child(3) > td:nth-child(2) > a")
	WebElement date3CalButton;
	@FindBy(id = "txtQty1")
	WebElement date1HourField;
	@FindBy(id = "txtQty2")
	WebElement date2HourField;
	@FindBy(id = "txtQty3")
	WebElement date3HourField;
	@FindBy(id = "txtTips1")
	WebElement tipField;
	@FindBy(id = "txtNotes1")
	WebElement tipNotesField;
	@FindBy(id = "cmdUpdate")
	WebElement backToPayrollButton;	
	@FindBy(id = "cmdUpdate2")
	WebElement saveAddNotherActButton;
	
	//Calendar Window
	@FindBy(linkText = "1")
	WebElement firstDay;
	@FindBy(linkText = "2")
	WebElement secondDay;
	@FindBy(linkText = "3")
	WebElement thirdDay;
	
	
	public AddActPage(WebDriver driver) {
		super(driver);
	}
	
	public TimesheetPage fillDataAndSubmitTripUnrelatedAct(String activity, String area, String notes, String authBy, int numFields){
		String [] dates = StringUtils.getDates(numFields); //get the dates from numFileds ago.
		double hours = 1.0; //can change
		WebElement[] dateFields = {date1Field, date2Field, date3Field};
		WebElement[] hoursFields = {date1HourField, date2HourField, date3HourField};
		
		selecActivityDropDown(activity);
		selectAreaDropDown(area);
		fillNotesAndAuthBy(notes, authBy);
		
		for (int i = 0; i < numFields; i++){			
			fillDate(dates[i], dateFields[i]);
			fillHours(hours, hoursFields[i]);
		}
		backToPayrollButton.click(); 
		driver.switchTo().alert().accept();
		return PageFactory.initElements(driver, TimesheetPage.class);	}
	

	private void fillDate(String date, WebElement df){
		df.clear();
		df.sendKeys(date.toString());
	}
	
	private void fillHours(double hr, WebElement hf){
		hf.clear();
		hf.sendKeys(String.valueOf(hr));
	}
	
	private void selecActivityDropDown(String activity){
		Select select = new Select(actCodeDropDown);
		select.selectByVisibleText(activity);		
	}
	
	private void selectAreaDropDown(String area){
		Select select = new Select(areaDropDown);
		select.selectByVisibleText(area);		
	}
	
	private void fillNotesAndAuthBy(String notes, String authBy){
		notesField.sendKeys(notes);
		authByField.sendKeys(authBy);
	}
	
}