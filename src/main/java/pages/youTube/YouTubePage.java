package pages.youTube;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class YouTubePage extends BasePage {
    public YouTubePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Zaakceptuj wszystko']/../..")
    private WebElement acceptAllCookiesButton;

    @FindBy(css = "//*[@id='time-status' and (ancestor::div[contains(@class,'ytd-rich-grid-row')])]")
    private List<WebElement> timeSection;


    public void ClickAcceptAllCookiesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptAllCookiesButton));
        click(acceptAllCookiesButton);
    }
}
