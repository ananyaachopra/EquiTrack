package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TestFinanceManager {
    private FinanceManager manager;
    private Expense expense1;
    private Expense expense2;
    private Expense expense3;

    @BeforeEach
    public void runBefore() {
        manager = new FinanceManager();
        expense1 = new Expense("Housing", 1200, "Rent for October", LocalDate.of(2024, 10, 1));
        expense2 = new Expense("Food", 100, "Groceries for the week", LocalDate.of(2024, 10, 2));
        expense3 = new Expense("Education", 50, "Online course fee", LocalDate.of(2024, 9, 30));
    }

    @Test
    public void testAddAndListExpenses() {
        manager.addExpense(expense1);
        manager.addExpense(expense2);

        List<Expense> expenses = manager.listExpenses();
        assertEquals(2, expenses.size());
        assertTrue(expenses.contains(expense1));
        assertTrue(expenses.contains(expense2));
    }

    @Test
    public void testRemoveExpense() {
        manager.addExpense(expense1);
        manager.addExpense(expense2);
        manager.removeExpense("Housing", "Rent for October");

        List<Expense> expenses = manager.listExpenses();
        assertEquals(1, expenses.size());
        assertFalse(expenses.contains(expense1));

        manager.addExpense(expense1);
        manager.removeExpense("Housing", "Rent for September");
        expenses = manager.listExpenses();
        assertEquals(2, expenses.size());
        assertTrue(expenses.contains(expense1));
    }

    @Test
    public void testCalculateTotalExpenses() {
        manager.addExpense(expense1);
        manager.addExpense(expense2);
        manager.addExpense(expense3);

        assertEquals(1350, manager.calculateTotalExpenses());
    }

    @Test
    public void testFilterByCategory() {
        manager.addExpense(expense1);
        manager.addExpense(expense2);
        manager.addExpense(expense3);

        List<Expense> filtered = manager.filterByCategory("Housing");
        assertEquals(1, filtered.size());
        assertTrue(filtered.contains(expense1));
    }

    @Test
    public void testFilterByMonth() {
        manager.addExpense(expense1);
        manager.addExpense(expense2);
        manager.addExpense(expense3);

        List<Expense> filtered = manager.filterByMonth(10, 2024);
        assertEquals(2, filtered.size());
        assertTrue(filtered.contains(expense1));
        assertTrue(filtered.contains(expense2));
    }

    @Test
    public void testGetHighestExpense() {
        Expense highest = manager.getHighestExpense();
        assertEquals(null, highest);

        manager.addExpense(expense2);
        manager.addExpense(expense1);
        manager.addExpense(expense3);

        highest = manager.getHighestExpense();
        assertEquals(expense1, highest);

    }

    @Test
    public void testGetExpenseCount() {
        manager.addExpense(expense1);
        manager.addExpense(expense2);
        assertEquals(2, manager.getNumberExpenses());
    }
}
