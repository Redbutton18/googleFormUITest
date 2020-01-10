package DataProvider;

import Utils.ExcelUtils;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {
    static String path = "C:/Users/Redbutton/IdeaProjects/googleForm/src/test/java/DataProvider/ExcelData.xlsx";
    static String sheet = "Sheet1";

    @DataProvider(name = "excelData")
    public Object[][] getExcelData(){
        Object data[][] = testData(path,sheet);
        return data;
    }

    public static Object[][] testData(String path, String sheet){

        ExcelUtils excel = new ExcelUtils(path, sheet);
        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();

        Object data[][] = new Object[rowCount-1][colCount];

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
            String cellData = excel.getCellDataString(i,j);
            data[i-1][j] = cellData;
            }
        }
        return data;
    }
}
