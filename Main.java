import TopologyApi.*;

import java.io.IOException;
import org.json.simple.parser.ParseException;

import org.json.simple.JSONObject;

class Main {
    public static void main(String[] args) throws IOException, ParseException {
        // test reading and writing json

        JSONReader readJSON = null;
        Topology topology = null;

        JSONObject jsonObject;
        try {
            readJSON = new JSONReader("./JSON-files/topology.json");
            jsonObject = readJSON.read();

            topology = new Topology(jsonObject);

        } catch (Exception e) {

        } finally {
            readJSON.close();
        }

    }
}