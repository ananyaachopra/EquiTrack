package model;

/**
 * Represents a company with gender diversity metrics.
 * Contains the name, percentage of women in leadership, and pay gap percentage.
 */

public class Company {
    private String name;              
    private double womenInLeadership; 
    private double payGap;            

    // EFFECTS: creates a company with the given name, women in leadership percentage, and pay gap percentage
    public Company(String name, double womenInLeadership, double payGap) {
        this.name = name;
        this.womenInLeadership = womenInLeadership;
        this.payGap = payGap;
    }

    // MODIFIES: this
    // EFFECTS: updates the percentage of women in leadership
    public void updateWomenInLeadership(double newPercentage) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: updates the pay gap percentage
    public void updatePayGap(double newPayGap) {
        //stub
    }

    // EFFECTS: returns a string with all the company's gender equity details
    public String getCompanyDetails() {
        return "";
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
}

