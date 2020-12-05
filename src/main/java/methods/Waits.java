package methods;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Waits {

    public static WebElement waitVisibilityOfElement(WebDriverWait wait, WebElement element) {
        try {
            element = wait.until(ExpectedConditions.visibilityOf(element));
            return element;
        } catch (Exception e) {
            System.out.println("The element is not visible. " + element.toString());
            return null;
        }
    }

    public static void waitForPageLoad(WebDriverWait wait, WebDriver driver) {
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver wdriver) {
                    return ((JavascriptExecutor)driver).executeScript("return document.readyState", new Object[0]).equals("complete");
                }
            });
        } catch (Exception var2) {
            Assert.fail("problem in the method waitForPageLoad");
        }
    }

    public static boolean waitVisibilityOfTwoElements(WebDriverWait wait, WebElement element1, WebElement element2) {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(element1),
                    ExpectedConditions.visibilityOf(element2)
            ));
        } catch (Exception e) {
            System.out.println("The elements are not visible. " + element1.toString() + "     " + element2.toString());
            return false;
        }
    }

    public static List<WebElement> waitVisibilityOfElements(WebDriverWait wait, List<WebElement> elements) {
        try {
            elements = wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            return elements;
        } catch (Exception e) {
            System.out.println("The elements are not visible. " + elements.toString());
            return null;
        }
    }

    public static WebElement waitPresenceOfElement(WebDriverWait wait, By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("The element is not presence using the locator: " + locator.toString());
            return null;
        }
    }

    public static List<WebElement> waitPresenseOfAllElements(WebDriverWait wait, By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            System.out.println("The elements are not presence using the locator: " + locator.toString());
            return new ArrayList<>();
        }
    }

    public static List<WebElement> waitMoreThanANumberOfElements(WebDriverWait wait, By locator, int numberOfElements) {
        try {
            return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberOfElements));
        } catch (Exception e) {
            System.out.println("The elements are not more than: " + numberOfElements + " using the locator: " + locator.toString());
            return new ArrayList<>();
        }
    }

    public static boolean waitUntilNumberOfElementsIs(WebDriverWait wait, By locator, int numberOfElements) {
        try {
            return (wait.until(ExpectedConditions.numberOfElementsToBe(locator, numberOfElements)).size() == numberOfElements);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean waitUntilNumberOfElementsIsMoreThan(WebDriverWait wait, By locator, int numberOfElements) {
        try {
            return (wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberOfElements)).size() > numberOfElements);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean waitUntilElementsAreInvisible(WebDriverWait wait, List<WebElement> elements) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
        } catch (Exception e) {
            System.out.println("The elements are visibile. " + elements.toString());
            return false;
        }
    }

    public static Boolean waitUntilPopUpWindowsIsOpen(WebDriverWait wait, WebDriver driver, int expectedNumberOfWindows) {
        try {
            return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
        } catch (Exception e) {
            System.out.println("The number of Windows expected isn't " + expectedNumberOfWindows + ", WIndows open is: " + driver.getWindowHandles().size());
            return false;
        }
    }

    public static Boolean waitUntilUrlContains(WebDriverWait wait, String text) {
        try {
            return wait.until(ExpectedConditions.urlContains(text));
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean waitUntilUrlDoesNotContains(WebDriverWait wait, String text) {
        try {
            return wait.until(ExpectedConditions.not(ExpectedConditions.urlContains(text)));
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean waitUntilTextIsEqual(WebDriverWait wait, String expectedText, WebElement element) {
        try {
            return wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    try {
                        String elementText = element.getText().replaceAll(",", "").toLowerCase();
                        return Boolean.valueOf(elementText.contains(expectedText.toLowerCase()));
                    } catch (StaleElementReferenceException var3) {
                        return null;
                    }
                }

                public String toString() {
                    return String.format("text ('%s') to be present in element %s", new Object[]{expectedText, element});
                }
            });
        } catch (Exception e) {
            System.out.println("The text is not equal");
            return false;
        }
    }

    public static WebElement waitForElementoToBeClickable(WebDriverWait wait, WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean waitUntilElementTextChange(WebDriverWait wait, By locator, String currentText) {
        try {
            return wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, currentText)));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean waitUntilElementsTextChange(WebDriverWait wait, By locator1, By locator2, By locator3, String text1, String text2, String text3) {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.not(ExpectedConditions.textToBe(locator1, text1)),
                    ExpectedConditions.not(ExpectedConditions.textToBe(locator2, text2)),
                    ExpectedConditions.not(ExpectedConditions.textToBe(locator3, text3))));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean waitUntilElementIsNotFocuses(WebDriverWait wait, String id) {
        try {
            return wait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.activeElement.getAttribute('id') !== '" + id + "'").equals(true));
        } catch (Exception e) {
            System.out.println("Error the element is not focused. Element Id: " + id);
            e.printStackTrace();
            return false;
        }
    }
}