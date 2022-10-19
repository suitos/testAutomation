package actionFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverFactory.Driver;
import pageFactory.pages.BasePage;

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
    
    public String getText(WebElement el) throws Exception {
    	
    	waitElement(el);
    	
		return el.getText();
		
	}
    
    public String getText(By by) throws Exception {
    	
    	waitElement(by);
    	
		return Driver.get().findElement(by).getText();
		
	}
    
    public void waitElement(By by) throws Exception {
    	
    	try {
			WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			
			throw new Exception(by + " is not displayed");
		}
		
    }
    
    public void waitElement(WebElement el) throws Exception {
    	
    	try {
			WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
			wait.until(ExpectedConditions.visibilityOf(el));
		} catch (Exception e) {
			
			throw new Exception(el + " is not displayed");
		}
		
    }
    
    public void waitText(By by, String text) throws Exception {
    	
    	try {
			WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
			
		} catch (Exception e) {
			
			throw new Exception(by + " text is wrong [Expcted] " + text + " [Actual]" + Driver.get().findElement(by).getText());
		}
		
    }
    
    public void waitText(WebElement el, String text) throws Exception {
    	
    	try {
			WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
			wait.until(ExpectedConditions.textToBePresentInElement(el, text));
			
		} catch (Exception e) {
			
			throw new Exception(el + " text is wrong [Expcted] " + text + " [Actual]" + el.getText());
		}
		
		
    }
    
}
