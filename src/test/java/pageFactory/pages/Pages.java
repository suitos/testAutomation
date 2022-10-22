package pageFactory.pages;

import pageFactory.component.Menu;

public class Pages {

	public static BasePage basepage() throws Exception {
		return new BasePage();
	}
	
	public static LoginPage loginpage() throws Exception {
		return new LoginPage();
	}
	
	public static Menu menu() throws Exception {
		return new Menu();
	}
	
}
