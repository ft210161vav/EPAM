package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class StartPage {
    public final Logger logger = LogManager.getLogger("Pages.StartPage.class");
    protected static final SelenideElement acceptCookiesButton =
            $(By.id("onetrust-accept-btn-handler"));

@BeforeAll
    public static void init() {
    Configuration.browser="chrome";
    Configuration.holdBrowserOpen = false;
        Configuration.driverManagerEnabled = true;
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.baseUrl= "localhost:4444";
        Configuration.clickViaJs=true;
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("browserName", "chrome");
    capabilities.setCapability("browserVersion", "90.0");
    capabilities.setCapability("enableVNC",true);
    capabilities.setCapability("enableVideo",false);
    Configuration.browserCapabilities = capabilities;

    SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
            .screenshots(true)
            .savePageSource(true)
    );
}

    public StartPage clickAcceptCookiesButton() {
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
            }
                return this;
    }
    public void eventView(String tab){
    //     * 1 Пользователь переходит на вкладку events
    open("https://events.epam.com/events");
    // * 2 Пользователь принимает политку куков сайта
    clickAcceptCookiesButton();
    SelenideElement closePopup = $(".evnt-popup-close");

    //   Пользователь нажимает на Past Events

    if (!tab.isEmpty())
    $$x(tab).last()
            .shouldBe(Condition.visible).
            click();

    if (closePopup.isDisplayed())
        closePopup
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();

}
    public void videoTalks(){
 /** 1 Пользователь переходит на вкладку VIDEO - Talks Library */

        open("https://events.epam.com");
        //$(".nav-item.active.talks-library-icon").shouldBe(Condition.visible).click();
        // * 2 Пользователь принимает политку куков сайта
        clickAcceptCookiesButton();
        SelenideElement closePopup = $(".evnt-popup-close");

        //   Пользователь нажимает на VIDEO

        $$(".nav-link").last()
                .shouldBe(Condition.visible).
                click();

        if (closePopup.isDisplayed())
            closePopup
                    .shouldBe(Condition.visible)
                    .shouldBe(Condition.enabled)
                    .click();

    }
    public static Date parseDate(String date) throws ParseException
    {
        String format="dd MMM yyyy";
        Locale.setDefault(Locale.US);
        return new SimpleDateFormat(format).parse(date);
    }
}


