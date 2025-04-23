package io.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ExcelData {

    // Method to fetch data for a given test case name with dynamic parameters
    public ArrayList<String> getData(String filePath, String sheetName, String columnHeader, String testCaseName) throws IOException, InvalidFormatException {
        // Initialize an ArrayList to store the row data
        ArrayList<String> data = new ArrayList<>();

        // Create a DataFormatter object to convert cell values into readable strings
        DataFormatter formatter = new DataFormatter();

        // Open the Excel file and create a workbook object
        try (FileInputStream fis = new FileInputStream(filePath); // Create a FileInputStream to read the Excel file
             Workbook workbook = WorkbookFactory.create(fis)) { // Create a workbook using WorkbookFactory

            // Loop through each sheet in the workbook
            for (Sheet sheet : workbook) {
                // Check if the current sheet matches the provided sheet name
                if (sheet.getSheetName().equalsIgnoreCase(sheetName)) {

                    // Get the first row (header row) to find the column header
                    Row headerRow = sheet.getRow(0);

                    // Initialize a variable to hold the column index of the provided column header
                    int targetColumn = -1;

                    // Loop through each cell in the header row
                    for (Cell cell : headerRow) {
                        // Check if the cell's value matches the provided column header
                        if (formatter.formatCellValue(cell).equalsIgnoreCase(columnHeader)) {
                            targetColumn = cell.getColumnIndex(); // Store the column index
                            break; // Break the loop once we find the column
                        }
                    }

                    // If the column was not found, print a message and return empty data
                    if (targetColumn == -1) {
                        System.out.println("Target column not found.");
                        return data;
                    }

                    // Loop through all rows starting from row 1 (skip header row)
                    for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                        Row row = sheet.getRow(rowIndex); // Get the current row

                        // Check if the row is not null
                        if (row != null) {
                            // Get the cell in the provided column header's column
                            Cell testCaseCell = row.getCell(targetColumn);

                            // Format the cell value as a string
                            String cellValue = formatter.formatCellValue(testCaseCell);

                            // Check if the cell value matches the provided test case name
                            if (cellValue.equalsIgnoreCase(testCaseName)) {
                                // Loop through all cells in the matching row and add the data to the list
                                for (Cell cell : row) {
                                    data.add(formatter.formatCellValue(cell)); // Convert cell value to string and add it to the list
                                }
                                break; // Break the loop after processing the matching row
                            }
                        }
                    }
                }
            }
        } // End of try-with-resources block

        // Return the collected data (ArrayList of strings)
        return data;
    }
}
