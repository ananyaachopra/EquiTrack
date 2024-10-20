package model;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonWriter {
    private static final String TEST_FILE = "./data/testWriter.json";
    private JsonWriter jsonWriter;
    private ResourceManager resourceManager;
    private FinanceManager financeManager;
    private GenderEquityTracker equityTracker;

    @BeforeEach
    public void setup() {
        jsonWriter = new JsonWriter(TEST_FILE);

        // Initialize ResourceManager
        resourceManager = new ResourceManager();
        Resource resource1 = new Resource("Financial Literacy", "Finance", "A guide to managing personal finances.", "https://www.finances.com");
        Resource resource2 = new Resource("Career Development", "Career", "Tips for advancing in your career.", "https://www.builtin.com");
        resourceManager.addResource(resource1);
        resourceManager.addResource(resource2);

        // Initialize FinanceManager
        financeManager = new FinanceManager();
        Expense expense = new Expense("Rent", 1200.0, "Monthly rent for apartment", LocalDate.of(2024, 10, 1));
        financeManager.addExpense(expense);

        // Initialize GenderEquityTracker
        equityTracker = new GenderEquityTracker();
        Company company = new Company("TechCorp", 40, 20, 60);
        equityTracker.addCompany(company);
    }

    @Test
    public void testWriterGeneralData() {
        try {
            // Write to file
            jsonWriter.write(resourceManager, financeManager, equityTracker);

            // Read the file back and verify contents
            String content = new String(Files.readAllBytes(Paths.get(TEST_FILE)));
            JSONObject jsonObject = new JSONObject(content);

            // Validate ResourceManager
            JSONObject jsonResourceManager = jsonObject.getJSONObject("resourceManager");
            List<Object> resources = jsonResourceManager.getJSONArray("resources").toList();
            assertEquals(2, resources.size());

            // Validate first resource
            JSONObject resource1 = jsonResourceManager.getJSONArray("resources").getJSONObject(0);
            assertEquals("Financial Literacy", resource1.getString("title"));
            assertEquals("Finance", resource1.getString("category"));
            assertEquals("A guide to managing personal finances.", resource1.getString("description"));
            assertEquals("https://www.finances.com", resource1.getString("url"));

            // Validate FinanceManager
            JSONObject jsonFinanceManager = jsonObject.getJSONObject("financeManager");
            List<Object> expenses = jsonFinanceManager.getJSONArray("expenses").toList();
            assertEquals(1, expenses.size());

            // Validate first expense
            JSONObject expense1 = jsonFinanceManager.getJSONArray("expenses").getJSONObject(0);
            assertEquals("Rent", expense1.getString("category"));
            assertEquals(1200.0, expense1.getDouble("amount"));
            assertEquals("Monthly rent for apartment", expense1.getString("description"));
            assertEquals("2024-10-01", expense1.getString("date"));

            // Validate GenderEquityTracker
            JSONObject jsonEquityTracker = jsonObject.getJSONObject("genderEquityTracker");
            List<Object> companies = jsonEquityTracker.getJSONArray("companies").toList();
            assertEquals(1, companies.size());

            // Validate first company
            JSONObject company1 = jsonEquityTracker.getJSONArray("companies").getJSONObject(0);
            assertEquals("TechCorp", company1.getString("name"));
            assertEquals(40, company1.getInt("womenInLeadershipPercentage"));
            assertEquals(20, company1.getInt("payGapPercentage"));
            assertEquals(60, company1.getInt("overallDiversityRatio"));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testWriterIOException() {
        try {
            JsonWriter badWriter = new JsonWriter("/invalidPath/testWriter.json");
            badWriter.write(resourceManager, financeManager, equityTracker);
            fail("IOException expected");
        } catch (IOException e) {
            // Pass: IOException expected
        }
    }
}
