package googleFormTest;

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

public class GoogleFormTest1 extends BaseTest {

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
    public void testEnterSpacesInYourNameField(){
        baseActionsWithForm.specifyDefaultUserData(email, "       ");
        formPage.specifyDate("22112210");
        formPage.clickOnCheckBoxVeryBad();
        formPage.clickOnSendButton();
        formPage.emptyFieldErrorText.shouldBe(Condition.visible);
    }

    @TmsLink(value = "8") //TODO: 06.12.19 bug #4
    @Test(description = "Enter Only Numbers In 'YourName' Field Test")
    public void testEnterOnlyNumbersInYourNameField(){
        baseActionsWithForm.specifyDefaultUserData(email, "123456789");
        formPage.specifyDate("23121998");
        formPage.clickOnCheckCouldBeBetter();
        formPage.clickOnSendButton();
        String errorNameField = formPage.yourNameFieldError.getText();
        assertEquals(errorNameField, "Имя не может содержать только цифры!");
    }

    @TmsLink(value = "9") //TODO: 06.12.19 bug #5
    @Test(description = "Enter Only Special Characters In 'YourName' Field Test")
    public void testEnterOnlySpecialCharactersInYourNameField(){
        baseActionsWithForm.specifyDefaultUserData(email, "!@#$%&*()<>");
        formPage.specifyDate("14011999");
        formPage.clickOnCheckCouldBeBetter();
        formPage.clickOnSendButton();
        String errorNameField = formPage.yourNameFieldError.getText();
        assertEquals(errorNameField, "Имя не может содержать только спецсимволы!");
    }

    @TmsLink(value = "12")
    @Test(description = "Enter More Then Max Length Name In 'Your Name' Field Test")
    public void testEnterMoreThenMaxLengthNameInYourNameField() {
        baseActionsWithForm.specifyDefaultUserData(email, "Asdfghjklqwertyuiopzx");
        formPage.specifyDate("03012001");
        formPage.clickOnCheckBoxVeryBad();
        formPage.clickOnSendButton();
        formPage.tooLongNameError.shouldBe(Condition.visible);
    }

    @TmsLink(value = "13") //TODO: 06.12.19 bug #9
    @Test(description = "Choose All CheckBoxes 'How is your mood' Test")
    public void testChooseAllCheckBoxesHowIsYourMood() {
        baseActionsWithForm.specifyDefaultUserData(email, userName);
        formPage.specifyDate("13112011");
        formPage.clickOnCheckBoxExcellent();
        formPage.clickOnCheckBoxGoodEnough();
        formPage.clickOnCheckCouldBeBetter();
        formPage.clickOnCheckBoxVeryBad();
        formPage.clickOnCheckBoxAnotherAndWriteText("Some text");
        formPage.clickOnSendButton();
        String checkBoxError = formPage.emptyCheckBoxError.getText();
        assertEquals(checkBoxError, "Выберите один чек бокс!");
    }

    @TmsLink(value = "14")
    @Test(description = "Enter Not Correct Email In 'Email' Field Test")
    public void testEnterNotCorrectEmailInEmailField() {
        baseActionsWithForm.specifyDefaultUserData("sfasfaa@d.d", userName);
        formPage.specifyDate("03012004");
        formPage.clickOnCheckBoxVeryBad();
        formPage.clickOnSendButton();
        formPage.nonCorrectEmailErrorText.shouldBe(Condition.visible);
    }

    @TmsLink(value = "15") //TODO: 06.12.19 bug #10
    @Test(description = "Enter Copy/Paste From Address Book With Name In 'Email' Field Test")
    public void testEnterCopyPasteFromAddressBookWithNameInEmailField() {
        baseActionsWithForm.specifyDefaultUserData("Joe Smith <email@domain.com>", userName);
        formPage.specifyDate("23102012");
        formPage.clickOnCheckBoxVeryBad();
        formPage.clickOnSendButton();
        formPage.nonCorrectEmailErrorText.shouldBe(Condition.visible);
    }

    @TmsLink(value = "20") //TODO: 06.12.19 bug #15
    @Test(description = "Specify Min Date In Calendar Test")
    public void testSpecifyMinDateInCalendar() {
        baseActionsWithForm.specifyDefaultUserData(email, userName);
        formPage.specifyDate("01010001");
        formPage.clickOnCheckBoxVeryBad();
        formPage.clickOnSendButton();
        String dateError = formPage.dateFieldError.getText();
        assertEquals(dateError, "Что то типа введите корректную дату");
    }

    @TmsLink(value = "21") //TODO: 06.12.19 bug #16
    @Test(description = "Specify Max Date In Calendar Test")
    public void testSpecifyMaxDateInCalendar() {
        baseActionsWithForm.specifyDefaultUserData(email, userName);
        formPage.specifyDate("0909999999");
        formPage.clickOnCheckBoxExcellent();
        formPage.clickOnSendButton();
        String dateError = formPage.dateFieldError.getText();
        assertEquals(dateError, "Что то типа введите корректную дату");
    }


}
