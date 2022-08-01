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

public class SignIn_Validation {
	private static final Logger logger= LogManager.getLogger(SignIn_Validation.class);
	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;
	
//*************Locators for WebElements **********************//

	private By signInBtn= By.xpath("//a[@class='login']");


	public SignIn_Validation(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
	
//***************SignIn_validation*******************//
    public void signIn_Validation()
    {
    	WebElement signIn_Validation =driver.findElement(signInBtn);
    	Assert.assertEquals(true, signIn_Validation.isDisplayed());
    	logger.info("Validate the signIn Button");
    	scn.log("Validate the signIn Button");
    }
    
//******************Method for SignIn Button *****************//
    public void clickOnSignInBtn()
    {
    	
    	WebElement signIn =driver.findElement(signInBtn);
    	
    	wait= new WebDriverWait(driver,20);
    	wait.until(ExpectedConditions.elementToBeClickable(signIn));
    	signIn.click();
    	logger.info("Click on the signIn Button");
    	scn.log("Click on the signIn Button");
    }
    
//********************Method to validate SignIn page title *************//
    public void validateSignInPage(String SignInPageTitle)
    {
    	wait= new WebDriverWait(driver,20);
    	boolean xyz =wait.until(ExpectedConditions.titleIs(SignInPageTitle));
        Assert.assertEquals(true, xyz);
    	logger.info("Validate title of signIn page, so title is: "+ SignInPageTitle);
    	scn.log("Validate title of signIn page, so title is: "+ SignInPageTitle);
    }

}
