package drivers;

import methods.GlobalVariables;
import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import util.TestDevice;

import java.net.URL;


public abstract class WebDriverBase {

    public TestDevice device;
    public org.openqa.selenium.WebDriver driver;
    public JSONObject suiteParams;
    public WebDriverBase(JSONObject deviceConfiguration){
        this.suiteParams = deviceConfiguration;
    }

    public WebDriverBase(){}

    public TestDevice getDevice() {
        String browser = this.suiteParams.get("browser").toString();

        if (browser != null && browser.equalsIgnoreCase("CHROME")) {

            device = new TestDevice("Chrome", "Mobile Simulation");
        } else if (browser != null && browser.equalsIgnoreCase("FIREFOX")) {

            device = new TestDevice("Firefox", "Mobile Simulation");
        } else if (browser != null && browser.equalsIgnoreCase("REMOTE")) {

            device = new TestDevice("Remote", "Mobile Simulation");
        } else {
            device = new TestDevice("not defined ", "custom");
        }
        return device;
    }

    public org.openqa.selenium.WebDriver getDriver(){
        if(driver == null) {
            try {
                URL remoteAddress = new URL(GlobalVariables.seleniumGridHubUrl);
                String browser = this.suiteParams.get("browser").toString();

                if (browser != null) {
                    switch(browser.toUpperCase()) {
                        case "CHROME":
                            System.out.println("es chrome");
                            driver = new RemoteWebDriver(remoteAddress, createChromeOptionsCapabilities());
                            break;
                        case "FIREFOX":
                            System.out.println("es firefox");
                            driver = new RemoteWebDriver(remoteAddress, createFirefoxOptionsCapabilities());
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }


    protected abstract ChromeOptions createChromeOptionsCapabilities();
    protected abstract FirefoxOptions createFirefoxOptionsCapabilities();

    public abstract void tearDown();
}
