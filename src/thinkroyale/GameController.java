/*
Pavneet Singh Dhanoa
Student ID: 991546920
JAVA2
 */
package thinkroyale;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     * 
     */
    
    FXMLDocumentController obj = new FXMLDocumentController();
    
    
    @FXML
    private Label lblQues;
    @FXML
    private Label lblName;
    @FXML                
    private Label lblWinning;
    @FXML
    private Button btnLeave;
    @FXML
    private GridPane gPane;
    
    String[] questions = {
    "Who is the founder of the company Tesla?",//Technology
    "Which one was the first search engine over internet?",  
    "Who is the author of Harry Potter series?",//Books
    "Who said these lines in one of the William Shakespeare's play writing, 'Et tu, Brute?' ",
    "Who directed the movie 'The Godfather'?",//Movies 
    "For which movie did Leornado DeCaprio won Oscar Award for Best Actor?",
    "What game feature the terms love, deuce, match and volley?",//Sports
    "Which country won 2019 men Cricket World Cup 2019?",
    "Beaver is the national emblem of which country?", //General Trivia
    "An average human body contains how many pints of blood?"
    };
    String[][] options = {
        {"Elon Musk","Sundar Pichai","Jeff Bezoz","Steve Jobs"}, //Elon Musk
        {"Google","Archie","Yahoo","WAIS"}, //Archie
        {"J. K. Rowling","Jenji Kohan","Stan Lee","Terry Rossio"}, //JK
        {"Marcus Junius Brutus","Mark Antony","Julius Caesar","Titinius"}, //Julius Ceasar
        {"Anthony Russo","Francis Ford Coppola",  "Christopher Nolan", "Rian Johnson"}, //Francis Ford 
        {"Titanic","The Revenant","The Wolf of Wall Street", "The Great Gatsby"}, //The Revenant
        {"Soccer","Badminton","Basketball","Tennis"}, //tennis    
        {"India","England","New Zealand","Australia"}, //England
        {"USA","England","Canada","South Africa"}, //Canada
        {"Ten","Nine","Eight","Twelve"}
    };
    
    int[] correct = {0,1,0,2,1,1,3,1,2,1}; //correct option from the array options
    int quesNum = 0;
    int amount = 0;
    
    @FXML
   private void ExitMethod(ActionEvent e){ //on action for exit button
       
       Alert message = new Alert(Alert.AlertType.CONFIRMATION);
       
       message.setTitle("Leaving Game");
       message.setHeaderText("You have answered " + this.quesNum + " questions and won $" + this.amount);
       message.setContentText("Press OK if you want to Exit");
       
       message.showAndWait().ifPresent(response -> {
           
           //exits the program
           if(response == ButtonType.OK){
               System.exit(0);
           }
       });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           
        lblQues.setText("0 Questions");
        lblWinning.setText("$0");
        
        int x = 5, y = 2;
        Button buttons[][] = new Button[5][5];
        
        String[] topics = {"Technology","Books","Movies","Sports", "General Trivia"};
        int question = 0; //number of questions
        
        for (int i = 0; i < x; i++) {
            
            //setting gridpane
            gPane.add(new Label(topics[i]), i, 0);
            
            for (int j = 0; j < y; j++) {
                
                final int price = (j + 1) * 100;
                final int quest = question;
                
                Button button = new Button("$" + price); //Price wriiten on buttons
                button.setOnAction(e -> {
                    Stage stage = new Stage();
                    GridPane gpPane = new GridPane();
                    gpPane.add(new Label(questions[quest]), 0, 0);
                    gpPane.add(new ComboBox(FXCollections.observableArrayList(options[quest][0], options[quest][1], options[quest][2], options[quest][3])), 0, 1);
                    Button b1 = new Button("Submit Answer");
                    b1.setOnAction(e1 -> {
                        stage.close();
                    });
                    gpPane.add(b1, 0, 2);
                    gpPane.setVgap(40); //vertical gaps bw panes
                    gpPane.setHgap(20); //horizontal gaps
                   
                    Scene scene = new Scene(gpPane, 450, 250);
                    String css = GameController.class.getResource("style.css").toExternalForm();
                    scene.getStylesheets().add(css);
                    stage.setTitle("Here is a question for you! ");
                    stage.setScene(scene);
                    Button va1 = ((Button) e.getSource());
                    stage.setOnHiding(e2 -> {
                        //retrieve the answer from the combobox through index selected
                        int selected = (((ComboBox) gpPane.getChildren().get(1)).getSelectionModel().getSelectedIndex()); 
                        
                        if (selected != -1) {
                            process(quest, selected, price);
                            va1.setDisable(true); //disables button after selection
                        }
                    });
                    stage.showAndWait();
                });
                
                buttons[i][j] = button;
                gPane.add(buttons[i][j], i, j + 1);
                question++;
            } 
        }
    }

    public void process(int question, int selection, int price) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("The answer to your question is: " + (correct[question] == selection ? "Correct" : "Wrong"));
        alert.setContentText("Correct answer is: " + options[question][correct[question]]);
        alert.setTitle("Question's response");
        
        if (correct[question] == selection) {
            this.amount += price;
        } else {
            this.amount -= price;
        }
        alert.showAndWait();
        this.lblQues.setText(++this.quesNum + "");
        this.lblWinning.setText("$" + this.amount);
        
    }

}

