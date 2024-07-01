package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.HomePage;
import com.juaracoding.pages.LoginPage;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginTest {

    static private WebDriver driver;

    static private LoginPage loginPage;

    static private HomePage homePage;

    @BeforeAll
    public static void setUp(){
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @AfterAll
    public static void finish(){
        DriverSingleton.delay(3);
        DriverSingleton.closeObjectInstance();
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page(){
        driver.get("https://www.saucedemo.com");
    }

    @When("I enter a valid username and password")
    public void i_enter_a_valid_username_and_password(){
        loginPage.login("standard_user", "secret_sauce");
    }

    @And("I click the login button")
    public void i_click_the_login_button(){
        loginPage.clickLoginBtn();
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page(){
        Assert.assertEquals(homePage.getTxtAppLogo(), "Swag Labs");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @When("I enter an invalid username or password")
    public void i_enter_an_invalid_username_or_password(){
        loginPage.login("invalid","invalid");
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message(){
        Assert.assertTrue(loginPage.getTxtError().contains("Username and password do not match"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getAttributeLoginLogo(), "login_logo");
    }


}
