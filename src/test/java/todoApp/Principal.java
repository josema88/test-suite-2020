package todoApp;

import methods.GlobalVariables;
import methods.Waits;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.todoApp.PrincipalPage;

public class Principal extends drivers.AQADriver {
    private PrincipalPage principalPage;

    public Principal(JSONObject deviceConfiguration) {
        super(deviceConfiguration);
    }

    @BeforeClass(alwaysRun = true)
    public void initPageObjectClass(){
        principalPage = new PrincipalPage(driver,wait);
        System.out.println("Pages Initialized!");
    }

    @BeforeMethod(alwaysRun = true)
    public void goToPage(){
        driver.get(GlobalVariables.urlRoot);
        Waits.waitForPageLoad(wait, driver);
        System.out.println("Go to: " + GlobalVariables.urlRoot);
    }

    @Test(groups = {"TestAddTodo","all", "successTest"},testName = "Add Todo",
            description = "Check if is possible to add a TODO")
    public void TestAddTodo() {
        System.out.println("--- Add 'comer'");
        principalPage.fillNewTodoInput("comer");
        System.out.println("--- Verify 'comer' exists");
        principalPage.checkIfTodoExist("comer");
    }

    @Test(groups = {"TestAddTodo","all", "failedTest"},testName = "Add Todo",
            description = "Check if is possible to add a TODO")
    public void TestAddTodoFailed() {
        System.out.println("--- Add 'dormir'");
        principalPage.fillNewTodoInput("dormir");
        System.out.println("--- Verify 'dormir' exists");
        principalPage.checkIfTodoExist("dormir");
    }

}
