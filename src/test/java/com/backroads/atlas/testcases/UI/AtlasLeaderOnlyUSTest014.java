package com.backroads.atlas.testcases.UI;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.backroads.atlas_browserstack.BrowserStackTestNGTest;
import com.backroads.common.util.StringUtils;
import com.backroads.webdriver.common.contentpatterns.PageContent;
import com.backroads.webdriver.common.dataprovider.DataProviderSource;
import com.backroads.webdriver.common.url.UrlBuilder;
import com.backroads.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.LogInPage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.TimesheetPage;

public class AtlasLeaderOnlyUSTest014 extends BrowserStackTestNGTest{

	private LogInPage loginPage;
	private HomePage homePage;
	private TimesheetPage timesheetPage;
//	private String partyId = "1855545"; //Jenna Smith
	private String password = "bubble";

	private String partyId = "1914832"; //Casey Carr

	private String [] originalValues;   
	
	@Test(dataProvider = "forAtlasLeaderOnlyUSTest014", dataProviderClass=DataProviderSource.class)
	@Parameters("os")
	public void main(double d, String env) throws Exception{
		driver.get(UrlBuilder.LOGIN_DEV);
		loginPage = new LogInPage(driver);
		homePage = loginPage.loginToAtlas(partyId, password);
		timesheetPage = homePage.goToPayroll(env);
		timesheetPage.toTimeSheetPage();
		originalValues = timesheetPage.getActualColmnValues();

		timesheetPage.updateNumFields(d);
		String [] changedActuals = timesheetPage.getActualColmnValues();
		String [] expectedActuals = StringUtils.increasedNumValues(d, originalValues);
		
		System.out.println(changedActuals.length + " " + expectedActuals.length);
		
		
		Assert.assertEquals(changedActuals.length, expectedActuals.length);
		for (int i = 0; i < changedActuals.length; i++){
			System.out.println(changedActuals[i] + " " + expectedActuals[i]);
			Assert.assertEquals(changedActuals[i], expectedActuals[i]);
		}
		timesheetPage.updateNumFields(originalValues);
	}

}
