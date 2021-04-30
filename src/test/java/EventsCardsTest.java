import Pages.StartPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
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
        startPage.eventView(".evnt-tab-item.nav-item");

        /*
         * Выбираем карточку  меропиятия
         *  Проверяем порядок отображаемых блоков с информацией в карточке мероприятия
         * определяем координаты каждого из блоков и убеждаемся, что они следуют друг за другом
         */


        for (int i=0;i<10;i++)
        {
            //Координаты блока языка
            int xPosLanguage = getCoordinates("p.language",i).get(0);
            int yPosLanguage = getCoordinates("p.language",i).get(1);

            //Координаты блока добавления в календарь
            int xPosCalendar = getCoordinates(".evnt-details-cell.calendar-cell.desktop",i).get(0);
            int yPosCalendar = getCoordinates(".evnt-details-cell.calendar-cell.desktop",i).get(1);
            //Одна из координат каждого следующего блока больше либо равна аналогичной координате предыдущего
            assert (xPosLanguage <= xPosCalendar);
            //Координаты блока наименования мероприятия
            int xPosEventName = getCoordinates(".evnt-event-name",i).get(0);
            int yPosEventName = getCoordinates(".evnt-event-name",i).get(1);
            //Координаты блока дат
            int xPosEventDates = getCoordinates(".evnt-event-dates",i).get(0);
            int yPosEventDates = getCoordinates(".evnt-event-dates",i).get(1);
            //Координаты блока спикеров
            int xPosEventSpeaker = getCoordinates(".evnt-speaker",i).get(0);
            int yPosEventSpeaker = getCoordinates(".evnt-speaker",i).get(1);

            assert (yPosEventName <= yPosEventDates);
        }

    }

    protected  ArrayList<Integer> getCoordinates(String elementSelector,int iterator) {
            SelenideElement element = $$(elementSelector).get(iterator);
            Point point = element.shouldBe(Condition.visible).getLocation();
            ArrayList<Integer>resultArray = new ArrayList<>();
            //System.out.println(elementSelector);
            //startPage.logger.info(elementSelector);
            //System.out.println("Element's Position from left side is: " + point.getX() + " pixels.");
            //System.out.println("Element's Position from top is: " + point.getY() + " pixels.");
            int xPoint = point.getX();
            int yPoint = point.getY();
        resultArray.add(xPoint);
        resultArray.add(yPoint);
        return resultArray;
    }

}


