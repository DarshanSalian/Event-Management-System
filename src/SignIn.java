import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignIn {
    @FXML
    void ToggleSignInbtn(ActionEvent event) {

    }

    
    @FXML
    private Button SignIn;

    @FXML
    private PasswordField SignInConfirmPass;

    @FXML
    private PasswordField signInPassword;

    @FXML
    private TextField signInUsername;

    @FXML
    void SignINbtn(ActionEvent event) {
        String inputUsername = signInUsername.getText();
        String inputPassword = signInPassword.getText();
        String inputConfirmPassword = SignInConfirmPass.getText();

        if (inputPassword.equals(inputConfirmPassword)) {
            if (registerUser(inputUsername, inputPassword)) {
                showSuccessMessage("User registered successfully");
                moveToDashboard(event);
            } else {
                showError("User registration failed");
            }
        } else {
            showError("Password and Confirm Password do not match");
        }
    }

    private boolean registerUser(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/Serverside"; // Use the correct database name
        String dbUsername = "root"; // Replace with your database username
        String dbPassword = "12@#HardhiK"; // Replace with your database password

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String insertSql = "INSERT INTO LOGIN (username, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                int affectedRows = preparedStatement.executeUpdate();
                return affectedRows > 0; // Check if the user was inserted successfully
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Registration failed on error
        }
    }
    private void moveToDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) SignIn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

     
}
