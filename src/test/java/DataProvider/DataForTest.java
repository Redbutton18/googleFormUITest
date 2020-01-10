package DataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataForTest {

    @DataProvider
    public Object[][] testData() {
        Object[][] data = new Object[2][3];

        data[0][0] = "Data1@ukr.net";
        data[0][1] = "Data2";
        data[0][2] = "12122012";

        data[1][0] = "Data4@ukr.net";
        data[1][1] = "Data5";
        data[1][2] = "14122014";

        return data;
    }

    @Test(dataProvider = "testData")
    public void test(String d1, String d2, String d3){
        System.out.println(d1 + " " + d2 + " " + d3);
    }
}
