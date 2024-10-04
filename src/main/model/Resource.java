package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a resource in the resource library.
 * A resource contains a title, category, description, and a URL.
 */
public class Resource {
    public String title;
    public List<String> categories;
    public String description;
    public String url;

    // EFFECTS: creates a resource with the given title, category, description, and url
    public Resource(String title, String category, String description, String url) {
        this.title = title;
        this.categories = new ArrayList<>();
        this.description = description;
        this.url = url;
    }

    // REQUIRES: other must be non-null
    // EFFECTS: returns true if the titles or URLs of two resources are the same
    public boolean isEqual(Resource other) {
        return this.title.equalsIgnoreCase(other.getTitle()) || this.url.equalsIgnoreCase(other.getUrl());
    }

    // MODIFIES: this
    // EFFECTS: adds a category to the list if it's not already present
    public void addCategory(String category) {       
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the specified category
    public void removeCategory(String category) {
        categories.remove(category);
    }

    // MODIFIES: this
    // EFFECTS: updates the title of the resource
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }
    // MODIFIES: this
    // EFFECTS: updates the description of the resource
    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    // MODIFIES: this
    // EFFECTS: updates the URL of the resource   
     public void updateUrl(String newUrl) {
        this.url = newUrl;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    // EFFECTS: returns all details of the resource as a formatted string
    public String getResourceDetails() {
        return "Title: " + title + "\n" +
               "Categories: " + categories + "\n" +
               "Description: " + description + "\n" +
               "URL: " + url;
    }
}
   

    


