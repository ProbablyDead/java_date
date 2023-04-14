import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class GUI extends Application {
  
  @Override
  public void start (Stage primariStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("fxmls/mainScene.fxml"));

    primariStage.setScene(new Scene(root));
    primariStage.setTitle("Fill information about yourself");
    primariStage.setResizable(false);
    primariStage.show();
  }
}
