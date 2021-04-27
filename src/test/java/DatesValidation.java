import Pages.StartPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * 1 Пользователь переходит на вкладку events
 * 2 Пользователь нажимает на Upcoming Events
 * 3 На странице отображаются карточки предстоящих мероприятий.
 * 4 В блоке This week даты проведения мероприятий больше или равны текущей дате и находятся в пределах текущей недели.
 */
public class DatesValidation {
    StartPage startPage = new StartPage();

    @Test
    public void datesValidation() throws Exception{
        startPage.init();
    /** 1 Пользователь переходит на вкладку events
     *  2 Пользователь нажимает на Upcoming Events
     */
        startPage.eventView("a[href='#']");
// * 3 На странице отображаются карточки предстоящих мероприятий.
        String date=$(".date").shouldBe(Condition.visible).getText().substring(8);
        String format = "dd MMM yyyy";

            Date eventDate = parseDate(date,format);
            Date currentDate=new Date();

/**  Отображаемые даты проведения будущих мероприятий
 *   больше или равны текущей дате
 */
        assert (currentDate.before(eventDate));
        }

    private Date parseDate(String date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format, new Locale("en"));
        return formatter.parse(date);
    }

}
