package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.CartPage;
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

public class CartTest {
    static private WebDriver driver;
    static private LoginPage loginPage;
    static private HomePage homePage;
    static private CartPage cartPage;

    @BeforeAll
    public static void setUp(){
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage();
        homePage = new HomePage();
        cartPage = new CartPage();
    }

    @AfterAll
    public static void finish(){
        DriverSingleton.delay(3);
        DriverSingleton.closeObjectInstance();
    }

    @Given("I am logged in to the application")
    public void i_am_logged_in_to_the_application(){
        loginPage.clearUsernamePassword();
        loginPage.login("standard_user", "secret_sauce");
        loginPage.clickLoginBtn();
    }

    @When("I add a product to the cart")
    public void i_add_a_product_to_the_cart(){
        homePage.setAddBtnToCart();
    }

    @Then("I should see total product on icon cart")
    public void i_should_see_total_product_on_icon_cart(){
        Assert.assertEquals(homePage.getTxtCartBadge(), "1");
    }

    @And("button Add to Cart changed to Remove")
    public void button_add_to_cart_changed_to_remove(){
        Assert.assertEquals(homePage.getTxtRemoveFromCart(), "Remove");
    }

    @And("I am on the cart page")
    public void i_am_on_the_cart_page(){
        homePage.setCartBtn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

    @And("the product should be in the cart")
    public void the_product_should_be_in_the_cart(){
        Assert.assertEquals(cartPage.getTxtItemName(), "Sauce Labs Backpack");
    }
}
