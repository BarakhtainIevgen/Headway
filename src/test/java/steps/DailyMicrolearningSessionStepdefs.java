package steps;

import enums.ButtonType;
import enums.OsType;
import framework.App;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.mn.Харин;
import org.testng.Assert;
import screens.StartScreen;

import java.util.List;

import static framework.App.driver;

public class DailyMicrolearningSessionStepdefs {
    @Given("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        App.StartScreen().waitUntilLoginPageApeared();
        App.StartScreen().clickOnLoginButton();
    }

    @When("I submit username and password")
    public void iSubmitUsernameAndPassword() {
        App.LoginScreen().loginUser("ybarakhtyan@gmail.com", "17091983");
    }

    @Then("I should be logged in")
    @Then("Home page should be displayed")
    @Then("Discover page is opened")
    public void iShouldBeLoggedIn() {
        Assert.assertTrue(App.HomeScreen().waitUntilLoginPageApeared(),
                "Expected that Discover page should be dispalyed");
    }

    @When("I select firts item in Daily microlearning session")
    public void iSelectFirtsItemInDailyMicrolearningSession() {
        App.HomeScreen().clickOnMicrolearningItem();
    }

    @Then("item is opened")
    public void itemIsOpened() {
        App.MicrolearningScreen().waitUntilLoginPageApeared();
    }

    @And("following button is present on screen")
    public void followingButtonIsPresentOnScreen(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);

        for (List<String> columns : rows) {
            switch (ButtonType.valueOf(columns.get(0).toUpperCase())) {
                case REMEMBER:
                    Assert.assertTrue(App.MicrolearningScreen().isVisibleBtnRemember(),
                            "Expected that button Remember is visibe");
                    break;

                case SHARE:
                    Assert.assertTrue(App.MicrolearningScreen().isVisibleBtnRemember(),
                            "Expected that button Remember is visibe");
                    break;

                default:
                    Assert.assertTrue(false, "Button " + columns.get(0) + " not expected");
            }
        }
    }

    @Then("microlearning items count is {int}")
    public void microlearningItemsCountIs(int expectedMicrolearningItemsCount) {
        Assert.assertTrue(App.HomeScreen().getMicrolearningItemsCount() == expectedMicrolearningItemsCount,
                "Expected that microlearning items count should be " + expectedMicrolearningItemsCount);
    }

    @And("content is displayed")
    public void contentIsDisplayed() {
        Assert.assertTrue(App.MicrolearningScreen().isVisiblePnlContent(),
                "Expected that microlearning item content is displayed");
    }

    @And("card view is visible")
    public void cardViewIsVisible() {
        Assert.assertTrue(App.MicrolearningScreen().isVisibleCardView(),
                "Expected that microlearning Card view is displayed");
    }

    @When("I click on close button")
    public void iClickOnCloseButton() {
        App.MicrolearningScreen().clickOnCloseButton();
    }
}
