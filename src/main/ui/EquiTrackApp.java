package ui;

import model.Company;
import model.Expense;
import model.Resource;
import model.FinanceManager;
import model.GenderEquityTracker;
import model.ResourceManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Scanner;

import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;

/**
 * The main user interface for the EquiTrack application.
 * This console-based UI allows users to interact with the Resource Manager,
 * Finance Manager, and Gender Equity Tracker.
 */
public class EquiTrackApp {
    private ResourceManager resourceManager;
    private FinanceManager financeManager;
    private GenderEquityTracker equityTracker;
    private Scanner input;

    // EFFECTS: runs the EquiTrack application
    public EquiTrackApp() {
        runEquiTrack();
    }

    // MODIFIES: this
    // EFFECTS: processes user input for the main menu
    private void runEquiTrack() {
        boolean keepRunning = true;
        input = new Scanner(System.in);

        resourceManager = new ResourceManager();
        financeManager = new FinanceManager();
        equityTracker = new GenderEquityTracker();

        while (keepRunning) {
            displayMenu();
            String command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Thank you for using EquiTrack. Goodbye!");
    }

    // EFFECTS: displays menu options to the user
    private void displayMenu() {
        System.out.println("\nWelcome to EquiTrack! Please select an option:");
        System.out.println("\tr -> Manage Resources");
        System.out.println("\tf -> Manage Finances");
        System.out.println("\te -> Manage Gender Equity Tracker");
        System.out.println("\ts -> Save application state to file");
        System.out.println("\tl -> Load application state from file");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "r":
                manageResources();
                break;
            case "f":
                manageFinances();
                break;
            case "e":
                manageGenderEquityTracker();
                break;
            case "s":
                saveState();
                break;
            case "l":
                loadState();
                break;
            default:
                System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: handles user interaction with the resource manager
    private void manageResources() {
        System.out.println("\nResource Manager:");
        System.out.println("\ta -> Add a Resource");
        System.out.println("\tv -> View All Resources");
        System.out.println("\tb -> Back to Main Menu");

        String command = input.next().toLowerCase();

        if (command.equals("a")) {
            addResource();
        } else if (command.equals("v")) {
            viewAllResources();
        } else {
            System.out.println("Returning to the main menu...");
        }
    }

    // EFFECTS: allows the user to add a resource
    private void addResource() {
        input.nextLine(); 
        System.out.println("Enter the title of the resource:");
        String title = input.nextLine();

        System.out.println("Enter the category of the resource:");
        String category = input.nextLine();  

        System.out.println("Enter the description of the resource:");
        String description = input.nextLine();

        System.out.println("Enter the URL of the resource:");
        String url = input.nextLine();

        // Create the resource object and add it to the resource manager
        Resource resource = new Resource(title, category, description, url);
        resourceManager.addResource(resource);
        System.out.println("Resource added successfully!");
    }


    // EFFECTS: displays all resources in the resource manager
    private void viewAllResources() {
        if (resourceManager.getResources().isEmpty()) {
            System.out.println("No resources available.");
        } else {
            for (Resource resource : resourceManager.getResources()) {
                System.out.println(resource.getResourceDetails());
                System.out.println("----------------------------");
            }
        }
    }

    // EFFECTS: handles user interaction with the finance manager
    private void manageFinances() {
        System.out.println("\nFinance Manager:");
        System.out.println("\ta -> Add an Expense");
        System.out.println("\tv -> View All Expenses");
        System.out.println("\tb -> Back to Main Menu");

        String command = input.next().toLowerCase();

        if (command.equals("a")) {
            addExpense();
        } else if (command.equals("v")) {
            viewAllExpenses();
        } else {
            System.out.println("Returning to the main menu...");
        }
    }

    // EFFECTS: allows the user to add an expense
    private void addExpense() {
        input.nextLine(); // Consume leftover newline
        System.out.println("Enter the category of the expense:");
        String category = input.nextLine();

        System.out.println("Enter the amount of the expense:");
        double amount = input.nextDouble();

        input.nextLine(); // Consume leftover newline
        System.out.println("Enter the description of the expense:");
        String description = input.nextLine();

        System.out.println("Enter the date of the expense (YYYY-MM-DD):");
        String dateStr = input.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        Expense expense = new Expense(category, amount, description, date);
        financeManager.addExpense(expense);
        System.out.println("Expense added successfully!");
    }

    // EFFECTS: displays all expenses in the finance manager
    private void viewAllExpenses() {
        if (financeManager.listExpenses().isEmpty()) {
            System.out.println("No expenses available.");
        } else {
            for (Expense expense : financeManager.listExpenses()) {
                System.out.println(expense.getExpenseDetails());
                System.out.println("----------------------------");
            }
        }
    }

    // EFFECTS: handles user interaction with the gender equity tracker
    private void manageGenderEquityTracker() {
        System.out.println("\nGender Equity Tracker:");
        System.out.println("\ta -> Add a Company");
        System.out.println("\tv -> View All Companies");
        System.out.println("\tb -> Back to Main Menu");

        String command = input.next().toLowerCase();

        if (command.equals("a")) {
            addCompany();
        } else if (command.equals("v")) {
            viewAllCompanies();
        } else {
            System.out.println("Returning to the main menu...");
        }
    }

    // EFFECTS: allows the user to add a company
    private void addCompany() {
        input.nextLine();
        System.out.println("Enter the company name:");
        String name = input.nextLine();

        System.out.println("Enter the percentage of women in leadership:");
        double womenInLeadership = input.nextDouble();

        System.out.println("Enter the pay gap percentage:");
        double payGap = input.nextDouble();

        System.out.println("Enter the overall diversity ratio:");
        double diversityRatio = input.nextDouble();

        Company company = new Company(name, womenInLeadership, payGap, diversityRatio);
        equityTracker.addCompany(company);
        System.out.println("Company added successfully!");
    }

    // EFFECTS: displays all companies in the gender equity tracker
    private void viewAllCompanies() {
        if (equityTracker.getCompanies().isEmpty()) {
            System.out.println("No companies available.");
        } else {
            for (Company company : equityTracker.getCompanies()) {
                System.out.println(company.getCompanyDetails());
                System.out.println("----------------------------");
            }
        }
    }

    // Save the application state
    private void saveState() {
        JsonWriter writer = new JsonWriter("./data/equitrack.json");
        try {
            writer.write(resourceManager, financeManager, equityTracker);
            System.out.println("Application state saved!");
        } catch (IOException e) {
            System.out.println("Error saving application state: " + e.getMessage());
        }
    }

    // Load the application state
    private void loadState() {
        JsonReader reader = new JsonReader("./data/equitrack.json");
        try {
            JSONObject json = reader.read();
            resourceManager = reader.loadResourceManager(json.getJSONObject("resourceManager"));
            financeManager = reader.loadFinanceManager(json.getJSONObject("financeManager"));
            equityTracker = reader.loadGenderEquityTracker(json.getJSONObject("genderEquityTracker"));
            System.out.println("Application state loaded!");
        } catch (Exception e) {
            System.out.println("Error loading application state: " + e.getMessage());
        }
    }
}
