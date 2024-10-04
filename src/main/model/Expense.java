package model;

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

    // EFFECTS: creates an expense with the given category, amount, description, and date
    public Expense(String category, double amount, String description, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    // MODIFIES: this
    // EFFECTS: updates the amount of the expense
    public void updateAmount(double newAmount) {
        //stub
    }

    // EFFECTS: returns true if the expense is in the specified category
    public boolean isInCategory(String category) {
        return false;
    }

    // EFFECTS: returns true if the expense exceeds the given budget amount
    public boolean exceedsBudget(double budgetAmount) {
        return false;
    }

    // EFFECTS: returns true if the expense occurred in the specified month and year
    public boolean isInMonth(int month, int year) {
        return false;
    }

    // MODIFIES: this
    // EFFECTS: applies a discount (in percentage) to the expense amount
    public void applyDiscount(double discountPercentage) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: applies a tax (in percentage) to the expense amount
    public void applyTax(double taxPercentage) {
        //stub
    }

    // EFFECTS: returns a formatted string containing all details of the expense
    public String getExpenseDetails() {
        return "";
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
}
