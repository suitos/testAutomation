package mainTest;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import actionFactory.ElActions;
import apiTest.ApiTest;
import commonValues.Values;
import log.Logging;
import pageFactory.pages.Pages;
import utils.JsonReader;

public class SignInTest extends BaseTest {

	private JsonReader testData;
	public ElActions actions;
	public ApiTest api;
	
	@BeforeClass
	public void setEnv(ITestContext context) throws Exception {
		
		Logging.createNewLogger(context.getCurrentXmlTest().getName().toString());
		
		testData = new JsonReader("src/test/resources/testData/phptravleTestData.json");
		actions = new ElActions();
		api = new ApiTest();
		
	}
	
	@BeforeMethod
	public void goTestUrl() throws Exception {

		Pages.loginpage()
			.open(Values.BASEURL);
		
	}
	
	@Test(priority = 1, enabled = true)
	public void testSignin() throws Exception {

		api.userSignInSuccess(testData.getTestData("admin.email"), testData.getTestData("admin.password"));
		
		Pages.loginpage()
			.userLogin(testData.getTestData("admin.email"), testData.getTestData("admin.password"));
		
	}
	
}
