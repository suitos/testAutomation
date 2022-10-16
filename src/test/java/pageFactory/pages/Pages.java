package pageFactory.pages;

public class Pages {

	public static BasePage basepage() throws Exception {
		return new BasePage();
	}
	
	public static LoginPage loginpage() throws Exception {
		return new LoginPage();
	}
	
}
