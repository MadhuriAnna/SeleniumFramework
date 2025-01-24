package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import dataprovider.ConfigReader;

public class BrowserFactory 
{
	public static WebDriver driver;
	
	public static WebDriver getInstance()
	{
		return driver;
	}
	
	public static WebDriver getDriver(String browser, String applicationURL) 
	{
		if (browser.equalsIgnoreCase("Chrome")) 
		{
			ChromeOptions opt=new ChromeOptions();
			
			if(ConfigReader.getValue("headless").equalsIgnoreCase("true"))
			{
				opt.addArguments("headless=new");
			}
			
			driver = new ChromeDriver(opt);
		} 
		else if (browser.equalsIgnoreCase("Firefox")) 
		{
			driver = new FirefoxDriver();
			
		} else if (browser.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("Safari")) 
		{
			driver = new SafariDriver();
		} else 
		{
			System.out.println("Sorry Current We Dont Support " + browser + " Browser");
		}
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("pageload"))));

		driver.get(applicationURL);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("implicitwait"))));

		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("scripttimeout"))));

		return driver;
	}

}
