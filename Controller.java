import java.util.ResourceBundle;
import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

public class Controller implements Initializable {
  
  @FXML
  private TextField surname;

  @FXML
  private TextField name;

  @FXML
  private TextField patronymic;

  @FXML
  private DatePicker datePicker;

  @FXML
  private Button button;

  private Person person;

  @Override
  public void initialize (URL location, ResourceBundle resources) {
    surname.setFocusTraversable(false);
    datePicker.setFocusTraversable(false);
    datePicker.setConverter(new StringConverter<LocalDate>() {
      String pattern = "dd.MM.yyyy";
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

      {
        datePicker.setPromptText(pattern.toLowerCase());
      }

      @Override
      public String toString (LocalDate date) {
        return date != null ? formatter.format(date) : "";
      }

      @Override
      public LocalDate fromString (String string) throws DateTimeException {
        try {
          return (string != null && !string.isEmpty()) ? LocalDate.parse(string, formatter) : null;
        }
        catch (DateTimeException ex) {
          return null;
        }
      }
    });
  }

  private boolean checkFileds () {
    return !name.getText().trim().isEmpty() && !surname.getText().trim().isEmpty() 
      && !patronymic.getText().trim().isEmpty() && datePicker.getValue() != null;
  }

  private void createErrorWindow (String message) {
      Alert error = new Alert(AlertType.ERROR);
      error.setHeaderText("Invalid input");
      error.setContentText(message);
      error.showAndWait();
  }

  @FXML
  private void getPass () {
    if (!checkFileds()) {
      createErrorWindow("Check the entered data");
      return;
    }

    person = new Person (String.format("%s %s %s %s", 
          surname.getText(), name.getText().trim(), patronymic.getText().trim(), datePicker.getValue()));

    String result;

    try {
      result = person.format();
    }
    catch (DateTimeException ex) {
      createErrorWindow(ex.getMessage());
      return;
    }

  }
  
}
