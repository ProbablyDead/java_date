import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * GUI класс
 * */
public class GUI extends Application {
  
  /**
   * Метод запуска приложения 
   * */
  @Override
  public void start (Stage primariStage) throws Exception {
    primariStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("sources/mainScene.fxml"))));
    primariStage.setTitle("Fill information about yourself");
    primariStage.setResizable(false);
    primariStage.show();
  } 
  
  /**
   * Метод вывода на экран результирующего окна
   * @param person данные о человеке
  */
  public static void showPass (Person person) {
    try {
      Stage stage = new Stage();
      stage.setTitle("Pass");
      stage.setResizable(false);
      stage.setScene(new Scene(FXMLLoader.load(GUI.class.getResource("sources/pass.fxml"))));
      stage.show();
    }
    catch (IOException ex) {
      System.out.println("Error");
      return;
    }
    
  }
}
