package com.browserstack.tests;

import com.browserstack.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

public class SampleTest extends BaseTest {
    private WebDriver driver;

    @Test(dataProvider = "capabilities")
    public  void testCase1( DesiredCapabilities capabilities, String url) throws Exception{
        // create driverFactory class
        driver = new RemoteWebDriver(
                new URL("http://" + USERNAME + ":" + ACCESSKEY + "@" + SERVER + "/wd/hub")
                , capabilities);
        driver.get(url);
        HomePage homePage = new HomePage(driver);
        homePage.searchText();
        Thread.sleep(5000);
        Assert.assertEquals("BrowserStack - Google Search", driver.getTitle());
        driver.quit();
    }

}
