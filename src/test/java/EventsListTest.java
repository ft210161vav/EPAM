import Pages.StartPage;
import com.codeborne.selenide.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;


public class EventsListTest {
    StartPage startPage = new StartPage();

    /**
     * Просмотр предстоящих мероприятий:
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Upcoming Events
     * 3 На странице отображаются карточки предстоящих мероприятий.
     * Количество карточек равно счетчику на кнопке Upcoming Events @param s
     */

    @Test
    public void eventsListView() {

        startPage.init();

        startPage.eventView("//span[contains(text(),'Upcoming events')]");

  //    *  Количество карточек равно счетчику на кнопке Upcoming Events

            SelenideElement EventsButton = $(".evnt-tab-counter.evnt-label.small.white");
            String EventsCount = EventsButton.getText();
            int cardsCount = $$(".evnt-cards-container").size();
            String CardsCount = Integer.toString(cardsCount);
            Assertions.assertEquals(EventsCount, CardsCount);
        }
    }

