package utils;

import static org.testng.Assert.fail;

import java.io.FileNotFoundException;
import java.io.FileReader;

import io.restassured.path.json.JsonPath;

public class JsonReader {
private FileReader reader;
	
	public String getTestData(String filename, String jsonPath) {
		Object testData = null;
		
		try {
			
			FileManager file = new FileManager();
			
			reader = new FileReader(file.getTestDataFilePath(filename));
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		testData = JsonPath.from(reader).getString(jsonPath);
		return String.valueOf(testData);
	}

}