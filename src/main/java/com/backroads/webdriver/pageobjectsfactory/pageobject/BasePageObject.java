package com.backroads.webdriver.pageobjectsfactory.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
	  public WebDriver driver;
	  public WebDriverWait wait;
	  public Actions builder;
	  protected int timeOut = 60;
	 // protected UrlBuilder ub;
	 // protected String env;
	 
	  @FindBy(css = "#rootMenu1 > span > a")
	  protected WebElement home;

	  @FindBy(css = "#rootMenu2 > span > a")
	  protected WebElement logout;
	  
	  @FindBy(css = "#rootMenu3 > span")
	  protected WebElement leaderProfile;
	 
	  @FindBy(css = "#rootMenu4 > span")
	  protected WebElement viewSchedule;
	  
	  @FindBy(css = "#rootMenu5 > span")
	  protected WebElement leaderHouseBeds;
	  
	  @FindBy(css = "#rootMenu6 > span")
	  protected WebElement payrollInfo;
	  
	  @FindBy(css = "#rootMenu9 > span > a")
	  protected WebElement employeeHandbook;
	  
	  // sub menu of Payroll Info
	  @FindBy(css = "#payrollMenu1 > table > tbody > tr > td:nth-child(1) > a")
	  protected WebElement currentPayroll2;
	  
	  @FindBy(css = "#payrollMenu2 > table > tbody > tr > td:nth-child(1) > a")
	  protected WebElement pastPayroll;
	  
	  public BasePageObject(WebDriver driver) {
	    this.wait = new WebDriverWait(driver, timeOut);
	    this.builder = new Actions(driver);
	    this.driver = driver;
//	    this.ub = new UrlBuilder(env);
	    PageFactory.initElements(driver, this);
	  }
	  
	  public TimesheetPage goToPayroll(String env){
		System.out.println(env);  
		// this way kind of works with android
		if (env.toLowerCase().equals("windows") || env.toLowerCase().equals("ios")){
			wait.until(ExpectedConditions.elementToBeClickable(payrollInfo));
		    builder.moveToElement(payrollInfo).moveToElement(currentPayroll2).click().build().perform();
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(payrollInfo));
		    payrollInfo.click();
		    wait.until(ExpectedConditions.elementToBeClickable(currentPayroll2));
		    currentPayroll2.click();
		} 
		return new TimesheetPage(driver);
	 }
	  
	  
}
