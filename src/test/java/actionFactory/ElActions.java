package actionFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import driverFactory.Driver;

public class ElActions {
	public ElActions() throws Exception {
	    PageFactory.initElements(Driver.get(), this);
	    
	}
	
    public ElActions click(By by) throws Exception {
		
		Driver.get().findElement(by).click();
		
		return this;
	}
    
    public ElActions sendkey(WebElement el, String text) throws Exception {
		
		el.sendKeys(text);
		
		return this;
	}
    
}
