package mainTest;

import java.io.File;

import org.testng.ITestContext;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import api.LoginAPI;
import commonValues.Values;
import log.Logging;
import module.makeEntity.MakeEntity;
import pageFactory.component.Menu;
import pageFactory.pages.CustomersAddPage;
import pageFactory.pages.CustomersPage;
import pageFactory.pages.LoginPage;
import utils.ExcelReader;

public class AddUserTest extends BaseTest {

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
		
		addCustomersTestData = new ExcelReader(new File("src/test/resources/testData/addCustomersTestData.xlsx"));
		
	}
	
	@BeforeGroups({"AddUser"})
	public void testSignin() throws Exception {

		LoginAPI api = new LoginAPI();
		//api.userSignInSuccess(testData.getTestData("admin.email"), testData.getTestData("admin.password"));
		api.userSignInSuccess();
		
		loginpage
			.open(Values.BASEURL);
		
		loginpage
			.userLogin(testData.getTestData("admin.email"), testData.getTestData("admin.password"));
		
	}

	@Test(priority = 1, enabled = true, groups = "AddUser")
	public void addUserTest() throws Exception {
		
		addCustomersTestData.switchToSheet("addcustomers");
		
		menu
			.goMenu("Accounts")
			.goSubMenu("Customers");
		
		customerspage
			.goCustomersAdd();
		
		//int row = exceltestData.getRowCount("addcustomers");

		customersaddpage.addCustomers(
				addCustomersTestData.getCellData("First Name"),
				addCustomersTestData.getCellData("Last Name"), addCustomersTestData.getCellData("Email"),
				addCustomersTestData.getCellData("Password"), addCustomersTestData.getCellData("Mobile Number"),
				addCustomersTestData.getCellData("Country"), addCustomersTestData.getCellData("Address1"),
				addCustomersTestData.getCellData("Address2"), addCustomersTestData.getCellData("Subscriber"));

		customerspage
			.checkCustomersinCustomersPage(addCustomersTestData.getCellData("Email"));
		
		customersaddpage
			.checkCustomersinCustomersAddPage(
				addCustomersTestData.getCellData("First Name"),
				addCustomersTestData.getCellData("Last Name"), 
				addCustomersTestData.getCellData("Email"),
				addCustomersTestData.getCellData("Mobile Number"), 
				addCustomersTestData.getCellData("Country"),
				addCustomersTestData.getCellData("Address1"), 
				addCustomersTestData.getCellData("Address2"),
				addCustomersTestData.getCellData("Subscriber"));

	}

	@Test(priority = 2, enabled = true, groups = "AddUser", dataProvider = "addUserAnotherWayTest")
	public void addUserAnotherWayTest(MakeEntity makeUser) throws Exception {

		menu
			.goMenu("Accounts")
			.goSubMenu("Customers");
		
		customerspage
			.goCustomersAdd();
		
		makeUser.makeEntity();
		makeUser.saveEntitys();

		customerspage
			.checkCustomersinCustomersPage(addCustomersTestData.getCellData("Email"));
		
		addCustomersTestData.switchToSheet("addcustomers");
		
		customersaddpage
			.checkCustomersinCustomersAddPage(
				addCustomersTestData.getCellData("First Name"),
				addCustomersTestData.getCellData("Last Name"), 
				addCustomersTestData.getCellData("Email"),
				addCustomersTestData.getCellData("Mobile Number"), 
				addCustomersTestData.getCellData("Country"),
				addCustomersTestData.getCellData("Address1"), 
				addCustomersTestData.getCellData("Address2"),
				addCustomersTestData.getCellData("Subscriber"));

	}
	
	@AfterGroups({"AddUser"})
	public void deleteUser() throws Exception {

		menu
			.goMenu("Accounts")
			.goSubMenu("Customers");
		
		customerspage
			.deleteCustomers(addCustomersTestData.getCellData("Email"));
		
	}

}
