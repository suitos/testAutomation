package mainTest;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import utils.ExcelReader;
import utils.JsonReader;

public class BaseTest {

	public static JsonReader testData;
	public static ExcelReader exceltestData;
	
	@AfterClass
	public void driverQuit() throws Exception {
		
		Driver.quit();
	}
	
}
