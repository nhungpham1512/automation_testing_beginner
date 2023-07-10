package com.nhungautomationproject.sky.features.login;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.*;
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
public class AuthenticationStory {

    public static final Target LOGOUT_BUTTON = Target.the("Logout button")
            .locatedBy("//a[text()='Log out']");
    public static final Target WRONG_EMAIL_ERROR = Target.the("Wrong email error")
            .locatedBy("//span[@id='Email-error']");
    public static final Target VALIDATION_EMAIL_ERROR = Target.the("Validation email error")
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
    public void should_authen_when_using_validate_user_and_validate_password() {

        nhung.wasAbleTo(
                LoginAs.normalUser("rong@gmail.com","12345@"),
                Ensure.that(LOGOUT_BUTTON).attribute("href").contains("/logout"),
                Ensure.that(LOGOUT_BUTTON).attribute("class").isEqualTo("ico-logout"),
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("https://demo.nopcommerce.com/"),
                Ensure.thatTheCurrentPage().title().isEqualTo("nopCommerce demo store")
        );
    }
    @WithTag("testcase:02")
    @Test
    public void verify_login_with_invalid_email() {
        nhung.wasAbleTo(
                LoginAs.normalUser("rong","12345@"),
                Ensure.that(WRONG_EMAIL_ERROR).text().isEqualTo("Wrong email"),
                Ensure.that(WRONG_EMAIL_ERROR).hasTextContent("Wrong email")
        );

    }
    @WithTag("testcase:03")
    @Test
    public void verify_login_with_wrong_email(){
        nhung.wasAbleTo(
                LoginAs.normalUser("rongcon@gmail.com","12345@"),
                Ensure.that(VALIDATION_EMAIL_ERROR).text().contains("Login was unsuccessful. Please correct the errors and try again.")
                //Verify by text
                //Ensure.that(VALIDATION_EMAIL_ERROR).hasTextContent("Login was unsuccessful. Please correct the errors and try again."+"No customer account found")
        );
    }
    @WithTag("testcase:04")
    @Test
    public void verify_login_with_wrong_password(){
        nhung.wasAbleTo(
                LoginAs.normalUser("rong@gmail.com","123"),
                Ensure.that(VALIDATION_EMAIL_ERROR).text().contains("The credentials provided are incorrect")
        );
    }
    @WithTag("testcase:05")
    @Test
    public void verify_login_when_user_input_nothing(){
        nhung.wasAbleTo(
                Open.url(LoginAs.BASE_URL),
                Click.on(LoginAs.BUTTON_TEXT_LOG_IN),
                Ensure.that(WRONG_EMAIL_ERROR).text().isEqualTo("Please enter your email")
        );
    }
}
