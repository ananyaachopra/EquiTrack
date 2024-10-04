package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of companies with gender diversity metrics.
 * Provides methods to add, remove, list, and analyze company data.
 */
public class GenderEquityTracker {
    private List<Company> companies; 

    // EFFECTS: creates an empty gender equity tracker
    public GenderEquityTracker() {
        companies = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a company to the tracker
    public void addCompany(Company company) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: removes a company from the tracker by name
    public void removeCompany(String name) {
        //stub
    }

    // EFFECTS: returns a list of all companies in the tracker
    public List<Company> getCompanies() {
        return null;
    }

    // EFFECTS: returns the average percentage of women in leadership across all companies
    public double AverageWomenInLeadership() {
        return 0;
    }

    // EFFECTS: returns the average pay gap across all companies
    public double AveragePayGap() {
        return 0;
    }

    // EFFECTS: returns the company with the highest percentage of women in leadership, or null if there are no companies
    public Company getHighestWomenInLeadership() {
        //stub
    }

    // EFFECTS: returns the company with the lowest pay gap, or null if there are no companies
    public Company getLowestPayGap() {
        //stub
    }
}
