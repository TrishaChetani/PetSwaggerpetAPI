package com.api.steps;


import com.api.config.DefaultConfg;
import com.api.config.SessionVariabl;
import com.api.support.ServicesSupport;


import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import java.io.File;
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
    public DefaultConfg defaultConfig = new DefaultConfg();


    /**
     * POST Request with json body
     *
     * @param bodyPrefs
     */
    @Step
    public void uploadImage(Map<String, String> bodyPrefs) {
        Map<String, String> params = new HashMap<>(bodyPrefs);
        params.entrySet().forEach(e -> {
            if (e.getValue() == null)
                e.setValue("");
            spec = rest().baseUri(defaultConfig.baseURI).headers("content-type", "multipart/form-data").multiPart("file", new File(e.getValue())).pathParam("petId", (int) (Math.random() * 100)).when().log().all();
            Response response = servicesSupport.executeRequest(spec, "POST", defaultConfig.createEndPoint);
            Serenity.setSessionVariable(SessionVariabl.RESPONSE_SESSION_VARIABLE).to(response);
        });
    }

    /**
     * Update Request with JSON body
     * @param bodyPrefs
     */

    @Step
    public void updateInformation(Map<String, String> bodyPrefs) {
        new HashMap<>(bodyPrefs).entrySet().forEach(e -> {
            if (e.getValue() == null)
                e.setValue("");
            spec = rest().baseUri(defaultConfig.baseURI).headers("*", "*").body(new JSONObject(bodyPrefs)).contentType(ContentType.JSON).when().log().all();
            Response response = servicesSupport.executeRequest(spec, "PUT", defaultConfig.updatePet);
            Serenity.setSessionVariable(SessionVariabl.RESPONSE_SESSION_VARIABLE).to(response);
        });


    }




    /*
     * DELETE request
     * @param bodyPrefs
     */
    @Step
    public void removeInformation(Map<String, String> bodyPrefs) {
        Map<String, String> params = new HashMap<>(bodyPrefs);
        params.entrySet().forEach(e -> {
            if (e.getValue() == null)
                e.setValue("");
            spec = rest().baseUri(defaultConfig.baseURI).headers("*", "*").contentType(ContentType.JSON).pathParam(e.getKey(), e.getValue()).when().log().all();
            Response response = servicesSupport.executeRequest(spec, "DELETE", defaultConfig.deleteRecordEndpoint);
            Serenity.setSessionVariable(SessionVariabl.RESPONSE_SESSION_VARIABLE).to(response);
        });
    }

    /*
    *
     */
    @Step
    public void fetchRequest(Map<String, String> bodyPrefs){
        Map<String, String> params = new HashMap<>(bodyPrefs);
        params.entrySet().forEach(e -> {
            if (e.getValue() == null)
                e.setValue("");
            spec = rest().baseUri(defaultConfig.baseURI).headers("*", "*").contentType(ContentType.JSON).queryParam(e.getKey(), e.getValue()).when().log().all();
            Response response = servicesSupport.executeRequest(spec, "GET", defaultConfig.fetchPet);
            Serenity.setSessionVariable(SessionVariabl.RESPONSE_SESSION_VARIABLE).to(response);
        });

    }

    /*
     * Verify status code
     */
    @Step
    public void verifyResponseCode() {
        Response result = Serenity.sessionVariableCalled("response");
        assertThat("Status code matching", result.getStatusCode(), equalTo(200));
    }
    /*
    * Verify response
     */
    @Step
    public void response(int code){
        Response result = Serenity.sessionVariableCalled("response");
        assertThat("Status code matching", result.getStatusCode(), equalTo(code));
    }




}




