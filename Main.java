import TopologyApi.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

class Main {
    public static void main(String[] args) throws IOException, ParseException, FileNotFoundException {
        // test reading and writing json

        JSONReader readJSON = null;

        JSONObject jsonObject;
        try {
            readJSON = new JSONReader("./JSON-files/topology.json");
            jsonObject = readJSON.read();
            
            JSONArray components = (JSONArray) jsonObject.get("components");
            
            // System.out.println((JSONObject)components.get(1));
            
            Device device = new Device((JSONObject)components.get(0));

            System.out.println(device.getId());
            System.out.println(device.getType());
            System.out.println(device.getNetlist());
            System.out.println(device.getOtherObjects());
            // for (int i = 0; i < components.size(); i++) {
            //     JSONObject device = (JSONObject) components.get(i);
            //     System.out.println(device.get("type"));
            // }
            
        } catch (Exception e) {

        } finally {
            readJSON.close();
        }

        // FileWriter writer = new FileWriter("./topologies/t.json");
        // jsonObject = new JSONObject();
        // jsonObject.put("id", "top2");

        // components = new JSONArray();

        // Map<String, String> devices = new HashMap<String, String>();
        // devices.put("type", "resistor");
        // devices.put("type", "nmos");

        // components.add(devices);

        // jsonObject.put("components", components);

        // try {
        // writer.write(jsonObject.toString());
        // writer.flush();
        // } catch (IOException e) {
        // System.out.println(e.getMessage());
        // }

    }
}