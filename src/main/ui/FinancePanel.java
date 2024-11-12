package ui;

import model.FinanceManager;
import model.Expense;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.List;

/**
 * GUI for managing financial data.
 */
public class FinancePanel extends JFrame {
    private FinanceManager financeManager;
    private JTextArea expenseDisplay;
    private JLabel imageLabel;

    // MODIFIES: this
    // EFFECTS: initializes components and layout for managing financial data
    public FinancePanel(FinanceManager financeManager, JFrame mainFrame) {
        this.financeManager = financeManager;
        setupFrame();
        addButtonPanel(mainFrame);
        addMainComponents();
    }

    // MODIFIES: this
    // EFFECTS: sets up the frame properties
    private void setupFrame() {
        setTitle("Finance Manager");
        setSize(400, 500);
        setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds button panel with add, filter, sort, and back
    // buttons
    private void addButtonPanel(JFrame mainFrame) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(new AddExpenseListener());
        buttonPanel.add(addExpenseButton);

        JButton filterButton = new JButton("Filter by Month");
        filterButton.addActionListener(e -> filterExpensesByMonth());
        buttonPanel.add(filterButton);

        JButton sortButton = new JButton("Sort by Amount");
        sortButton.addActionListener(e -> sortExpensesByAmount());
        buttonPanel.add(sortButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
            mainFrame.setVisible(true);
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds main display and image components
    private void addMainComponents() {
        expenseDisplay = new JTextArea();
        expenseDisplay.setEditable(false);
        updateExpenseDisplay();

        imageLabel = new JLabel(new ImageIcon("./data/finance_image.png"));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(new JScrollPane(expenseDisplay), BorderLayout.CENTER);
        add(imageLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: displays expense information in the text area
    private void updateExpenseDisplay() {
        expenseDisplay.setText("");
        for (Expense expense : financeManager.listExpenses()) {
            expenseDisplay.append(expense.getExpenseDetails() + "\n\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: filters expenses by a specified month
    private void filterExpensesByMonth() {
        int monthValue = getMonthInput();
        if (monthValue == -1) {
            return; // Exit if invalid month input
        }

        List<Expense> filteredExpenses = filterExpensesByMonthValue(monthValue);
        updateExpenseDisplayWithFilter(filteredExpenses, monthValue);
    }

    // EFFECTS: prompts user for month input and returns it, or -1 if invalid
    private int getMonthInput() {
        String month = JOptionPane.showInputDialog("Enter month (1-12):");
        try {
            int monthValue = Integer.parseInt(month);
            if (monthValue < 1 || monthValue > 12) {
                throw new NumberFormatException();
            }
            return monthValue;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid month. Please enter a number between 1 and 12.");
            return -1;
        }
    }

    // EFFECTS: filters and returns expenses matching the specified month value
    private List<Expense> filterExpensesByMonthValue(int monthValue) {
        return financeManager.listExpenses().stream()
                .filter(expense -> expense.getDate().getMonthValue() == monthValue)
                .collect(Collectors.toList());
    }

    // MODIFIES: this
    // EFFECTS: updates expense display with filtered expenses for specified month
    private void updateExpenseDisplayWithFilter(List<Expense> filteredExpenses, int monthValue) {
        expenseDisplay.setText("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        String monthName = LocalDate.of(2000, monthValue, 1).format(formatter);
        expenseDisplay.append("Expenses for " + monthName + ":\n\n");

        if (filteredExpenses.isEmpty()) {
            expenseDisplay.append("No expenses for this month.");
        } else {
            for (Expense expense : filteredExpenses) {
                expenseDisplay.append(expense.getExpenseDetails() + "\n\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sorts expense by amount in ascending order
    private void sortExpensesByAmount() {
        List<Expense> sortedExpenses = financeManager.listExpenses().stream()
                .sorted((e1, e2) -> Double.compare(e1.getAmount(), e2.getAmount()))
                .collect(Collectors.toList());

        // Update display with sorted expenses
        expenseDisplay.setText("");
        for (Expense expense : sortedExpenses) {
            expenseDisplay.append(expense.getExpenseDetails() + "\n\n");
        }
    }

    private class AddExpenseListener implements ActionListener {
        // MODIFIES: financeManager
        // EFFECTS: prompts user for expense details, adds expense to the manager, and
        // updates display
        @Override
        public void actionPerformed(ActionEvent e) {
            String category = JOptionPane.showInputDialog("Enter Expense Category:");
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter Amount:"));
            String description = JOptionPane.showInputDialog("Enter Description:");
            LocalDate date = LocalDate.parse(JOptionPane.showInputDialog("Enter Date (YYYY-MM-DD):"));

            Expense newExpense = new Expense(category, amount, description, date);
            financeManager.addExpense(newExpense);
            updateExpenseDisplay();
        }
    }
}