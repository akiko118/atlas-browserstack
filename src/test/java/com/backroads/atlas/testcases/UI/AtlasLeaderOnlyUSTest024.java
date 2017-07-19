package com.backroads.atlas.testcases.UI;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.backroads.atlas_browserstack.BrowserStackTestNGTest;
import com.backroads.common.util.StringUtils;
import com.backroads.webdriver.common.contentpatterns.PageContent;
import com.backroads.webdriver.common.dataprovider.DataProviderSource;
import com.backroads.webdriver.common.url.UrlBuilder;
import com.backroads.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.LogInPage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.TimesheetPage;

public class AtlasLeaderOnlyUSTest024 extends BrowserStackTestNGTest{

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
		
		List<WebElement> columnHeaders = timesheetPage.getOtherActHeaderTexts();
		for (int i = 0; i < columnHeaders.size(); i++){
			Assert.assertEquals(columnHeaders.get(i).getText(), PageContent.OTHER_COLUMN_HEADER_TEXTS[i]);			
		}
	}

}
