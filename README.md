Here’s the `README.md` in markdown format for you to copy and paste into VSCode:

```markdown
# Fees Management System

This is a JavaFX application that manages fees in the TravelExperts database using CRUD (Create, Read, Update, Delete) operations. The project connects to a MySQL database and allows users to view, add, edit, and delete fee records.

## Project Structure

```
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   ├── fee
│       │   │   ├── Fee.java
│       │   │   ├── FeeDB.java
│       │   │   ├── FeeDialogController.java
│       │   │   ├── MainApp.java
│       │   │   ├── PrimaryController.java
│       │   │   └── Validator.java
│       └── resources
│           └── fee
│               ├── feeDialog.fxml
│               └── primary.fxml
├── target
└── travelexperts.sql
```

## Features
- View all fees in a table.
- Add new fee records.
- Edit existing fee records.
- Delete fee records.
- Input validation for form fields and handling of database exceptions.

## Prerequisites
- **Java 8+**
- **JavaFX** installed (for the GUI interface).
- **MySQL** database (with the `travelexperts.sql` schema imported).
- **Maven** (to handle dependencies).

## Setup

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd fees-management
   ```

2. **Set up MySQL Database**:
   - Create a database named `travelexperts`.
   - Run the `travelexperts.sql` file to set up the `fees` table:
     ```bash
     mysql -u root -p travelexperts < travelexperts.sql
     ```

3. **Update Database Credentials**:
   - In the `FeeDB.java` file, update the database credentials (`DB_URL`, `USER`, and `PASSWORD`) to match your local MySQL setup:
     ```java
     private static final String DB_URL = "jdbc:mysql://localhost:3306/travelexperts";
     private static final String USER = "root";
     private static final String PASSWORD = "your_password";
     ```

4. **Build the Project**:
   - Use Maven to build the project. In the root directory, run:
     ```bash
     mvn clean install
     ```

5. **Run the Application**:
   - To run the JavaFX application, use the following Maven command:
     ```bash
     mvn javafx:run
     ```

6. **Using the Application**:
   - **Add Fee**: Click the "Add" button to open the fee dialog, fill in the details, and click "OK".
   - **Edit Fee**: Select a fee in the table, click "Edit", modify the fields, and save.
   - **Delete Fee**: Select a fee in the table and click "Delete" to remove it from the database.

## Project Files

- **`Fee.java`**: Represents the `Fee` entity corresponding to the `fees` table in the database.
- **`FeeDB.java`**: Handles all the database operations (CRUD) for the `fees` table.
- **`FeeDialogController.java`**: Controller for the `feeDialog.fxml`, used for adding and editing fees.
- **`MainApp.java`**: The main application class that sets up the UI and handles interactions.
- **`Validator.java`**: Contains validation methods to ensure proper input handling.
- **`feeDialog.fxml`**: FXML layout for the fee add/edit dialog.
- **`primary.fxml`**: FXML layout for the main application window.

## Troubleshooting

1. **Database Connection Issues**:
   - Ensure that MySQL is running and that the database credentials in `FeeDB.java` are correct.
   
2. **JavaFX Issues**:
   - Ensure that JavaFX is installed and correctly linked in your `pom.xml`.

3. **Maven Build Issues**:
   - If you encounter issues while building with Maven, ensure that your `pom.xml` dependencies are correct and that Maven is up to date.

## License

This project is for educational purposes and not intended for commercial use.
```