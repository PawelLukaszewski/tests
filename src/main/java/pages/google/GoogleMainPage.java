package pages.google;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleMainPage extends BasePage {

    public GoogleMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#L2AGLb")
    private WebElement acceptAllCookiesButton;

    @FindBy(css = "#APjFqb")
    private WebElement searchField;

    @FindBy(css = ".FPdoLc center .RNmpXc")
    private WebElement luckyShotButton;

    public void shouldClickAcceptAllCookiesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptAllCookiesButton));
        click(acceptAllCookiesButton);
    }

    public void shouldSetTextToTheSearchField(String text) {
        sendKeys(searchField, text);
    }

    public void shouldClickLuckyShotButtonAndVerifyPageUrl(String w3SchoolsUrl) {
        wait.until(ExpectedConditions.elementToBeClickable(luckyShotButton));
        click(luckyShotButton);

        if (driver.getCurrentUrl().equals(w3SchoolsUrl)) {
            System.out.println("Jesteś na prawidłowej stronie!!");
        } else {
            System.out.println("Jesteś na złej stronie!" + "Strona, na której jestes ma adres: " + driver.getCurrentUrl());

            System.out.println("Za moment zostaniesz przekierowany na prawidłową stronę");
            driver.get(w3SchoolsUrl);
        }
    }
}
