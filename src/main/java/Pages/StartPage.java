package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class StartPage {
    public final Logger logger = LogManager.getLogger("Pages.StartPage.class");
    protected final SelenideElement acceptCookiesButton =
            $(By.id("onetrust-accept-btn-handler"));


    public void init() {
        Configuration.holdBrowserOpen = false;
        Configuration.driverManagerEnabled = true;

    }

    public StartPage clickAcceptCookiesButton() {
        acceptCookiesButton.shouldBe(Condition.visible).click();
        return this;
    }

@Test
    public void eventView(String tab){
    //     * 1 Пользователь переходит на вкладку events
    open("https://events.epam.com/events");
    // * 2 Пользователь принимает политку куков сайта
    clickAcceptCookiesButton();
    SelenideElement closePopup = $(".evnt-popup-close");

    //   Пользователь нажимает на Past Events

    $$(tab).last()
            .shouldBe(Condition.visible).
            click();

    if (closePopup.isDisplayed())
        closePopup
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();

}
}


