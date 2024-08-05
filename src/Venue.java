import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Venue implements Initializable {

    @FXML
    private Button venueAddbtn;

    @FXML
    private TextField venueCapacity;

    @FXML
    private TextField venueCost;

    @FXML
    private TextField venueID;

    @FXML
    private ImageView venueImage;

    @FXML
    private TextArea venueLocation;

    @FXML
    private Button venueResetbtn1;

    @FXML
    private Label venue_event_dash;

    @FXML
    private Label venue_notification_dash;

    @FXML
    private Label venue_supplier_dash;
    private String imagePath;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Load and set the image during initialization
      
    }





   

    // Initialize the database connection
   

    @FXML
    void Add(ActionEvent event) throws SQLException {
        // Retrieve data from the form
        String capacity = venueCapacity.getText();
        String cost = venueCost.getText();
        String location = venueLocation.getText();
        

        // Validate input fields
        if ( capacity.isEmpty() || cost.isEmpty() || location.isEmpty()|| imagePath==null) {
            // Display an alert or handle validation as needed
            showAlert("Please Enter The Details", "All fields are required.");
        } else {
            // Proceed with adding the venue to the database
            String url = "jdbc:mysql://localhost:3306/FORM";
            String dbUsername = "root";
            String dbPassword = "12@#HardhiK";
        
            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                // Define the SQL statement for insertion without specifying EventID
            String insertQuery = "INSERT INTO Venue (VenueCapacity, VenueLocation, VenueCost, VenueImage) VALUES (?, ?, ?, ?)";

            try {
                // Prepare the SQL statement
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, capacity);
                preparedStatement.setString(2, location);
                preparedStatement.setString(3, cost);
                preparedStatement.setString(4, imagePath);
              

                // Execute the INSERT statement
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Insertion successful, retrieve the auto-generated ID
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int autoGeneratedID = generatedKeys.getInt(1);
                        venueID.setText(String.valueOf(autoGeneratedID));
                        // Optionally, you can do something with the generated ID
                    }

                    System.out.println("Venue added to the database.");
                    // Optionally, you can clear the form fields here.
                    
                } else {
                    System.err.println("Failed to add the venue.");
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error adding the venue: " + e.getMessage());
            }}}
        }
          private void showAlert(String title, String content) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
    

    // ... Your existing code ...


        
    

    private void ClearForm() {
        venueID.clear();
        venueCapacity.clear();
        venueCost.clear();
        venueLocation.clear();
        venueImage.setImage(null); // Clear the image
    }

    @FXML
    void Reset(ActionEvent event) {
        // Your Reset logic here
         ClearForm();
    }

    @FXML
    void venue_event_click(MouseEvent event) {
        navigateToEventPage();
    }

    @FXML
    void venue_notify_click(MouseEvent event) {
        navigateToNotificationPage();
    }

    @FXML
    void venue_supplier_click(MouseEvent event) {
        navigateToSupplierPage();
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
            Stage currentStage = (Stage) venue_event_dash.getScene().getWindow();
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
            Stage currentStage = (Stage) venue_supplier_dash.getScene().getWindow();
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
            Stage currentStage = (Stage)venue_notification_dash.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
private Button eventBrowsebtn;

@FXML
void Browse(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
    File selectedFile = fileChooser.showOpenDialog(null);

    if (selectedFile != null) {
        imagePath = selectedFile.toURI().toString();
        Image image = new Image(imagePath);
        venueImage.setImage(image);
    }
}
   
}
