package model;

import org.json.JSONArray;
import org.json.JSONObject;
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
        expenses.add(expense);
    }

    // MODIFIES: this
    // EFFECTS: removes the first expense that matches the category and description
    public void removeExpense(String category, String description) {
        expenses.removeIf(expense -> expense.getCategory().equalsIgnoreCase(category)
                && expense.getDescription().equalsIgnoreCase(description));
    }

    // EFFECTS: returns a list of all expenses
    public List<Expense> listExpenses() {
        return expenses;
    }

    // EFFECTS: returns the total amount of all expenses
    public double calculateTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    // EFFECTS: returns a list of expenses in the specified category
    public List<Expense> filterByCategory(String category) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.isInCategory(category)) {
                filtered.add(expense);
            }
        }
        return filtered;
    }

    // EFFECTS: returns a list of expenses in the specified month and year
    public List<Expense> filterByMonth(int month, int year) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.isInMonth(month, year)) {
                filtered.add(expense);
            }
        }
        return filtered;
    }

    // EFFECTS: finds and returns the highest expense, or null if there are no
    // expenses
    public Expense getHighestExpense() {
        if (expenses.isEmpty()) {
            return null;
        }
        Expense highest = expenses.get(0);
        for (Expense expense : expenses) {
            if (expense.getAmount() > highest.getAmount()) {
                highest = expense;
            }
        }
        return highest;
    }

    // EFFECTS: returns the number of expenses in the finance manager
    public int getNumberExpenses() {
        return expenses.size();
    }

    // EFFECTS: returns this FinanceManager as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray expenseArray = new JSONArray();
        for (Expense expense : expenses) {
            expenseArray.put(expense.toJson());  // Assuming Expense has a toJson() method
        }
        json.put("expenses", expenseArray);
        return json;
    }
}
