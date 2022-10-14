package driverFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.bonigarcia.wdm.WebDriverManager;
import log.Logging;

public class Driver {

	public static String Browser = System.getProperty("browser");
    
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

	public synchronized static WebDriver get() throws Exception {
        String GRID_URL = "";
        
        if (threadDriver.get() == null) {

            if (System.getProperty("grid_url") != null) {
                GRID_URL = System.getProperty("grid_url");
            }
            
            Logging.getLogger().info("Browser : " + Browser);
            switch (Browser) {
                case "chrome":
                    WebDriverManager.chromedriver().clearResolutionCache().clearDriverCache().setup();
                    
                    threadDriver.set(new ChromeDriver(DriverOptions.ChromeOptions()));
                    break;
                
                case "chrome_beta":
                	
                    String BetaFullVersion = DriverOptions.version().substring(DriverOptions.version().lastIndexOf("=")+1);
                    String[] Betaversion = BetaFullVersion.split("[.]");

                    WebDriverManager.chromedriver().clearResolutionCache().clearDriverCache().driverVersion(Betaversion[0] + "." + Betaversion[1] + "." + Betaversion[2]).setup();
                    
                    threadDriver.set(new ChromeDriver(DriverOptions.ChromeBetaOptions()));
                    break;
                    
                case "chromeheadless":
                	WebDriverManager.chromedriver().clearResolutionCache().clearDriverCache().setup();
                    
                    threadDriver.set(new ChromeDriver(DriverOptions.ChromeHeadlessOptions()));
                    break;
                    
                case "chrome-remote":
                    try {
                        URL url = new URL(GRID_URL);
                        
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(BrowserType.CHROME);
                        desiredCapabilities.setPlatform(Platform.ANY);

                        threadDriver.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    
                case "firefox-remote":
                    try {
                        URL url = new URL(GRID_URL);
                        
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(BrowserType.FIREFOX);
                        desiredCapabilities.setPlatform(Platform.ANY);
                        threadDriver.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    threadDriver.set(new FirefoxDriver());
                    break;
                default:
                	
                	Logging.getLogger().error("Wrong browser name : " + Browser);
                    throw new RuntimeException("Wrong browser name : " + Browser);
            }
        }
        
        return threadDriver.get();
    }

	public static void quit() throws Exception {
		if (threadDriver.get() != null) {
			threadDriver.get().quit();
			threadDriver.remove();
			
			Logging.getLogger().info("Driver quit!!");

		}
		
   }
	
	
	
	
	
}
