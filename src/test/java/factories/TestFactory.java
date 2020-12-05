package factories;

import org.json.JSONObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import todoApp.Principal;

public class TestFactory {

    @Factory
    @Parameters({"browsers"})
    public Object[] factoryMethod(@Optional("none") String browsers) {

        System.out.println("TESTFACTORY browsers: " + browsers);

        if (browsers.equalsIgnoreCase("none")) {

            JSONObject device = new JSONObject();
            device.put("browser", "CHROME");
            return getClasses(device).toArray();

        } else {
            ArrayList<String> browsersList = new ArrayList<>(Arrays.asList(browsers.split(",")));
            ArrayList<Object> result = new ArrayList<>();

            for(String browser: browsersList) {
                JSONObject device = new JSONObject();
                device.put("browser", browser);
                result.addAll(getClasses(device));
            }
            return result.toArray();
        }
    }

    private ArrayList<Object>  getClasses(JSONObject device) {
        ArrayList<Object> result = new ArrayList<>();
        result.add(new Principal(device));
        return result;
    }

}
