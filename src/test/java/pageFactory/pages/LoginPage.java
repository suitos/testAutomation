package pageFactory.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import actionFactory.ElActions;
import driverFactory.*;

public class LoginPage extends BasePage{

	@FindBy(name = "email")
	public WebElement emailinput;
	
	@FindBy(name = "password")
	public WebElement passwordinput;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement loginbtn;
	
	public LoginPage() throws Exception {
	    PageFactory.initElements(Driver.get(), this);
	    
	}
	
	public LoginPage userLogin(String email, String password) throws Exception {
		
		ElActions act = new ElActions();
		
		act.sendkey(emailinput, email);
		
		act.sendkey(passwordinput, password);
		
		act.click(loginbtn);
		
		return this;
	}
}
