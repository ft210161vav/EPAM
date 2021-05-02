import Pages.BasePage;
import Pages.StartPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.apache.commons.lang3.time.DateUtils.parseDate;

/**
 * 1 Пользователь переходит на вкладку events
 * 2 Пользователь нажимает на Past Events
 * 3 Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке
 * 4 На странице отображаются карточки прошедших мероприятий.
 * Количество карточек равно счетчику на кнопке Past Events.
 * Даты проведенных мероприятий меньше текущей даты.
 */

public class CanadaEventsTest {
    StartPage startPage = new StartPage();

    @Test
    public void CanadaEvents() throws Exception{
    startPage.init();
        /**
         * * 1 Пользователь переходит на вкладку events
         *  * 2 Пользователь нажимает на Past Events
          */
        startPage.eventView(".evnt-tab-item.nav-item");
   //
        // 3 Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке
        $(By.id("filter_location")).should(Condition.visible).click();
        $("[data-group='Canada']").scrollIntoView(false).shouldBe(Condition.visible).click();
 //* 4 На странице отображаются карточки прошедших мероприятий.
//    * Количество карточек равно счетчику на кнопке Past Events.
        SelenideElement EventsButton = $(".evnt-tab-counter.evnt-label.small.white");
        String EventsCount = EventsButton.getText();
        int cardsCount = $$(".evnt-cards-container").size();
        String CardsCount = Integer.toString(cardsCount);
        Assertions.assertEquals(EventsCount, CardsCount);

// * Даты проведенных мероприятий меньше текущей даты.
        Date currentDate=new Date();

        for (int i=1;i<cardsCount;i++) {
            String date = $$(".date").get(i).shouldBe(Condition.visible).getText();
            Date eventDate = parseDate(date);
            assert (currentDate.after(eventDate));
        }
    }

    }


