package model;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of resources in the resource library.
 * Can add, remove, and list resources, and also search for resources by title or category.
 */
public class ResourceManager {
    private List<Resource> resources; 
    
    // EFFECTS: creates an empty resource library
    public ResourceManager() {
        resources = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a resource to the library if it doesn't already exist
    public void addResource(Resource resource) {
        if (!resourceExists(resource)) {
            resources.add(resource);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a resource from the library by title or URL
    public void removeResource(String titleOrUrl) {
        resources.removeIf(r -> r.getTitle().equalsIgnoreCase(titleOrUrl) || r.getUrl().equalsIgnoreCase(titleOrUrl));
    }

    // EFFECTS: returns a list of all resources in the library
    public List<Resource> getResources() {
        return resources;
    }

    // EFFECTS: searches for resources by title and returns a list of matches
    public List<Resource> searchByTitle(String title) {
        List<Resource> foundResources = new ArrayList<>();
        for (Resource r : resources) {
            if (r.getTitle().equalsIgnoreCase(title)) {
                foundResources.add(r);
            }
        }
        return foundResources;
    }

    // EFFECTS: searches for resources by category and returns a list of matches
    public List<Resource> searchByCategory(String category) {
        List<Resource> foundResources = new ArrayList<>();
        for (Resource r : resources) {
            if (r.getCategory() == category) {
                foundResources.add(r);
            }
        }
        return foundResources;
    }

    // EFFECTS: checks if a resource already exists in the library (by title or URL)
    private boolean resourceExists(Resource resource) {
        for (Resource r : resources) {
            if (r.isEqual(resource)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns this ResourceManager as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray resourceArray = new JSONArray();
        for (Resource resource : resources) {
            resourceArray.put(resource.toJson());  // Assuming Resource has a toJson() method
        }
        json.put("resources", resourceArray);
        return json;
    }
    
}

