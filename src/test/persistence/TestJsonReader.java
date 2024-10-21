package persistence;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestJsonReader {
    private JsonReader jsonReader;

    @BeforeEach
    public void setup() {
        jsonReader = new JsonReader("./data/testData.json");
    }

    @Test
    public void testReadFile() throws IOException {
        JSONObject jsonObject = jsonReader.read();
        assertNotNull(jsonObject);
    }

    @Test
    public void testLoadResourceManager() throws IOException {
        JSONObject jsonObject = jsonReader.read();
        ResourceManager resourceManager = jsonReader.loadResourceManager(jsonObject);

        assertEquals(2, resourceManager.getResources().size());

        Resource resource1 = resourceManager.getResources().get(0);
        assertEquals("Financial Literacy", resource1.getTitle());
        assertEquals("Finance", resource1.getCategory());
        assertEquals("A guide to managing personal finances.", resource1.getDescription());
        assertEquals("https://www.finances.com", resource1.getUrl());

        Resource resource2 = resourceManager.getResources().get(1);
        assertEquals("Career Development", resource2.getTitle());
        assertEquals("Career", resource2.getCategory());
        assertEquals("Tips for advancing in your career.", resource2.getDescription());
        assertEquals("https://www.builtin.com", resource2.getUrl());
    }

    @Test
    public void testLoadFinanceManager() throws IOException {
        JSONObject jsonObject = jsonReader.read();
        FinanceManager financeManager = jsonReader.loadFinanceManager(jsonObject);

        assertEquals(1, financeManager.listExpenses().size());

        Expense expense = financeManager.listExpenses().get(0);
        assertEquals("Rent", expense.getCategory());
        assertEquals(1200.0, expense.getAmount());
        assertEquals("Monthly rent for apartment", expense.getDescription());
        assertEquals(LocalDate.of(2024, 10, 1), expense.getDate());
    }

    @Test
    public void testLoadGenderEquityTracker() throws IOException {
        JSONObject jsonObject = jsonReader.read();
        GenderEquityTracker genderEquityTracker = jsonReader.loadGenderEquityTracker(jsonObject);

        assertEquals(1, genderEquityTracker.getCompanies().size());

        Company company = genderEquityTracker.getCompanies().get(0);
        assertEquals("TechCorp", company.getName());
        assertEquals(40, company.getWomenInLeadership());
        assertEquals(20, company.getPayGap());
        assertEquals(60, company.getDiversityRatio());
    }
}
