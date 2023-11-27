package pages.w3Schools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import base.BasePage;

public class W3SchoolsTryItYourselfPage extends BasePage {

    public W3SchoolsTryItYourselfPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "tryitLeaderboard")
    private WebElement leaderBoard;
    @FindBy(css = "body > h1")
    private WebElement title;

    @FindBy(css = "#cars")
    private WebElement chooseCar;


    public void getTitleFromPage() {
        switchToTheNewOpenedTab();
        wait.until(ExpectedConditions.visibilityOf(leaderBoard));
        driver.switchTo().frame("iframeResult");
        System.out.println("Nagłówek strony to: " + title.getText());
    }

    public void chooseCar(String carName) {
        Select selectCar = new Select(chooseCar);
        selectCar.selectByVisibleText(carName);
        String elementValue = chooseCar.getAttribute("value");

        System.out.println("(" + carName + ", " + elementValue + ")");

    }
}