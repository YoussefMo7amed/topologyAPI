package TopologyApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.LinkedList;

import TopologyApi.Device;

public class Topology {
    private JSONObject jsonObject;
    private String id;
    private LinkedList<Device> devices;

    public Topology(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        extractID(jsonObject);
        extractDevices(jsonObject);
    }

    public Topology(String id) {
        this.id = id;
        createObject(id);
    }

    public Topology(String id, String components) {
        this.id = id;
        extractDevices(components);
        createObject(id, components);
    }

    public Topology(String id, LinkedList<Device> components) {
        this.id = id;
        devices = components;
        createObject(id);
    }

    private void extractID(JSONObject jsonObject) {
        this.id = (String) jsonObject.get("id");
    }

    private void extractDevices(JSONObject jsonObject) {
        JSONArray components = (JSONArray) jsonObject.get("components");
        devices = new LinkedList<>();

        for (int i = 0; i < components.size(); i++) {
            devices.add(new Device((JSONObject) components.get(i)));
        }
    }

    private void extractDevices(String components) {
        JSONParser jsonParser = new JSONParser();
        Object parsed = null;
        try {
            parsed = (Object) jsonParser.parse(components);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONArray componentsArray = (JSONArray) parsed;
        for (int i = 0; i < componentsArray.size(); i++) {
            devices.add(new Device((JSONObject) componentsArray.get(i)));
        }
    }

    private void createObject(String id) {
        jsonObject = new JSONObject();
        jsonObject.put("id", id);
    }

    private void createObject(String id, String components) {
        jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("components", devices.toString()); // TODO: CHECK IT
    }

    /**
     * Return the Topology ID
     * 
     * @return String
     */

    public String getId() {
        return this.id;
    }

    /**
     * Return LinkedList of Device Class
     * 
     * @return {@link LinkedList <Device>}
     */
    public LinkedList<Device> getConnectedDevices() {
        return this.devices;
    }

    /**
     * Return the connected devices as String.
     * 
     * @return {@link String}
     */
    public String getConnectedDevicesAsString() {
        StringBuilder str = new StringBuilder();
        str.append("[");

        for (int i = 0; i < devices.size(); i++) {
            str.append(devices.get(i).toString());
            if (i < devices.size() - 1) {
                str.append(",");
            }
        }
        str.append("]");
        return str.toString();
    }


    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        str.append(String.format("\"id\":\"%1$s\"", id));
        str.append(",");
        str.append("\"components\":");
        str.append(getConnectedDevicesAsString());
        str.append("}");

        return str.toString();
    }

}
