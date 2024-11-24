# **EquiTrack**

A  platform designed to empower gender minorities by providing essential tools for personal growth, financial management, and advocating for equity in the workplace. Through access to a curated library of resources, personalized finance tracking tools, and a gender equity monitoring system, *EquiTrack* empowers individuals and organizations to take proactive steps towards a more inclusive and equitable future.

## **Key Features**
### 1. 📚 **Resource Library**
- Access empowering articles and opportunities designed to boost career growth, personal development, and well-being.
- Easily categorize resources into topics such as financial literacy, mental health, career development, and more to support your personal and professional journey.

### 2. 💸 **Finance Tracking**
- Log income and expenses and monitor your monthly budget effortlessly.

### 3. 📊 **Gender Equity Tracker**
- Organizations can input key diversity metrics such as gender representation in leadership roles, pay equity, and overall diversity ratios.
- Generate reports to track progress in reducing gender gaps in pay and leadership representation.


## **User Stories**
- *As a user*, I want to browse resources that focus on personal empowerment, career development, and financial well-being.
- *As a user*, I want to be able to log an expense (with a category and amount) to the finance tracker, so I can manage my monthly budget.
- *As a user*, I want to view a list of my logged expenses, categorized by type, to monitor where my money is going.
- *As a user*, I want to be able to log gender diversity data for a company, including women in leadership percentage and pay gap percentage, so I can track progress toward gender equity goals.
- *As a user*, I want to view a report of the logged gender diversity data for a company, so I can assess its current status and improvements.
- *As a user*, I want to be able to save the state of my resource library, expense tracker, and gender equity tracker, so I can retrieve it later.
- *As a user*, I want to be able to load previously saved resources, expenses, and gender equity data, so I can access all the information I had entered in my last session.

## Instructions for End User

- To add a new **Resource** to the Resource Manager, open the **Manage Resources** panel and click the **Add Resource** button. Enter the resource details when prompted.
- To add a new **Expense** to the Finance Manager, open the **Manage Finances** panel and click the **Add Expense** button. Fill in the details as requested.
- To add a new **Company** to the Gender Equity Tracker, open the **Manage Equity** panel and click the **Add Company** button, then provide the company's gender equity details.

### Filter
- **Resource Filter Action**: Within the **Manage Resources** panel, click **Filter Resources** to view resources of a specific category. Enter the desired category when prompted to filter results.
- **Finance Filter Action**: In the **Manage Finances** panel, click the **Filter by Month** button. You’ll be asked to enter a month (1-12), and the display will update to show expenses from the specified month.
- **Equity Filter Action**: Within the **Manage Equity** panel, click **Filter by Diversity** to see companies with a diversity ratio above a specified percentage. Enter the percentage when prompted to filter results.

### Sort/Highlight
- **Resource Highlight Action**: In the **Manage Resources** panel, click **Highlight Important** to emphasize important resources in the display.
- **Finance Sort Action**: In the **Manage Finances** panel, click **Sort by Amount** to view expenses sorted by amount in ascending order.
- **Equity Sort Action**: In the **Manage Equity** panel, click **Sort by Diversity** to order companies based on their diversity ratios in descending order.

### Saving the State of the Application
- To **save the state** of the application, return to the main menu and click the **Save State** button. You will receive a confirmation message if the save is successful.

### Loading the State of the Application
- To **load a previously saved state**, return to the main menu and click the **Load State** button. You will receive a confirmation message when the data is loaded successfully, and all panels will display the loaded information.

#### The **visual component** for this application includes **images** in each panel. Each panel (Resources, Finances, and Equity) displays a relevant  image at the bottom of the panel window. Additionally, there is a **splash screen image** displayed when the application first launches.

## **Why EquiTrack?**
*EquiTrack* is a reflection of my passion for creating solutions that promote fairness, inclusion, and personal development.
*EquiTrack* is not just a tool—it’s a movement. By arming individuals and organizations with the right data and resources, it empowers users to drive meaningful change. Whether you're an individual seeking personal growth or an organization striving for gender equity, *EquiTrack* is designed to meet your needs. Join the movement, **let's close the gap. Together.**

## Phase 4: Task 2

Below is a representative sample of the events logged during the execution of the EquiTrack application. These events showcase basic interactions:

Sat Nov 23 17:40:18 PST 2024: Added resource: financial literacy

Sat Nov 23 17:40:18 PST 2024: Added resource: stem education for women

Sat Nov 23 17:40:18 PST 2024: Created new Expense: rent for $1000.0

Sat Nov 23 17:40:18 PST 2024: Created new Expense: grocery for $100.0

Sat Nov 23 17:40:18 PST 2024: Added company: netflix

Sat Nov 23 17:40:18 PST 2024: Added company: google

## Phase 4: Task 3

The UML class diagram for this project demonstrates a well-structured design with clear separation of responsibilities among classes. However, with more time, I would refactor repeated patterns in the manager classes into an abstract class or interface, to standardize shared behaviors like adding, removing, filtering, and sorting while reducing redundancy. Additionally, I would decouple the `toJson()` methods from the core model classes and move the serialization logic to a dedicated `Serializer` class. This change would simplify the model classes, ensure a cleaner design, and better adhere to the single-responsibility principle. These changes would enhance the overall design by improving modularity, reducing code duplication, and aligning with SOLID principles. While the current design is functional and adheres to project requirements, these refinements would make the system more scalable, maintainable, and easier to extend in future iterations.



