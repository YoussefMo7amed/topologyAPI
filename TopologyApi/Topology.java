package TopologyApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import TopologyApi.Device;

public class Topology {
    private JSONObject jsonObject;
    private String id;
    private LinkedList <Device> components;

    Topology(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    Topology(String id) {
        this.id = id;
    }

    Topology(String id, String components) {
        
    }

    // JSONObject toJSONObject(String key, String value) {
    // Map<String, String> object = new HashMap<String, String>();
    // object.put(key, value);
    // return new JSONObject(object);
    // }
}
