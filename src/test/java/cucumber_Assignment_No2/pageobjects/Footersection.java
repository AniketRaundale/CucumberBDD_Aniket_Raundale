package cucumber_Assignment_No2.pageobjects;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.Scenario;
import cucumber_Assignment_No2.core.WebDriverFactory;

public class Footersection {
	private static final Logger logger= LogManager.getLogger(Footersection.class);
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Scenario scn;
	
// Locators

	private By twitterLink= By.xpath("//li[@class='twitter']");
	private By twitterAcName= By.xpath("(//div[@dir='auto']//span[text()='Selenium Framework'])[1]");
	private By newsLetterElement= By.id("newsletter-input");
	private By proceedBtn= By.name("submitNewsletter");
	private By successSubscriptionMsgField = By.xpath("//p[@class='alert alert-success']");
	private By failSubscriptionMsgField=By.xpath("//p[@class='alert alert-danger']");

	// Expected results
	
	String twitterPageTitle= "Selenium Framework (@seleniumfrmwrk) / Twitter";
	String randomStringGenerator = "";
	String successedSubMsg= " Newsletter : You have successfully subscribed to this newsletter.";
	String failSubMsg= " Newsletter : This email address is already registered.";
	
	public Footersection(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
	
    public void setTwitterLink()
    {
    	WebElement twitterElement =driver.findElement(twitterLink);
		js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", twitterElement);
		Assert.assertEquals(true, twitterElement.isDisplayed());
    	logger.info("Validate the twitter link");
    }
    
    public void clickOnTwitterLink()
    {
    	WebElement twitterElement =driver.findElement(twitterLink);
    	twitterElement.click();
    	logger.info("Click the twitter link");
    	scn.log("Click the twitter link");
    }
    
    public void twitterAcPage()
    {
    	WebDriverFactory.switchToNewWindow();
    	logger.info("Switch to Twitter Account window");
    	wait= new WebDriverWait(driver, 20);
    	boolean bool =wait.until(ExpectedConditions.titleIs(twitterPageTitle));
    	Assert.assertEquals(true, bool);
    	logger.info("twitter account page with its title is: "+ twitterPageTitle);
    	scn.log("twitter account page with its title is: "+ twitterPageTitle);
    }

    public void twitterAcNameValidation(String AcName)
    {
    	WebElement twitterAcNameElement =driver.findElement(twitterAcName);
    	Assert.assertEquals(AcName, twitterAcNameElement.getText());
    	logger.info("Validate twitter account name, account name is: "+twitterAcNameElement.getText());
    	scn.log("Validate twitter account name, account name is: "+twitterAcNameElement.getText());
    }
   
    
    public void setNewsLetter()
    {
    	WebElement newsLetterTextBox =driver.findElement(newsLetterElement);
    	
    	js= (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", newsLetterTextBox);
    	Assert.assertEquals(true, newsLetterTextBox.isDisplayed());
    	logger.info("Validate newsletter text box");
    	WebElement proceedButton= driver.findElement(proceedBtn);
    	Assert.assertEquals(true, proceedButton.isDisplayed());
    	logger.info("Validate newsletter's proceed button");
    	scn.log("Validate newsletter text box and proceed button");
    }
    
    public void enterEmailId()
    {
    	WebElement newsLetterTextBox =driver.findElement(newsLetterElement);
    	newsLetterTextBox.sendKeys(randomStringGenerator());
    	logger.info("Enter emailId in newsletter text box");
    	scn.log("Enter emailId in newsletter text box");
    }
    
    public void clickOnProceedBtn()
    {
    	WebElement proceedButton= driver.findElement(proceedBtn);
    	proceedButton.click();
    	logger.info("Click on proceed button");
    	scn.log("Click on proceed button");
    }
    public String randomStringGenerator()
   	{
   		Random rand = new Random();
   		char s;
   		String randomName ="";
   		
   		for(int i = 0; i < 10; i++) {
   			s =  (char)(rand.nextInt(26)+97);
   			randomName += s;
   			}
   		
   		randomName=randomName+"@gmail.com";
   		return randomName;
   	}
    
    public void SubscriptionMsg()
    {
	    try 
	    {
	     WebElement Message= driver.findElement(successSubscriptionMsgField);
	     Message.getText().equals(successedSubMsg);
		 logger.info("successful email subscription,subscription msg is: "+ Message.getText());
		 scn.log("successful email subscription, ,subscription msg is: "+ Message.getText());
		} 
	    catch(Exception e)
	    {
	     WebElement failMsg= driver.findElement(failSubscriptionMsgField);
	     failMsg.getText().equals(failSubMsg);
	     logger.info("message for failed email subscription: "+failMsg.getText());
	     scn.log("message for failed email subscription: "+failMsg.getText());
	    }
	    }
	   
    }
    

