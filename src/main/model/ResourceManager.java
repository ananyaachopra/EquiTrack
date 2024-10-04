package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of resources in the resource library.
 * Can add, remove, and list resources, and also search for resources by title or category.
 */
public class ResourceManager {
    public List<Resource> resources; 
    
    // EFFECTS: creates an empty resource library
    public ResourceManager() {
        resources = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a resource to the library if it doesn't already exist
    public void addResource(Resource resource) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: removes a resource from the library by title or URL
    public void removeResource(String titleOrUrl) {
       //stub
    }

    // EFFECTS: returns a list of all resources in the library
    public List<Resource> getResources() {
        return null;
    }

    // EFFECTS: searches for resources by title and returns a list of matches
    public List<Resource> searchByTitle(String title) {
        return null;
    }

    // EFFECTS: searches for resources by category and returns a list of matches
    public List<Resource> searchByCategory(String category) {
        return null;
    }

    // EFFECTS: checks if a resource already exists in the library (by title or URL)
    private boolean resourceExists(Resource resource) {
        return false;
}

}

