package Utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class screenShots   {

	//We must extend base test to use the driver !!!
	//to set screenshots folder name and it's path for example D:/screenshots/.
	//or we can type "." then folder name to create it in the project directory
	//the below will create the folder in the project directory
	private String screenshotsFolderName = "/screenshots/";//you have just to provide the location and foldername
	protected String fullDirectory = System.getProperty("user.dir") + screenshotsFolderName + "/";//i have to add ###test name and "/"##
	protected static String currentDateAndTime;
	//to use it in renaming the folder after test and to name the word file too !! if it's passed twice it'll take all the runs ect....
	protected static String lastTimeOfTestCase;

	/**
	 * Takes screenshot of whole page and uses the current date/time as the file name
	 * LAST SHAPE
	 * Take screenshot of whole page and uses the current date/time as the file name
	 * also provide screenshots for each test case in seperate folders with test case name
	 * ## to use it in normal @test method  As Below :
	 evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
	 */
	
	public synchronized void takeFullScreenshot(String TCName,String TCDescription,WebDriver driver) {
		try {
		//taking the screenshot
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
	
			// add the folder path and screenshot name
			// i added the screen shot status with the path to make folder fo each run of each test case run
			//the first part untill "/" is for the folder path after that is the screenshot name
			FileUtils.copyFile(file, new File(fullDirectory + TCName + "_"+TCDescription+ "/" + TCName + "_" + TCDescription + "_" + GetCurrenDateAndTime() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// test screenshot

	
	
	
	
	
	//end test screenshot
	//changeing the fodler name to the folder with status name
		public synchronized void renameScreenShotsFolder(String tCName, String tCDescription, String status){
			lastTimeOfTestCase = GetCurrenDateAndTime();
			//getting the old folder directory and it's name
			File oldName = new File(fullDirectory+tCName+"_"+tCDescription);
			//creating new folder name and directory
			File newName = new File(fullDirectory+tCName+"_"+tCDescription+"_"+status+"_"+lastTimeOfTestCase);
	        //if the old name is exist then rename it to the new name we created
			if (oldName.renameTo(newName)) {
				//after renaming success print this
				System.out.println("folder renamed successfully");
			} else {
				//printing this if the renaming failed
				System.out.println("Failed to rename folder---Folder Not Exist");
			}
			
		}

		//Creating a method to get current date and time to use it in naming
		private synchronized String GetCurrenDateAndTime() {
			//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-ss-SSS");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
			LocalDateTime dateTime = LocalDateTime.now();
			currentDateAndTime = dateTime.format(formatter);//test
			return currentDateAndTime;
		}




}