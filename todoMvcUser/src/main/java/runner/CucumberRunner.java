package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features={"src/main/java/feature/TodoAllTaskCompletion.feature","src/main/java/feature/TodoPartialTaskCompletion.feature"},
                  glue="steps",
                  monochrome=true,
                  publish=true
                  )
public class CucumberRunner  extends AbstractTestNGCucumberTests{

}           