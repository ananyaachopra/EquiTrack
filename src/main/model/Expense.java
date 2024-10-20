package model;

import org.json.JSONObject;
import java.time.LocalDate;

/**
 * Represents an expense in the finance tracker.
 * An expense has a category, an amount, a description, and a date.
 */
public class Expense {
    private String category;
    private double amount;
    private String description;
    private LocalDate date;

    // EFFECTS: creates an expense with the given category, amount, description, and
    // date
    public Expense(String category, double amount, String description, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    // MODIFIES: this
    // EFFECTS: updates the amount of the expense
    public void updateAmount(double newAmount) {
        this.amount = newAmount;
    }

    // EFFECTS: returns true if the expense is in the specified category
    public boolean isInCategory(String category) {
        return this.category.equalsIgnoreCase(category);
    }
     
    // EFFECTS: returns true if the expense exceeds the given budget amount
    public boolean exceedsBudget(double budgetAmount) {
        return this.amount > budgetAmount;
    }

    // EFFECTS: returns true if the expense occurred in the specified month and year
    public boolean isInMonth(int month, int year) {
        return this.date.getMonthValue() == month && this.date.getYear() == year;
    }

    // MODIFIES: this
    // EFFECTS: applies a discount (in percentage) to the expense amount
    public void applyDiscount(double discountPercentage) {
        double discount = (discountPercentage / 100) * this.amount;
        this.amount -= discount;
    }

    // MODIFIES: this
    // EFFECTS: applies a tax (in percentage) to the expense amount
    public void applyTax(double taxPercentage) {
        double tax = (taxPercentage / 100) * this.amount;
        this.amount += tax;
    }

    // EFFECTS: returns a formatted string containing all details of the expense
    public String getExpenseDetails() {
        return "Category: " + category + "\n"
                +
                "Amount: $" + String.format("%.2f", amount) + "\n"
                +
                "Description: " + description + "\n"
                +
                "Date: " + date.toString();
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    // EFFECTS: returns this Expense as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("amount", amount);
        json.put("description", description);
        json.put("date", date.toString()); // Store date as string
        return json;
    }
}
