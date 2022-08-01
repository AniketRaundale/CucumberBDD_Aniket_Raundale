package cucumber_Assignment_No2.core;
import java.util.Iterator;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
	 private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
	    private static WebDriver driver=null;
	    public static WebDriver getWebDriverForBrowser(String browser) throws Exception {
	        switch(browser.toLowerCase()){
	            case "chrome":
	                driver = new ChromeDriver();
	                logger.info("Chrome Browser invoked");
	                break;
	            case "firefox":
	                driver = new FirefoxDriver();
	                logger.info("Firefox Browser invoked");
	                break;
	            case "headless":
	                ChromeOptions options = new ChromeOptions();
	                options.addArguments("headless");
	                options.addArguments("window-size=1200x600");
	                driver = new ChromeDriver(options);
	                logger.info("Headless Chrome Browser invoked");
	                break;
	            default:
	                logger.fatal("No such browser is implemented.Browser name sent: " + browser);
	                throw new Exception("No such browser is implemented.Browser name sent: " + browser);
	        }

	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        logger.info("Driver maximized and implicit time out set to 20 seconds");
	        return driver;
	    }

	    //Method to navigate to URL 
	    		public static void navigateToURL(String url)
	    		{
	    			driver.get(url);
	    			logger.info("navigate to url");
	    		}

	    		public static void quitTheBrowser()
	    		{
	    			driver.quit();
	    			logger.info("Quit the browser");
	    		}

	    	//Method to switch to new window 
	    		public static void switchToNewWindow()
	    		{
	    			//1.using Set
	    			Set <String> set= driver.getWindowHandles();
	    			Iterator <String> itr= set.iterator();
	    			itr.next();
	    			String newWindowId =itr.next();
	    			driver.switchTo().window(newWindowId); 	        
	    			logger.info("Switch to new Window, its id is: "+ newWindowId);
	    		}
	    

	    	//Method for browser  
	    		public static String getBrowserName()
	    		{
	    			String defaultBrowser= "firefox";                            
	    			String browserSentFromCmd= System.getProperty("browser");  
	    			if(browserSentFromCmd==null)
	    			{
	    				return defaultBrowser;
	    			}
	    			else 
	    			{
	    				return browserSentFromCmd;
	    			}
	    		}
}
