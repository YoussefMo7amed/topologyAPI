package TopologyApi;

import java.text.ParseException;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Topology {
    private JSONObject jsonObject;
    private String id;
    private LinkedList<Component> componentsList;

    public Topology(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        extractID(jsonObject);
        extractComponents(jsonObject);
    }

    public Topology(String id) {
        this.id = id;
        createObject(id);
    }

    public Topology(String id, String components) {
        this.id = id;
        extractComponents(components);
        createObject(id, components);
    }

    public Topology(String id, LinkedList<Component> components) {
        this.id = id;
        componentsList = components;
        createObject(id);
    }

    private void extractID(JSONObject jsonObject) {
        this.id = (String) jsonObject.get("id");
    }

    private void extractComponents(JSONObject jsonObject) {
        JSONArray components = (JSONArray) jsonObject.get("components");
        componentsList = new LinkedList<>();

        for (int i = 0; i < components.size(); i++) {
            componentsList.add(new Component((JSONObject) components.get(i)));
        }
    }

    private void extractComponents(String components) {
        JSONParser jsonParser = new JSONParser();
        Object parsed = null;

        try {
            parsed = (Object) jsonParser.parse(components);
        } catch (org.json.simple.parser.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONArray componentsArray = (JSONArray) parsed;
        for (int i = 0; i < componentsArray.size(); i++) {
            componentsList.add(new Component((JSONObject) componentsArray.get(i)));
        }
    }

    private void createObject(String id) {
        jsonObject = new JSONObject();
        jsonObject.put("id", id);
    }

    private void createObject(String id, String components) {
        jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("components", componentsList.toString()); // TODO: CHECK IT
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
     * Return LinkedList of Component Class
     * 
     * @return {@link LinkedList <{@link Component}>
     */
    public LinkedList<Component> getComponents() {
        return this.componentsList;
    }

    /**
     * Return the connected components objects as String.
     * 
     * @return {@link String}
     */
    public String getComponentsAsString() {
        StringBuilder str = new StringBuilder();
        str.append("[");

        for (int i = 0; i < componentsList.size(); i++) {
            str.append(componentsList.get(i).toString());
            if (i < componentsList.size() - 1) {
                str.append(",");
            }
        }
        str.append("]");
        return str.toString();
    }

    /**
     * Return the connected components types
     * as LinkedList of String.
     * 
     * @return {@link LinkedList <{@link String}>
     */
    public LinkedList<String> getComponentsTypes() {
        LinkedList<String> componentsTypes = new LinkedList<>();
        for (Component component : componentsList) {
            componentsTypes.add(component.getType());
        }
        return componentsTypes;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        str.append(String.format("\"id\":\"%1$s\"", id));
        str.append(",");
        str.append("\"components\":");
        str.append(getComponentsAsString());
        str.append("}");

        return str.toString();
    }

}
