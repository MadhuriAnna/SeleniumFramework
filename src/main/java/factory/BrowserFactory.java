package factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;

import base.Parameters;
import dataprovider.ConfigReader;

public class BrowserFactory 
{
	public static WebDriver driver;
	
	public static WebDriver getInstance()
	{
		return driver;
	}
	
	/*public static WebDriver getDriver(String browser, String applicationURL) 
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
		}*/
	
	public static WebDriver getDriver(String br, String vr, String pf, String applicationURL) {
        try {
            // Setting up Desired Capabilities for Sauce Labs
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", br);
            capabilities.setCapability("version", vr);
            capabilities.setCapability("platformName", pf);
            capabilities.setCapability("name", "Cross-Browser Test - " + br + " on " + pf);
            capabilities.setCapability("name", "Sample Selenium Test on Sauce Labs"); // Test name for Sauce Labs dashboard

            // Fetching Sauce Labs credentials from environment variables or config file
            String sauceUsername = System.getenv("SAUCE_USERNAME") != null ? System.getenv("SAUCE_USERNAME") : ConfigReader.getValue("sauceUsername");
            String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY") != null ? System.getenv("SAUCE_ACCESS_KEY") : ConfigReader.getValue("sauceAccessKey");

            // Sauce Labs URL
            String sauceUrl = "https://" + sauceUsername + ":" + sauceAccessKey + "@ondemand.saucelabs.com:443/wd/hub";

            // Initializing RemoteWebDriver to connect with Sauce Labs
            driver = new RemoteWebDriver(new URL(sauceUrl), capabilities);
        }
            catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println("Error: Unable to connect to Sauce Labs. Check your credentials or URL.");
            }
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("pageload"))));

		driver.get(applicationURL);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("implicitwait"))));

		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("scripttimeout"))));

		return driver;
	}

}
