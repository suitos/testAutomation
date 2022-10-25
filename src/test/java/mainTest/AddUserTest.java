package mainTest;

import java.io.File;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apiTest.ApiTest;
import commonValues.Values;
import log.Logging;
import pageFactory.pages.Pages;
import utils.ExcelReader;
import utils.JsonReader;

public class AddUserTest extends BaseTest{

	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext context) throws Exception {
		
		Logging.createNewLogger(context.getCurrentXmlTest().getName().toString());
		
		testData = new JsonReader("src/test/resources/testData/phptravleTestData.json");
		exceltestData = new ExcelReader(new File("src/test/resources/testData/addCustomersTestData.xlsx"));
		
	}
	
	@BeforeGroups({"AddUser"})
	public void testSignin() throws Exception {

		ApiTest api = new ApiTest();
		//api.userSignInSuccess(testData.getTestData("admin.email"), testData.getTestData("admin.password"));
		api.userSignInSuccess();
		
		Pages.loginpage()
			.open(Values.BASEURL);
		
		Pages.loginpage()
			.userLogin(testData.getTestData("admin.email"), testData.getTestData("admin.password"));
		
	}

	@Test(priority = 1, enabled = true, groups = "AddUser")
	public void addUserTest() throws Exception {
		
		exceltestData.switchToSheet("addcustomers");
		
		Pages.menu()
			.goMenu("Accounts")
			.goSubMenu("Customers");
		
		Pages.customerspage()
			.goCustomersAdd();
		
		//int row = exceltestData.getRowCount("addcustomers");

		Pages.customersaddpage().addCustomers(
				exceltestData.getCellData("First Name"),
				exceltestData.getCellData("Last Name"), exceltestData.getCellData("Email"),
				exceltestData.getCellData("Password"), exceltestData.getCellData("Mobile Number"),
				exceltestData.getCellData("Country"), exceltestData.getCellData("Address1"),
				exceltestData.getCellData("Address2"), exceltestData.getCellData("Subscriber"));

		Pages.customerspage()
			.checkCustomersinCustomersPage(exceltestData.getCellData("Email"));
		
		Pages.customersaddpage()
			.checkCustomersinCustomersAddPage(
				exceltestData.getCellData("First Name"),
				exceltestData.getCellData("Last Name"), 
				exceltestData.getCellData("Email"),
				exceltestData.getCellData("Mobile Number"), 
				exceltestData.getCellData("Country"),
				exceltestData.getCellData("Address1"), 
				exceltestData.getCellData("Address2"),
				exceltestData.getCellData("Subscriber"));

	}
	
}
