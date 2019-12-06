package googleForm.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ResultFormPage {

    public SelenideElement positiveRegistrationMessage = $x("//div[contains(text(),\"Вашу відповідь було записано.\")]");
    public SelenideElement positiveRegistrationMessageRus = $x("//div[contains(text(),\"Ответ записан.\")]");

}
