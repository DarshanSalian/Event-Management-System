import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Events implements Initializable {

    @FXML
    private Button eventAddbtn;

    @FXML
    private TextArea eventDescription;

    @FXML
    private DatePicker eventEndDate;

    @FXML
    private TextField eventId;

    @FXML
    private TextField eventName;

    @FXML
    private Button eventResetbtn;

    @FXML
    private DatePicker eventStartDate;

    @FXML
    private ChoiceBox<String> eventType;
    private String[] eventTypes = { "Birthday", "Marriage", "Product Launch" };

    @FXML
    private Label event_notify_dash;

    @FXML
    private Label event_supply_dash;

    @FXML
    private Label event_venue_dash;

    @FXML
    void Add(ActionEvent event) {
            // Get the input values
            String name = eventName.getText();
            String type = eventType.getValue();
            String startDate = eventStartDate.getValue() == null ? "" : eventStartDate.getValue().toString();
            String endDate = eventEndDate.getValue() == null ? "" : eventEndDate.getValue().toString();
            String description = eventDescription.getText();
        
            if (name.isEmpty() || type == null || startDate.isEmpty() || endDate.isEmpty() || description.isEmpty()) {
                // Display an alert
                showAlert("Please Enter The Details", "All fields are required.");
            } else {
                // Proceed with adding the event to the database
                // ... (code for database insertion, as mentioned in the previous response)
                 // Database connection parameters
            String url = "jdbc:mysql://localhost:3306/FORM";
            String dbUsername = "root";
            String dbPassword = "12@#HardhiK";
        
            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                // Define the SQL statement for insertion without specifying EventID
                String insertSql = "INSERT INTO Events (EventName, EventType, EventStartDate, EventEndDate, EventDescription) " +
                    "VALUES (?, ?, ?, ?, ?)";
        
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, type);
                    preparedStatement.setString(3, startDate);
                    preparedStatement.setString(4, endDate);
                    preparedStatement.setString(5, description);
        
                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        // Insertion successful, retrieve the auto-generated EventID
                        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int autoGeneratedID = generatedKeys.getInt(1);
                            eventId.setText(String.valueOf(autoGeneratedID));
                        }
        
                        System.out.println("Event added to the database.");
                    } else {    
                        // Insertion failed
                        System.out.println("Failed to add the event to the database.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Database connection error.");
            }
        }
            }
        
           
    

        private void showAlert(String title, String content) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }


    @FXML
    void Reset(ActionEvent event) {
        // Clear all the input fields
        eventName.clear();
        eventType.setValue(null);
        eventStartDate.getEditor().clear();
        eventEndDate.getEditor().clear();
        eventDescription.clear();
    
        // You can also clear the EventID text field if needed
        eventId.clear();
    }

    @FXML
    void event_notify_click(MouseEvent event) {
        // Your event_notify_click logic here
        navigateToNotificationPage();
    }

    @FXML
    void event_supplier_click(MouseEvent event) {
        navigateToSupplierPage();
    }

    @FXML
    void event_venue_click(MouseEvent event) {
        navigateToVenuePage();
    }

    void navigateToVenuePage() {
        try {
            // Load the Venue FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Venue.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Venue page
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Show the Venue page
            stage.show();

            // Close the current Events page
            Stage currentStage = (Stage) event_venue_dash.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void navigateToSupplierPage() {
        try {
            // Load the Venue FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Supplier.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Venue page
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Show the Venue page
            stage.show();

            // Close the current Events page
            Stage currentStage = (Stage) event_supply_dash.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void navigateToNotificationPage() {
        try {
            // Load the Venue FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Notification.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Venue page
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Show the Venue page
            stage.show();

            // Close the current Events page
            Stage currentStage = (Stage)event_notify_dash.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        eventType.getItems().addAll(eventTypes);
    }
}

   