package com.nhungautomationproject.sky.features.exampletest;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.SelectOptions;
import net.serenitybdd.screenplay.ui.Select;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.lang.annotation.Target;
import java.util.List;

@RunWith(SerenityRunner.class)
public class ExampleTestStory {
    Actor nhung = Actor.named("Nhung");
    @Managed(uniqueSession = true)
    public WebDriver herBrowser;

    //@Steps
    //OpenTheApplication openTheApplication;

    @Before
    public void annaCanBrowseTheWeb() {
        nhung.can(BrowseTheWeb.with(herBrowser));
    }

    @WithTag("testcase:example")
    @Test
    public void example_test(){
        int days =  SelectOptions.of(By.name("DateOfBirthDay"))
                        .answeredBy(nhung).size();
        nhung.wasAbleTo(
                //Wait
                Open.url("https://demo.nopcommerce.com/login?returnUrl=%2F"),
                Click.on("//a[text()='Register']").afterWaitingUntilEnabled(),
                //Hit
                Enter.theValue("book").into("//input[@id='small-searchterms']"),
                Hit.the(Keys.ENTER).into("//input[@id='small-searchterms']"),
                //Clear
                Open.url("https://demo.nopcommerce.com/login?returnUrl=%2F"),
                Click.on("//a[text()='Register']"),
                Enter.theValue("nhung").into("//input[@id='FirstName']"),
                Clear.field("//input[@id='FirstName']"),
                //Send Key
                SendKeys.of("pham").into("//input[@id='LastName']"),
                Hit.the(Keys.TAB).into("//input[@id='LastName']"),
                //Scroll
                Select.option("15").from("//select[@name='DateOfBirthDay']"),
                Select.optionNumber(5).from("//select[@name='DateOfBirthMonth']"),
                Select.option("1991").from("//select[@name='DateOfBirthYear']"),
                Ensure.that(days).isEqualTo(32),
                //Javascript clear
//                Open.url("https://demo.nopcommerce.com/login?returnUrl=%2F"),
//                Click.on("//a[text()='Register']"),
//                Enter.theValue("june").into("//input[@id='FirstName']"),
//                Evaluate.javascript("window.localStorage.clear();"),
//                Evaluate.javascript("window.localStorage.removeItem('//input[@id='FirstName']');"),
                //Checkbox
                Open.url("https://demo.nopcommerce.com/login?returnUrl=%2F"),
                Click.on("//a[text()='Register']"),
                Click.on("//input[@id='Newsletter']"),
                SetCheckbox.of("//input[@id='Newsletter']").toTrue(),
                SetCheckbox.of("//input[@id='Newsletter']").toFalse()
        );
    }
}


