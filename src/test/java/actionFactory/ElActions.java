package actionFactory;

import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import driverFactory.Driver;

public class ElActions {
	
    public ElActions click(By by) throws Exception {
    	
    	if(checkElement(by)) Driver.get().findElement(by).click();
		
    	else fail(by + " is not clickable");
    	
		return this;
	}
    
    public ElActions click(WebElement el) throws Exception {
    	
    	if(checkElement(el)) el.click();
		
    	else fail(el + " is not clickable");
    	
		return this;
	}
    
    public ElActions sendkey(WebElement el, String text) throws Exception {
    	
    	if(checkElement(el)) {
    		
    		el.clear();
    		Thread.sleep(500);
    		
    		el.sendKeys(text);
    	}
		
    	else fail(el + " is not clickable");
    	
		return this;
	}
    
    public String getText(WebElement el) throws Exception {
    	
    	if(checkElement(el)) return el.getText();
    	
    	else return null;
		
	}
    
    public String getText(By by) throws Exception {
    	
    	if(checkElement(by)) return Driver.get().findElement(by).getText();
		
    	else return null;
	}

	public Boolean checkElement(By by) throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
		
		try {
			((JavascriptExecutor) Driver.get()).executeScript("return document.readyState").equals("complete");
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
		
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
	
	public Boolean checkElement(List<WebElement> list) throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
		
		try {
			((JavascriptExecutor) Driver.get()).executeScript("return document.readyState").equals("complete");
			
			wait.until(ExpectedConditions.visibilityOfAllElements(list));
			
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}
	
    
}
