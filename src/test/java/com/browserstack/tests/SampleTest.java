package com.browserstack.tests;

import com.browserstack.pages.HomePage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.FileReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class SampleTest {
    public static  final String USERNAME = "mohammedk1";
    public static  final String ACCESSKEY = "spBCpUJaVTnvxxssFtEJ";
    public static  final String SERVER = "hub-cloud.browserstack.com";
    public WebDriver driver;

    JSONParser parser = new JSONParser();
    JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf.json"));
    JSONObject envs = (JSONObject) config.get("environments");

    public SampleTest() throws Exception{
    }

    @DataProvider(name = "CAPS")
    public  Object[][] getDataFromDataprovider(){
        return new Object[][]{
                {envs.get("env1")},
                {envs.get("env2")},
                {envs.get("env3")},
                {envs.get("env4")}
        };
    }

    @Test(dataProvider = "CAPS")
    public  void testCase1( JSONObject env) throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        setDeviceCapabilities(env,capabilities);
        driver = new RemoteWebDriver(
                new URL("http://" + USERNAME + ":" + ACCESSKEY + "@" + SERVER + "/wd/hub"), capabilities);
        driver.get((String) config.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.searchText();
        Thread.sleep(5000);
        Assert.assertEquals("BrowserStack - Google Search", driver.getTitle());
        tearDown(driver);
    }

    private DesiredCapabilities setDeviceCapabilities(Map<String, String> envCapabilities, DesiredCapabilities capabilities) {
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }
        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (capabilities.getCapability(pair.getKey().toString()) == null) {
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }
        return capabilities;
    }

    private void tearDown(WebDriver driver){
        driver.quit();
    }
}
