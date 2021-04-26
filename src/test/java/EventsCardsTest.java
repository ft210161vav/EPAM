import Pages.StartPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class EventsCardsTest {
    StartPage startPage = new StartPage();

    /**
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Past Events
     * 3 На странице отображаются карточки предстоящих мероприятий.
     * 4 В карточке указана информация о мероприятии:
     * язык
     * название мероприятия
     * дата мероприятия
     * информация о регистрации
     * список спикеров /
     * Важно проверить порядок отображаемых блоков с информацией в карточке мероприятия
     */
    @Test
    public void eventCardView() {
        startPage.init();

        //     * 1 Пользователь переходит на вкладку events
        open("https://events.epam.com/events");
        // * 2 Пользователь принимает политку куков сайта
        startPage.clickAcceptCookiesButton();
        SelenideElement closePopup = $(".evnt-popup-close");

        //   Пользователь нажимает на Past Events

        $$(".evnt-tab-item.nav-item").last()
                .shouldBe(Condition.visible).
                click();

        if (closePopup.isDisplayed())
            closePopup
                    .shouldBe(Condition.visible)
                    .shouldBe(Condition.enabled)
                    .click();

        /*
         * Выбираем карточку  меропиятия
         *  Проверяем порядок отображаемых блоков с информацией в карточке мероприятия
         * определяем координаты каждого из блоков и убеждаемся, что они следуют друг за другом
         */

     // $$(".evnt-event-name").get(1).shouldBe(Condition.visible);

        //Координаты блока языка
        int xPosLanguage=getCoordinates("p.language").get(0);
        int yPosLanguage=getCoordinates("p.language").get(1);

        //Координаты блока добавления в календарь
        int xPosCalendar=getCoordinates(".evnt-details-cell.calendar-cell.desktop").get(0);
        int yPosCalendar=getCoordinates(".evnt-details-cell.calendar-cell.desktop").get(1);
        //Одна из координат каждого следующего блока больше либо равна аналогичной координате предыдущего
       Assertions.assertTrue(xPosLanguage<=xPosCalendar);
        //Координаты блока наименования мероприятия
        int xPosEventName=getCoordinates(".evnt-event-name").get(0);
        int yPosEventName=getCoordinates(".evnt-event-name").get(1);
        //Координаты блока дат
        int xPosEventDates=getCoordinates(".evnt-event-dates").get(0);
        int yPosEventDates=getCoordinates(".evnt-event-dates").get(1);
        //Координаты блока спикеров
        int xPosEventSpeaker=getCoordinates(".evnt-speaker").get(0);
        int yPosEventSpeaker=getCoordinates(".evnt-speaker").get(1);

        Assertions.assertTrue(yPosEventName<=yPosEventDates);
        Assertions.assertTrue(xPosEventDates<=xPosEventSpeaker);


    }

    protected  ArrayList<Integer> getCoordinates(String elementSelector) {
            SelenideElement element = $$(elementSelector).get(9);
            Point point = element.shouldBe(Condition.visible).getLocation();
            ArrayList<Integer>resultArray = new ArrayList<>();
            System.out.println(elementSelector);
            System.out.println("Element's Position from left side is: " + point.getX() + " pixels.");
            System.out.println("Element's Position from top is: " + point.getY() + " pixels.");
            int xPoint = point.getX();
            int yPoint = point.getY();
        resultArray.add(xPoint);
        resultArray.add(yPoint);
        return resultArray;
    }

}


