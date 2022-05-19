import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Topology {
    private JSONObject id;
    private JSONArray components;

    Topology() {

    }

    Topology(String id) {
        this.id = toJSONObject("id", id);
    }

    Topology(String id, String components) {
        this.id = toJSONObject("id", id);
        
    }

    JSONObject toJSONObject(String key, String value) {
        Map<String, String> object = new HashMap<String, String>();
        object.put(key, value);
        return new JSONObject(object);
    }
}
