package googleFormTest;

import DataProvider.DataForTest;
import DataProvider.ExcelDataProvider;
import com.codeborne.selenide.Condition;
import googleForm.pages.FormPage;
import googleForm.pages.ResultFormPage;
import googleForm.steps.BaseActionsWithForm;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static googleForm.dataGenerator.UserDataGenerator.getFakerEmailAddress;
import static googleForm.dataGenerator.UserDataGenerator.getFakerFirstName;
import static org.testng.Assert.assertEquals;

public class GoogleFormTestWithDataProvider extends BaseTest {

    FormPage formPage = new FormPage();
    BaseActionsWithForm baseActionsWithForm = new BaseActionsWithForm();
    ResultFormPage resultFormPage = new ResultFormPage();

    @BeforeMethod
    public void preCondition() {
        open(baseUrl);
        try {
            switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        } finally {
            clearBrowserCookies();
            clearBrowserLocalStorage();
        }
    }

    @TmsLink(value = "1P")
    @Test(description = "Enter Correct Customer Data", dataProvider = "testData", dataProviderClass = DataForTest.class)
    public void testEnterCorrectCustomerData(String email, String username, String date){
        baseActionsWithForm.specifyDefaultUserData(email, username);
        formPage.specifyDate(date);
        formPage.clickOnCheckBoxExcellent();
        formPage.clickOnSendButton();
        resultFormPage.positiveRegistrationMessageRus.shouldBe(Condition.visible);
    }

    @TmsLink(value = "")
    @Test(description = "Enter Excel Data", dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
    public void testEnterExcelData(String email, String username, String date){
        baseActionsWithForm.specifyDefaultUserData(email, username);
        formPage.specifyDate(date);
        formPage.clickOnCheckBoxExcellent();
        formPage.clickOnSendButton();
        resultFormPage.positiveRegistrationMessageRus.shouldBe(Condition.visible);
    }

}
