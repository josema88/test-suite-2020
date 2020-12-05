package util;

import com.hubspot.jinjava.Jinjava;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CreateTestNgFile {
    public static void main(String[] args) {
        Map<String, Object> context = new HashMap<>();
        String env =  args[3];

        context.put("includedGroups", args[0]);
        context.put("groupsToRun", args[0].split(","));
        context.put("groupsToExclude", args[1].split(","));
        context.put("testName", args[2]);
        context.put("filesToAttach", args[3]);
        context.put("projectName", args[4]);
        context.put("build", args[5]);
        context.put("browsers", args[6]);
        context.put("runOn", args[7]);


        for (String key : context.keySet()) {
            System.out.println(key + " --> " + context.get(key));
        }

        String testNgTemplatePath = System.getProperty("user.dir") + "/src/test/resources/template.xml";


        String testNgFile = renderTemplate(context, testNgTemplatePath);

        writeFile(testNgFile, "testng.xml"); //create testng file
    }

    private static String renderTemplate(Map<String, Object> context, String path) {
        Jinjava jinjava = new Jinjava();
        String renderedTemplate = "";
        try {
            File file = new File(path);
            String template = FileUtils.readFileToString(file, StandardCharsets.UTF_8.toString());
            renderedTemplate = jinjava.render(template, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return renderedTemplate;
    }

    private static void writeFile(String renderedTemplate, String fileName) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(renderedTemplate);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
