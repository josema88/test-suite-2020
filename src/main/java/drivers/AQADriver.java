package drivers;

import org.json.JSONObject;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class AQADriver extends WebDriver {

    public AQADriver(JSONObject deviceConfiguration) {
        super(deviceConfiguration);
    }

    protected WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void initDriver(){
        driver = getDriver();
        device = getDevice();
        wait = new WebDriverWait(driver,15,1000);
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver(){
        tearDown();
    }
}
