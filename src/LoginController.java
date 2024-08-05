import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class LoginController {
    private Stage stage;
    private Parent root;
    @FXML
    private Button LoginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private Button signinToggle;
    @FXML
    private Label validcheck;
    @FXML
    private TextField username;
    Socket socket;
    BufferedReader br;
    PrintWriter prnt;
    ObjectInputStream obread;
    InputStream input;
    @FXML
    void loginButtonAction(ActionEvent event) {
        try {
            
            if (username.getText().equals("") && password.getText().equals("")) {
                validcheck.setText("Please fill both feilds");
            } else {
                //System.out.println(event);
                validcheck.setText("");
                socket=new Socket("127.0.0.1",8000); //connect here
                System.out.println("connected to server");
                //br=new BufferedReader(new InputStreamReader(socket.getInputStream())); //read form socket
                prnt=new PrintWriter(socket.getOutputStream()); //write to socket
                input=socket.getInputStream();
                obread=new ObjectInputStream(input);
                String username1=username.getText().toString();
                String pass1=password.getText().toString();
                String fina=username1+" "+pass1;
                username.setText("");
                password.setText("");
                prnt.println(fina);
                prnt.flush();
                String res=(String) obread.readObject();
            
                // System.out.println(res);
                // System.out.println(ip);
                if (res.equals("success")) {
                    try {
                        // Load the Venue FXML file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                Parent root = loader.load();
                Stage dashboardStage = new Stage();
                dashboardStage.setScene(new Scene(root));
                dashboardStage.show();

                // Close the current login window
                Stage currentStage = (Stage) LoginBtn.getScene().getWindow();
                currentStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{
                    validcheck.setText("Invalid credentials");
                    
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

   
    public static void main(String[] args) {

        //logincontroller ll=new logincontroller();
    }
    

    @FXML
    void siginButtonAction(ActionEvent event) {
try {
            // Load the Venue FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Venue page
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Show the Venue page
            stage.show();

            // Close the current Events page
            Stage currentStage = (Stage)signinToggle.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


