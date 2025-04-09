package tqs.hw1;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("tqs/hw1")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "tqs.hw1") 
public class CucumberTest {

}
