package TopologyApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

public class Component {
    private JSONObject component = null;
    private String id = null;
    private String type = null;
    private JSONObject netList = null;
    private Map<String, String> otherObjects = null;

    public Component() {

    }

    public Component(JSONObject component) {
        this.component = component;
        extractID(component);
        extractType(component);
        extractNetList(component);
        extractOtherObjects(component);
    }

    public Component(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public Component(String type, String id, JSONObject netList) {
        this.type = type;
        this.id = id;
        this.netList = netList;
    }

    private void extractID(JSONObject component) {
        this.id = (String) component.get("id");
    }

    private void extractType(JSONObject component) {
        this.type = (String) component.get("type");
    }

    private void extractNetList(JSONObject component) {
        this.netList = (JSONObject) component.get("netlist");
    }

    private void extractOtherObjects(JSONObject component) {
        Set<String> keys = (Set<String>) component.keySet();
        otherObjects = new HashMap<>();
        for (String key : keys) {
            if (key.equals("id") || key.equals("type") || key.equals("netlist")) {
                continue;
            }
            otherObjects.put(key, component.get(key).toString());
        }
    }

    private void createJSONObject(String type, String Id) {
        component = new JSONObject();
        component.put("id", id);
        component.put("type", type);
    }

    private void createJSONObject(String type, String Id, JSONObject netList) {
        createJSONObject(type, id);
        component.put("netlist", netList.toJSONString());
    }

    /**
     * Return the component ID
     * 
     * @return String
     */
    public String getId() {
        return this.id;
    }

    /**
     * Return the component type
     * 
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * Return the component netlist
     * 
     * @return {@link JSONObject}
     */
    public JSONObject getNetlist() {
        return this.netList;
    }

    /**
     * Return other objects (not the ID, type, netlist)
     * 
     * @return {@link Map <String,String>}
     */
    public Map<String, String> getOtherObjects() {
        return this.otherObjects;
    }

    /**
     * Return component as JSON object
     * 
     * @return {@link JSONObject }
     */
    public JSONObject getComponentObject() {
        if (component == null) {
            if (netList == null) {
                createJSONObject(this.type, this.id);
            } else {
                createJSONObject(this.type, this.id, this.netList);
            }
        }
        return this.component;
    }

    /**
     * create new object inside the component
     */
    public void createSubJSONObject(String key, String value) {
        otherObjects.put(key, value);
    }

    /**
     * Return The Component JSON Object as String
     * 
     * @return {@link String}
     */
    @Override
    public String toString() {
        return component.toJSONString();
    }
}
