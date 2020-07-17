package googleForm.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FormPage {

    private SelenideElement emailField = $("input[type='email']");
    private SelenideElement dateField = $("input[type=\"date\"]");
    private SelenideElement yourNameField = $("div.quantumWizTextinputPaperinputInputArea > input[type='text']");
    private SelenideElement checkBoxExcellent = $("div[aria-label=\"Excellent\"]");
    private SelenideElement checkBoxGoodEnough = $("div[aria-label=\"Good enough\"]");
    private SelenideElement checkBoxCouldBeBetter = $("div[aria-label=\"Could be better\"]");
    private SelenideElement checkBoxVeryBad = $("div[aria-label=\"Very bad\"]");
    private SelenideElement checkBoxAnother = $("div[aria-label=\"Інше:\"]");
    private SelenideElement checkBoxAnotherRus = $("div[aria-label=\"Другое:\"]");
    private SelenideElement inputCheckBoxAnother = $("input[aria-label=\"Інша відповідь\"]");
    private SelenideElement inputCheckBoxAnotherRus = $("input[aria-label=\"Другой ответ\"]");
    private SelenideElement sendButton = $("#mG61Hd > div > div > div.freebirdFormviewerViewNavigationNavControls > div.freebirdFormviewerViewNavigationButtonsAndProgress > div > div > span");

    public SelenideElement yourNameFieldError = $("div[id=\"i.err.404367803\"]");
    public SelenideElement emptyCheckBoxError = $("#i\\.err\\.1001784558");
    public SelenideElement emptyFieldErrorText = $x("//div[contains(text(),\"Это обязательный вопрос.\")]");
    public SelenideElement nonCorrectEmailErrorText = $x("//div[contains(text(),\"Укажите действительный адрес эл. почты\")]");
    public SelenideElement tooLongNameError = $x("//div[contains(text(),\"Текст превышает 20 символов.\")]");
    public SelenideElement notCorrectData = $(".freebirdFormviewerComponentsQuestionBaseErrorIcon");

    @Step
    public void specifyEmail(String email) {
        emailField.setValue(email);
    }

    @Step
    public void specifyDate(String date) {
        dateField.sendKeys(date);
    }

    @Step
    public void specifyName(String userName){
        yourNameField.setValue(userName);
    }

    @Step
    public void clickOnCheckBoxExcellent() {
        checkBoxExcellent.scrollTo().click();
    }

    @Step
    public void clickOnCheckBoxGoodEnough() {
        checkBoxGoodEnough.scrollTo().click();
    }

    @Step
    public void clickOnCheckCouldBeBetter() {
        checkBoxCouldBeBetter.scrollTo().click();
    }

    @Step
    public void clickOnCheckBoxVeryBad() {
        checkBoxVeryBad.scrollTo().click();
    }

    @Step
    public void clickOnCheckBoxAnotherAndWriteText(String text){
        checkBoxAnotherRus.scrollTo().click();
        inputCheckBoxAnotherRus.setValue(text);
    }

    @Step
    public void clickOnSendButton() {
        sendButton.scrollTo().click();
    }
}
