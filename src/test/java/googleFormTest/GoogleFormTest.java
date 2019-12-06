package googleFormTest;

import com.codeborne.selenide.Condition;
import googleForm.pages.FormPage;
import googleForm.pages.ResultFormPage;
import googleForm.steps.BaseActionsWithForm;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;
import static googleForm.dataGenerator.UserDataGenerator.getFakerEmailAddress;
import static googleForm.dataGenerator.UserDataGenerator.getFakerFirstName;

public class GoogleFormTest extends BaseTest {

    FormPage formPage = new FormPage();
    BaseActionsWithForm baseActionsWithForm = new BaseActionsWithForm();
    ResultFormPage resultFormPage = new ResultFormPage();

    String email = getFakerEmailAddress();
    String userName = getFakerFirstName();

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
    @Test(description = "Enter Correct Customer Data")
    public void testEnterCorrectCustomerData(){
        baseActionsWithForm.specifyDefaultUserData(email, userName);
        formPage.specifyDate("21122012");
        formPage.clickOnCheckBoxExcellent();
        formPage.clickOnSendButton();
        resultFormPage.positiveRegistrationMessageRus.shouldBe(Condition.visible);
    }

    @TmsLink(value = "2P") //TODO: 05.12.12 bug #1
    @Test(description = "Enter Max Length Name In 'Your Name' Field")
    public void testEnterMaxLengthNameInYourNameField() {
        baseActionsWithForm.specifyDefaultUserData(email, "Asdfghjklqwertyuiopz");
        formPage.specifyDate("03012001");
        formPage.clickOnCheckBoxGoodEnough();
        formPage.clickOnSendButton();
        resultFormPage.positiveRegistrationMessageRus.shouldBe(Condition.visible);
    }

    @TmsLink(value = "3P") //TODO: 05.12.12 bug #2
    @Test(description = "Enter Less Then Max Length Name In 'Your Name' Field")
    public void testEnterLessThenMaxLengthNameInYourNameField() {
        baseActionsWithForm.specifyDefaultUserData(email, "Asdfghjklqwertyuio");
        formPage.specifyDate("03012001");
        formPage.clickOnCheckBoxVeryBad();
        formPage.clickOnSendButton();
        resultFormPage.positiveRegistrationMessageRus.shouldBe(Condition.visible);
    }

    @TmsLink(value = "1")
    @Test(description = "Leave Email Field Empty")
    public void testLeaveEmailFieldEmpty() {
        baseActionsWithForm.specifyDefaultUserData("", userName);
        formPage.specifyDate("12122012");
        formPage.clickOnCheckCouldBeBetter();
        formPage.clickOnSendButton();
        formPage.emptyFieldErrorText.shouldBe(Condition.visible);
    }

    @TmsLink(value = "2")
    @Test(description = "Leave Your Age Field Empty")
    public void testLeaveYourAgeFieldEmpty() {
        baseActionsWithForm.specifyDefaultUserData(email, userName);
        formPage.specifyDate("");
        formPage.clickOnCheckBoxVeryBad();
        formPage.clickOnSendButton();
        formPage.emptyFieldErrorText.shouldBe(Condition.visible);
    }

    @TmsLink(value = "3")
    @Test(description = "Leave Your Name Field Empty")
    public void testLeaveYourNameFieldEmpty() {
        baseActionsWithForm.specifyDefaultUserData(email, "");
        formPage.specifyDate("12122013");
        formPage.clickOnCheckBoxAnotherAndWriteText("Some text");
        formPage.clickOnSendButton();
        formPage.emptyFieldErrorText.shouldBe(Condition.visible);
    }

    @TmsLink(value = "4")
    @Test(description = "Uncheck All Checkboxes Test")
    public void testUncheckAllCheckboxes() {
        baseActionsWithForm.specifyDefaultUserData(email, userName);
        formPage.specifyDate("22222011");
        formPage.clickOnSendButton();
        formPage.emptyFieldErrorText.shouldBe(Condition.visible);
    }

    @TmsLink(value = "5") //TODO: 05.12.19 bug #3
    @Test(description = "Specify Future Date In 'Your Age' Field Test")
    public void testSpecifyFutureDateInYourAgeField(){
        baseActionsWithForm.specifyDefaultUserData(email, userName);
        formPage.specifyDate("22112222");
        formPage.clickOnCheckCouldBeBetter();
        formPage.clickOnSendButton();
        formPage.emptyFieldErrorText.shouldBe(Condition.visible);
    }

    @TmsLink(value = "6")
    @Test(description = "Enter Spaces In 'Email' Field Test")
    public void testSEnterSpacesInEmailField(){
        baseActionsWithForm.specifyDefaultUserData("      ", userName);
        formPage.specifyDate("22112211");
        formPage.clickOnCheckBoxVeryBad();
        formPage.clickOnSendButton();
        formPage.emptyFieldErrorText.shouldBe(Condition.visible);
    }

    @TmsLink(value = "7")
    @Test(description = "Enter Spaces In 'YourName' Field Test")
    public void testSEnterSpacesInYourNameField(){
        baseActionsWithForm.specifyDefaultUserData(email, "       ");
        formPage.specifyDate("22112210");
        formPage.clickOnCheckBoxVeryBad();
        formPage.clickOnSendButton();
        formPage.emptyFieldErrorText.shouldBe(Condition.visible);
    }


}
