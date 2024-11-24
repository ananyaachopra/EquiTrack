package model;

import org.json.JSONArray;
import org.json.JSONObject;
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
        companies.add(company);
        EventLog.getInstance().logEvent(new Event("Added company: " + company.getName()));
    }

    // MODIFIES: this
    // EFFECTS: removes a company from the tracker by name
    public void removeCompany(String name) {
        companies.removeIf(company -> company.getName().equalsIgnoreCase(name));
        EventLog.getInstance().logEvent(new Event("Removed company: " + name));
    }

    // EFFECTS: returns a list of all companies in the tracker
    public List<Company> getCompanies() {
        return companies;
    }

    // EFFECTS: returns the average percentage of women in leadership across all
    // companies
    public double averageWomenInLeadership() {
        if (companies.isEmpty()) {
            return 0;
        }
        double totalWomenInLeadership = 0;
        for (Company company : companies) {
            totalWomenInLeadership += company.getWomenInLeadership();
        }
        return totalWomenInLeadership / companies.size();
    }

    // EFFECTS: returns the average pay gap across all companies
    public double averagePayGap() {
        if (companies.isEmpty()) {
            return 0;
        }
        double totalPayGap = 0;
        for (Company company : companies) {
            totalPayGap += company.getPayGap();
        }
        return totalPayGap / companies.size();
    }

    // EFFECTS: returns the average diversity ratio across all companies
    public double averageDiversityRatio() {
        if (companies.isEmpty()) {
            return 0;
        }
        double totalDiversityRatio = 0;
        for (Company company : companies) {
            totalDiversityRatio += company.getDiversityRatio();
        }
        return totalDiversityRatio / companies.size();
    }

    // EFFECTS: returns the company with the highest percentage of women in
    // leadership, or null if there are no companies
    public Company getHighestWomenInLeadership() {
        if (companies.isEmpty()) {
            return null;
        }
        Company highest = companies.get(0);
        for (Company company : companies) {
            if (company.getWomenInLeadership() > highest.getWomenInLeadership()) {
                highest = company;
            }
        }
        return highest;
    }

    // EFFECTS: returns the company with the lowest pay gap, or null if there are no
    // companies
    public Company getLowestPayGap() {
        if (companies.isEmpty()) {
            return null;
        }
        Company lowest = companies.get(0);
        for (Company company : companies) {
            if (company.getPayGap() < lowest.getPayGap()) {
                lowest = company;
            }
        }
        return lowest;
    }

    // EFFECTS: returns this GenderEquityTracker as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray companyArray = new JSONArray();
        for (Company company : companies) {
            companyArray.put(company.toJson());  // Assuming Company has a toJson() method
        }
        json.put("companies", companyArray);
        return json;
    }

}
