package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestExpense {
    private Expense expense;

    @BeforeEach
    public void runBefore() {
        expense = new Expense("Housing", 1200,
                "Rent for October", LocalDate.of(2024, 10, 1));
    }

    @Test
    public void testConstructor() {
        assertEquals("Housing", expense.getCategory());
        assertEquals(1200, expense.getAmount());
        assertEquals("Rent for October", expense.getDescription());
        assertEquals(LocalDate.of(2024, 10, 1), expense.getDate());
    }

    @Test
    public void testUpdateAmount() {
        expense.updateAmount(1300);
        assertEquals(1300, expense.getAmount());
    }

    @Test
    public void testIsInCategory() {
        assertTrue(expense.isInCategory("Housing"));
        assertFalse(expense.isInCategory("Education"));
    }

    @Test
    public void testExceedsBudget() {
        assertTrue(expense.exceedsBudget(1000));
        assertFalse(expense.exceedsBudget(1500));
    }

    @Test
    public void testIsInMonth() {
        assertTrue(expense.isInMonth(10, 2024));
        assertFalse(expense.isInMonth(10, 2023));
        assertFalse(expense.isInMonth(9, 2024));
    }

    @Test
    public void testApplyDiscount() {
        expense.applyDiscount(10);
        assertEquals(1080, expense.getAmount(), 0.01);
    }

    @Test
    public void testApplyTax() {
        expense.applyTax(5);
        assertEquals(1260, expense.getAmount(), 0.01);
    }

    @Test
    public void testGetExpenseDetails() {
        String expected = "Category: Housing\n"
                +
                "Amount: $1200.00\n"
                +
                "Description: Rent for October\n"
                +
                "Date: 2024-10-01";
        assertEquals(expected, expense.getExpenseDetails());
    }
}
