
package clientWebSer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import webservice.*;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
public class QuizController implements Initializable {
    QuestionWebService qstWebService = (new QuestionWebService_Service()).getQuestionWebServicePort();
    public QuizController(){
       
        
    }
    @FXML
    private Label question_id;
    
    @FXML
    private Label question;
    
  
    
    @FXML
    private Label rep1;
    
    @FXML
    private Label rep2;
    
    @FXML
    private Label rep3;
    
    @FXML
    private Label rep4;
    
    @FXML
    private Button btn;
    
    
    
    @FXML
    private int bareme;
    @FXML
    private Label note;
    
   
    private void suivant(){
        Question q = qstWebService.getQuestionAlea();
        this.question.setText(q.getEnonce());
        this.question_id.setText(Integer.toString(q.getId()));
        this.rep1.setText(q.getReponse1());
        this.rep2.setText(q.getReponse2());
        this.rep3.setText(q.getReponse3());
        this.rep4.setText(q.getReponse4());
        this.note.setText("?/"+q.getBareme());
        this.bareme = q.getBareme();
    }
    
    
    @FXML
    private void reponse1(){
     int id = Integer.parseInt(this.question_id.getText());
        int mark = qstWebService.evaluateQuestion(id, 1);
        this.note.setText(Integer.toString(mark)+"/"+Integer.toString(this.bareme));
    }
    
    @FXML
    private void reponse2(){
        int id = Integer.parseInt(this.question_id.getText());
        int mark = qstWebService.evaluateQuestion(id, 2);
        this.note.setText(Integer.toString(mark)+"/"+Integer.toString(this.bareme));
    }
    
    @FXML
    private void reponse3(){
     int id = Integer.parseInt(this.question_id.getText());
        int mark = qstWebService.evaluateQuestion(id, 3);
        this.note.setText(Integer.toString(mark)+"/"+Integer.toString(this.bareme));
    }
    
    @FXML
    private void reponse4(){
     int id = Integer.parseInt(this.question_id.getText());
        int mark = qstWebService.evaluateQuestion(id, 4);
        this.note.setText(Integer.toString(mark)+"/"+Integer.toString(this.bareme));}
    
    @FXML
    private void retour() throws FileNotFoundException, IOException{
        Scene scene = btn.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        FXMLLoader loader = new FXMLLoader();
        VBox root =(VBox) loader.load(getClass().getResource("main.fxml"));
        scene = new Scene(root, 636.0,453.0 );
        stage.setScene(scene);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.suivant();
    }    
    
}
