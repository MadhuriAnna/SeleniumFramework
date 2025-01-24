package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	private By welcomeMsg=By.className("welcomeMessage");
	
	private By manage=By.xpath("//span[normalize-space()='Manage']");
	
	private By manageCourses=By.xpath("//a[normalize-space()='Manage Courses']");
	
	private By manageCategory=By.xpath("//a[normalize-space()='Manage Categories']");
	
	public String getWelcomeMsg()
	{
		String welcomeMessage=driver.findElement(welcomeMsg).getText();
		
		return welcomeMessage;
	}
	
	public Courses clickOnManageCourse()
	{
		Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(manage)).perform();
		
		driver.findElement(manageCourses).click();
		
		Courses course=PageFactory.initElements(driver, Courses.class);
		
		return course;
	}
	
	public Category clickOnManageCategory()
	{
		Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(manage)).perform();
		
		driver.findElement(manageCategory).click();
		
		return PageFactory.initElements(driver, Category.class);
	}

}
