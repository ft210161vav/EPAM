import Pages.StartPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static Pages.StartPage.init;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$x;
import static org.apache.commons.lang3.time.DateUtils.parseDate;

/**
 * 1 Пользователь переходит на вкладку events
 * 2 Пользователь нажимает на Past Events
 * 3 Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке
 * 4 На странице отображаются карточки прошедших мероприятий.
 * Количество карточек равно счетчику на кнопке Past Events.
 * Даты проведенных мероприятий меньше текущей даты.
 */

public class CanadaEventsTest extends StartPage{
    StartPage startPage = new StartPage();

    @Test
    @Feature(value = "Просмотр прошедших мероприятий в Канаде:")
    @Step("3 Пользователь нажимает на Location в блоке фильтров" +
            " и выбирает Canada в выпадающем списке")
    public void CanadaEvents() throws Exception {

        /**
         * * 1 Пользователь переходит на вкладку events
         *  * 2 Пользователь нажимает на Past Events
          */
                eventView("//span[contains(text(),'Past Events')]");
   //
        // 3 Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке
        $(By.id("filter_location")).should(Condition.visible).click();
        $("[data-group='Canada']").scrollIntoView(false).shouldBe(Condition.visible).click();
 //* 4 На странице отображаются карточки прошедших мероприятий.
//    * Количество карточек равно счетчику на кнопке Past Events.


        int cardsCount = $$(".evnt-cards-container").size();
        cardsCountTest(cardsCount);

        // * Даты проведенных мероприятий меньше текущей даты.
        Date currentDate=new Date();
        datesComparison(cardsCount,currentDate);

    }
    @Feature(value = "Сравнение количества мероприятий")
    @Step("Проверка, что количество карточек равно счетчику на кнопке Past Events.")
    public void cardsCountTest(int cardsCount){
        SelenideElement EventsButton = $(".evnt-tab-counter.evnt-label.small.white");
        String EventsCount = EventsButton.getText();
        String CardsCount = Integer.toString(cardsCount);
        Assertions.assertEquals(EventsCount, CardsCount);
    }
    @Feature(value = "Сравнение дат")
    @Step("Проверка, что даты проведенных мероприятий меньше текущей даты")
    public void datesComparison(int cardsCount,Date currentDate) throws Exception{
    for (int i=1;i<cardsCount;i++) {
        String date = $$(".date").get(i).shouldBe(Condition.visible).getText();
        Date eventDate = parseDate(date);
        assert (currentDate.after(eventDate));
    }
    }


    }


