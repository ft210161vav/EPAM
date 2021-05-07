import Pages.StartPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ByIdOrName;

import static com.codeborne.selenide.Selenide.*;

/**
 * 1 Пользователь переходит на вкладку VIDEO - Talks Library
 * 2 Пользователь вводит ключевое слово QA в поле поиска
 * 3 На странице отображаются доклады, содержащие в названии ключевое слово поиска
 */
public class KeySearchTest extends StartPage{
    StartPage startPage = new StartPage();

    @Test
    public void keySearch() {
        startPage.videoTalks();
// * 2 Пользователь вводит ключевое слово QA в поле поиска
        $(".evnt-text-fields.form-control.evnt-search")
                .shouldBe(Condition.visible)
                .sendKeys("QA");
// * 3 На странице отображаются доклады, содержащие в названии ключевое слово поиска
            //Первая отображаемая карточка с искомым текстом
            SelenideElement divQA1 = $(".evnt-talk-name").shouldHave(Condition.text("QA"));
            assert (divQA1.isDisplayed());
            //Последняя отображаемая карточка с искомым текстом
            SelenideElement divQAN=$$(".evnt-talk-name").last();
            assert (divQAN.shouldHave(Condition.text("QA")).isDisplayed());
            }
    }


