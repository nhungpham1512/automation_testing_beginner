package com.nhungautomationproject.sky.features.login;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.targets.Target;

public class LoginAs {
    public static final Target INPUT_ID_EMAIL = Target.the("email field")
            .locatedBy("//input[@id='Email']");
    public static final Target INPUT_ID_PASSWORD = Target.the("password field")
            .locatedBy("//input[@id='Password']");
    public static final Target BUTTON_TEXT_LOG_IN = Target.the("login button")
            .locatedBy("//button[text()='Log in']");
    public static final String BASE_URL = "https://demo.nopcommerce.com/login?returnUrl=%2F";

    public static Performable normalUser(String mail, String password) {
        return Task.where("{0} login as normal user",actor -> actor.attemptsTo(
                Open.url(BASE_URL),
                Enter.theValue(mail).into(INPUT_ID_EMAIL),
                Enter.theValue(password).into(INPUT_ID_PASSWORD),
                Click.on("//input[@id='RememberMe']"),
                Click.on(BUTTON_TEXT_LOG_IN)
                )
        );
    }
}
