package actionFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverFactory.Driver;

public class ElActions {
	
	public ElActions() throws Exception {
	    PageFactory.initElements(Driver.get(), this);
	    
	}
	
    public ElActions click(By by) throws Exception {
    	waitElement(by);
    	
		Driver.get().findElement(by).click();
		
		return this;
	}
    
    public ElActions click(WebElement el) throws Exception {
    	
    	waitElement(el);
    	
		el.click();
		
		return this;
	}
    
    public ElActions sendkey(WebElement el, String text) throws Exception {
    	
    	waitElement(el);
    	
		el.sendKeys(text);
		
		return this;
	}
    
    private static void waitElement(By by) throws Exception {
    	
    	try {
			WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			
			throw new Exception(by + " is not displayed");
		}
		
    }
    
    private static void waitElement(WebElement el) throws Exception {
    	
    	try {
			WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
			wait.until(ExpectedConditions.visibilityOf(el));
		} catch (Exception e) {
			
			throw new Exception(el + " is not displayed");
		}
		
    }
    
}
