package model;
import org.json.JSONObject;
/**
 * Represents a company with gender diversity metrics.
 * Contains the name, percentage of women in leadership, pay gap percentage, and diversity ratio.
 */

public class Company {
    private String name;
    private double womenInLeadership;
    private double payGap;
    private double diversityRatio;

    // EFFECTS: creates a company with the given name, women in leadership
    // percentage, pay gap percentage, and diversity ratio
    public Company(String name, double womenInLeadership, double payGap, double diversityRatio) {
        this.name = name;
        this.womenInLeadership = womenInLeadership;
        this.payGap = payGap;
        this.diversityRatio = diversityRatio;
    }

    // MODIFIES: this
    // EFFECTS: updates the percentage of women in leadership
    public void updateWomenInLeadership(double newPercentage) {
        this.womenInLeadership = newPercentage;
    }

    // MODIFIES: this
    // EFFECTS: updates the pay gap percentage
    public void updatePayGap(double newPayGap) {
        this.payGap = newPayGap;
    }

    // MODIFIES: this
    // EFFECTS: updates the overall diversity ratio
    public void updateDiversityRatio(double newDiversityRatio) {
        this.diversityRatio = newDiversityRatio;
    }

    // EFFECTS: returns a string with all the company's gender equity details
    public String getCompanyDetails() {
        return "Company Name: " + name + "\n"
                +
                "Women in Leadership: " + womenInLeadership + "%\n"
                +
                "Pay Gap: " + payGap + "%\n"
                +
                "Overall Diversity Ratio: "
                + diversityRatio;
    }

    public String getName() {
        return name;
    }

    public double getWomenInLeadership() {
        return womenInLeadership;
    }

    public double getPayGap() {
        return payGap;
    }

    public double getDiversityRatio() {
        return diversityRatio;
    }

    // EFFECTS: returns this Company as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("womenInLeadershipPercentage", womenInLeadership);
        json.put("payGapPercentage", payGap);
        json.put("overallDiversityRatio", diversityRatio);
        return json;
    }
}