package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of expenses in the finance tracker.
 * Provides methods to add, remove, list, and filter expenses.
 */
public class FinanceManager {
    private List<Expense> expenses; 

    // EFFECTS: creates an empty finance manager
    public FinanceManager() {
        expenses = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an expense to the collection
    public void addExpense(Expense expense) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: removes the first expense that matches the category and description
    public void removeExpense(String category, String description) {
        //stub
    }

    // EFFECTS: returns a list of all expenses
    public List<Expense> listExpenses() {
        return null;
    }

    // EFFECTS: returns the total amount of all expenses
    public double calculateTotalExpenses() {
        return 0.0;
    }

    // EFFECTS: returns a list of expenses in the specified category
    public List<Expense> filterByCategory(String category) {
        return null;
    }

    // EFFECTS: returns a list of expenses in the specified month and year
    public List<Expense> filterByMonth(int month, int year) {
        return null;
    }

    // EFFECTS: finds and returns the highest expense, or null if there are no expenses
    public Expense getHighestExpense() {
        //stub
        }

    // EFFECTS: returns the number of expenses in the finance manager
    public int getNumberExpenses() {
        return 0;
    }
}

