import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController {
    @FXML
    public TextArea serverTextArea;
    private SharedData sharedData;
    @FXML
    void loginSuccessful(ActionEvent event) {
        serverTextArea.appendText("Login Successful!\n");
    }

    @FXML
    void loginNotSuccessful(ActionEvent event) {
        serverTextArea.appendText("Login Not Successful!\n");
    }
    public void setSharedData(SharedData sharedData) {
        this.sharedData = sharedData;
    }
    public void appendToServerTextArea(String text) {
        if (serverTextArea != null) {
            serverTextArea.appendText(text + "\n");
        }
}
public void setServerTextArea(TextArea serverTextArea) {
    this.serverTextArea = serverTextArea;
}
}