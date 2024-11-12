package ui;

import model.Company;
import model.GenderEquityTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

/**
 * GUI for managing gender equity data.
 */
public class EquityPanel extends JFrame {
    private GenderEquityTracker equityTracker;
    private JTextArea companyDisplay;
    private JLabel imageLabel;

    // MODIFIES: this
    // EFFECTS: initializes components and layout for managing gender equity data
    public EquityPanel(GenderEquityTracker equityTracker, JFrame mainFrame) {
        this.equityTracker = equityTracker;
        setupFrame();
        addButtonPanel(mainFrame);
        addMainComponents();
    }

    // MODIFIES: this
    // EFFECTS: sets up the main frame properties
    private void setupFrame() {
        setTitle("Gender Equity Tracker");
        setSize(400, 500);
        setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds button panel with add, filter, sort, and back
    // buttons
    private void addButtonPanel(JFrame mainFrame) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addCompanyButton = new JButton("Add Company");
        addCompanyButton.addActionListener(new AddCompanyListener());
        buttonPanel.add(addCompanyButton);

        JButton filterButton = new JButton("Filter by Diversity Ratio");
        filterButton.addActionListener(e -> filterCompaniesByDiversity());
        buttonPanel.add(filterButton);

        JButton sortButton = new JButton("Sort by Diversity Ratio");
        sortButton.addActionListener(e -> sortCompaniesByDiversity());
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
        companyDisplay = new JTextArea();
        companyDisplay.setEditable(false);
        updateCompanyDisplay();

        imageLabel = new JLabel(new ImageIcon("./data/equity_image.jpeg"));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(new JScrollPane(companyDisplay), BorderLayout.CENTER);
        add(imageLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: displays company information in the text area
    private void updateCompanyDisplay() {
        companyDisplay.setText("");
        for (Company company : equityTracker.getCompanies()) {
            companyDisplay.append(company.getCompanyDetails() + "\n\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: filters companies by a specified diversity ratio
    private void filterCompaniesByDiversity() {
        String input = JOptionPane.showInputDialog("Enter minimum diversity ratio to filter companies:");
        try {
            double minRatio = Double.parseDouble(input);
            companyDisplay.setText("");
            for (Company company : equityTracker.getCompanies()) {
                if (company.getDiversityRatio() >= minRatio) {
                    companyDisplay.append(company.getCompanyDetails() + "\n\n");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    // MODIFIES: this
    // EFFECTS: sorts companies by a specified diversity ratio
    private void sortCompaniesByDiversity() {
        List<Company> companies = equityTracker.getCompanies();
        companies.sort(Comparator.comparingDouble(Company::getDiversityRatio).reversed());
        updateCompanyDisplay();
    }

    private class AddCompanyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Enter Company Name:");
            double womenInLeadership = Double
                    .parseDouble(JOptionPane.showInputDialog("Enter % of Women in Leadership:"));
            double payGap = Double.parseDouble(JOptionPane.showInputDialog("Enter Pay Gap %:"));
            double diversityRatio = Double.parseDouble(JOptionPane.showInputDialog("Enter Diversity Ratio %:"));

            Company newCompany = new Company(name, womenInLeadership, payGap, diversityRatio);
            equityTracker.addCompany(newCompany);
            updateCompanyDisplay();
        }
    }
}
