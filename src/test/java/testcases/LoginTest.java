package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import pages.LoginPage;

public class LoginTest extends BaseClass
{
	
	/*
	 *  BaseClass > BrowserFactory > ConfigReader > DataProvider > ExcelReader > DataProvider > Test (Pages > Utility) > BaseClass
	 * 
	 */
	
	
	@Test(description = "This test will verify valid login scenario",groups={"Smoke"},dataProvider = "login",dataProviderClass = CustomDataProvider.class)
	public void validLoginTest(String username,String password)
	{
		LoginPage login=new LoginPage(driver);
		
		login.loginToApp(username, password);
		
		Assert.assertTrue(driver.findElement(By.className("welcomeMessage")).isDisplayed());
		
	}

}



