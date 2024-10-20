package persistence;

import model.ResourceManager;
import model.FinanceManager;
import model.GenderEquityTracker;
import model.Resource;
import model.Expense;
import model.Company;
import org.json.JSONObject;
import org.json.JSONArray;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.io.IOException;

/**
 * Represents a reader that reads JSON data from a file and creates the application state.
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads JSON data from file and returns it as a JSONObject
    public JSONObject read() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(source)));
        return new JSONObject(content);
    }

    // EFFECTS: reads ResourceManager from JSON object
    public ResourceManager loadResourceManager(JSONObject jsonObject) {
        ResourceManager resourceManager = new ResourceManager();
        JSONArray resourceArray = jsonObject.getJSONArray("resources");
        for (Object obj : resourceArray) {
            JSONObject jsonResource = (JSONObject) obj;
            resourceManager.addResource(parseResource(jsonResource));
        }
        return resourceManager;
    }

    // EFFECTS: reads FinanceManager from JSON object
    public FinanceManager loadFinanceManager(JSONObject jsonObject) {
        FinanceManager financeManager = new FinanceManager();
        JSONArray expenseArray = jsonObject.getJSONArray("expenses");
        for (Object obj : expenseArray) {
            JSONObject jsonExpense = (JSONObject) obj;
            financeManager.addExpense(parseExpense(jsonExpense));
        }
        return financeManager;
    }

    // EFFECTS: reads GenderEquityTracker from JSON object
    public GenderEquityTracker loadGenderEquityTracker(JSONObject jsonObject) {
        GenderEquityTracker equityTracker = new GenderEquityTracker();
        JSONArray companyArray = jsonObject.getJSONArray("companies");
        for (Object obj : companyArray) {
            JSONObject jsonCompany = (JSONObject) obj;
            equityTracker.addCompany(parseCompany(jsonCompany));
        }
        return equityTracker;
    }

    // HELPER: parses a Resource from a JSONObject
    private Resource parseResource(JSONObject json) {
        String title = json.getString("title");
        String category = json.getString("category");  
        String description = json.getString("description");
        String url = json.getString("url");

        return new Resource(title, category, description, url);  
    }

    // HELPER: parses an Expense from a JSONObject
    private Expense parseExpense(JSONObject json) {
        String category = json.getString("category");
        double amount = json.getDouble("amount");
        String description = json.getString("description");
        String dateString = json.getString("date");
        LocalDate date = LocalDate.parse(dateString);
        return new Expense(category, amount, description, date);
    }

    // HELPER: parses a Company from a JSONObject
    private Company parseCompany(JSONObject json) {
        String name = json.getString("name");
        int womenInLeadershipPercentage = json.getInt("womenInLeadershipPercentage");
        int payGapPercentage = json.getInt("payGapPercentage");
        int overallDiversityPercentage = json.getInt("overallDiversityPercentage");
        return new Company(name, womenInLeadershipPercentage, payGapPercentage, overallDiversityPercentage);
    }
}
