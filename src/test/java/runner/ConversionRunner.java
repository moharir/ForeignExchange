package runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/results.html"},
        features = {"resources/Features"},
        glue = {"stepdefs"},
        monochrome = true)
public class ConversionRunner {

}