package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
	private static Properties configFile;

	static {
		try (FileInputStream input = new FileInputStream("config.properties");) {

			configFile = new Properties();
			
			configFile.load(input);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load properties file!");
		}

	}

	public static String getProperty(String keyName) {
		return configFile.getProperty(keyName);
	}
}
