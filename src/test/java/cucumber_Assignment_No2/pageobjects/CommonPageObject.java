package cucumber_Assignment_No2.pageobjects;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.Scenario;


public class CommonPageObject {
	private static final Logger logger= LogManager.getLogger(CommonPageObject.class);
	WebDriver driver;
	Scenario scn;
	WebDriverWait wait;

//Locators

	private By logoImage= By.xpath("//div[@id='header_logo']/a/img[@alt='My Store']");
	
//Expected Results 
	
	String expCurrentURL= "http://automationpractice.com/index.php";
	
	public CommonPageObject(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
	
//validate page URL 
	public void PageURL()
	{
		wait= new WebDriverWait(driver,20);
		boolean xyz = wait.until(ExpectedConditions.urlToBe(expCurrentURL));
		Assert.assertEquals(true,xyz);
	    logger.info("validate current URL of page ,so URL is: "+ driver.getCurrentUrl());
	}
	
//validate page Title
    public void validatePageTitle(String pageTitle)
    {
    	String actTitle= driver.getTitle();
    	Assert.assertEquals(pageTitle, actTitle);
        logger.info("Validate title of page, title is:" + actTitle);
    }
    
 //display logo 
    public void displayLogo()
    {
    	WebElement logo =driver.findElement(logoImage);
    	Assert.assertEquals(true, logo.isDisplayed());
    	logger.info("Display the logo on landing page");
    }
    
// height of the logo   
    public void LogoHeight()
    {
    	WebElement logo =driver.findElement(logoImage);
    	String logoHeight= logo.getAttribute("height");
    	logger.info("Height of logo is: "+ logoHeight);
    	scn.log("Height of logo is: "+ logoHeight);
    }
    
// validate logo height    
    public void logoHeightValid(String height)
    {
    	WebElement logo =driver.findElement(logoImage);
    	Assert.assertEquals(height, logo.getAttribute("height"));
    	logger.info("Validate the height of logo");
    	scn.log("Validate the height of logo");
    }
   
// width of logo   
    public void LogoWidth()
    {
    	WebElement logo =driver.findElement(logoImage);
    	String logoWidth= logo.getAttribute("width");
    	logger.info("Width of logo is: "+ logoWidth);
    	scn.log("Width of logo is: "+ logoWidth);
    }
 
//validate logo width    
    public void logoWidthValid(String width)
    {
    	WebElement logo =driver.findElement(logoImage);
    	Assert.assertEquals(width, logo.getAttribute("width"));
    	logger.info("Validate the width of logo");
    	scn.log("Validate the width of logo");
    }   
}


