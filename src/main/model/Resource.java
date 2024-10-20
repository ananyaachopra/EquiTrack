package model;
import org.json.JSONObject;

/**
 * Represents a resource in the resource library.
 * A resource contains a title, category, description, and a URL.
 */
public class Resource {
    private String title;
    private String category;
    private String description;
    private String url;

    // EFFECTS: creates a resource with the given title, category, description, and
    // url
    public Resource(String title, String category, String description, String url) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.url = url;
    }

    // REQUIRES: other must be non-null
    // EFFECTS: returns true if the titles or URLs of two resources are the same
    public boolean isEqual(Resource other) {
        return this.title.equalsIgnoreCase(other.getTitle()) || this.url.equalsIgnoreCase(other.getUrl());
    }

    // MODIFIES: this
    // EFFECTS: updates the category of the resource
    public void setCategory(String category) {
        this.category = category;
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

    public String getCategory() {
        return category;
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
               "Category: " + category + "\n" +
               "Description: " + description + "\n" +
               "URL: " + url;
    }

    // EFFECTS: returns this Resource as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("category", category);  
        json.put("description", description);
        json.put("url", url);
        return json;
    }
}
