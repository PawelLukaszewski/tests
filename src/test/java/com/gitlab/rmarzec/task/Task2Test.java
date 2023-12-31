package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.wikipedia.WikipediaMainPage;


public class Task2Test {
    @Test
    public void Task2Test(){
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();
        WikipediaMainPage wikipediaMainPage = new WikipediaMainPage(webDriver);
        webDriver.get("https://pl.wikipedia.org/wiki/Wiki");

        wikipediaMainPage.getAllCountries();
    }
}
