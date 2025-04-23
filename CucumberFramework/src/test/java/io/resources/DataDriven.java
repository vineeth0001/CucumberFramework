package io.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public ArrayList<String> getData(String filePath, String sheetName, String columnHeader, String testCaseName ) throws IOException {

		ArrayList<String> data = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		// 1st get the number of sheets
		int sheetsCount = workbook.getNumberOfSheets();
		// System.out.println(sheetsCount);

		// Loop through each sheet

		for (int i = 0; i < sheetsCount; i++) {

			// Print the sheet names
			// System.out.println(workbook.getSheetName(i));
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {

				XSSFSheet sheet = workbook.getSheetAt(i);
				// identify TestCases column by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator(); // Here sheet is a collection of rows
				Row firstRow = rows.next(); // now it will be in the 1st row
				Iterator<Cell> cells = firstRow.cellIterator(); // // Here firstrow is a collection of cells
				int k = 0;
				int column = 0;
				while (cells.hasNext()) {
					Cell cellValue = cells.next();
					if (cellValue.getStringCellValue().equalsIgnoreCase(columnHeader)) {
						column = k;

					}
					k++;
				}
				// System.out.println(column);

				// once coulmn is identified then scan entire TestCase column to identify snacks
				// row
				while (rows.hasNext()) {

					Row row = rows.next();
					if (row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {

						// after you grab snacks testcase row -- pull all the data
						Iterator<Cell> cell = row.cellIterator();
						while (cell.hasNext()) {

							Cell cellDataType = cell.next();
							if (cellDataType.getCellType() == CellType.STRING) {
								// System.out.println(cellDataType.getStringCellValue());
								data.add(cellDataType.getStringCellValue());
							} else {
								// System.out.println(cellDataType.getNumericCellValue());
								data.add(NumberToTextConverter.toText(cellDataType.getNumericCellValue()));
							}
						}
					}
				}

			}

		}
		return data;
	}

}
