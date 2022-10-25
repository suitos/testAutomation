package mainTest;


import org.testng.annotations.AfterClass;

import driverFactory.Driver;
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
