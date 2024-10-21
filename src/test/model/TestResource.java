package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestResource {
    private Resource resource1;

    @BeforeEach
    void runBefore() {
        resource1 = new Resource("Financial Literacy", "Finance",
                "A guide to managing personal finances.", "https://www.finances.com");
    }

    @Test
    public void testConstructor() {
        assertEquals("Financial Literacy", resource1.getTitle());
        assertEquals("A guide to managing personal finances.", resource1.getDescription());
        assertEquals("https://www.finances.com", resource1.getUrl());
        assertEquals("Finance", resource1.getCategory());
    }

    @Test
    public void testUpdateCategory() {
        resource1.setCategory("Investment");
        assertEquals("Investment", resource1.getCategory());
    }

    @Test
    public void testUpdateTitle() {
        resource1.updateTitle("FinancialWorld");
        assertEquals("FinancialWorld", resource1.getTitle());
    }

    @Test
    public void testUpdateDescription() {
        resource1.updateDescription("All things Finance");
        assertEquals("All things Finance", resource1.getDescription());
    }

    @Test
    public void testUpdateUrl() {
        resource1.updateUrl("https://www.investopedia.com");
        assertEquals("https://www.investopedia.com", resource1.getUrl());
    }

    @Test
    public void testIsEqual() {
        Resource duplicate = new Resource("Financial Literacy", "Finance", "All things Finance",
                "https://investopedia.com");
        assertTrue(resource1.isEqual(duplicate));

        duplicate = new Resource("World of Finance", "Finance", "Everything Finance", "https://www.finances.com");
        assertTrue(resource1.isEqual(duplicate));

        duplicate = new Resource("Financial Literacy", "Finance", "All things Finance", "https://www.finances.com");
        assertTrue(resource1.isEqual(duplicate));

        // Should return false since neither title nor URL matches
        duplicate = new Resource("World of Finance", "Finance", "All things Finance", "https://investopedia.com");
        assertFalse(resource1.isEqual(duplicate));
    }

    @Test
    public void testGetResourceDetails() {
        String expected = "Title: Financial Literacy\n"
                +
                "Category: Finance\n"
                +
                "Description: A guide to managing personal finances.\n"
                +
                "URL: https://www.finances.com";
        assertEquals(expected, resource1.getResourceDetails());
    }
}
