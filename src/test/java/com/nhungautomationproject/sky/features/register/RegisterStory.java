package com.nhungautomationproject.sky.features.register;

import com.nhungautomationproject.sky.features.login.LoginAs;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.SetCheckbox;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.SelectOptions;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Select;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.apache.commons.lang3.RandomStringUtils;

@RunWith(SerenityRunner.class)
public class RegisterStory {
    public static final Target  PAGE_TITLE_REGISTER = Target.the("register page title")
            .locatedBy("//div[@class='page-title']/h1");
    public static final Target INPUT_ID_GENDER_FEMALE = Target.the("female checkbox")
            .locatedBy("//input[@id='gender-female']");
    public static final Target FIRST_NAME = Target.the("register first name textbox")
            .locatedBy("//input[@id='FirstName']");
    public static final Target LAST_NAME = Target.the("register last name textbox")
            .locatedBy("//input[@id='LastName']");
    public static final Target EMAIL = Target.the("register email textbox")
            .locatedBy("//input[@id='Email']");
    public static final Target BIRTH_DAY = Target.the("birth day")
            .locatedBy("//select[@name='DateOfBirthDay']");
    public static final Target BIRTH_MONTH = Target.the("birth month")
            .locatedBy("//select[@name='DateOfBirthMonth']");
    public static final Target BIRTH_YEAR = Target.the("birth year")
            .locatedBy("//select[@name='DateOfBirthYear']");
    public static final Target COMPANY = Target.the("company")
            .locatedBy("//input[@id='Company']");
    public static final Target NEWSLETTER_CHECKBOX = Target.the("newsletter checkbox")
            .locatedBy("//input[@id='Newsletter']");
    public static final Target PASSWORD = Target.the("password")
            .locatedBy("//input[@id='Password']");
    public static final Target CONFIRM_PASSWORD = Target.the("confirm password")
            .locatedBy("//input[@id='ConfirmPassword']");
    public static final Target REGISTER_BUTTON = Target.the("register button")
            .locatedBy("//button[@id='register-button']");
    public static final Target REGISTER_RESULT = Target.the("register successful message")
            .locatedBy("//div[@class='result']");
    public static final Target FIRST_NAME_ERROR = Target.the("register error message")
            .locatedBy("//span[@id='FirstName-error']");
    public static final String NOPCOMMERCE_COM_URL = "https://demo.nopcommerce.com/";
    public static final Target REGISTER = Target.the("register herf")
            .locatedBy("//a[@class='ico-register']");

    public String emailAddress = "june"+ RandomStringUtils.randomNumeric(4) +"@gmail.com";
    Actor nhung = Actor.named("Nhung");
    @Managed(uniqueSession = true)
    public WebDriver herBrowser;
    //@Steps
    //OpenTheApplication openTheApplication;
    @Before
    public void annaCanBrowseTheWeb() {
        nhung.can(BrowseTheWeb.with(herBrowser));
    }

    @WithTag("testcase:Register01")
    @Test
    public void should_register_successfully_when_input_all_manatory_fields() {
        nhung.wasAbleTo(
                Open.url(NOPCOMMERCE_COM_URL),
                Click.on(REGISTER),
                Ensure.that(PAGE_TITLE_REGISTER).text().contains("Register"),
                SetCheckbox.of(INPUT_ID_GENDER_FEMALE).toTrue(),
                Enter.theValue("nhung").into(FIRST_NAME),
                Enter.theValue("pham").into(LAST_NAME),
                Enter.theValue(emailAddress).into(EMAIL),
                Select.option("15").from(BIRTH_DAY),
                Select.option("May").from(BIRTH_MONTH),
                Select.option("1991").from(BIRTH_YEAR),
                Enter.theValue("PHN company").into(COMPANY),
                SetCheckbox.of(NEWSLETTER_CHECKBOX).toTrue(),
                Enter.theValue("12345@").into(PASSWORD),
                Enter.theValue("12345@").into(CONFIRM_PASSWORD),
                Click.on(REGISTER_BUTTON),
                Ensure.that(REGISTER_RESULT).text().contains("Your registration completed")
        );
    }
    @WithTag("testcase:Register02")
    @Test
    public void should_error_message_when_input_nothing() {
        nhung.wasAbleTo(
                Open.url(NOPCOMMERCE_COM_URL),
                Click.on(REGISTER),
                Ensure.that(PAGE_TITLE_REGISTER).text().contains("Register"),
                Click.on(REGISTER_BUTTON),
                Ensure.that(FIRST_NAME_ERROR).text().contains("First name is required.")
        );
    }
}
