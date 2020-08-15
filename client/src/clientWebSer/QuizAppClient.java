
package clientWebSer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QuizAppClient extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent r = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(r); 
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
     
}
