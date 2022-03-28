package com.api.steps;


import com.api.config.DefaultConfg;
import com.api.config.SessionVariabl;
import com.api.support.ServicesSupport;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * CommonSteps for StepDefinition
 */
public class CommonSteps {

    public RequestSpecification spec;
    public ServicesSupport servicesSupport = new ServicesSupport();
    public DefaultConfg defaultConfig = new DefaultConfg();


    /**
     * POST Request with json body
     *
     * @param bodyPrefs
     */
    @Step
    public void uploadPetImage(Map<String, String> bodyPrefs) {
        Map<String, String> params = new HashMap<>(bodyPrefs);
        params.entrySet().forEach(e -> {
            if (e.getValue() == null)
                e.setValue("");
            spec = rest().baseUri(defaultConfig.BASEURI).headers("content-type", "multipart/form-data").multiPart("file", new File(e.getValue())).pathParam("petId", (int) (Math.random() * 100)).when().log().all();
            Response response = servicesSupport.executeRequest(spec, "POST", defaultConfig.CREATEENDPOINT);
            Serenity.setSessionVariable(SessionVariabl.RESPONSE_SESSION_VARIABLE).to(response);
        });
    }

    /**
     * Update Request with JSON body
     *
     * @param bodyPrefs
     */

    @Step
    public void updatePetRequest(Map<String, String> bodyPrefs) {
        new HashMap<>(bodyPrefs).entrySet().forEach(e -> {
            if (e.getValue() == null)
                e.setValue("");
            spec = rest().baseUri(defaultConfig.BASEURI).headers("*", "*").body(new JSONObject(bodyPrefs)).contentType(ContentType.JSON).when().log().all();
            Response response = servicesSupport.executeRequest(spec, "PUT", defaultConfig.UPDATEENDPOINT);
            Serenity.setSessionVariable(SessionVariabl.RESPONSE_SESSION_VARIABLE).to(response);
        });
    }

    /*
     * DELETE request
     * @param bodyPrefs
     */
    @Step
    public void removePetRequest(Map<String, String> bodyPrefs) {
        Map<String, String> params = new HashMap<>(bodyPrefs);
        params.entrySet().forEach(e -> {
            if (e.getValue() == null)
                e.setValue("");
            spec = rest().baseUri(defaultConfig.BASEURI).headers("*", "*").contentType(ContentType.JSON).pathParam(e.getKey(), e.getValue()).when().log().all();
            Response response = servicesSupport.executeRequest(spec, "DELETE", defaultConfig.DELETEENDPOINT);
            Serenity.setSessionVariable(SessionVariabl.RESPONSE_SESSION_VARIABLE).to(response);
        });
    }

    /*
     * Get request
     * @param bodyPrefs
     */
    @Step
    public void fetchPetRequest(Map<String, String> bodyPrefs) {
        Map<String, String> params = new HashMap<>(bodyPrefs);
        params.entrySet().forEach(e -> {
            if (e.getValue() == null)
                e.setValue("");
            spec = rest().baseUri(defaultConfig.BASEURI).headers("*", "*").contentType(ContentType.JSON).queryParam(e.getKey(), e.getValue()).when().log().all();
            Response response = servicesSupport.executeRequest(spec, "GET", defaultConfig.FETCHENDPOINT);
            Serenity.setSessionVariable(SessionVariabl.RESPONSE_SESSION_VARIABLE).to(response);
        });

    }

    /*
     * Verify response code
     * @param code
     */
    @Step
    public void responseCode(int code) {
        Response result = Serenity.sessionVariableCalled("response");
        assertThat("Status code matching", result.getStatusCode(), equalTo(code));
    }
}




