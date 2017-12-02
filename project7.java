/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import static javafx.geometry.Pos.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import java.util.*;

/**
 *
 * @author awelts
 */
public class project7 extends Application {
    File temp,temp2;
    boolean tester=false;
    LinkedList<String> ll;
            
    @Override
    public void start(Stage primaryStage)throws Exception {
        HBox hbox=new HBox();
        Button btn = new Button();
        Button quit=new Button();
        GridPane gridLeft=new GridPane();
        GridPane gridRight=new GridPane();
        hbox.setStyle("-fx-background-color: #b8d6c8");//gray #adadad
        
        //Left Grid formatting
        gridLeft.setPadding(new Insets(10, 10, 10, 10));
        gridLeft.setVgap(8);
        gridLeft.setHgap(10);
        
        //Dictionary Label(Left Grid)
        Label dictInputLabel=new Label("Dictionary input file:");
        gridLeft.setConstraints(dictInputLabel,0,0);
        
        //Dictionary Text field(Left Grid)
        TextField dictInput=new TextField();
        gridLeft.setConstraints(dictInput,0,1);
        
        //File to be checked label(Left Grid)
        Label FileLabel=new Label("Filename for file to be checked:");
        gridLeft.setConstraints(FileLabel,0,2);
        
        //File to be checked input(Left Grid)
        TextField FileInput=new TextField();
        gridLeft.setConstraints(FileInput,0,3);
        
        //Commit button for file imports(Left Grid)
        btn.setText("Import");
        gridLeft.setConstraints(btn,0,5);
        
        quit.setText("Quit");
        gridLeft.setConstraints(quit,0,8);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
                    public void handle(ActionEvent event){
                      System.exit(0);  
                    }
            });
        
       //Dictionary List Label(Left Grid)
        Label dictListLabel=new Label("Dictionary:");
        gridLeft.setConstraints(dictListLabel,0,6);
        
        //Setting Dictionary List frame (Left Grid)
        ObservableList<String> dictionary=FXCollections.observableArrayList();
        ListView dictList=new ListView(dictionary);
        dictList.setMaxSize(300, 400);
        gridLeft.setConstraints(dictList,0,7);
        
        //Right Grid Formatting
        gridRight.setPadding(new Insets(10, 10, 10, 10));
        gridRight.setVgap(8);
        gridRight.setHgap(10);
        
        //Imported File Text area(Right Grid)
        TextArea inputFileText=new TextArea();
        inputFileText.setMinSize(600,400);
        gridRight.setConstraints(inputFileText,0,1);
        
        //Imported File Label(Right Grid)
        Label inputBox=new Label("Imported File:");
        gridRight.setConstraints(inputBox,0,0);
        
        //Label for stats(Right Grid)
        Label statsLabel=new Label("Words Checked:");
        gridRight.setConstraints(statsLabel,0,2);
        
        //Statistics for spellchecker(Right Grid)
        ListView stats=new ListView();
        stats.setMinSize(200,100);
        gridRight.setConstraints(stats,0,3);
        
        //adding components to grids
        gridLeft.getChildren().addAll(dictInputLabel, dictInput, FileLabel, FileInput, btn,dictListLabel, dictList, quit);
        gridRight.getChildren().addAll(inputBox, inputFileText, statsLabel, stats);
        
        //adding grids to hbox
        hbox.getChildren().addAll(gridLeft, gridRight);
        
        StackPane root = new StackPane();
        root.getChildren().addAll(hbox);
        
        Scene scene = new Scene(root, 900, 650);
        
        
        primaryStage.setTitle("SpellChecker");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Commit button action event (Left Grid)
        btn.setOnAction(new EventHandler<ActionEvent>() {                   
            @Override
            public void handle(ActionEvent event) {
                try{
                temp=new File(dictInput.getText());
                Scanner stdin=new Scanner(temp);
                temp2=new File(FileInput.getText());
                Scanner stdin2=new Scanner(temp2);
                ll=new LinkedList();
                
                if(temp!=null){
                    while(stdin.hasNext()){
                        ll.add(stdin.next());
                    }
                    System.out.println(ll.size());
                    for(int i=0;i<ll.size();i++){
                        dictionary.add(ll.get(i));
                    }
                }
                if(temp2!=null){
                    for(int j=0;stdin2.hasNextLine();j++){
                        inputFileText.appendText(stdin2.nextLine() + "\n");
                    }
                }
                
                
                }catch (Exception e){};
            }
        });
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
