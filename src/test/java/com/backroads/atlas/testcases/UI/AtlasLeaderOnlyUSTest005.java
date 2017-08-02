package com.backroads.atlas.testcases.UI;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.backroads.atlas_browserstack.BrowserStackTestNGTest;
import com.backroads.webdriver.common.contentpatterns.PageContent;
import com.backroads.webdriver.common.url.UrlBuilder;
import com.backroads.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.LogInPage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.TimesheetPage;

public class AtlasLeaderOnlyUSTest005 extends BrowserStackTestNGTest{

	private LogInPage loginPage;
	private HomePage homePage;
	private TimesheetPage timesheetPage;
//	private String partyId = "1855545"; //Jenna Smith
	private String password = "bubble";

	private String partyId = "1914832"; //Casey Carr


	@Test
	@Parameters("os")
	public void main(String env) throws Exception{
//		driver.get(UrlBuilder.LOGIN_DEV);
		driver.get(UrlBuilder.LOGIN_STAGE);
		loginPage = new LogInPage(driver);
		homePage = loginPage.loginToAtlas(partyId, password);
		timesheetPage = homePage.goToPayroll(env);
		timesheetPage.toTimeSheetPage();
		
		List<WebElement> columnHeaders = timesheetPage.getLeaderTableHeaderTexts();
		for (int i = 0; i < columnHeaders.size(); i++){
			Assert.assertEquals(columnHeaders.get(i).getText(), PageContent.LEADING_COLUMN_HEADER_TEXTS[i]);			
		}
	}

}
