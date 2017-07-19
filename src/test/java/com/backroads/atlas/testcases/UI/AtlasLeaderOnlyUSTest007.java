package com.backroads.atlas.testcases.UI;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.backroads.atlas_browserstack.BrowserStackTestNGTest;
import com.backroads.webdriver.common.contentpatterns.PageContent;
import com.backroads.webdriver.common.url.UrlBuilder;
import com.backroads.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.LogInPage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.TimesheetPage;

public class AtlasLeaderOnlyUSTest007 extends BrowserStackTestNGTest{

	private LogInPage loginPage;
	private HomePage homePage;
	private TimesheetPage timesheetPage;
//	private String partyId = "1855545"; //Jenna Smith
	private String password = "bubble";

	private String partyId = "1914832"; //Casey Carr


	@Test
	public void main() throws Exception{
		driver.get(UrlBuilder.LOGIN_DEV);
		loginPage = new LogInPage(driver);
		homePage = loginPage.loginToAtlas(partyId, password);
		timesheetPage = homePage.goToPayroll();
		timesheetPage.toTimeSheetPage();
		
		Assert.assertEquals(timesheetPage.getPayPeriodLabel(), PageContent.PAY_PERIOD_LABEL);
		Assert.assertEquals(timesheetPage.getPayPeriod(), PageContent.PAY_PERIOD);
		Assert.assertEquals(timesheetPage.getPayDateLabel(), PageContent.PAY_DATE_LABEL);
		Assert.assertEquals(timesheetPage.getPayDate(), PageContent.PAY_DATE);
	}

}
