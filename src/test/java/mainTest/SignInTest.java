package mainTest;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.LoginAPI;
import commonValues.Values;
import log.Logging;
import pageFactory.pages.LoginPage;

public class SignInTest extends BaseTest {

	private LoginPage loginpage;
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext context) throws Exception {
		
		Logging.createNewLogger(context.getCurrentXmlTest().getName().toString());
		
		loginpage = new LoginPage();
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void goTestUrl() throws Exception {

		loginpage
			.open(Values.BASEURL);
		
	}
	
	@Test(priority = 1, enabled = true)
	public void testSignin() throws Exception {

		LoginAPI api = new LoginAPI();
		api.userSignInSuccess(testData.getTestData("admin.email"), testData.getTestData("admin.password"));
		
		loginpage
			.userLogin(testData.getTestData("admin.email"), testData.getTestData("admin.password"));
		
	}
	
}
