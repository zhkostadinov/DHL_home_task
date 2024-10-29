import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import page_objects.Base;

import java.io.IOException;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html: cucumber-reports/execution-report.html"},
        features = {"src/resources/features/"},
        glue = "step_definition",
        tags = "not @ignore")
public class CucumberRunnerTest {

    private static final Base base = Base.getInstance();

    @BeforeClass
    public static void beforeAll() throws IOException {
        base.initProperties();
        base.initializeDriver();
    }

    @AfterClass
    public static void after() {
        base.closeDriver();
    }

}