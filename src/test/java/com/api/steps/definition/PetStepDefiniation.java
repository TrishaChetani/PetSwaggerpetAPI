package com.api.steps.definition;

import com.api.config.DefaultConfg;
import com.api.steps.CommonSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.*;

public class PetStepDefiniation {
    @Steps
    private CommonSteps commonSteps;

    private DefaultConfg defaultConfig = new DefaultConfg();



    @When("I request service to create to upload image")
    public void create(DataTable prefs) {
        commonSteps.uploadImage(prefs.asMap(String.class, String.class));
        Response result = Serenity.sessionVariableCalled("response");
        result.then().assertThat().body("$", hasKey("code"));
        result.then().assertThat().body("$", hasKey("type"));
        result.then().assertThat().body("$", hasKey("message"));
    }


    @When("I request service to update the pet information")
    public void updateInformation(DataTable prefs){
        commonSteps.updateInformation(prefs.asMap(String.class, String.class));
        Response result = Serenity.sessionVariableCalled("response");
        result.then().assertThat().body("$", hasKey("id"));
        result.then().assertThat().body("$", hasKey("photoUrls"));
        result.then().assertThat().body("$", hasKey("tags"));
    }

    @When("I request service to fetch the pet information")
    public void fetchInformation(DataTable prefs){
        commonSteps.fetchRequest(prefs.asMap(String.class, String.class));
        Response result = Serenity.sessionVariableCalled("response");

    }

    @When("I request service to delete the pet information")
    public void deleteInformation(DataTable prefs){
        commonSteps.removeInformation(prefs.asMap(String.class, String.class));
        Response result = Serenity.sessionVariableCalled("response");
        Integer StatusCode = result.statusCode();
        if(StatusCode == 200){
            result.then().assertThat().body("$", hasKey("code"));
            result.then().assertThat().body("$", hasKey("type"));
            result.then().assertThat().body("$", hasKey("message"));
        }
    }

    @Then("the service returns the successful status code")
    public void responseCode() {
        commonSteps.verifyResponseCode();
    }

    @Then("the service returns the status {int}")
    public void response(int code){
        commonSteps.response(code);
    }
}
