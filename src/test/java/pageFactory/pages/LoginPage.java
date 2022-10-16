package pageFactory.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import actionFactory.ElActions;
import driverFactory.*;

public class LoginPage {

	public ElActions act;
	
	@FindBy(name = "email")
	public WebElement emailinput;
	
	public LoginPage() throws Exception {
	    PageFactory.initElements(Driver.get(), this);
	    
	}
	
	public LoginPage userLogin(String email, String password) throws Exception {
		
		act.sendkey(emailinput, email);
		
		return this;
	}
}
