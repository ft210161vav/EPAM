import Pages.StartPage;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import static Pages.StartPage.init;
import static com.codeborne.selenide.Selenide.$;

/**
 * 1 Пользователь переходит на вкладку events
 * 2 Пользователь нажимает на Upcoming Events
 * 3 На странице отображаются карточки предстоящих мероприятий.
 * 4 В блоке This week даты проведения мероприятий больше или равны текущей дате и находятся в пределах текущей недели.
 */
public class DatesValidationTest extends StartPage {
    StartPage startPage = new StartPage();

    @Test
    public void datesValidation() throws Exception{
    /** 1 Пользователь переходит на вкладку events
     *  2 Пользователь нажимает на Upcoming Events
     */
        startPage.eventView("//span[contains(text(),'Upcoming events')]");
// * 3 На странице отображаются карточки предстоящих мероприятий.
        String date=$(".date").shouldBe(Condition.visible).getText().substring(8);

            Date eventDate = startPage.parseDate(date);
            Date currentDate=new Date();

/**  Отображаемые даты проведения будущих мероприятий
 *   больше или равны текущей дате
 */
        assert (currentDate.before(eventDate));
        }


}
