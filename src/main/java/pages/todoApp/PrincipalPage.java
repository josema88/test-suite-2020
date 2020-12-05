package pages.todoApp;

import methods.CommonActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PrincipalPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private CommonActions commonActions;

    public PrincipalPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        commonActions = new CommonActions(wait,driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.CLASS_NAME,using = "new-todo")
    private WebElement newTodoInput;

    public void fillNewTodoInput(String todoText) {
        commonActions.fillInput(newTodoInput, todoText);
        newTodoInput.sendKeys(Keys.ENTER);
    }

    public void checkIfTodoExist(String todoText) {
        String xpath = "*//ul[@class=\"todo-list\"]//label[text()=\"" + todoText + "\"]";
        commonActions.verifyExistanceOfElementWitXpath(xpath);
    }

}
