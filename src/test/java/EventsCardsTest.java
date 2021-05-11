import Pages.StartPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class EventsCardsTest extends StartPage{
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
    @Feature(value = "Проверка блоков карточек мероприятий")
    @Step("Проверка, что блоки в карточке расположены друг за другом.")
    public void eventCardView() {
        startPage.eventView("//span[contains(text(),'Past Events')]");

        /*
         *  Выбираем карточку  меропиятия
         *  Проверяем порядок отображаемых блоков с информацией в карточке мероприятия
         *  определяем координаты каждого из блоков и убеждаемся, что они следуют друг за другом
         */

        //** Проверяем, что блоки наименования события и дат являются соседями - следуют за один за другим

        assert ($x("//div[@class='evnt-event-name']/following-sibling::div[@class='evnt-event-dates']").isDisplayed());

        checkCoordinates();

    }
    @Feature(value = "Получение координат блоков карточки мероприятия")
    @Step("Получаем координаты x в пикселях от левого края экрана и y - от верха")
    protected  ArrayList<Integer> getCoordinates(String elementSelector,int iterator) {
            SelenideElement element = $$(elementSelector).get(iterator);
            //System.out.println(element.getText());
            startPage.logger.info(element.getText());
            Point point = element.getLocation();
            ArrayList<Integer>resultArray = new ArrayList<>();
           // System.out.println("Element's Position from left side is: " + point.getX() + " pixels.");
           // System.out.println("Element's Position from top is: " + point.getY() + " pixels.");
            int xPoint = point.getX();
            int yPoint = point.getY();
        resultArray.add(xPoint);
        resultArray.add(yPoint);
        return resultArray;
    }

    @Feature(value = "Проверка следования координат блоков карточки мероприятия друг за другом")
    @Step("Сравнение вертикальных координат блоков карточки мероприятия")
    protected void checkCoordinates() {
    for (int i = 1; i < 12; i++) {
        //Координата блока языка
        int yPosLanguage = getCoordinates(".language", i).get(1);
        //Координата блока наименования мероприятия
        int yPosEventName = getCoordinates(".evnt-event-name", i).get(1);
        //Координата блока дат
        int yPosEventDates = getCoordinates(".date", i).get(1);
        //Координата блока спикеров
        int yPosEventSpeaker = getCoordinates(".speakers-wrapper", i).get(1);
        //** Координата Y каждого следующего блока больше либо равна аналогичной координате предыдущего
        assert (yPosLanguage <= yPosEventName && yPosEventName <= yPosEventDates && yPosEventDates <= yPosEventSpeaker);
    }
}
}


