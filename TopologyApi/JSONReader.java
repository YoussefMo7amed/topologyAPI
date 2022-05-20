package TopologyApi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
    private String path = "";
    JSONObject jsonObject = new JSONObject();
    FileReader reader = null;

    public JSONReader(String path) throws FileNotFoundException {
        this.path = path;
        reader = readFile(path);
    }

    private FileReader readFile(String path) throws FileNotFoundException {
        try {
            reader = new FileReader(this.path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return reader;
    }

    private JSONObject getJSONObject(FileReader reader) {
        JSONParser jsonParser = new JSONParser();
        try {
            BufferedReader br = new BufferedReader(reader);
            Object object = jsonParser.parse(br.lines().collect(Collectors.joining()));
            br.close();
            close();
            jsonObject = (JSONObject) object;
        } catch (ParseException | IOException e) {
            return null;
        }
        return jsonObject;
    }

    /**
     * Return a {@link JSONObject} or null if there is any exception
     * 
     * @return a JSONObject
     */
    public JSONObject read() {
        try {
            return getJSONObject(reader);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Void function that close {@link FileReader}
     * 
     * @return
     */
    public void close() throws IOException {
        try {
            reader.close();
        } catch (Exception e) {
            return;
        }
    }
}