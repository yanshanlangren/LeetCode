package elvis.test;

import com.alibaba.fastjson.JSONObject;

public class JSONTest {

    public static void main(String[] args) {
        JSONObject o = new JSONObject();
        o.put("aasda", "asdsad");

        System.out.println(o.toJSONString());
        System.out.println(o);
    }
}
