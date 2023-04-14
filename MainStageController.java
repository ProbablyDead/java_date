import java.util.ResourceBundle;
import java.io.File;
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
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

public class MainStageController implements Initializable {
  
  @FXML
  private TextField surname;

  @FXML
  private TextField name;

  @FXML
  private TextField patronymic;

  @FXML
  private DatePicker datePicker;

  @FXML
  private Button getPassButton;

  @FXML 
  private TextField pathToImage;

  @FXML
  private Button cleatPathButton;

  @FXML
  private Button selectImageButton;

  private Person person;
  private static File image = null; 

  public static File getImage () {
    return image;
  }

  @Override
  public void initialize (URL location, ResourceBundle resources) {
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

    try {
      person.format();
    }
    catch (DateTimeException ex) {
      createErrorWindow(ex.getMessage());
      return;
    } 

    person.setImage(image);
    PassController.setPerson(person);
    GUI.showPass(person);
  }

  @FXML
  private void selectImage () {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JPEG, GIF files", "*.jpg", "*.gif");
    fileChooser.getExtensionFilters().add(filter);

    image = fileChooser.showOpenDialog(selectImageButton.getScene().getWindow());
    
    pathToImage.setText(image.toString());
  }

  @FXML
  private void clearPath () {
    pathToImage.setText("");
    image = null;
  }
  
}
