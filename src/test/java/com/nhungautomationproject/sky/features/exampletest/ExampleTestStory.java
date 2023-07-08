package com.nhungautomationproject.sky.features.example_test;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.*;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

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
        nhung.wasAbleTo(
                Open.url("https://demo.nopcommerce.com/login?returnUrl=%2F"),
                Click.on("//a[text()='Register']"),
                Click.on("//select [@name='DateOfBirthDay']"),
                Scroll.to("//select [@name='DateOfBirthDay']/option[text()='31']"),
                //search
                Enter.theValue("book").into("//input[@id='small-searchterms']"),
                Hit.the(Keys.ENTER).into("//input[@id='small-searchterms']")
        );
    }
}


