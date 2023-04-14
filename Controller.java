import java.util.ResourceBundle;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
      public LocalDate fromString (String string) {
        return (string != null && !string.isEmpty()) ? LocalDate.parse(string, formatter) : null;
      }
    });
  }

  @FXML
  private void getPass () {
    Person person = new Person();

    person.setName(name.getText());
    person.setSurname(surname.getText());
    person.setPatronymic(patronymic.getText());
    person.setBDDate(datePicker.getValue().toString());

    System.out.println(person.format());

  }
  
}
