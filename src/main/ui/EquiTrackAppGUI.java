package ui;

import model.Event;
import model.EventLog;
import model.FinanceManager;
import model.GenderEquityTracker;
import model.ResourceManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import org.json.JSONObject;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The main GUI for the EquiTrack application.
 */
public class EquiTrackAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/equitrack.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private ResourceManager resourceManager;
    private FinanceManager financeManager;
    private GenderEquityTracker equityTracker;

    // MODIFIES: this
    // EFFECTS: initializes the application, data managers, and displays the main
    // menu
    public EquiTrackAppGUI() {
        showSplashScreen();

        resourceManager = new ResourceManager();
        financeManager = new FinanceManager();
        equityTracker = new GenderEquityTracker();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeMainMenu();

    
        // Attach a WindowListener for logging
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printEventLog(); // Print the event log when the application closes
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: constructs the main menu with buttons for managing resources,
    // finances, equity, and for saving/loading.
    private void initializeMainMenu() {
        setTitle("EquiTrack Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addTitleLabel();
        addButtonPanel();
        addFooterLabel();

        setVisible(true);
    }

    // Prints all events from the EventLog to the console
    private void printEventLog() {
        System.out.println("Event Log:");
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.getDate() + ": " + event.getDescription());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the title label at the top of the main menu
    private void addTitleLabel() {
        JLabel title = new JLabel("EquiTrack Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        title.setOpaque(true);
        title.setBackground(new Color(245, 183, 177));
        title.setForeground(Color.BLACK);
        add(title, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the main button panel to the center of the main
    // menu
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton resourceButton = createStyledButton("Manage Resources", "Manage your resources here",
                "resources_icon.png");
        JButton financeButton = createStyledButton("Manage Finances", "Track your expenses", "finance_icon.png");
        JButton equityButton = createStyledButton("Manage Equity", "View company gender diversity", "equity_icon.png");
        JButton saveButton = createStyledButton("Save State", "Save your data", "save_icon.png");
        JButton loadButton = createStyledButton("Load State", "Load your data", "load_icon.png");

        resourceButton.addActionListener(e -> openResourcePanel());
        financeButton.addActionListener(e -> openFinancePanel());
        equityButton.addActionListener(e -> openEquityPanel());
        saveButton.addActionListener(e -> saveState());
        loadButton.addActionListener(e -> loadState());

        buttonPanel.add(resourceButton);
        buttonPanel.add(financeButton);
        buttonPanel.add(equityButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds the footer label at the bottom of the main menu
    private void addFooterLabel() {
        JLabel footerLabel = new JLabel("EquiTrack - Keeping Your Data in Track", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(footerLabel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: returns a JButton styled according to the application theme
    private JButton createStyledButton(String text, String tooltip, String iconPath) {
        JButton button = new JButton(text);
        button.setToolTipText(tooltip);
        button.setIcon(new ImageIcon("./data/icons/" + iconPath));
        button.setBackground(new Color(169, 223, 191));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(133, 193, 233), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(214, 234, 248));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(169, 223, 191));
            }
        });
        return button;
    }

    // EFFECTS: displays splash screen at the start of the application
    private void showSplashScreen() {
        JWindow splashScreen = new JWindow();
        splashScreen.setSize(400, 300);
        splashScreen.setLocationRelativeTo(null);

        ImageIcon splashIcon = new ImageIcon("./data/splash_image.jpeg");
        JLabel imageLabel = new JLabel(splashIcon);
        splashScreen.add(imageLabel);

        splashScreen.setVisible(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splashScreen.setVisible(false);
        splashScreen.dispose();
    }

    // Opens various panels
    private void openResourcePanel() {
        new ResourcePanel(resourceManager, this);
    }

    private void openFinancePanel() {
        new FinancePanel(financeManager, this);
    }

    private void openEquityPanel() {
        new EquityPanel(equityTracker, this);
    }

    // MODIFIES: JSON file at JSON_STORE
    // EFFECTS: saves the current state of resourceManager, financeManager, and
    // equityTracker
    private void saveState() {
        try {
            jsonWriter.write(resourceManager, financeManager, equityTracker);
            JOptionPane.showMessageDialog(this, "Application state saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving application state: " + e.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the state of resourceManager, financeManager, and
    // equityTracker from file
    private void loadState() {
        try {
            JSONObject json = jsonReader.read();
            resourceManager = jsonReader.loadResourceManager(json.getJSONObject("resourceManager"));
            financeManager = jsonReader.loadFinanceManager(json.getJSONObject("financeManager"));
            equityTracker = jsonReader.loadGenderEquityTracker(json.getJSONObject("genderEquityTracker"));
            JOptionPane.showMessageDialog(this, "Application state loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading application state: " + e.getMessage());
        }
    }

    
}
