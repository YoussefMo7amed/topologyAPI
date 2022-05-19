import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class Main {
    public static void main(String[] args) throws IOException, ParseException, FileNotFoundException {
        // test reading and writing json

        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader("./topologies/topology.json");

        Object object = jsonParser.parse(reader);
        JSONObject jsonObject = (JSONObject) object;
        String id = (String) jsonObject.get("id");
        System.out.println(id);

        JSONArray components = (JSONArray) jsonObject.get("components");
        // System.out.println((String) (JSONObject) components.get(0));
        for (int i = 0; i < components.size(); i++) {
            JSONObject device = (JSONObject) components.get(i);
            System.out.println(device.get("type"));
        }

        FileWriter writer = new FileWriter("./topologies/t.json");
        jsonObject = new JSONObject();
        jsonObject.put("id", "top2");

        components = new JSONArray();

        Map<String, String> devices = new HashMap<String, String>();
        devices.put("type", "resistor");
        devices.put("type", "nmos");

        components.add(devices);

        jsonObject.put("components", components);

        try {
            writer.write(jsonObject.toString());
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}