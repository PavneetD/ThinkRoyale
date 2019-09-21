/*
Pavneet Singh Dhanoa
Student ID: 991546920
JAVA2
 */
package thinkroyale;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author DELL
 */
public class FXMLDocumentController implements Initializable {
    
    
    
    @FXML
    private Label lblThink;
    @FXML
    private Label lblRoyale;
    @FXML
    public Label lblName;
    @FXML
    private TextField txtPlay;
    @FXML
    private Button btnPlay;
    
    @FXML
    public void buttonPlay(ActionEvent eve){
        
        String out = txtPlay.getText();
        if(!out.isEmpty()){
         
           Stage stage = (Stage)((Button) eve.getSource()).getScene().getWindow(); //creating a new stage 
           Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("game.fxml"));
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
            } catch (IOException ex) {
               
                Alert alert = new Alert(AlertType.ERROR);
            
                alert.setTitle("OOPS!");
                alert.setHeaderText("SORRY! Could not start your game :'(");
                alert.setContentText("Please TRY AGAIN LATER");
                alert.showAndWait();
                
                //for console output
                System.out.println("Error occured while starting the game. Sorry!");
            }
          
           
        }else {
            
            Alert alert = new Alert(AlertType.ERROR);
            
            alert.setTitle("OOPS!");
            alert.setHeaderText("Name is not entered.");
            alert.setContentText("Please enter your name.");
            alert.showAndWait();
        }
        
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
