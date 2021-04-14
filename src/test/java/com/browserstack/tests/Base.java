package com.browserstack.tests;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public abstract class Base {
    protected static  final String USERNAME = "";
    protected static  final String ACCESSKEY = "";
    protected static  final String SERVER = "hub-cloud.browserstack.com";
    protected JSONParser parser = new JSONParser();
    protected JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf.json"));
    protected JSONObject envs = (JSONObject) config.get("environments");

     Base() throws Exception {
    }


    @DataProvider(name = "capabilities")
    protected   Object[][] getDataFromDataprovider(){
        return new Object[][]{
                {envs.get("env1")},
                {envs.get("env2")},
                {envs.get("env3")},
                {envs.get("env4")}
        };
    }


    protected DesiredCapabilities setDeviceCapabilities(Map<String, String> envCapabilities, DesiredCapabilities capabilities) {
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

}
