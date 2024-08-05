import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class dashboard {

    @FXML
    private ImageView event_dashboard;

    @FXML
    private ImageView notification_dashboard;

    @FXML
    private ImageView suppliers_dashboard;

    @FXML
    private ImageView venue_dashboard;

    @FXML
    void event_dash(MouseEvent event) {
        navigateToEventsPage(event);
    }

    @FXML
    void notification_dash(MouseEvent event) {
        navigateToNotificationPage();
    }

    @FXML
    void suppliers_dash(MouseEvent event) {
         navigateToSupplierPage();
    }

    @FXML
    void venue_dash(MouseEvent event) {
          navigateToVenuePage(event);
    }


private void navigateToEventsPage(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Events.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any exceptions during navigation
        }
    }
private void navigateToVenuePage(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Venue.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any exceptions during navigation
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
            Stage currentStage = (Stage)suppliers_dashboard.getScene().getWindow();
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
            Stage currentStage = (Stage)notification_dashboard.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
