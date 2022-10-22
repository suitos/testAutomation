package pageFactory.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import actionFactory.ElActions;
import driverFactory.*;
import pageFactory.component.Menu;

public class LoginPage extends BasePage{

	@FindBy(name = "email")
	public WebElement emailinput;
	
	@FindBy(name = "password")
	public WebElement passwordinput;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement loginbtn;
	
	@FindBy(xpath = "//div[contains(@class,'alert')]")
	public WebElement loginalert;
	
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
	
	public LoginPage checkFailedLogin(String alertmsg) throws Exception {
		
		ElActions act = new ElActions();
		
		assertEquals(act.getText(loginalert), alertmsg);
		
		return this;
	}
}
