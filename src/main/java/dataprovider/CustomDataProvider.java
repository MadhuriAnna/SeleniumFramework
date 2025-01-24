package dataprovider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider 
{
	@DataProvider(name="login")
	public static Object[][] getUserDetails()
	{
		System.out.println("LOG:INFO - Generating Test Data For The Test");
		
		Object[][]arr= ExcelReader.getDataFromExcel("login_details");
		
		System.out.println("LOG:INFO - Test Data Generated");
		
		return arr;
	}

}
