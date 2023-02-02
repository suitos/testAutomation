package utils;

import java.io.File;

import log.Logging;


public class FileManager {

	public File getDownloadFilePath(String filename) {
		
		String tempfolder = System.getProperty("os.name").toLowerCase().contains("mac") ? "/test-temp/" : "\\test-temp\\";
		String temppath = System.getProperty("user.dir") + tempfolder;
		
		File folder = new File(temppath);
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		File tempfilepath = new File(temppath + filename);
		
		return tempfilepath;
	}
	
	public File getTestDataFilePath(String filename) {
		
		String testdatafolder = System.getProperty("os.name").toLowerCase().contains("mac") ? "/testdata/" : "\\testdata\\";
		String testdatapath = System.getProperty("user.dir") + testdatafolder;
		
		File folder = new File(testdatapath);
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		File testdatafilepath = new File(testdatapath + filename);
		
		return testdatafilepath;
	}
	
	public boolean fileExist(String filename) {
		File file = new File(filename);
		boolean exists = file.exists();

		while (!exists) {
			file = new File(filename);
			exists = file.exists();
			if (exists) {
				break;

			}
		}

		return true;
	}

	public void deleteFile(String filepath) throws Exception {
		
	    File file = new File(filepath);

	    for(int i = 0; i< 50; i++) {
	    	if (file.exists()) {
				file.delete();
				
				Logging.getLogger().info("delete file : " + filepath);
				
			} else {
				
		    	break;
			}
	    	
	    }
	    
	}
}
