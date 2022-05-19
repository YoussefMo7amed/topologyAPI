package TopologyApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

public class Device {
    private String id="";
    private String type="";
    private JSONObject netList=null;
    private Map<String, String> otherObjects=null;

    public Device() {

    }

    public Device(JSONObject device) {
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

    /**
     * Return the device ID
     * 
     * @return String
     */

    public String getId() {
        return id;
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
        return otherObjects;
    }
}
