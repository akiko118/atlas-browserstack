package com.backroads.atlas.testcases.UI;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.backroads.atlas_browserstack.BrowserStackTestNGTest;
import com.backroads.webdriver.common.contentpatterns.PageContent;
import com.backroads.webdriver.common.dataprovider.DataProviderSource;
import com.backroads.webdriver.common.url.UrlBuilder;
import com.backroads.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.LogInPage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.TimesheetPage;

public class AtlasLeaderOnlyUSTest012 extends BrowserStackTestNGTest{

	private LogInPage loginPage;
	private HomePage homePage;
	private TimesheetPage timesheetPage;
//	private String partyId = "1855545"; //Jenna Smith
	private String password = "bubble";

	private String partyId = "1914832"; //Casey Carr

	private String [] originalValues;   
	
	@Test(dataProvider = "forAtlasLeaderOnlyUSTest012_13", dataProviderClass=DataProviderSource.class)
	public void main(String [] inputs) throws Exception{
		driver.get(UrlBuilder.LOGIN_DEV);
		loginPage = new LogInPage(driver);
		homePage = loginPage.loginToAtlas(partyId, password);
		timesheetPage = homePage.goToPayroll();
		timesheetPage.toTimeSheetPage();
		originalValues = timesheetPage.getActualColmnValues();

    	timesheetPage.updateNumFields(inputs);
    	timesheetPage.clickSubmit();
		Assert.assertEquals(timesheetPage.getTopError(), PageContent.DIFFERENT_ACTUAL_ERROR);
		Assert.assertEquals(timesheetPage.getBottomError(), PageContent.DIFFERENT_ACTUAL_ERROR);
		//revert the values to the original for other testing.
		timesheetPage.updateNumFields(originalValues);
	}

}
