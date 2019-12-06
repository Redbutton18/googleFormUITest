package googleFormTest;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;

import static googleForm.properties.UrlLinkProp.MAIN_URL;

public class BaseTest {

    public static String baseUrl = MAIN_URL;

    @BeforeTest
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        Configuration.reportsFolder = "target/test-result/reports";
    }

}
