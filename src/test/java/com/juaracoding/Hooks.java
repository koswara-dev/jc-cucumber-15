package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks {

    static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
    }

    // if step == error/bug then screenshot
    @AfterStep
    public void getResultStatus(Scenario scenario){
        if(scenario.isFailed()){
            System.out.println("Action Screenshot");
        }
    }

    @AfterAll
    public static void finish(){
        DriverSingleton.delay(3);
        DriverSingleton.closeObjectInstance();
    }

}
