package com.browserstack.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AppDriver {
    protected static  final String USERNAME = "mohammedk1";
    protected static  final String ACCESSKEY = "spBCpUJaVTnvxxssFtEJ";
    protected static  final String SERVER = "hub-cloud.browserstack.com";


    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void setDriver(WebDriver Driver){
        driver.set(Driver);
    }


   public static void createDriver(WebDriver driver, DesiredCapabilities capabilities) throws MalformedURLException {
       driver = new RemoteWebDriver(
               new URL("http://" + USERNAME + ":" + ACCESSKEY + "@" + SERVER + "/wd/hub")
               , capabilities);

        AppDriver.setDriver(driver);
   }
}
