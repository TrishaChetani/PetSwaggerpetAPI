package com.api.testrunner;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = {"src/test/resources/features/PetAPI.feature"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        glue = {"com.api.steps.definition"})
public class TestRunner {

    // private static final Logger LOGGER = LoggerFactory.getLogger(TestRunner.class);

}
