package bxfw;

import net.sf.json.JSONObject;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

public class Test {
public static void main(String[] args) {
    String s = "{loginErrorTime=0, password=121}";
    JSONObject json =JSONObject.fromObject(s);
    System.out.println(json.toString());
}
}
