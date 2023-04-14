import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PassController implements Initializable {

  /** Поле вывода изображения */
  @FXML
  private ImageView imageView;

  /** Текстовое поле инициалов */
  @FXML
  private TextField initials;

  /** Текстовое поле возраста */
  @FXML
  private TextField age;

  /** Текстовое поле пола */
  @FXML
  private TextField sex;

  /** Поле с данными о человеке */
  private static Person person;

  /** Поле с данными о человеке для контроллера */
  public static void setPerson (Person hum) {
    person = hum;
  }
  
  /** Метод при инициализации окна */
  @Override
  public void initialize (URL location, ResourceBundle resources) {
    if (person.getImage() == null) {
      if (person.getSex() == "Жен")
        imageView.setImage(new Image(new File ("sources/woman.jpg").toURI().toString()));
      else
        imageView.setImage(new Image(new File ("sources/man.jpg").toURI().toString()));
    }
    else {
      imageView.setImage(new Image(person.getImage().toURI().toString()));
    }

    initials.setText(person.getInitial());
    age.setText(person.getAge());
    sex.setText(person.getSex());
    
  }

}
