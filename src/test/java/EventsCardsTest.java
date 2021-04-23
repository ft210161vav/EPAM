import Pages.StartPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EventsCardsTest {
    StartPage startPage = new StartPage();

    /**
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Upcoming Events
     * 3 На странице отображаются карточки предстоящих мероприятий.
     * 4 В карточке указана информация о мероприятии:
     * • город проведения, язык
     * • название мероприятия
     * • дата мероприятия
     * • информация о регистрации
     * • список спикеров
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

        //   Пользователь нажимает на Upcoming Events

        $("a[href='#']").
                shouldBe(Condition.visible).
                click();

        if (closePopup.isDisplayed())
            closePopup
                    .shouldBe(Condition.visible)
                    .shouldBe(Condition.enabled)
                    .click();
        /**
         *      * 3 На странице отображаются карточки предстоящих мероприятий.
         *         В карточке указана информация о мероприятии:
         *      * • город проведения, язык
         *      * • название мероприятия
         *      * • дата мероприятия
         *      * • информация о регистрации
         *      * • список спикеров
         *      * Важно проверить порядок отображаемых блоков с информацией в карточке мероприятия
         */

        /**
         * Проверяем порядок отображаемых блоков с информацией в карточке мероприятия
         * определяем координаты каждого из блоков и убеждаемся, что они следуют друг за другом
          */
        //Координаты блока места проведения
        int xPosOnline=getCoordinates("p.online").get(0);
        int yPosOnline=getCoordinates("p.online").get(1);
        //Координаты блока языка
        int xPosLanguage=getCoordinates("p.language").get(0);
        int yPosLanguage=getCoordinates("p.language").get(1);
        //Координаты блока добавления в календарь
        int xPosCalendar=getCoordinates(".evnt-details-cell.calendar-cell.desktop").get(0);
        int yPosCalendar=getCoordinates(".evnt-details-cell.calendar-cell.desktop").get(1);
        //Одна из координат каждого следующего блока больше либо равна аналогичной координате предыдущего
        Assertions.assertTrue(xPosOnline<=xPosLanguage&&xPosLanguage<=xPosCalendar);
        Assertions.assertTrue(yPosOnline<=yPosLanguage&&xPosLanguage<=yPosCalendar);

        //Координаты блока языка
        int xPosEventName=getCoordinates(".evnt-event-name").get(0);
        int yPosEventName=getCoordinates(".evnt-event-name").get(1);
        //Координаты блока дат
        int xPosEventDates=getCoordinates(".evnt-event-dates").get(0);
        int yPosEventDates=getCoordinates(".evnt-event-dates").get(1);
        //Координаты блока спикеров
        int xPosEventSpeaker=getCoordinates(".evnt-speaker").get(0);
        int yPosEventSpeaker=getCoordinates(".evnt-speaker").get(1);
        Assertions.assertTrue(xPosEventName<=xPosEventDates&&xPosEventDates<=xPosEventSpeaker);
        Assertions.assertTrue(yPosEventName<=yPosEventDates&&yPosEventDates<=yPosEventSpeaker);




    }


    protected  ArrayList<Integer> getCoordinates(String elementSelector) {
        SelenideElement element = $(elementSelector);
        Point point = element.shouldBe(Condition.visible).getLocation();
        ArrayList<Integer> result= new ArrayList<>();
        System.out.println("Element's Position from left side is: " + point.getX() + " pixels.");
        System.out.println("Element's Position from top is: " + point.getY() + " pixels.");
        int xPoint = point.getX();
        int yPoint = point.getY();
        result.add(xPoint);
        result.add(yPoint);
        return result;
    }
}
