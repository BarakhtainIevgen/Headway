package steps;

import framework.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static framework.TestBase.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class Hooks {

    @Before
    public void beforeScenario() throws URISyntaxException, IOException, InterruptedException {
        System.out.println("This will run before the Scenario");
        setUpAppium();
    }

    @After
    public void afterScenario() throws InterruptedException {
        System.out.println("This will run after the Scenario");
        tearDownAppium();
    }
}