import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SharedData {
    private final StringProperty textData = new SimpleStringProperty();

    public StringProperty textDataProperty() {
        return textData;
    }

    public final String getTextData() {
        return textData.get();
    }

    public final void setTextData(String value) {
        textData.set(value);
    }
}
