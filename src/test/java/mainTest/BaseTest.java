package mainTest;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import driverFactory.Driver;
import utils.ExcelReader;
import utils.JsonReader;

public class BaseTest {

	public static JsonReader testData;
	public static ExcelReader addCustomersTestData;
	
	@BeforeSuite(alwaysRun = true)
	public void setEnv() {
		
		testData = new JsonReader("src/test/resources/testData/phptravleTestData.json");
		
	}
	
	@AfterClass(alwaysRun = true)
	public void driverQuit() throws Exception {
		
		Driver.quit();
		
	}
	
	@DataProvider(name = "addUserAnotherWayTest")
	public Object[][] addUserAnotherWayTest() throws Exception {
		
		addCustomersTestData.switchToSheet("addcustomers");
		
		return new Object[][] {
				{new module.makeEntity.MakeEntity_User(addCustomersTestData.getCellData("First Name"),
				addCustomersTestData.getCellData("Last Name"), addCustomersTestData.getCellData("Email"),
				addCustomersTestData.getCellData("Password"), addCustomersTestData.getCellData("Mobile Number"),
				addCustomersTestData.getCellData("Country"), addCustomersTestData.getCellData("Address1"),
				addCustomersTestData.getCellData("Address2"), addCustomersTestData.getCellData("Subscriber"))} };
	}
	
	
}
