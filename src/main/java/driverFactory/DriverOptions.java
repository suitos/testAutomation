package driverFactory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DriverOptions {

	public ChromeOptions chromeOptions() throws JsonProcessingException {
		
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
	    options.addArguments("--remote-allow-origins=*");
	    
	    Map<String, Object> prefs = new HashMap<>();

	    // with this chrome still asks for permission
		//prefs.put("profile.managed_default_content_settings.media_stream", 1);
		//prefs.put("profile.managed_default_content_settings.media_stream_camera", 1);
		//prefs.put("profile.managed_default_content_settings.media_stream_mic", 1);
		prefs.put("profile.default_content_setting_values.notifications", 2);

		prefs.put("profile.content_settings.exceptions.clipboard", getClipBoardSettingsMap(1));
		
		//다운로드 경고 무시
		prefs.put("safebrowsing.enabled", "true");
		
		prefs.put("download.default_directory", Driver.getDownloadPath());	// 다운로드 되는 파일 경로 지정 

		options.setExperimentalOption("prefs", prefs);
		
		return options;
	}
	
	public ChromeOptions chromeBetaOptions() throws JsonProcessingException {

		ChromeOptions options = new ChromeOptions();
        
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
		
		prefs.put("download.default_directory", Driver.getDownloadPath());	// 다운로드 되는 파일 경로 지정 

		options.setExperimentalOption("prefs", prefs);

		return options;
	}
	
	public ChromeOptions chromeHeadlessOptions() throws JsonProcessingException {
		
		ChromeOptions options = new ChromeOptions();
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
		
		prefs.put("download.default_directory", Driver.getDownloadPath());	// 다운로드 되는 파일 경로 지정 

		options.setExperimentalOption("prefs", prefs);

		return options;
		
	}
	
	public FirefoxOptions firefoxOptions() {
		 
		FirefoxOptions firefoxoptions = new FirefoxOptions();

         firefoxoptions.addArguments("high-dpi-support=1");
		
         return firefoxoptions;
	}

	public EdgeOptions edgeOptions() throws JsonProcessingException {

        EdgeOptions edgeoptions = new EdgeOptions();

        edgeoptions.addArguments("disable-gpu");
        edgeoptions.addArguments("enable-automation");
        edgeoptions.addArguments("--no-sandbox");
        edgeoptions.addArguments("--disable-infobars"); 
        edgeoptions.addArguments("--disable-dev-shm-usage");
        edgeoptions.addArguments("--disable-browser-side-navigation");
        edgeoptions.addArguments("--start-maximized");
        edgeoptions.addArguments("--use-fake-ui-for-media-stream");
        edgeoptions.addArguments("--use-fake-device-for-media-stream");
        
        edgeoptions.addArguments("force-device-scale-factor=1");
        edgeoptions.addArguments("high-dpi-support=1");
	
	    Map<String, Object> edgeprefs = new HashMap<>();

	    edgeprefs.put("profile.default_content_setting_values.notifications", 2);

	    edgeprefs.put("profile.content_settings.exceptions.clipboard", getClipBoardSettingsMap(1));
		
		//다운로드 경고 무시
	    edgeprefs.put("safebrowsing.enabled", "true");
		
	    edgeprefs.put("download.default_directory", Driver.getDownloadPath());	// 다운로드 되는 파일 경로 지정 

	    edgeoptions.setExperimentalOption("prefs", edgeprefs);
	    
	    return edgeoptions;

	}
	
	public static Map<String, Object> getClipBoardSettingsMap(int settingValue) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		map.put("last_modified", String.valueOf(System.currentTimeMillis()));
		map.put("setting", settingValue);
		Map<String, Object> cbPreference = new HashMap<>();
		cbPreference.put("[*.],*", map);
		return cbPreference;
	}

}
