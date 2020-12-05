package methods;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class CommonActions {

    private WebDriverWait wait;
    private WebDriver driver;

    public CommonActions(WebDriverWait wait, WebDriver driver) {
        this.wait = wait;
        this.driver = driver;
    }

    public void fillInput(WebElement element, String value) {
        WebElement elementToFill = Waits.waitVisibilityOfElement(wait, element);
        if (elementToFill == null)
            Assert.fail("The input that you are searching is not visible");
        elementToFill.sendKeys(value);
        checkInputText(element, value);
    }

    public boolean checkVisibilityOfELement(WebElement elementToVerify) {
        WebElement elementVisible = Waits.waitVisibilityOfElement(wait,elementToVerify);
        return elementVisible != null;
    }

    private void checkInputText(WebElement element, String value) {
        WebElement elementToCheck = Waits.waitVisibilityOfElement(wait, element);
        if (elementToCheck == null)
            Assert.fail("The input that you are searching is not visible");
        String currentText = elementToCheck.getAttribute("value");
        if (!currentText.equals(value))
            Assert.fail("The element doesn't contains the same text. Current " + currentText + " expected: " + value);
    }

    public void clickElement(WebElement element) {
        Waits.waitPresenceOfElement(wait, By.xpath("//div[@class='click-block click-block-enabled'][not(@class='click-block click-block-enabled click-block-active')]"));

        WebElement elementToClick = Waits.waitForElementoToBeClickable(wait, element);
        if (elementToClick == null)
            Assert.fail("The element you are trying to click is not clickable");
        elementToClick.click();
    }

    public void verifyExistanceOfElementWitXpath(String xpath) {
        WebElement elementToVerify = Waits.waitPresenceOfElement(wait, By.xpath(xpath));
        if (elementToVerify == null)
            Assert.fail("The element doesn't exists");
    }

    public void verifyExistanceOfElementWitID(String identifier) {
        WebElement elementToVerify = Waits.waitPresenceOfElement(wait, By.id(identifier));
        if (elementToVerify == null)
            Assert.fail("The element doesn't exists");
    }

    public void step (String message, boolean status ) {
        String style = (status)? "style='font-size: 15px;color: #27ae60;'" : "style='font-size: 15px;color: #e74c3c;'";
        String icon = (status)? "&#10004;" : "&#10006;";
        Reporter.log("<li " + style + ">" + message + " " + icon + "</li>");
    }

    public void step (String message) {
        Reporter.log("<li>" + message + "</li>");
    }

    public void stepImage(String img) {
        this.step("<br /><img class='img-fluid' src='data:image/png;base64," + img + "'/>");
    }

    public String screenShotAll() {
        try {
            File scrFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
            byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(scrFile));
            String img = new String(encoded, StandardCharsets.US_ASCII);
            return img;
        } catch(Exception e) {
            System.out.println(e.toString() + e);
            return null;
        }
    }
}
