package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestResourceManager {
    private ResourceManager manager;
    private Resource resource1;
    private Resource resource2;
    private Resource resource3;

    @BeforeEach
    public void runBefore() {
        manager = new ResourceManager();
        resource1 = new Resource("Financial Literacy", "Finance", "A guide to managing personal finances.", "https://www.finances.com");
        resource2 = new Resource("Career Development", "Career", "Tips for advancing in your career.", "https://www.builtin.com");
        resource3 = new Resource("Mental Health Awareness", "Health", "A guide to maintaining mental well-being.", "https://www.mentalhealth.com");
    }

    @Test
    public void testAddResource() {
        manager.addResource(resource1);
        List<Resource> resources = manager.getResources();
        assertEquals(1, resources.size());
        assertTrue(resources.contains(resource1));

        // Adding the same resource again (should not duplicate)
        manager.addResource(resource1);
        assertEquals(1, resources.size());
    }

    @Test
    public void testAddMultipleResources() {
        manager.addResource(resource1);
        List<Resource> resources = manager.getResources();
        assertEquals(1, resources.size());
        assertTrue(resources.contains(resource1));

        manager.addResource(resource2);
        manager.addResource(resource3);
        assertEquals(3, resources.size());
        assertTrue(resources.contains(resource2));
        assertTrue(resources.contains(resource3));
    }

    @Test
    public void testRemoveResourceByTitle() {
        manager.addResource(resource1);
        manager.addResource(resource2);
        manager.removeResource("Financial Literacy");
        List<Resource> resources = manager.getResources();
        assertEquals(1, resources.size());
        assertFalse(resources.contains(resource1));
    }

    @Test
    public void testRemoveResourceByUrl() {
        manager.addResource(resource1);
        manager.addResource(resource2);
        manager.removeResource("https://www.builtin.com");
        List<Resource> resources = manager.getResources();
        assertEquals(1, resources.size());
        assertFalse(resources.contains(resource2));
    }

    @Test
    public void testSearchByTitle() {
        manager.addResource(resource1);
        manager.addResource(resource2);
        List<Resource> foundResources = manager.searchByTitle("Career Development");
        assertEquals(1, foundResources.size());
        assertTrue(foundResources.contains(resource2));
    }

    @Test
    public void testSearchByCategory() {
        resource1.setCategory("Personal Finance");
        resource2.setCategory("Career Advice");
        manager.addResource(resource1);
        manager.addResource(resource2);

        List<Resource> foundResources = manager.searchByCategory("Personal Finance");
        assertEquals(1, foundResources.size());
        assertTrue(foundResources.contains(resource1));
    }

    @Test
    public void testResourceExists() {
        manager.addResource(resource1);
        manager.addResource(resource2);

        // Create a resource with the same title but different URL (should not be added)
        Resource duplicateResource = new Resource("Financial Literacy", "Finance", "Another finance guide", "https://www.investopedia.com");
        manager.addResource(duplicateResource);

        List<Resource> resources = manager.getResources();
        assertEquals(2, resources.size());
    }
}
