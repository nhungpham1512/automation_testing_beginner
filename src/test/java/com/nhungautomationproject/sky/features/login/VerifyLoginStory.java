package com.nhungautomationproject.sky.features.login;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.hasItem;

@RunWith(SerenityRunner.class)
public class VerifyLoginStory {

    public static final net.serenitybdd.screenplay.targets.Target INPUT_ID_EMAIL = net.serenitybdd.screenplay.targets.Target.the("email textbox")
            .locatedBy("//input[@id='Email']");
    public static final Target INPUT_ID_PASSWORD = Target.the("password textbox")
            .locatedBy("//input[@id='Password']");
    public static final Target BUTTON_TEXT_LOG_IN = Target.the("login button")
            .locatedBy("//button[text()='Log in']");
    public static final Target LOGOUT_BUTTON = Target.the("Logout button")
            .locatedBy("//a[text()='Log out']");
    public static final Target WRONG_EMAIL_ERROR = Target.the("Wrong email error")
            .locatedBy("//span[@id='Email-error']");
    public static final Target VALIDATION_EMAIL_ERROR = Target.the("Validation email eror")
            .locatedBy("//div[@class='message-error validation-summary-errors']");
    Actor nhung = Actor.named("Nhung");
    @Managed(uniqueSession = true)
    public WebDriver herBrowser;

    //@Steps
    //OpenTheApplication openTheApplication;

    @Before
    public void annaCanBrowseTheWeb() {
        nhung.can(BrowseTheWeb.with(herBrowser));
    }

    @WithTag("testcase:01")
    @Test
    public void verify_login_successfully() {

        nhung.wasAbleTo(
                Open.url("https://demo.nopcommerce.com/login?returnUrl=%2F"),
                //Enter.theValue("rong1@gmail.com").into(INPUT_ID_EMAIL),
                //Clear.field(INPUT_ID_EMAIL),
                Enter.theValue("rong@gmail.com").into(INPUT_ID_EMAIL),
                Enter.theValue("12345@").into(INPUT_ID_PASSWORD),
                Click.on("//input[@id='RememberMe']"),
                Click.on(BUTTON_TEXT_LOG_IN),
                //Verify by attribute contains
                Ensure.that(LOGOUT_BUTTON).attribute("href").contains("/logout"),
                Ensure.that(LOGOUT_BUTTON).attribute("class").isEqualTo("ico-logout"),
                //Verify by current url
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("https://demo.nopcommerce.com/"),
                //Verify by tittle
                Ensure.thatTheCurrentPage().title().isEqualTo("nopCommerce demo store")
        );
    }
    @WithTag("testcase:02")
    @Test
    public void verify_login_with_invalid_email() {
        nhung.wasAbleTo(
                Open.url("https://demo.nopcommerce.com/login?returnUrl=%2F"),
                Enter.theValue("rong").into(INPUT_ID_EMAIL),
                Enter.theValue("12345@").into(INPUT_ID_PASSWORD),
                Click.on(BUTTON_TEXT_LOG_IN),
                //Verify by text
                Ensure.that(WRONG_EMAIL_ERROR).text().isEqualTo("Wrong email"),
                 //Verify by has text
                Ensure.that(WRONG_EMAIL_ERROR).hasTextContent("Wrong email")
        );
    }
    @WithTag("testcase:03")
    @Test
    public void verify_login_with_wrong_email(){
        nhung.wasAbleTo(
                Open.url("https://demo.nopcommerce.com/login?returnUrl=%2F"),
                Enter.theValue("rongcon@gmail.com").into(INPUT_ID_EMAIL),
                Enter.theValue("12345@").into(INPUT_ID_PASSWORD),
                Click.on(BUTTON_TEXT_LOG_IN),
                //Verify by contains
                Ensure.that(VALIDATION_EMAIL_ERROR).text().contains("Login was unsuccessful. Please correct the errors and try again.")
                //Verify by text
                //Ensure.that(VALIDATION_EMAIL_ERROR).hasTextContent("Login was unsuccessful. Please correct the errors and try again."+"No customer account found")
        );
    }
    @WithTag("testcase:04")
    @Test
    public void verify_login_with_wrong_password(){
        nhung.wasAbleTo(
                Open.url("https://demo.nopcommerce.com/login?returnUrl=%2F"),
                Enter.theValue("rong@gmail.com").into(INPUT_ID_EMAIL),
                Enter.theValue("123").into(INPUT_ID_PASSWORD),
                Click.on(BUTTON_TEXT_LOG_IN),
                //Verify by contains
                Ensure.that(VALIDATION_EMAIL_ERROR).text().contains("The credentials provided are incorrect")
        );
    }
    @WithTag("testcase:05")
    @Test
    public void verify_login_when_user_input_nothing(){
        nhung.wasAbleTo(
                Open.url("https://demo.nopcommerce.com/login?returnUrl=%2F"),
                Click.on(BUTTON_TEXT_LOG_IN),
                Ensure.that(WRONG_EMAIL_ERROR).text().isEqualTo("Please enter your email")
        );
    }
}
