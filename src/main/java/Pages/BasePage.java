package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	//this webdriver for detecting the web elements
public  WebDriver driver;
	
//the below is a constructor with the same class name  for passing many values to this class 
	public BasePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	
	}
}
