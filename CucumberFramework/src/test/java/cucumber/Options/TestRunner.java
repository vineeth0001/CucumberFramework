package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/io/features/website.feature", plugin = "json:target/jsonReports/cucumber-report.json", glue = {
		"io.website" })
// ,tags ="@AddProduct"

public class TestRunner {

}
