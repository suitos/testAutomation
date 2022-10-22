package pageFactory.pages;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;

import driverFactory.*;
import log.*;

public class BasePage {

	public BasePage open(String url) throws Exception {
		
		try {
			
			Driver.get().get(url);
			((JavascriptExecutor) Driver.get()).executeScript("return document.readyState").equals("complete");
			
			Logging.getLogger().info(url + " open completed!!");
			
		} catch (InvalidArgumentException e) {
			Logging.getLogger().error(url + " is open fail!!");
			
			Driver.get().quit();
		}

		return this;
	}
}
