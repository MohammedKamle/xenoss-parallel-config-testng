package com.browserstack.tests;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class BaseTest {
    protected static  final String USERNAME = "mohammedk1";
    protected static  final String ACCESSKEY = "spBCpUJaVTnvxxssFtEJ";
    protected static  final String SERVER = "hub-cloud.browserstack.com";

    @DataProvider(name = "capabilities")
    protected   Object[][] getDataFromDataprovider() throws Exception{
         JSONParser parser = new JSONParser();
         JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf.json"));
         JSONObject envs = (JSONObject) config.get("environments");
        return new Object[][]{
                { setDeviceCapabilities((Map<String, String>) envs.get("chrome"),new DesiredCapabilities()), config.get("url")},
                { setDeviceCapabilities((Map<String, String>) envs.get("firefox"),new DesiredCapabilities()), config.get("url")},
                { setDeviceCapabilities((Map<String, String>) envs.get("safari"),new DesiredCapabilities()), config.get("url")},
                { setDeviceCapabilities((Map<String, String>) envs.get("Google Pixel 4"),new DesiredCapabilities()),config.get("url") },
        };
    }

    private DesiredCapabilities setDeviceCapabilities(Map<String, String> envCapabilities,
                                                        DesiredCapabilities capabilities) throws IOException, ParseException {
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }
        JSONObject config = (JSONObject) new JSONParser().parse(new FileReader("src/test/resources/conf.json"));
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

}
