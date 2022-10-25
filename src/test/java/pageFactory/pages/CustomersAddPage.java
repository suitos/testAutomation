package pageFactory.pages;

import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import actionFactory.ElActions;
import driverFactory.Driver;
import log.Logging;

public class CustomersAddPage extends BasePage {

	@FindBy(name = "fname")
	public WebElement firstname;
	
	@FindBy(name = "lname")
	public WebElement lastname;
	
	@FindBy(name = "email")
	public WebElement email;
	
	@FindBy(name = "password")
	public WebElement password;
	
	@FindBy(name = "mobile")
	public WebElement mobile;
	
	@FindBy(xpath = "//span[@class='select2-chosen']")
	public WebElement selectCountry;
	
	@FindBy(xpath = "//input[contains(@class,'select2-input')]")
	public WebElement inputCountry;
	
	@FindBy(xpath = "//div[@class='select2-result-label']")
	public List<WebElement> resultCountryList;
	
	@FindBy(name = "address1")
	public WebElement address1;
	
	@FindBy(name = "address2")
	public WebElement address2;
	
	@FindBy(name = "newssub")
	public WebElement newssub;
	
	@FindBy(xpath = "//button[@class='btn btn-primary mdc-ripple-upgraded']")
	public WebElement updatesettingbtn;
	
	@FindBy(xpath = "//div[@class='alert alert-danger']")
	public WebElement alertdanger;
	
	public CustomersAddPage() throws Exception {
	    PageFactory.initElements(Driver.get(), this);
	    
	}
	
	public CustomersAddPage addCustomers(String fname, String lname, String em, String pw, String mobilenum, String country, String ad1, String ad2, String news) throws Exception {

		ElActions act = new ElActions();
		
		act.sendkey(firstname, fname);
		
		act.sendkey(lastname, lname);
		
		act.sendkey(this.email, em);
		
		act.sendkey(password, pw);
		
		act.sendkey(mobile, mobilenum);
		
		act.click(selectCountry);
		
		act.click(inputCountry);
		
		act.sendkey(inputCountry, country);
		
		act.click(resultCountryList.get(0));
		
		act.sendkey(this.address1, ad1);
		
		act.sendkey(this.address2, ad2);
		
		if(news.contains("TRUE")) act.click(newssub);
		
		act.click(updatesettingbtn);
		
		checkAlert();
		
		return this;
	}
	
	public String[] checkAlert() throws Exception {
		
		ElActions act = new ElActions();
		
		String[] alertmsg = null;
		
		if(act.checkElement(alertdanger)) {
			List<WebElement> alertlist = alertdanger.findElements(By.xpath(".//p"));
			
			for(int i = 0; i < alertlist.size(); i++) {
				Logging.getLogger().error(alertlist.get(i).getText());
				
				alertmsg = new String[alertlist.size()];
				
				alertmsg[i] = alertlist.get(i).getText();
								
			}

			fail("add Customers is failed!!");
			return alertmsg;
			
		}
		
		return null;
	}
	
	public CustomersAddPage checkCustomersinCustomersAddPage(String fname, String lname, String em, String mobilenum, String country, String ad1, String ad2, String news) throws Exception {
		
		ElActions act = new ElActions();
		
		Assert.assertEquals(act.checkAttribute(firstname, "value"), fname);
		Assert.assertEquals(act.checkAttribute(lastname, "value"), lname);
		Assert.assertEquals(act.checkAttribute(email, "value"), em);
		Assert.assertEquals(act.checkAttribute(mobile, "value"), mobilenum);
		Assert.assertEquals(act.getText(selectCountry), country);
		Assert.assertEquals(act.checkAttribute(address1, "value"), ad1);
		Assert.assertEquals(act.checkAttribute(address2, "value"), ad2);
		Assert.assertEquals(act.checkAttribute(newssub, "checked"), news);
		
		return this;
	}
	
	
}
