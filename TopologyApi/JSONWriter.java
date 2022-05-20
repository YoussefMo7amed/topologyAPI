package TopologyApi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class JSONWriter {
    private String path = "";
    JSONObject jsonObject = null;
    FileWriter writer = null;

    public JSONWriter(String path) {
        this.path = path;
        createJSON(null);
        writeFile(path);
    }

    public JSONWriter(String path, JSONObject jsonObject) {
        this.path = path;
        createJSON(jsonObject);
        writeFile(path);
    }

    private FileWriter writeFile(String path) {
        try {
            writer = new FileWriter(path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return writer;
    }

    private void createJSON(JSONObject jsonObject) {
        if (jsonObject == null) {
            this.jsonObject = new JSONObject();
        } else {
            this.jsonObject = jsonObject;
        }
    }

    /**
     * Write the JSON file.
     */
    public void write() {
        try {
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(jsonObject.toJSONString());
            bw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Void function that close {@link FileWriter}
     * 
     * @return
     */
    public void close() throws IOException {
        try {
            writer.close();
        } catch (Exception e) {
            return;
        }
    }

}
