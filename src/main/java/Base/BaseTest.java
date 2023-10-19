package Base;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.Status;
import Utilities.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	//some varibles and instances that'll be used 
	public   WebDriver driver;
	public  WebDriverWait wait;
	//for tcname and description threadsafe for parallel execution we do the below 
	private static final ThreadLocal<String> tCName = new ThreadLocal<>();
	private static final ThreadLocal<String> tCDescription = new ThreadLocal<>();
	protected static final ThreadLocal<String> tcStatus = new ThreadLocal<>();

	
	///
//	ChromeOptions options = new ChromeOptions(); 
	//global varibles from other classes	
	protected extentReport extentreportObject  = new extentReport();
	protected screenShots screenShotsOb = new screenShots();


	//Getting value of tcname and tcDescription for thread local
	public static String getTCName() {
		return tCName.get();//to return the value of this thread safe 
	}
	public static String getTCD() {
		return tCDescription.get();//to return the value of this thread safe 
	}
	public static String getTCStatus() {
		return tcStatus.get();
	}


	@BeforeSuite
	public void setupSuite() {
		WebDriverManager.chromedriver().setup();
		//open report page in the beginning 
		extentReport.setUpExtent();
		extentreportObject.open_reportPage(); 
	}

	//before method anotaion we'll be performed before test case
	@BeforeMethod
	public synchronized void beforeMethod(ITestResult result) {
//		//initiating a chromediver
		driver=new ChromeDriver();
		//maximixing the screen
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,30);

		//setting value of tcname and tcDescription for thread local
		tCName.set(result.getMethod().getMethodName());
		tCDescription.set(result.getMethod().getDescription());
		//##Creating tests  
		extentreportObject.test = extentReport.extent.createTest(getTCName() + "_" +getTCD());

	}

	//after method anotaion we'll be performed after test case is done
	@AfterMethod
	public synchronized void afterMethod(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				//setting up name of status
				tcStatus.set("Passed");
				//setting up the status of the test case
				//  log the status to the html report 
				extentreportObject.test.log(Status.PASS, getTCName()+"  :  "+getTCStatus());
				//calling a helper method
				insideStatusOfTestCase(getTCStatus());
			}
			else if (result.getStatus() == ITestResult.FAILURE) {
				//setting up name of status
				//				String status = "Failed";
				tcStatus.set("Failed");
				//  log the status to the html report 
				extentreportObject.test.log(Status.FAIL, getTCName()+"  :  "+getTCStatus());
				extentreportObject.test.fail(result.getThrowable());
				//calling a helper method
				insideStatusOfTestCase(getTCStatus());
			} 
			else {
				//setting up name of status
				tcStatus.set("Skiped");
				//  log the status to the html report 
				extentreportObject.test.log(Status.SKIP, getTCName()+"  :  "+getTCStatus());
				extentreportObject.test.skip(result.getThrowable());
				//calling a helper method
				insideStatusOfTestCase(getTCStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//after test suite annotation   we'll be performed after test suite is done 
	@AfterSuite
	public void afterSuite() {

		extentreportObject.refreshReport();

	}


	//>>>Below the section of Helper methods to reduce the code redundancy<<< 


	//the below will help to have the screenshots inserted inside word document and report 
	//It'll be used inside each status of each test case 
	public void insideStatusOfTestCase(String status) {

		//rename the folder to new name with status
		screenShotsOb.renameScreenShotsFolder(getTCName(), getTCD(),status);
		//save screenshots to word evidence file
		//		//log the status to the html report and insert screenshot to it
		extentreportObject.InsertAllImagesToTheReport(getTCName(), getTCD(),status);
		//updating and refreshing the report
		extentReport.extent.flush();
		extentreportObject.refreshReport();
		//closing after the method
				TearDown();
	}

	//after test method being done it'll help to close the driver correctly  
	public synchronized void TearDown() {
		//closing the driver
		if (driver != null) {
			driver.quit();

		}
	}

}
