package pages.wikipedia;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WikipediaMainPage extends BasePage {
    public WikipediaMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#content.mw-body>header>div")
    private WebElement countryListButton;
    @FindBy(css = "#p-lang-btn .interlanguage-link-target span")
    private List<WebElement> countries;
    @FindBy(css = "a[lang='en']")
    private WebElement englishWikiUrl;

    public void getAllCountries() {
        wait.until(ExpectedConditions.visibilityOf(countryListButton));
        String engWikiURL = englishWikiUrl.getAttribute("href");
        for (WebElement element : countries) {
            if (element.getAttribute("innerHTML").equals("English")) {
                System.out.println(element.getAttribute("innerHTML") + " - URL: " + engWikiURL);
            } else {
                System.out.println(element.getAttribute("innerHTML"));
            }
        }
    }
}
