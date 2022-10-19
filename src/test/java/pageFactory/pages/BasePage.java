package pageFactory.pages;

import org.openqa.selenium.InvalidArgumentException;

import driverFactory.*;
import log.*;

public class BasePage {

	public BasePage open(String url) throws Exception {
		
		try {
			
			Driver.get().get(url);

			Logging.getLogger().info(url + " open completed!!");
			
		} catch (InvalidArgumentException e) {
			Logging.getLogger().error(url + " is open fail!!");
			
			Driver.get().quit();
		}

		return this;
	}
}
