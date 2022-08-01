package cucumber_Assignment_No2.stepdefs;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import cucumber_Assignment_No2.core.WebDriverFactory;
import cucumber_Assignment_No2.pageobjects.CommonPageObject;
import cucumber_Assignment_No2.pageobjects.Footersection;
import cucumber_Assignment_No2.pageobjects.Productlist;
import cucumber_Assignment_No2.pageobjects.Search_Product;
import cucumber_Assignment_No2.pageobjects.SignIn_Validation;

public class StepDef {
	private static final Logger logger = LogManager.getLogger(StepDef.class);
	WebDriver driver;
	Scenario scn;
	String base_url = "http://automationpractice.com/";

//====================== Declare object reference of pageObjects classes =========================================//
	
	CommonPageObject cmnPageObject;
	Footersection footerObject;
	Productlist prodCatObject;
	Search_Product searchProdObject;
	SignIn_Validation signInObject;
	
//====================== Before Hook =========================================================//	
	@Before
	public void setUp(Scenario scn) throws Exception
	{
		this.scn=scn;

		String browserName= WebDriverFactory.getBrowserName();
		driver=WebDriverFactory.getWebDriverForBrowser(browserName); 
		scn.log("Browser get invoked");

		 cmnPageObject= new CommonPageObject(driver, scn);
		 footerObject= new Footersection(driver, scn);
		 prodCatObject= new Productlist(driver, scn);
		 searchProdObject= new Search_Product(driver, scn);
		 signInObject= new SignIn_Validation(driver, scn);
	}


	@After(order=1)
	//Quit the browser
	public void tearDown(Scenario scn)
	{
		WebDriverFactory.quitTheBrowser();
		scn.log("Browser is quit");
	}
	@After(order=2)
	//Capture screenshot if test case get failed
	public void captureScreenshot(Scenario scn)
	{
		if(scn.isFailed())
		{
			TakesScreenshot srnshot= ((TakesScreenshot)driver);
			byte [] data =srnshot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Name of failed step is: "+ scn.getName());
			scn.log("Attach a screenshot as step get failed");
		}
		else
		{
			scn.log("Test case get passed, no screenshot is captured");
		}
	}
	
	
//Background
	@Given("User navigate to URL and open the landing page")
	public void user_navigate_to_url_and_open_the_landing_page() {
		WebDriverFactory.navigateToURL(base_url);
		scn.log("Navigate to base URL");
	    }

//1. URLRedirection_Test Scenario

	@When("User is on landing page")
	public void user_is_on_landing_page() {
		logger.info("User is on landing page after navigating to base URL");
	    scn.log("User is on landing page after navigating to base URL");
	}
	@Then("Landing page URL must be {string}")
	public void Landing_page_URL_must_be(String URL) {
		 cmnPageObject.PageURL();
		 scn.log("current URL is: "+ driver.getCurrentUrl());
	}
// 2. LandingPageTitle_Test Scenario 
	
	@Then("landing page title must be {string}")
	public void landing_page_title_must_be(String title) {
		cmnPageObject.validatePageTitle(title);
		scn.log("Validate page title is: "+ driver.getTitle());
	}
	
// 3. Productlist_Test Scenario 
	
	@When("User see the product category")
	public void user_see_the_product_category() {
		prodCatObject.setProdCategory();
	    }

	@Then("Validate product category list")
	public void validate_product_category_list(List<String> prodCat) {
		prodCatObject.validateProdCategory(prodCat); 
		scn.log("Validate the product category with expected datatable");
	}
	@Then("Size of product category should be {int}")
	public void size_of_product_category_should_be(Integer prodCount) {
		prodCatObject.sizeOfProdCategory(prodCount);
	}

	

// 4. DisplayLogo_Test Scenario
	
	@Then("User see the logo of application")
	public void user_see_the_logo_of_application() {
		cmnPageObject.displayLogo();
		scn.log("Display the application logo on landing page");
	}

// 5. LogoHeight_Test Scenario 
	
	@When("fetch the height of logo")
	public void fetch_the_height_of_logo() {
		cmnPageObject.LogoHeight();
	}

	@Then("Height of logo must be {string}")
	public void height_of_logo_should_be(String height) {
		cmnPageObject.logoHeightValid(height);
	}
	
// 6. LogoWidth_Test Scenario     
	
	@When("fetch the width of logo")
	public void fetch_the_width_of_logo() {
		cmnPageObject.LogoWidth(); 
	}

	@Then("Width of logo must be {string}")
	public void width_of_logo_should_be(String width) {
		cmnPageObject.logoWidthValid(width);
	}
	
// 7. SignInPage_Test Scenario 
	
	@Given("User see SignIn button")
	public void user_see_sign_in_button() {
		signInObject.signIn_Validation();
		scn.log("Display the signIn Button");
	}

	@When("User click on SignIn button")
	public void user_click_on_sign_in_button() {
		signInObject.clickOnSignInBtn();
	}
	@Then("User click on signIn button next page title must be {string}")
	public void User_click_on_signIn_button_next_page_title_must_be(String SignInPageTitle) {
		signInObject.validateSignInPage(SignInPageTitle);
	}
	
// 8. Search_Product_Test Scenario 
	
	@Given("User able to see searchBox")
	public void user_able_to_see_search_box() {
		 searchProdObject.setSearchBox();
	}

	@When("User search for product {string} in searchBox")
	public void user_search_for_product_in_given_search_box(String product) {
		 searchProdObject.searchProduct(product);
	}
	@Then("User see the product list")
	public void user_see_the_product_list() {
		 searchProdObject.getSuggestedProdList();
	}
	@Then("Search product match count must be {int}")
	public void Search_product_match_count_must_be ( Integer prodSize) {
		 searchProdObject.validateSuggProdList( prodSize);
	}

// 9. TwitterHandle_Test Scenario	

	@Given("User click on twitter link")
	public void user_click_on_twitter_link() {
		footerObject.setTwitterLink();
		footerObject.clickOnTwitterLink();
	}

	@When("navigate to twitter account page")
	public void navigate_to_twitter_account_page() {
		footerObject.twitterAcPage();
	}
	@Then("User see the twitter account name {string}")
	public void user_see_the_twitter_account_name(String AcName) {
		footerObject.twitterAcNameValidation(AcName);
	}
	
// 10. NewsLetterSubscription_Test Scenario 	
	@Given("User see newsletter text box and proceed button")
	public void user_see_newsletter_text_box_and_proceed_button() {
		footerObject.setNewsLetter();
	}

	@When("User enter email id in newsletter text box and click on proceed button")
	public void user_enter_email_id_in_newsletter_text_box_and_click_on_proceed_button() {
		footerObject.enterEmailId();
		footerObject.clickOnProceedBtn();
	}
	
	@Then("User get the {string} massage")
	public void User_get_the_massage(String message) {
		footerObject.SubscriptionMsg();
	}

}
