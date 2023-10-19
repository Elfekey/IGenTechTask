package Tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Base.GeneralMethods;
import Pages.mekegPage;
import Utilities.retryFailedTCs;

public class donationCycleTest  extends GeneralMethods{




	String linkName="https://sandbag.mekeg.org/ar";
	Select select;	
	//to receive values from pages as select to use it to selcet values  

	@Test(description = "This donation TC",retryAnalyzer =retryFailedTCs.class)
	public void donationCycle() {

			//we must create object for the page we need to use in each test if we'll use it many times in many test cases
			mekegPage	mekegPage = new mekegPage(driver);
			//Step 1 
			//		Open a web browser (e.g., Chrome or Firefox).
			//		• Navigate to the home page of mekeg.org website
			NavigateTo(linkName);
			//		Step 2 • Select any donation product with amount and proceed to check out.
			//		• Fill the data with dummy data using test and select from home as donation channel
			clickAnElement(mekegPage.donationTab); 

			//	//---------------Second page  ----------------------------------------
			//Scroll to donation amount element
			moveToElement(mekegPage.amountOfDonation);
			//filling the donation amount and continue
			sendText(mekegPage.amountOfDonation, "2000");
			clickAnElement(mekegPage.donationBtn);
			clickAnElement(mekegPage.continueDonationBtn);

			//---------------Third page  ----------------------------------------
			sendText(mekegPage.firstName, "Hussam");
			sendText(mekegPage.lastName, "Elfekey");		
			sendText(mekegPage.email, "elfekey3@gmail.com");
			sendText(mekegPage.mobileNo, "01005669013");

			//----------------------
			//selecting country
			selectFromDropDownWithOptionName(select,mekegPage.selectCountry, "مصر");

			//selecting government
			//opening the list 
			mekegPage.govContainer.click();
			//typing the gov and pressing enter 
			sendText(mekegPage.govName, "القاهرة");
			mekegPage.govName.sendKeys(Keys.RETURN);



			//First we need to move  down
			moveToElement(mekegPage.repeatDontaion);

			//selecting City
			//opening the list 
			mekegPage.cityContainer.click();
			//typing the city and pressing enter 
			sendText(mekegPage.cityName, "الزيتون");
			mekegPage.cityName.sendKeys(Keys.RETURN);

			//selecting district
			//opening the list 
			mekegPage.districtContainer.click();
			//typing the district and pressing enter 
			sendText(mekegPage.districtName, "الزيتون الشرقية");
			mekegPage.districtName.sendKeys(Keys.RETURN);


			//------------address
			//		sendText(mekegPage.street, "العزيز بالله");
			sendText(mekegPage.street, "Alzeez bellah");
			sendText(mekegPage.mainStreetSquare, "Elzayetto");
			sendText(mekegPage.landmark, "ELTaw7eed WelNour");
			sendText(mekegPage.building, "75");
			sendText(mekegPage.floor, "11");
			sendText(mekegPage.flat, "113");

			//------------end of address


			//selecting specific gov RadioBtn Y/N
			clickRadioBtnElement(mekegPage.specificGovRadioBtn);

			//selecting country
			selectFromDropDownWithOptionName(select,mekegPage.paymentMethodSelect, "تبرع من المنزل");

			//click on pay button
			clickAnElement(mekegPage.paymBtn);

			//wait the confirmation to appear
			//		mekegPage.confirmationAfterDonationDone.isDisplayed();
			assertElementExist(mekegPage.confirmationAfterDonationDone);

	}
}
