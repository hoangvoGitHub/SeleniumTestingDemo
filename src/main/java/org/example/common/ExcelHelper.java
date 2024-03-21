package org.example.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;

public class ExcelHelper {
    // To read file
    private FileInputStream fileInputStream;

    // To write file
    private FileOutputStream fileOutputStream;

    // Represents
    private Workbook workbook;

    private Sheet sheet;
    private Cell cell;
    private Row row;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String excelFilePath, String sheetName) throws Exception {
        try {
            File f = new File(excelFilePath);

            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File doesn't exist, so create");
            }

            fileInputStream = new FileInputStream(excelFilePath);
            workbook = WorkbookFactory.create(fileInputStream);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
            this.excelFilePath = excelFilePath;

            //adding all the column header names to the map 'colums;
            sheet.getRow(0).forEach(_cell -> {
                columns.put(_cell.getStringCellValue(), _cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String getCellData(int rowIndex, int columnIndex) throws Exception {
        try {
            cell = sheet.getRow(rowIndex).getCell(columnIndex);
            String cellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        cellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    cellData = "";
                    break;
            }
            return cellData;
        } catch (Exception e) {
            return "";
        }
    }

    public String getCellData(String columnName, int rowIndex) throws Exception {
        return getCellData(rowIndex, columns.get(columnName));
    }

    public void setCellData(String text, int rowIndex, int colnumIndex) throws Exception {
        try{
            row  = sheet.getRow(rowIndex);
            if(row ==null)
            {
                row = sheet.createRow(rowIndex);
            }
            cell = row.getCell(colnumIndex);

            if (cell == null) {
                cell = row.createCell(colnumIndex);
            }
            cell.setCellValue(text);

            fileOutputStream = new FileOutputStream(excelFilePath);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch(Exception e){
            throw (e);
        }
    }

}
