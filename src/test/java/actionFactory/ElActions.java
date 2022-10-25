package actionFactory;

import static org.testng.Assert.fail;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.JavascriptExecutor;

import driverFactory.Driver;
import log.Logging;

public class ElActions {
	
    public ElActions click(WebElement el) throws Exception {
    	
    	if(checkElement(el)) el.click();
		
    	else {
    		Logging.getLogger().error(el + " click is failed!!");
			
    		fail(el + " click is failed!!");
    	}
    	
		return this;
	}
    
    public ElActions sendkey(WebElement el, String text) throws Exception {
    	
    	if(checkElement(el)) {
    		
    		el.clear();
    		Thread.sleep(500);
    		
    		el.sendKeys(text);
    	}
		
    	else {
    		Logging.getLogger().error(el + "sendkey is failed!!");
			
    		fail(el + " sendkeys is failed!!");
    	}
    	
		return this;
	}
    
    public String getText(WebElement el) throws Exception {
    	
    	if(checkElement(el)) return el.getText();
    	
    	else {
    		Logging.getLogger().error(el + "getText is failed!!");

			fail(el + "getText is failed!!");
			
			return null;
    	}
		
	}
    
    public String checkAttribute(WebElement el, String attribute) throws Exception {
		
    	if(checkElement(el)) {
			
		} else {
			Logging.getLogger().error(el + "[Attribute]" + attribute + " checkAttribute is failed!!");
			
			fail(el + "[Attribute]" + attribute + " checkAttribute is failed!!");
		}
		
		return el.getAttribute(attribute);
    	
    }
    
	public Boolean checkElement(WebElement el) throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
		
		try {
			((JavascriptExecutor) Driver.get()).executeScript("return document.readyState").equals("complete");
			
			wait.until(ExpectedConditions.visibilityOf(el));
			
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}
	
}
