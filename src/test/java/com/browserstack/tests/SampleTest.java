package com.browserstack.tests;

import com.browserstack.pages.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.URL;

public class SampleTest extends BaseTest {
    private WebDriver driver;

    @Test(dataProvider = "capabilities")
    public  void testCase1(DesiredCapabilities capabilities, String url, Method m) throws Exception{
        AppDriver.createDriver(driver, capabilities);
        JavascriptExecutor jse = (JavascriptExecutor)AppDriver.getDriver();
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\""+m.getName()+"\" }}");
        AppDriver.getDriver().get(url);
        HomePage homePage = new HomePage(AppDriver.getDriver());
        homePage.searchText();
        Thread.sleep(5000);
        Assert.assertEquals("BrowserStack - Google Search", AppDriver.getDriver().getTitle());
        //driver.quit();
        AppDriver.getDriver().quit();
    }
    @Test(dataProvider = "capabilities")
    public  void testCase2( DesiredCapabilities capabilities, String url, Method m) throws Exception{
        AppDriver.createDriver(driver, capabilities);
        JavascriptExecutor jse = (JavascriptExecutor)AppDriver.getDriver();
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\""+m.getName()+"\" }}");
       AppDriver.getDriver().get(url);
        HomePage homePage = new HomePage(AppDriver.getDriver());
        homePage.searchText();
        Thread.sleep(5000);
        Assert.assertEquals("BrowserStack - Google Search", AppDriver.getDriver().getTitle());
        AppDriver.getDriver().quit();
    }
    @Test(dataProvider = "capabilities")
    public  void testCase3( DesiredCapabilities capabilities, String url, Method m) throws Exception{

        // create driverFactory class
        AppDriver.createDriver(driver, capabilities);
        JavascriptExecutor jse = (JavascriptExecutor)AppDriver.getDriver();
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\""+m.getName()+"\" }}");
        AppDriver.getDriver().get(url);
        HomePage homePage = new HomePage(AppDriver.getDriver());
        homePage.searchText();
        Thread.sleep(5000);
        Assert.assertEquals("BrowserStack - Google Search", AppDriver.getDriver().getTitle());
        AppDriver.getDriver().quit();
    }



}
