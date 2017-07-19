package com.backroads.webdriver.pageobjectsfactory.pageobject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInPage {
	
	@FindBy(id = "txtPartyID")
	private WebElement partyIdField;
	@FindBy(id = "txtPassword")
	private WebElement psswdField;
	@FindBy(id = "cmdLogin")
	private WebElement loginBtn;
	
	private WebDriver driver;
	private WebDriverWait wait;
	private static final int TIMEOUT = 10;
	
	public LogInPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver, TIMEOUT);
		PageFactory.initElements(driver, this);
	}
	
	public void setPartyId(String partyId){
		wait.until(ExpectedConditions.visibilityOf(partyIdField));
		partyIdField.sendKeys(partyId);
	}
	
	public void setPassword(String strPassword){
		psswdField.sendKeys(strPassword);
	}
	
	public void clickLoginBtn(){
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
	}
	
	public HomePage loginToAtlas(String partyId, String strPassword){		
		setPartyId(partyId);
		setPassword(strPassword);
		clickLoginBtn();
		return new HomePage(driver);
	}	

}
