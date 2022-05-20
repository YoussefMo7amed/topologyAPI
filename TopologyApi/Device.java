package TopologyApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

public class Device {
    private JSONObject device = null;
    private String id = null;
    private String type = null;
    private JSONObject netList = null;
    private Map<String, String> otherObjects = null;

    public Device() {

    }

    public Device(JSONObject device) {
        this.device = device;
        extractID(device);
        extractType(device);
        extractNetList(device);
        extractOtherObjects(device);
    }

    public Device(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public Device(String type, String id, JSONObject netList) {
        this.type = type;
        this.id = id;
        this.netList = netList;
    }

    private void extractID(JSONObject device) {
        this.id = (String) device.get("id");
    }

    private void extractType(JSONObject device) {
        this.type = (String) device.get("type");
    }

    private void extractNetList(JSONObject device) {
        this.netList = (JSONObject) device.get("netlist");
    }

    private void extractOtherObjects(JSONObject device) {
        Set<String> objects = (Set<String>) device.keySet();
        otherObjects = new HashMap<>();
        for (String key : objects) {
            if (key.equals("id") || key.equals("type") || key.equals("netlist")) {
                continue;
            }
            otherObjects.put(key, device.get(key).toString());
        }
    }

    private void createJSONObject(String type, String Id) {
        device.put("id", id);
        device.put("type", type);
    }

    private void createJSONObject(String type, String Id, JSONObject netList) {
        device = new JSONObject();
        device.put("id", id);
        device.put("type", type);
        device.put("netlist", netList.toJSONString());
    }

    /**
     * Return the device ID
     * 
     * @return String
     */
    public String getId() {
        return this.id;
    }

    /**
     * Return the device type
     * 
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * Return the device netlist
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
     * Return Device as JSON object
     * 
     * @return {@link JSONObject }
     */
    public JSONObject getDeviceObject() {
        if (device == null) {
            if (netList == null) {
                createJSONObject(this.type, this.id);
            } else {
                createJSONObject(this.type, this.id, this.netList);
            }
        }
        return this.device;
    }

    /**
     * create new object inside the device
     */
    public void createSubJSONObject(String key, String value) {
        otherObjects.put(key, value);
    }

    /**
     * Return The Device JSON Object as String
     * 
     * @return {@link Stirng}
     */
    @Override
    public String toString() {
        return device.toJSONString();
    }
}
