import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

  @FXML
  private DatePicker datePicker;
  
  @FXML
  private TextField textField;

  @FXML
  private Button button;

  @Override 
  public void initialize (URL location, ResourceBundle resources) {
  }

  @FXML
  public void getPass () {
    System.out.println("akljfd");
  }
  
}
