package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestResource {
    private Resource resource1;
    private Resource resource2;
    
    @BeforeEach
    void runBefore() {
        resource1 = new Resource("Financial Literacy", "Finance", 
        "A guide to managing personal finances.", "https://www.finances.com");
        resource2 = new Resource("Career Development", "Career", 
        "Tips for advancing in your career.", "https://www.builtin.com");
    }

   @Test
   public void testConstructor() {
       assertEquals("Financial Literacy", resource1.getTitle());
       assertEquals("A guide to managing personal finances.", resource1.getDescription());
       assertEquals("https://www.finances.com", resource1.getUrl());
       assertFalse(resource1.getCategories().isEmpty());
   }

   @Test
   public void testAddCategory() {
       resource1.addCategory("Finance");
       List<String> categories = resource1.getCategories();
       assertEquals(1, categories.size());
       assertTrue(categories.contains("Finance"));

       // Adding the same category again should not duplicate it
       resource1.addCategory("Finance");
       assertEquals(1, categories.size());
   }

   @Test
   public void testAddMultipleCategories() {
       resource2.addCategory("Career");
       List<String> categories = resource2.getCategories();
       assertEquals(1, categories.size());
       assertTrue(categories.contains("Career"));

       resource2.addCategory("Personal Development");
       categories = resource2.getCategories();
       assertEquals(2, categories.size());
       assertTrue(categories.contains("Career"));
       assertTrue(categories.contains("Personal Development"));
   }

   @Test
   public void testRemoveCategory() {
       resource1.addCategory("Finance");
       assertEquals(1, resource1.getCategories().size());

       resource1.removeCategory("Finance");
       assertEquals(0, resource1.getCategories().size());
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
       Resource duplicate = new Resource("Financial Literacy", "Finance", "All things Finance", "https://investopedia.com");
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
       resource1.addCategory("Finance");
       String expected = "Title: Financial Literacy\n" +
                         "Categories: [Finance]\n" +
                         "Description: A guide to managing personal finances.\n" +
                         "URL: https://www.finances.com";
       assertEquals(expected, resource1.getResourceDetails());
   }
}