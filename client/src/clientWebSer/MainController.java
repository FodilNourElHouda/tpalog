
package clientWebSer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import webservice.Question;
//import webservice.QuestionWebService;
import webservice.QuestionWebService;
import webservice.QuestionWebService_Service;


public class MainController implements Initializable {
    
    QuestionWebService qstWebService=(new QuestionWebService_Service()).getQuestionWebServicePort();
    public MainController(){
       
    }
    
    @FXML
    private Button btn;
    
    @FXML
    private Text text;
    
    @FXML
    private void ajouterQst(){
        List<Question> qsts;
        String ligne ;
        String separateur = ";";
        Stage stage;
        File f;
        boolean ajout ;
        ObservableList<FileChooser.ExtensionFilter> ext ;
        qsts = new ArrayList<Question>();
        FileChooser selecteur = new FileChooser();
        stage = new Stage();
        selecteur.setTitle("ouvrez le fichier ressources");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        ext= selecteur.getExtensionFilters();
        ext.add(extFilter);
        f = selecteur.showOpenDialog(stage);
        
        int ind = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            ligne = br.readLine();
            while (ligne != null) {  
                String[] iteme = ligne.split(separateur);
                Question qst = new Question();
                qst.setEnonce(iteme[0]);
                qst.setReponse1(iteme[1]);
                qst.setReponse2(iteme[2]);
                qst.setReponse3(iteme[3]);
                qst.setReponse4(iteme[4]);
                qst.setIndiceReponceCorrecte(Integer.parseInt(iteme[5]));
                qst.setBareme(Integer.parseInt(iteme[6]));
                qsts.add(qst);
                ind++;
                ligne = br.readLine();
            }
            
           ajout = qstWebService.addQuestions(qsts);
            if (ajout) text.setText(" qst"+ ind+" ajoutee");
            else text.setText("Erreur dans l ajout");
        } catch (Exception e) {
            text.setText("erreur");
        }
    }
    
    @FXML
    private void suivant() throws IOException{
        Window window;
        Scene scene;
        Stage stage;
        FXMLLoader fxml;
        AnchorPane ress;
        
        
        scene = this.btn.getScene();
        window = scene.getWindow();     
        stage = (Stage) window;
        fxml = new FXMLLoader();
        
        ress = (AnchorPane) fxml.load(getClass().getResource("Quiz.fxml"));
        scene = new Scene(ress, 636.0,453.0 );
        stage.setScene(scene);
    }

    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
