package com.backroads.atlas.testcases.UI;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.backroads.atlas_browserstack.BrowserStackTestNGTest;
import com.backroads.common.util.StringUtils;
import com.backroads.webdriver.common.contentpatterns.PageContent;
import com.backroads.webdriver.common.dataprovider.DataProviderSource;
import com.backroads.webdriver.common.url.UrlBuilder;
import com.backroads.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.LogInPage;
import com.backroads.webdriver.pageobjectsfactory.pageobject.TimesheetPage;

public class AtlasUIRegressionTestSuite2 extends BrowserStackTestNGTest {
	//private WebDriver driver;  //excluded for bs testing
	private LogInPage loginPage;
	private HomePage homePage;
	private TimesheetPage timesheetPage;
	private String partyId = "1855545"; //Jenna Smith
	private String password = "bubble";
	
	private String [] originalValues;   
	
//	private WebDriver driver;
	
	@BeforeClass
	public void SetUp(){
//		driver.get(UrlBuilder.LOGIN);
		driver.get("http://atlasstage.backroads.com/Login.aspx?ReturnUrl=%2f");
		loginPage = new LogInPage(driver);
		homePage = loginPage.loginToAtlas(partyId, password);
		timesheetPage = homePage.goToPayroll();
		timesheetPage.toTimeSheetPage();
		originalValues = timesheetPage.getActualColmnValues();
	}
	
	@Test(priority = 1)
	public void AtlasLeaderOnlyUSTest005(){
		List<WebElement> columnHeaders = timesheetPage.getLeaderTableHeaderTexts();
		for (int i = 0; i < columnHeaders.size(); i++){
			Assert.assertEquals(columnHeaders.get(i).getText(), PageContent.LEADING_COLUMN_HEADER_TEXTS[i]);			
		}
	}

	@Test(priority = 2)
	public void AtlasLeaderOnlyUSTest007(){
		Assert.assertEquals(timesheetPage.getPayPeriodLabel(), PageContent.PAY_PERIOD_LABEL);
		Assert.assertEquals(timesheetPage.getPayPeriod(), PageContent.PAY_PERIOD);
		Assert.assertEquals(timesheetPage.getPayDateLabel(), PageContent.PAY_DATE_LABEL);
		Assert.assertEquals(timesheetPage.getPayDate(), PageContent.PAY_DATE);
	}	
	
	@Test(priority = 3)
	public void AtlasLeaderOnlyUSTest008(){
		timesheetPage.clickTermsLink();
		String [] windowTexts = timesheetPage.getTermsWindowTextsFromWindow();
		Assert.assertEquals(windowTexts[0], PageContent.TERMS_HEADER);
	    Assert.assertEquals(windowTexts[1], PageContent.TERMS_TEXT_US);
	}
	

	@Test(priority = 4)
	public void AtlasLeaderOnlyUSTest009(){
		Assert.assertTrue(timesheetPage.isLeaderActDatesAsc());
	}
	

	@Test(priority = 5)
	public void AtlasLeaderOnlyUSTest011_Regular(){
		//Regular day column is at the 5th column.
		//Expected Hours for Regular Day is at the 7th column.
		Assert.assertTrue(timesheetPage.isDayColRepresentExpHoursColmn(5, 7));
	}
	
	@Test(priority = 6)
	public void AtlasLeaderOnlyUSTest011_Dinner(){
		//Dinner day column is at the 6th column.
		//Expected Hours for Dinner Day is at the 8th column.
		Assert.assertTrue(timesheetPage.isDayColRepresentExpHoursColmn(6, 8));
		System.out.println("ended.");
	}

    @Test(priority = 7, dataProvider = "forAtlasLeaderOnlyUSTest012_13", dataProviderClass=DataProviderSource.class)
	public void AtlasLeaderOnlyUSTest012(String [] inputs){ 	
    	timesheetPage.updateNumFields(inputs);
    	timesheetPage.clickSubmit();
		Assert.assertEquals(timesheetPage.getTopError(), PageContent.DIFFERENT_ACTUAL_ERROR);
		Assert.assertEquals(timesheetPage.getBottomError(), PageContent.DIFFERENT_ACTUAL_ERROR);
		//revert the values to the original for other testing.
		timesheetPage.updateNumFields(originalValues);
	}
   

    
	
	@Test(priority = 8, dataProvider = "forAtlasLeaderOnlyUSTest014", dataProviderClass=DataProviderSource.class)	
	public void AtlasLeaderOnlyUSTest014 (double d){
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
	
	@Test(priority = 9)
	public void AtlasLeaderOnlyUSTest017(){
		//Dinner day column is at the 6th column.
		//Expected Hours for Dinner Day is at the 8th column.
		Assert.assertTrue(timesheetPage.isRadioWorking());
	}

/* attempted	
	@Test(priority = 1, dataProvider = "AtlasLeaderOnlyUSTest016_Non_Trip", dataProviderClass=DataProviderSource.class)	
 	public void AtlasLeaderOnlyUSTest016_Non_Trip(String activity, String area, String notes, String authBy, String numFields){
		AddActPage addactPage = timesheetPage.clickAddActButton();
		timesheetPage = addactPage.fillDataAndSubmitTripUnrelatedAct(activity, area, notes, authBy, Integer.parseInt(numFields));  
		timesheetPage.getOneRowData(activity);
 	}

	

    @Test(priority = 7)
 	public void AtlasLeaderOnlyUSTest016_Trip_Related(){
     	timesheetPage.;
     	timesheetPage.clickSubmit();
 		Assert.assertEquals(timesheetPage.getTopError(), PageContent.DIFFERENT_ACTUAL_ERROR);
 		Assert.assertEquals(timesheetPage.getBottomError(), PageContent.DIFFERENT_ACTUAL_ERROR);
 	}
*/
	
	@Test(priority = 10)
	public void AtlasLeaderOnlyUSTest024(){
		List<WebElement> columnHeaders = timesheetPage.getOtherActHeaderTexts();
		for (int i = 0; i < columnHeaders.size(); i++){
			Assert.assertEquals(columnHeaders.get(i).getText(), PageContent.OTHER_COLUMN_HEADER_TEXTS[i]);			
		}
	}	

	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}

}
