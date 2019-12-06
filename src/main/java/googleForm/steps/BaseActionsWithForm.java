package googleForm.steps;

import googleForm.pages.FormPage;
import io.qameta.allure.Step;

public class BaseActionsWithForm {

    private static FormPage formPage = new FormPage();

    @Step("Specify Default User Data In Form")
    public static void specifyDefaultUserData(String email, String name){
        formPage.specifyEmail(email);
        formPage.specifyName(name);
    }
}
