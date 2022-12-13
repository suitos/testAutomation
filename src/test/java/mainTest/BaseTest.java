package mainTest;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import driverFactory.Driver;
import utils.ExcelReader;
import utils.JsonReader;

public class BaseTest {

	public static JsonReader testData;
	public static ExcelReader exceltestData;
	
	@BeforeSuite(alwaysRun = true)
	public void setEnv() {
		
		testData = new JsonReader("src/test/resources/testData/phptravleTestData.json");
		
	}
	
	@AfterClass(alwaysRun = true)
	public void driverQuit() throws Exception {
		
		Driver.quit();
		
	}
	
}
