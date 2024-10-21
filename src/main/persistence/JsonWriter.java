package persistence;

import model.ResourceManager;
import model.FinanceManager;
import model.GenderEquityTracker;
import org.json.JSONObject;
import java.io.*;

/**
 * Represents a writer that writes JSON representation of the application state
 * to a file.
 */
public class JsonWriter {
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ResourceManager, FinanceManager, and
    // GenderEquityTracker to file
    public void write(ResourceManager resourceManager, FinanceManager financeManager,
            GenderEquityTracker equityTracker) throws IOException {
        JSONObject json = new JSONObject();
        json.put("resourceManager", resourceManager.toJson());
        json.put("financeManager", financeManager.toJson());
        json.put("genderEquityTracker", equityTracker.toJson());

        try (FileWriter file = new FileWriter(destination)) {
            file.write(json.toString(4)); // 4 spaces indent
        }
    }
}
