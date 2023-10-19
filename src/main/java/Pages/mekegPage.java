package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class mekegPage  extends BasePage{


	//using the parent constructor 
	public mekegPage(WebDriver driver) {
		super(driver);
	}

	//Elements of the page that will be used inside my test case



	//---------------First page elements ----------------------------------------

	//donation tab
	@FindBy (xpath = "//*[@id=\"navbar-collapse\"]/li[4]/a" )
	public WebElement donationTab;

	//---------------Second page elements ----------------------------------------
	//amountOfDonation to be sent  
	@FindBy (xpath = "//input[@placeholder='0.00 ج.م']" )
	public WebElement amountOfDonation;

	//donationBtn
	@FindBy (xpath = "(//i[@class='fas fa-shopping-cart'])[2]" )
	public WebElement donationBtn;


	//continueDonationBtn
	@FindBy (xpath = "//a[contains(text(),'تابع عملية التبرع')]" )
	public WebElement continueDonationBtn;


	//---------------Third page elements ----------------------------------------

	//firstName
	@FindBy (xpath = "//input[@id='cartFirstName']" )
	public WebElement firstName;

	//last name
	@FindBy (xpath = "//input[@id='cartLasttName']" )
	public WebElement lastName;

	//email
	@FindBy (xpath = "//input[@id='cartEmail']" )
	public WebElement email;


	//mobileNo
	@FindBy (xpath = "//input[@id='cartMobile']" )
	public WebElement mobileNo;	

	//select country   //Egypt
	@FindBy (xpath = "//select[@id='cartCountry']" )
	public WebElement selectCountry;

	//select Government Container   //click
	@FindBy (xpath = "//span[@id='select2-cartGovernorate-container']" )
	public WebElement govContainer;
	//Government typeGovName //Cairo
	@FindBy (xpath = "//span/input[@class=\"select2-search__field\"]" )
	public WebElement govName;

	//City Container   //click
	@FindBy (xpath = "//span[@id='select2-cartCity-container']" )
	public WebElement cityContainer;
	//Government typeGovName  //Elzaytoon
	@FindBy (xpath = "//*/span/input[@class=\"select2-search__field\"]" )
	public WebElement cityName;


	//district Container    //click
	@FindBy (xpath = "//span[@id='select2-cartDistrict-container']" )
	public WebElement districtContainer;
	//districtName typeGovName  //Elzaytoon el sharqya
	@FindBy (xpath = "//span/input[@class=\"select2-search__field\"]" )
	public WebElement districtName;


	//---------------Address 
	//street
	@FindBy (xpath = "//input[@id='street']" )
	public WebElement street;

	//mainStreetSquare
	@FindBy (xpath = "//input[@id='mainStreetSquare']" )
	public WebElement mainStreetSquare;

	//landmark
	@FindBy (xpath = "//input[@id=\"landmark\"]" )
	public WebElement landmark;

	//building
	@FindBy (xpath = "//input[@id='building']" )
	public WebElement building;

	//floor
	@FindBy (xpath = "//input[@id='floor']" )
	public WebElement floor;

	//flat
	@FindBy (xpath = "//input[@id='flat']" )
	public WebElement flat;


	//---------------end of Address 

	//do you want to donate for a specific Gov ! radioBTN
	@FindBy (xpath = "//input[@id='no_direct']" )
	public WebElement specificGovRadioBtn;

	//Select paymentMethod
	@FindBy (xpath = "//select[@id='paymentMethod']" )
	public WebElement paymentMethodSelect;

	// pay button
	@FindBy (xpath = "//button[@name=\"pay\"]" )
	public WebElement paymBtn;

//-----------------confirmationAfterDonationDone  assertion 
	@FindBy (xpath = "//h2[contains(text(),'شكراً')]" )
	public WebElement confirmationAfterDonationDone;
	
	
	//-------------- repeat donation just for scrolling
	@FindBy (xpath = "//select[@id='recurring']" )
	public WebElement repeatDontaion;




}















