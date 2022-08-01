package cucumber_Assignment_No2.pageobjects;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.java.Scenario;

public class Productlist {
	private static final Logger logger= LogManager.getLogger(Productlist.class);
	WebDriver driver;
	Scenario scn;
	
// Locators for productlist 

	private By prodCatgory= By.xpath("//div[@id='block_top_menu']/ul/li");


	public Productlist(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
	

    public void setProdCategory()
    {
    	List <WebElement> prodList =driver.findElements(prodCatgory);
    	Assert.assertEquals(false, prodList.isEmpty());
    	logger.info("Display the product category list, size of list is: "+ prodList.size());
    	scn.log("Product category is displayed on page with size is: "+ prodList.size());
    }
    

    public void validateProdCategory(List<String> prodList)
    {
     List <WebElement> prodCategoryList =driver.findElements(prodCatgory);
    {
     for(int i=0; i< prodCategoryList.size(); i++)
    {
    if(prodCategoryList.get(i).getText().equals(prodList.get(i).toString()))
     {
      Assert.assertTrue(true);
      logger.info(prodCategoryList.get(i).getText()+ " is matched with expected "+ prodList.get(i).toString()+" product name in datatable");
       }
    else
    {
    Assert.fail();
     logger.fatal(prodCategoryList.get(i).getText()+ " is not matched with expected "+ prodList.get(i).toString()+" product name in datatable");
    }
   }
     logger.info("Validate the product category with expected datatable");
    	}
    }	
    public void sizeOfProdCategory(int prodCount)
    {
 	   List <WebElement> prodCategoryList =driver.findElements(prodCatgory);
 	   Assert.assertEquals(prodCount, prodCategoryList.size());
 	   logger.info("validate the Size of product category, size is: "+ prodCategoryList.size());
 	   scn.log("validate the Size of product category, size is: "+ prodCategoryList.size());
    }
  
}
