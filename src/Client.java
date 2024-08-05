
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class Client {
       @FXML
    private TextArea serverTextArea;

    // Add a method to set the serverTextArea
    public void setServerTextArea(TextArea serverTextArea) {
        this.serverTextArea = serverTextArea;
    }
  /*   public static final Button LoginBtn = null; */
  private ServerController serverController;
    @FXML
    private ToggleButton toggleButton;
 @FXML
    void ToggleLoginbtn(ActionEvent event){
        
    }
    @FXML Button LoginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private SharedData sharedData;
    public void initialize() {
        try {
            // Connect to the server on localhost and port 8089 (Replace with your server's IP and port)
            socket = new Socket("localhost", 8094);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void startClient() {
        // Start the client or perform any other actions here
        // You can add your client logic here
    }

    @FXML
    void LoginBtn(ActionEvent event) {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();
    
        // Access the ServerController instance through the App class
        App app = new App();
    
        TextArea serverTextArea = app.getServerTextArea();
        // Send the username and password to the server's TextArea
        serverTextArea.appendText("Username: " + enteredUsername + "\n");
        serverTextArea.appendText("Password: " + enteredPassword + "\n");
    }
    public void setSharedData(SharedData sharedData) {
        this.sharedData = sharedData;
    }
    
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    // Add a method to send data to the serverTextArea in the ServerController
    public void sendDataToServerTextArea(String data) {
        serverController.appendToServerTextArea(data);
    }
    private void showSuccessMessage(String message) {
        showAlert("Success", message, AlertType.INFORMATION);
    }

    private void showInfoMessage(String message) {
        showAlert("Information", message, AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void navigateToDashboardView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any exceptions during navigation
        }
    }
}
