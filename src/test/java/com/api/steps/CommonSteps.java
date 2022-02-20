package com.api.steps;

import com.api.config.DefaultConfig;
import com.api.config.SessionVariables;
import com.api.support.ServicesSupport;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * CommonSteps for StepDefinition
 */
public class CommonSteps {

    public RequestSpecification spec;
    public ServicesSupport servicesSupport = new ServicesSupport();
    public DefaultConfig defaultConfig = new DefaultConfig();


    /**
     * POST Request with json body
     *
     * @param bodyPrefs
     */
    @Step
    public void createUser(Map<String, String> bodyPrefs) {
        new HashMap<>(bodyPrefs).entrySet().forEach(e -> {
            if (e.getValue() == null)
                e.setValue("");
            spec = rest().baseUri(defaultConfig.baseURI).headers("*", "*").body(new JSONObject(bodyPrefs)).contentType(ContentType.JSON).when().log().all();
            Response response = servicesSupport.executeRequest(spec, "POST", defaultConfig.createEndPoint);
            Serenity.setSessionVariable(SessionVariables.RESPONSE_SESSION_VARIABLE).to(response);
        });


    }




    /*
     * DELETE request
     * @param bodyPrefs
     */
    @Step
    public void removeUser(Map<String, String> bodyPrefs) {
        Map<String, String> params = new HashMap<>(bodyPrefs);
        params.entrySet().forEach(e -> {
            if (e.getValue() == null)
                e.setValue("");
            spec = rest().baseUri(defaultConfig.baseURI).headers("*", "*").contentType(ContentType.JSON).pathParam(e.getKey(), e.getValue()).when().log().all();
            Response response = servicesSupport.executeRequest(spec, "DELETE", defaultConfig.deleteRecordEndpoint);
            Serenity.setSessionVariable(SessionVariables.RESPONSE_SESSION_VARIABLE).to(response);
        });

    }

    /*
     * Verify status code
     */
    @Step
    public void verifyResponseCode(Integer code) {
        Response result = Serenity.sessionVariableCalled("response");
        assertThat("Status code matching", result.getStatusCode(), equalTo(code));
    }


}




