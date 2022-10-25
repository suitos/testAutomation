package mainTest;

import java.io.File;

import org.testng.ITestContext;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import apiTest.ApiTest;
import commonValues.Values;
import log.Logging;
import pageFactory.component.Menu;
import pageFactory.pages.CustomersAddPage;
import pageFactory.pages.CustomersPage;
import pageFactory.pages.LoginPage;
import utils.ExcelReader;

public class AddUserTest extends BaseTest{

	private LoginPage loginpage;
	private Menu menu;
	private CustomersPage customerspage;
	private CustomersAddPage customersaddpage;
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext context) throws Exception {
		
		Logging.createNewLogger(context.getCurrentXmlTest().getName().toString());
		
		loginpage = new LoginPage();
		menu = new Menu();
		customerspage = new CustomersPage();
		customersaddpage = new CustomersAddPage();
		
		exceltestData = new ExcelReader(new File("src/test/resources/testData/addCustomersTestData.xlsx"));
		
	}
	
	@BeforeGroups({"AddUser"})
	public void testSignin() throws Exception {

		ApiTest api = new ApiTest();
		//api.userSignInSuccess(testData.getTestData("admin.email"), testData.getTestData("admin.password"));
		api.userSignInSuccess();
		
		loginpage
			.open(Values.BASEURL);
		
		loginpage
			.userLogin(testData.getTestData("admin.email"), testData.getTestData("admin.password"));
		
	}

	@Test(priority = 1, enabled = true, groups = "AddUser")
	public void addUserTest() throws Exception {
		
		exceltestData.switchToSheet("addcustomers");
		
		menu
			.goMenu("Accounts")
			.goSubMenu("Customers");
		
		customerspage
			.goCustomersAdd();
		
		//int row = exceltestData.getRowCount("addcustomers");

		customersaddpage.addCustomers(
				exceltestData.getCellData("First Name"),
				exceltestData.getCellData("Last Name"), exceltestData.getCellData("Email"),
				exceltestData.getCellData("Password"), exceltestData.getCellData("Mobile Number"),
				exceltestData.getCellData("Country"), exceltestData.getCellData("Address1"),
				exceltestData.getCellData("Address2"), exceltestData.getCellData("Subscriber"));

		customerspage
			.checkCustomersinCustomersPage(exceltestData.getCellData("Email"));
		
		customersaddpage
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
	
	@AfterGroups({"AddUser"})
	public void deleteUser() throws Exception {

		menu
			.goMenu("Accounts")
			.goSubMenu("Customers");
		
		customerspage
			.deleteCustomers(exceltestData.getCellData("Email"));
		
	}

}
