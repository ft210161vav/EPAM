import Pages.StartPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Sleeper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class FiltersCategoriesTest {
    /**
     * 1 Пользователь переходит на вкладку Talks Library
     * 2 Пользователь нажимает на More Filters
     * 3 Пользователь выбирает: Category – Testing,
     * Location – Belarus, Language – English, На вкладке фильтров
     * 4 На странице отображаются карточки соответствующие правилам выбранных фильтров
     */
    StartPage startPage = new StartPage();

    @Test
    public void filterCategory() {
        startPage.init();
        //* 1 Пользователь переходит на вкладку Talks Library
        startPage.videoTalks();

        //* 2 Пользователь нажимает на More Filters
        $(".evnt-toggle-filters-button.evnt-button.btn").shouldBe(Condition.visible).click();

        //* 3 Пользователь выбирает: Category – Testing
        $(By.id("filter_category")).should(Condition.visible).click();
        $("[data-group='Testing']").scrollIntoView(true).shouldBe(Condition.visible).click();

        //* Location – Belarus
        $(By.id("filter_location")).should(Condition.visible).click();
        $("[data-group='Belarus']").scrollIntoView(false).shouldBe(Condition.visible).click();

        // Language – English
        $(By.id("filter_language")).should(Condition.visible).click();
        $("[data-value='ENGLISH']").shouldBe(Condition.visible).click();

        //Скрываем фильтры
        $(".evnt-toogle-filters-text.hide-more").shouldBe(Condition.visible).click();

        //     * 4 На странице отображаются карточки соответствующие правилам выбранных фильтров
        assert ($$(".evnt-talk-card").size()>0);
        String lang=$(".language").getText().substring(0,2);
        Assertions.assertEquals (lang,"En");
        }
    }


