package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import log.Logging;

import static org.testng.Assert.fail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

	private File spreadsheet;
	private Sheet currentSheet;
	private Map<String, Integer> columns;

	public ExcelReader(File file) {
		spreadsheet = file;
		columns = new HashMap<String, Integer>();
	}

	public void switchToSheet(String name) {
		try (Workbook workbooks = WorkbookFactory.create(spreadsheet)) {
			currentSheet = workbooks.getSheet(name);
			currentSheet.getRow(0).forEach(cell -> {
				columns.put(cell.getStringCellValue(), cell.getColumnIndex());
			});
		} catch (Exception e) {
			Logging.getLogger().error(e);
			fail(e.getMessage());
		}
	}

	public String getCellData(String column, int row) {
		try {
			Row dataRow = currentSheet.getRow(row - 1);
			return getCellDataAsString(dataRow.getCell(columns.get(column)));
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

		return switch (cell.getCellType()) {

		case STRING -> cell.getStringCellValue();
		case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
		default -> "";
		};
	}
	
	
}
