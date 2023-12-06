package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import com.google.common.collect.Iterables;
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
import java.util.Optional;

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

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ytd-channel-name #text")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='time-status' and not(ancestor::div[contains(@id,'thumbnail-underlay')])]")));
        List<WebElement> videoElements = webDriver.findElements(By.cssSelector(".style-scope ytd-rich-item-renderer > div"));

        for (WebElement vidElements : Iterables.limit(videoElements, 12)) {
            String videoTitle = vidElements.findElement(By.id("video-title")).getText();
            String channelName = vidElements.findElement(By.cssSelector("ytd-channel-name #text")).getText();
            String videoLength;
            Optional<WebElement> length;
            length = vidElements.findElements(By.cssSelector("ytd-thumbnail>a>div>ytd-thumbnail-overlay-time-status-renderer>div>span")).stream().findFirst();
            if (length.isPresent()) {
                videoLength = length.get().getText();
            } else {
                videoLength = "Na żywo";
            }
            ytTileList.add(new YTTile(videoTitle, channelName, videoLength));
        }

        for (int i = 0; i < ytTileList.size(); i++) {
            YTTile ytTile = ytTileList.get(i);
            if (!(ytTile.getLength() == "Na żywo")) {
                System.out.println("Filmik #" + (i + 1) + ":");
                System.out.println("Tytuł filmiku: " + ytTile.getTitle());
                System.out.println("Czas trwania: " + ytTile.getLength() + "\n");
            }
        }
    }
}