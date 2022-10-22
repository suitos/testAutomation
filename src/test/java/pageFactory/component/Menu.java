package pageFactory.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import actionFactory.ElActions;
import driverFactory.Driver;

public class Menu {

	@FindBy(id = "drawerToggle")
	public WebElement dashboardMenu_button;
	
	@FindBy(className = "drawer-menu")
	public WebElement dashboardMenu;
	
	public Menu() throws Exception {
	    PageFactory.initElements(Driver.get(), this);
	    
	}
	
	public Menu goMenu(String menu) throws Exception {
		openDashboardIfClosed();
		
		ElActions act = new ElActions();
		act.click(By.xpath("//a[contains(., '"+ menu +"')]"));
		
		return this;
	}
	
	public Menu goSubMenu(String menu) throws Exception {
		openDashboardIfClosed();
		
		ElActions act = new ElActions();
		act.click(By.xpath("//a[contains(@href, '"+ menu.toLowerCase() +"')]"));
		
		return this;
	}

	private void openDashboardIfClosed() throws Exception {
		ElActions act = new ElActions();

		if (!act.checkElement(dashboardMenu)) {
			act.click(dashboardMenu_button);
		}
	}

}
