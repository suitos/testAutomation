package pageFactory.pages;

import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import actionFactory.ElActions;
import driverFactory.Driver;
import log.Logging;

public class CustomersPage extends BasePage {

	public By editbtnby = By.xpath("./../..//a[@title='Edit']");
	public By deletebtnby = By.xpath("./../..//a[@title='DELETE']");
	
	@FindBy(xpath = "//button[@class='btn btn-success mdc-ripple-upgraded']")
	public WebElement addCustomersbtn;
	
	@FindBy(xpath = "//a[contains(@href,'mailto')]")
	public List<WebElement> mailList;
	
	public CustomersPage() throws Exception {
	    PageFactory.initElements(Driver.get(), this);
	    
	}
	
	public CustomersPage goCustomersAdd() throws Exception {
		
		ElActions act = new ElActions();
		
		act.click(addCustomersbtn);
		
		return this;
	}

	public WebElement checkEmail(String email) throws Exception {

		ElActions act = new ElActions();
		try {
			for (int i = 0; i < mailList.size(); i++) {

				if (act.getText(mailList.get(i)).contentEquals(email)) {

					WebElement element = mailList.get(i);
					return element;
				}

			}
			
		} catch (NullPointerException e) {
			return null;
			
		}

		return null;

	}
	
	public CustomersPage checkCustomersinCustomersPage(String email) throws Exception {
		
		ElActions act = new ElActions();
		
		act.click(checkEmail(email).findElement(editbtnby));
		
		
		return this;
	}
	
	public CustomersPage deleteCustomers(String email) throws Exception {
		
		ElActions act = new ElActions();
		
		act.click(checkEmail(email).findElement(deletebtnby));
		
		Driver.get().switchTo().alert().accept();
		
		if(!act.checkElementInvisual(checkEmail(email))) {
			Logging.getLogger().error("Customers is not deleted!! [Email] " + email);
			
			fail("Customers is not deleted!! [Email] " + email);
		}
		
		return this;
	}
	
}
