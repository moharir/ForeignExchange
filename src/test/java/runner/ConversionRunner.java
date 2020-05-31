package runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber-report/cucumber.json", "html:target/cucumber-report/cucumber.html"},
        features = {"resources/Features"},
        glue = {"stepdefs"},
        monochrome = true)
public class ConversionRunner {

}