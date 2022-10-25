package utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import log.Logging;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

	private File spreadsheet;
	private Sheet currentSheet;
	private Map<String, Integer> columns;

	private Workbook workbooks;
	
	public ExcelReader(File file) throws EncryptedDocumentException, IOException {
		spreadsheet = file;
		columns = new HashMap<String, Integer>();
		workbooks = WorkbookFactory.create(spreadsheet);
		
	}
	
	public void switchToSheet(String name) {
		try {
			currentSheet = workbooks.getSheet(name);
			currentSheet.getRow(0).forEach(cell -> {
				columns.put(cell.getStringCellValue(), cell.getColumnIndex());
			});
		} catch (Exception e) {
			Logging.getLogger().error(e);
			fail(e.getMessage());
		}
	}
	
	public int getRowCount(String name) throws InvalidFormatException, IOException {
		
			Sheet testDataSheet = workbooks.getSheet(name);

			int rowCount = testDataSheet.getLastRowNum();
				
			return rowCount;
			
	}
	
	public String getCellData(String column, int row) {
		try {
			Row dataRow = currentSheet.getRow(row - 1);
			
			return getCellDataAsString(dataRow.getCell(columns.get(column))).toString();
			
		} catch (NullPointerException e) {
			
			Logging.getLogger().error("Can't find the column name [" + column + "]........" + e.getMessage());
			fail("Can't find the column name [" + column + "]........" + e.getMessage());
			
		} catch (Exception e) {
			
			Logging.getLogger().error(e.getMessage());
			fail(e.getMessage());
		}
		
		return null;
	}

	public String getCellData(String column) {
		return getCellData(column, 2);
	}
	
    private String getCellDataAsString(Cell cell) {
    	
    	String value = ""; 
        
    	if(cell != null) {

    	    if(cell.getCellType() == CellType.STRING ) {
    	        value = cell.getStringCellValue(); 
    	    }

    	    if(cell.getCellType() == CellType.NUMERIC ) {
    	        value = Integer.toString((int)cell.getNumericCellValue());
    	    }
    	    
    	    if(cell.getCellType() == CellType.BOOLEAN) {
    	    	value = Boolean.toString(cell.getBooleanCellValue());
    	    }
    	    
    	    
    	}

	    return value;
    }
    
}
