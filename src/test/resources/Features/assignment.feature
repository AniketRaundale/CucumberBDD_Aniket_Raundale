@endtoend
Feature: User can iteract with different products & links

Background: Navigation to the URL
Given  User navigate to URL and open the landing page

@URLRedirection_Test
Scenario: User naviaget to URL, User redirect to landing page with expected current URL
When User is on landing page
Then Landing page URL must be "http://automationpractice.com/index.php"

@LandingPageTitle_Test
Scenario: User naviaget to URL, User redirect to landing page with expected page title
When User is on landing page
Then landing page title must be "My Store"

@Productlist_Test
Scenario: Product Category Validation Test
When User see the product category
Then Validate product category list
|     WOMEN    |
|    DRESSES   |
|    T-SHIRTS  |
And Size of product category should be 3 

@DisplayLogo_Test
Scenario: Display landing page application logo
When User is on landing page
Then User see the logo of application

@LogoHeight_Test
Scenario:  Validate Logo Height On Landing Page
When fetch the height of logo
Then Height of logo must be "99"

@LogoWidth_Test
Scenario:  Validate Logo width On Landing Page
When fetch the width of logo
Then Width of logo must be "350"

@SignIn_Validation_Test
Scenario: User click on SignIn button and navigate to respective page
Given User see SignIn button
When User click on SignIn button
Then User click on signIn button next page title must be "Login - My Store"

@Search_Product_Test
Scenario: User search a product and see the product list 
Given User able to see searchBox
When User search for product "Dress" in searchBox
Then User see the product list
And Search product match count must be 5


@TwitterHandle_Test
Scenario: Validate Twitter Social Media Handle          
Given User click on twitter link
When navigate to twitter account page
Then User see the twitter account name "Selenium Framework"


@NewsLetterSubscription_Test
Scenario: User enter email id in newsletter text box and click on proceed button
Given User see newsletter text box and proceed button
When User enter email id in newsletter text box and click on proceed button
Then User get the "successfully subscribed" massage
