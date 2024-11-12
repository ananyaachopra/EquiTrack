package ui;

import model.ResourceManager;
import model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for managing resources.
 */
public class ResourcePanel extends JFrame {
    private ResourceManager resourceManager;
    private JTextArea resourceDisplay;
    private JLabel imageLabel;

    // MODIFIES: this
    // EFFECTS: initializes components and layout for managing resources
    public ResourcePanel(ResourceManager resourceManager, JFrame mainFrame) {
        this.resourceManager = resourceManager;

        setTitle("Resource Manager");
        setSize(400, 500);
        setLayout(new BorderLayout());

        add(createButtonPanel(mainFrame), BorderLayout.NORTH);
        initializeResourceDisplay();
        initializeImageLabel();

        setVisible(true);
    }

    // EFFECTS: creates and returns a JPanel with control buttons
    private JPanel createButtonPanel(JFrame mainFrame) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addResourceButton = new JButton("Add Resource");
        addResourceButton.addActionListener(new AddResourceListener());
        buttonPanel.add(addResourceButton);

        JButton filterButton = new JButton("Filter by Category");
        filterButton.addActionListener(e -> filterResourcesByCategory());
        buttonPanel.add(filterButton);

        JButton highlightButton = new JButton("Highlight Important");
        highlightButton.addActionListener(e -> highlightImportantResources());
        buttonPanel.add(highlightButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
            mainFrame.setVisible(true);
        });
        buttonPanel.add(backButton);

        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: initializes the resource display area
    private void initializeResourceDisplay() {
        resourceDisplay = new JTextArea();
        resourceDisplay.setEditable(false);
        updateResourceDisplay();
        add(new JScrollPane(resourceDisplay), BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds the image label at the bottom of the panel
    private void initializeImageLabel() {
        imageLabel = new JLabel(new ImageIcon("./data/resource_image.jpeg"));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: displays resource information in the text area
    private void updateResourceDisplay() {
        resourceDisplay.setText("");
        for (Resource resource : resourceManager.getResources()) {
            resourceDisplay.append(resource.getResourceDetails() + "\n\n");
        }
    }

    /// MODIFIES: this
    // EFFECTS: filters resources by a specified category
    private void filterResourcesByCategory() {
        String category = JOptionPane.showInputDialog("Enter category to filter resources:");
        resourceDisplay.setText("");
        for (Resource resource : resourceManager.getResources()) {
            if (resource.getCategory().equalsIgnoreCase(category)) {
                resourceDisplay.append(resource.getResourceDetails() + "\n\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: highlights important resources
    private void highlightImportantResources() {
        String keyword = JOptionPane.showInputDialog("Enter keyword to highlight important resources:");
        resourceDisplay.setText("");
        for (Resource resource : resourceManager.getResources()) {
            if (resource.getTitle().contains(keyword) || resource.getCategory().contains(keyword)) {
                resourceDisplay.append("**IMPORTANT** " + resource.getResourceDetails() + "\n\n");
            } else {
                resourceDisplay.append(resource.getResourceDetails() + "\n\n");
            }
        }
    }

    private class AddResourceListener implements ActionListener {
        // MODIFIES: resourceManager
        // EFFECTS: prompts user for resource details, adds resource to the manager, and
        // updates display
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = JOptionPane.showInputDialog("Enter Resource Title:");
            String category = JOptionPane.showInputDialog("Enter Category:");
            String description = JOptionPane.showInputDialog("Enter Description:");
            String url = JOptionPane.showInputDialog("Enter URL:");

            Resource newResource = new Resource(title, category, description, url);
            resourceManager.addResource(newResource);
            updateResourceDisplay();
        }
    }
}
