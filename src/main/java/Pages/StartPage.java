package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class StartPage {
    protected final SelenideElement acceptCookiesButton=
    $(By.id("onetrust-accept-btn-handler"));

    public void init(){
        Configuration.holdBrowserOpen=false;
        Configuration.driverManagerEnabled=true;
    }
    public StartPage clickAcceptCookiesButton() {
        acceptCookiesButton.shouldBe(Condition.visible).click();
        return this;
    }

}
