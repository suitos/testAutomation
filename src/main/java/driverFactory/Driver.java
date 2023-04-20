package driverFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import io.github.bonigarcia.wdm.WebDriverManager;
import log.Logging;

public class Driver {

	public static String Browser = System.getProperty("browser");
    
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

	public static WebDriver get() throws Exception {
        WebDriver driver = threadDriver.get();

        if (driver == null) {
            driver = createDriver();
            threadDriver.set(driver);
        }
        
        return driver;
    }
	
	private static synchronized WebDriver createDriver() throws Exception {
		String GRID_URL = "";

        if (System.getProperty("grid_url") != null) {
            GRID_URL = System.getProperty("grid_url");
        }
        
        Logging.getLogger().info("Browser : " + Browser);
        
        switch (Browser) {
        
            case "chrome":
                WebDriverManager.chromedriver().clearResolutionCache().clearDriverCache().setup();
                
                return new ChromeDriver(new DriverOptions().chromeOptions());
                    
            case "chrome_beta":
                
                String BetaFullVersion = version().substring(version().lastIndexOf("=")+1);
                String[] Betaversion = BetaFullVersion.split("[.]");
                System.out.println(Betaversion[0] + "." + Betaversion[1] + "." + Betaversion[2] );

                WebDriverManager.chromedriver().clearResolutionCache().clearDriverCache().driverVersion(Betaversion[0] + "." + Betaversion[1] + "." + Betaversion[2]).setup();

                return new ChromeDriver(new DriverOptions().chromeBetaOptions());
                    
            case "chromeheadless":
                WebDriverManager.chromedriver().clearResolutionCache().clearDriverCache().setup();
                
                return new ChromeDriver(new DriverOptions().chromeHeadlessOptions());
                    
            case "chrome-remote":
                
                try {
                    URL url = new URL(GRID_URL);
                    
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setBrowserName("CHROME");
                    desiredCapabilities.setPlatform(Platform.ANY);

                    return new RemoteWebDriver(url, desiredCapabilities);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            case "firefox-remote":
                
                try {
                    URL url = new URL(GRID_URL);
                    
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setBrowserName("FIREFOX");
                    desiredCapabilities.setPlatform(Platform.ANY);
                    return new RemoteWebDriver(url, desiredCapabilities);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            case "firefox":
                WebDriverManager.firefoxdriver().clearResolutionCache().clearDriverCache().setup();

                return new FirefoxDriver(new DriverOptions().firefoxOptions());
                    
            case "edge":
                WebDriverManager.edgedriver().clearResolutionCache().clearDriverCache().setup();

                return new EdgeDriver(new DriverOptions().edgeOptions());
                    
            default:
                Logging.getLogger().error("Wrong browser name : " + Browser);
                throw new RuntimeException("Wrong browser name : " + Browser);
                
        }

    }

	public static void quit() throws Exception {
		
		WebDriver driver = threadDriver.get();
	    if (driver != null) {
	        try {
	            driver.quit();
	        } catch (Exception e) {
	            Logging.getLogger().error("Failed to quit driver", e);
	        } finally {
	            threadDriver.remove();
	            Logging.getLogger().info("Driver quit!!");
	        }
	    }

   }

	public static String version() throws Exception {
		String cmd = "wmic datafile where name=\"C:\\\\Program Files\\\\Google\\\\Chrome Beta\\\\Application\\\\chrome.exe\" get Version /value";
		int counter = 0;
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			
			p.waitFor();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        
	        while((line = r.readLine()) != null) {
	            counter++;
	            if(counter == 5)
	            {
	            	return line;
	            }
	        }  
			return line;

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static String getDownloadPath() {
		
		String tempfolder = System.getProperty("os.name").toLowerCase().contains("mac") ? "/test-temp/" : "\\test-temp\\";
		String temppath = System.getProperty("user.dir") + tempfolder;
		
		File folder = new File(temppath);
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		return temppath;
	}
	
	
	
	
}
