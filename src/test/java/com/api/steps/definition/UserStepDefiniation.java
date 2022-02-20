package com.api.steps.definition;

import com.api.config.DefaultConfig;
import com.api.steps.CommonSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import io.cucumber.datatable.DataTable;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserStepDefiniation {
    @Steps
    private CommonSteps commonSteps;

    private DefaultConfig defaultConfig = new DefaultConfig();



    @When("I request service to create user detail")
    public void create(DataTable prefs) {
        commonSteps.createUser(prefs.asMap(String.class, String.class));
        Response result = Serenity.sessionVariableCalled("response");
        String res = result.asString();
        result.then().assertThat().body("$", hasKey("code"));
        result.then().assertThat().body("$", hasKey("type"));
        result.then().assertThat().body("$", hasKey("message"));
    }

    @When("I request service to remove user detail by username")
    public void remove(DataTable prefs) {
        commonSteps.removeUser(prefs.asMap(String.class, String.class));
        Response result = Serenity.sessionVariableCalled("response");
        String res = result.asString();
    }


    @Then("the service returns the status code {int}")
    public void responseCode(Integer code) {
        commonSteps.verifyResponseCode(code);
    }

}
