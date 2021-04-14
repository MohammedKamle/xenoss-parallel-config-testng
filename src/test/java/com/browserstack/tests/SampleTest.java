package com.browserstack.tests;

import com.browserstack.pages.HomePage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class SampleTest extends Base {
    private WebDriver driver;

    SampleTest() throws Exception{
    }

    @Test(dataProvider = "capabilities")
    public  void testCase1( JSONObject env) throws Exception{

        driver = new RemoteWebDriver(
                new URL("http://" + USERNAME + ":" + ACCESSKEY + "@" + SERVER + "/wd/hub")
                , setDeviceCapabilities(env,new DesiredCapabilities()));
        driver.get((String) config.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.searchText();
        Thread.sleep(5000);
        Assert.assertEquals("BrowserStack - Google Search", driver.getTitle());

        driver.quit();
    }

}
