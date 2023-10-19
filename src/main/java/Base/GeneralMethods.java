package Base;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.util.Assert;

import Utilities.screenShots;

//here we are creating inheritance from the basetest as parent to use it's varibles and methods
public class GeneralMethods extends BaseTest{

	//the below is an instance from the screenshots calss to be used here to take screen shots within the actions we are doing
	screenShots screenShots = new Utilities.screenShots();


	public synchronized void NavigateTo(String url) {//,String tcN,String TcDesc

		driver.navigate().to(url);
	}


	public  void sendText(WebElement webElement,String text) {
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
		wait.until(ExpectedConditions.visibilityOf(webElement));

		//clear the text box if it's not empty
		if (! webElement.getText().isEmpty()) {
			webElement.clear();	
		}
		webElement.sendKeys(text);

	}

	public void moveToElement(WebElement webElement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement);
		actions.perform();
		//or maybe use this((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
		// explicit wait - to wait until a condition happen
		wait.until(ExpectedConditions.visibilityOf(webElement));

	}

	//clicking normal element
	public void clickAnElement(WebElement webElement) {
		// explicit wait - to wait until a condition happen
		wait.until(ExpectedConditions.elementToBeClickable(webElement));	
		webElement.click();
	}


	//clicking radiobtn element
	public void clickRadioBtnElement(WebElement webElement) {
		// explicit wait - to wait until a condition happen
		wait.until(ExpectedConditions.visibilityOf(webElement));
		if (!webElement.isSelected()) {
			webElement.click();			

		}
		else
			System.out.println("this radio btn is already selected");
	}


	//to select a value from drop down list
	public void selectFromDropDownWithOptionName(Select select,WebElement webelement,String optionName) {
		select = new Select(webelement);
		select.selectByVisibleText(optionName);

	}


	//assert if element exist 
	public void assertElementExist(WebElement webElement){
		wait.until(ExpectedConditions.visibilityOf(webElement));
		try {
//			Boolean isPresent = webElement.isDisplayed();
//			assertTrue(isPresent);
			assertEquals(true, webElement.isDisplayed());
			
			//Taking screenshot of successful donation
			screenShots.takeFullScreenshot(getTCName(),getTCD(),driver);

		} catch (Exception e) {
			System.out.println("The confirmation message doen't appear!");		}
	}




}
