package driverFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverOptions {

	public static ChromeOptions ChromeOptions() throws JsonProcessingException {

        ChromeOptions options = new ChromeOptions();
        
        options.addArguments("disable-gpu");
	    options.addArguments("enable-automation");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-infobars"); 
	    options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--disable-browser-side-navigation");
	    options.addArguments("--start-maximized");
	    options.addArguments("--use-fake-ui-for-media-stream");
	    options.addArguments("--use-fake-device-for-media-stream");
        
        options.addArguments("force-device-scale-factor=1");
	    options.addArguments("high-dpi-support=1");
	    
	    Map<String, Object> prefs = new HashMap<>();

	    // with this chrome still asks for permission
		//prefs.put("profile.managed_default_content_settings.media_stream", 1);
		//prefs.put("profile.managed_default_content_settings.media_stream_camera", 1);
		//prefs.put("profile.managed_default_content_settings.media_stream_mic", 1);
		prefs.put("profile.default_content_setting_values.notifications", 2);

		prefs.put("profile.content_settings.exceptions.clipboard", getClipBoardSettingsMap(1));
		
		//다운로드 경고 무시
		prefs.put("safebrowsing.enabled", "true");
		
		String filePath = getDownloadPath();
		prefs.put("download.default_directory", filePath);	// 다운로드 되는 파일 경로 지정 

		options.setExperimentalOption("prefs", prefs);
		
		return options;

	}
	
	public static ChromeOptions ChromeBetaOptions() throws Exception {

        ChromeOptions options = new ChromeOptions();

        options = new ChromeOptions();
        
        options.setBinary("C:\\Program Files\\Google\\Chrome Beta\\Application\\chrome.exe");
    	
        options.addArguments("disable-gpu");
	    options.addArguments("enable-automation");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-infobars"); 
	    options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--disable-browser-side-navigation");
	    options.addArguments("--start-maximized");
	    options.addArguments("--use-fake-ui-for-media-stream");
	    options.addArguments("--use-fake-device-for-media-stream");
        
        options.addArguments("force-device-scale-factor=1");
	    options.addArguments("high-dpi-support=1");
	    
	    Map<String, Object> prefs = new HashMap<>();

	    // with this chrome still asks for permission
		//prefs.put("profile.managed_default_content_settings.media_stream", 1);
		//prefs.put("profile.managed_default_content_settings.media_stream_camera", 1);
		//prefs.put("profile.managed_default_content_settings.media_stream_mic", 1);
		prefs.put("profile.default_content_setting_values.notifications", 2);

		prefs.put("profile.content_settings.exceptions.clipboard", getClipBoardSettingsMap(1));
		
		//다운로드 경고 무시
		prefs.put("safebrowsing.enabled", "true");
		
		String filePath = getDownloadPath();
		prefs.put("download.default_directory", filePath);	// 다운로드 되는 파일 경로 지정 

		options.setExperimentalOption("prefs", prefs);

		return options;

	}
	
	public static ChromeOptions ChromeHeadlessOptions() throws Exception {

        ChromeOptions options = new ChromeOptions();

        options = new ChromeOptions();
        options.setHeadless(true);
        
        options.addArguments("disable-gpu");
	    options.addArguments("enable-automation");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-infobars"); 
	    options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--disable-browser-side-navigation");
	    options.addArguments("--start-maximized");
	    options.addArguments("--use-fake-ui-for-media-stream");
	    options.addArguments("--use-fake-device-for-media-stream");
        
        options.addArguments("force-device-scale-factor=1");
	    options.addArguments("high-dpi-support=1");
	    
        options.addArguments("--window-size=1400,1080");
        
        Map<String, Object> prefs = new HashMap<>();

	    // with this chrome still asks for permission
		//prefs.put("profile.managed_default_content_settings.media_stream", 1);
		//prefs.put("profile.managed_default_content_settings.media_stream_camera", 1);
		//prefs.put("profile.managed_default_content_settings.media_stream_mic", 1);
		prefs.put("profile.default_content_setting_values.notifications", 2);

		prefs.put("profile.content_settings.exceptions.clipboard", getClipBoardSettingsMap(1));
		
		//다운로드 경고 무시
		prefs.put("safebrowsing.enabled", "true");
		
		String filePath = getDownloadPath();
		prefs.put("download.default_directory", filePath);	// 다운로드 되는 파일 경로 지정 

		options.setExperimentalOption("prefs", prefs);

		return options;

	}
	

	public static Map<String, Object> getClipBoardSettingsMap(int settingValue) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		map.put("last_modified", String.valueOf(System.currentTimeMillis()));
		map.put("setting", settingValue);
		Map<String, Object> cbPreference = new HashMap<>();
		cbPreference.put("[*.],*", map);
		return cbPreference;
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
}
