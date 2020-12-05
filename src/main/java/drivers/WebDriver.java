package drivers;

import org.json.JSONObject;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class WebDriver extends WebDriverBase {

    public WebDriver(JSONObject deviceConfiguration) {
        super(deviceConfiguration);
    }

    public WebDriver(){}

    @Deprecated
    protected DesiredCapabilities createChromeDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setBrowserName("chrome");
        return desiredCapabilities;
    }

    protected ChromeOptions createChromeOptionsCapabilities() {
        ChromeOptions cap = new ChromeOptions();
        cap.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        return cap;
    }

    @Deprecated
    protected DesiredCapabilities createFirefoxDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setBrowserName("firefox");
        return desiredCapabilities;
    }

    protected FirefoxOptions createFirefoxOptionsCapabilities() {
        FirefoxOptions cap = new FirefoxOptions();
        cap.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        return cap;
    }

    public void tearDown(){
        if(driver != null){
            System.out.println("closing the driver...");
            driver.quit();
            System.out.println("driver closed");
            driver= null;
        }
    }

    @AfterClass
    public void close(){
        tearDown();
    }
}
