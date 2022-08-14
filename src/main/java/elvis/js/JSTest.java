package elvis.js;

import org.json.JSONObject;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class JSTest {

    public static void main(String[] args) throws ScriptException {
        String js = "function dataTransform(data){\r\n" +
                "//console.log('123')\r\n" +
                "var _data = data;\r\n" +
                "return _data;\r\n" +
                "}";
        ScriptEngine engine;
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("JavaScript");
        engine.eval(js);
        List<JSONObject> data = new ArrayList<>();
        data.add(new JSONObject("{\"name\":\"hello world\"}"));
        try {
            System.out.println(((Invocable) engine).invokeFunction("dataTransform", data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
