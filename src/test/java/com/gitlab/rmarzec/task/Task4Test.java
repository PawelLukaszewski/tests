package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.youTube.YouTubePage;

import java.util.ArrayList;
import java.util.List;

public class Task4Test {
    @Test
    public void Task4Test() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();
        YouTubePage youTubeMainPage = new YouTubePage(webDriver);
        webDriver.get("https://www.youtube.com/");
        //Lista kafelkow
        List<YTTile> ytTileList = new ArrayList<YTTile>();

        youTubeMainPage.ClickAcceptAllCookiesButton();

        WebDriverWait wait = new WebDriverWait(webDriver, 15);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ytd-rich-grid-row.ytd-rich-grid-renderer:nth-child(4)")));
        WebElement element = webDriver.findElement(By.cssSelector("ytd-rich-grid-row.ytd-rich-grid-renderer:nth-child(4)"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='time-status' and not(ancestor::div[contains(@id,'thumbnail-underlay')])]")));

        List<WebElement> videoElements = webDriver.findElements(By.xpath("//*[@id='video-title' and (ancestor::div[contains(@class,'ytd-rich-grid-row')])]"));
        List<WebElement> channelElements = webDriver.findElements(By.xpath("//*[@id='text-container' and (ancestor::div[contains(@class,'ytd-rich-grid-row')])]"));
        List<WebElement> durationElements = webDriver.findElements(By.cssSelector("ytd-thumbnail>a>div>ytd-thumbnail-overlay-time-status-renderer>div>span"));

        for (int i = 0; i < 12; i++) {
            WebElement videoTitleElement = videoElements.get(i);
            WebElement channelNameElement = channelElements.get(i);
            WebElement durationTimeElement = durationElements.get(i);

            String videoTitle = videoTitleElement.getText();
            String channelName = channelNameElement.getText();
            String duration = durationTimeElement.getText();

            ytTileList.add(new YTTile(videoTitle, channelName, duration));
        }

        for (int i = 0; i < ytTileList.size(); i++) {
            YTTile ytTile = ytTileList.get(i);
            System.out.println("Filmik #" + (i + 1) + ":");
            System.out.println("Tytuł filmiku: " + ytTile.getTitle());
            System.out.println("Nazwa kanał: " + ytTile.getChannel());
            System.out.println("Czas trwania: " + ytTile.getLength() + "\n");
        }
    }
}