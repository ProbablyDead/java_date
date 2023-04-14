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
  
  /** Текстовое поле фамилии */
  @FXML
  private TextField surname;

  /** Текстовое поле имени */
  @FXML
  private TextField name;

  /** Текстовое поле отчества */
  @FXML
  private TextField patronymic;

  /** Поле выбора даты */
  @FXML
  private DatePicker datePicker;

  /** Кнопка получения разультата */
  @FXML
  private Button getPassButton;

  /** Текстовое поле пути до файла */
  @FXML 
  private TextField pathToImage;
  
  /** Кнопка очищения пути */
  @FXML
  private Button cleatPathButton;

  /** Кнопка выбора файла */
  @FXML
  private Button selectImageButton;

  /** Поле класса с данными о человеке */
  private Person person;

  /** Поле переменной файла */
  private static File image = null; 

  /** Получить файл */
  public static File getImage () {
    return image;
  }

  /** Метод инициализации окна */
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

  /** Проверить поля на заполненность */
  private boolean checkFileds () {
    return !name.getText().trim().isEmpty() && !surname.getText().trim().isEmpty() 
      && !patronymic.getText().trim().isEmpty() && datePicker.getValue() != null;
  }

  /** Метод создания окна ошибки */
  private void createErrorWindow (String message) {
      Alert error = new Alert(AlertType.ERROR);
      error.setHeaderText("Invalid input");
      error.setContentText(message);
      error.showAndWait();
  }

  /** Обработчик нажатия на кнопку результата */
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

  /** Обработчик нажатия на кнопку выбора файла */
  @FXML
  private void selectImage () {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JPEG, GIF files", "*.jpg", "*.gif");
    fileChooser.getExtensionFilters().add(filter);

    image = fileChooser.showOpenDialog(selectImageButton.getScene().getWindow());
    
    pathToImage.setText(image.toString());
  }

  /** Обработчик нажатия на кнопку очистки пути */
  @FXML
  private void clearPath () {
    pathToImage.setText("");
    image = null;
  }
  
}
