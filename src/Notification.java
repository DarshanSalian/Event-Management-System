import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Notification {

    @FXML
    private Button DELETE;

    @FXML
    private Button RETREIVE;

    @FXML
    private TextArea notification_container;

    @FXML
    private TextField notifyCustomerID;

    @FXML
    private Label notify_event_dash;

    @FXML
    private Label notify_supplier_dash;

    @FXML
    private Label notify_venue_dash;

    @FXML
    private TextArea retriveContainer;

    @FXML
    void notifyDeletebtn(ActionEvent event) {

    }

    
    @FXML
    
    void notifyRetreivebtn(ActionEvent event) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/FORM";
        String dbUsername = "root";
        String dbPassword = "12@#HardhiK";
        int eventID = Integer.parseInt(notifyCustomerID.getText());
    
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sqlEvent = "SELECT * FROM EVENTS WHERE EventID = ?";
            try (PreparedStatement preparedStatementEvent = connection.prepareStatement(sqlEvent)) {
                preparedStatementEvent.setInt(1, eventID);
                ResultSet eventResult = preparedStatementEvent.executeQuery();
    
                String eventName = null;
                String eventType = null;
                String startDate = null;
                String endDate = null;
                String eventDescription=null;
    
                if (eventResult.next()) {
                    eventName = eventResult.getString("EventName");
                    eventType = eventResult.getString("EventType");
                    startDate = eventResult.getString("EventStartDate");
                    endDate = eventResult.getString("EventEndDate");
                    eventDescription=eventResult.getString("EventDescription");
                }
    
                StringBuilder eventData = new StringBuilder("Event Description: " + eventDescription + "\n");
    
                // Check for matching Supplier data
                String sqlSupplier = "SELECT * FROM SUPPLIER WHERE SupplierID = ?";
                try (PreparedStatement preparedStatementSupplier = connection.prepareStatement(sqlSupplier)) {
                    preparedStatementSupplier.setInt(1, eventID);
    
                    ResultSet supplierResult = preparedStatementSupplier.executeQuery();
    
                    String supplierName = null;
                    String contactInfo = null;
                    String products = null;
    
                    if (supplierResult.next()) {
                        supplierName = supplierResult.getString("SupplierName");
                        contactInfo = supplierResult.getString("ContactInfo");
                        products = supplierResult.getString("Products");
    
                        eventData.append("SupplierName: ").append(supplierName).append("\n");
                        eventData.append("ContactInfo: ").append(contactInfo).append("\n");
                        eventData.append("Products: ").append(products).append("\n");
                    }
                }
    
                // Now check for matching Venue data
                String sqlVenue = "SELECT * FROM VENUE WHERE VenueID = ?";
                try (PreparedStatement preparedStatementVenue = connection.prepareStatement(sqlVenue)) {
                    preparedStatementVenue.setInt(1, eventID);
    
                    ResultSet venueResult = preparedStatementVenue.executeQuery();
    
                    if (venueResult.next()) {
                        String venueName = venueResult.getString("VenueLocation");
    
                        eventData.append("VenueName: ").append(venueName);
    
                        String message = "You have successfully added the event " + eventName + " " + eventType +
                                " which starts from " + startDate + " and ends at " + endDate +
                                " in " + venueName;
    
                        notification_container.setText(message);
                    }
                }
    
                retriveContainer.setText(eventData.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error retrieving data: " + e.getMessage());
            }
        }
    }
    
        
    
    
    
    

    @FXML
    void notify_event_click(MouseEvent event) {
  navigateToEventPage();
    }

    @FXML
    void notify_supplier_click(MouseEvent event) {
navigateToSupplierPage();
    }

    @FXML
    void notify_venue_click(MouseEvent event) {
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
            Stage currentStage = (Stage)notify_venue_dash.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void navigateToEventPage() {
        try {
            // Load the Venue FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Events.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Venue page
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Show the Venue page
            stage.show();

            // Close the current Events page
            Stage currentStage = (Stage)notify_event_dash.getScene().getWindow();
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
            Stage currentStage = (Stage)notify_supplier_dash.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
