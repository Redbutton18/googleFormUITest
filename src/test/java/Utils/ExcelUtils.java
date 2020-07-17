package Utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelUtils {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtils(String excelPath, String sheetName) {
        try {
            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getRowCount() {
        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println("No of rows is: " + rowCount);
        return rowCount;
    }

    public static int getColCount() {
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        System.out.println("No of col is: " + colCount);
        return colCount;
    }

    public static String getCellDataString(int rowNum, int colNum) {

        String cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
        return cellData;
    }

    public static void getCellDataNumber(int rowNum, int colNum) {

        double cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
    }


}
