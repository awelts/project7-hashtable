/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
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
import javafx.scene.control.ComboBox;


/**
 *
 * @author awelts
 */
public class project7 extends Application {
    File temp,temp2;
    boolean testIn1=false;
    boolean testIn2=false;
    LinkedList<String> dictLL;
    String s1,s2;
    ComboBox<String> cb,cb2;
            
    @Override
    public void start(Stage primaryStage)throws Exception {
        HBox hbox=new HBox();
        HBox init=new HBox();
        Button commit = new Button();
        Button quit=new Button();
        Button quit2=new Button();
        GridPane gridLeft=new GridPane();
        GridPane gridRight=new GridPane();
        GridPane begin=new GridPane();
        
        
        //File Filter and finder
        File[] files;
        files=finder("/home/awelts/NetBeansProjects/project7-hashtables/");
        LinkedList filesLL=new LinkedList();
        
        for(int i=0;i<files.length;i++){
            filesLL.add(files[i].getName());
        }
                
        //Start screen format
        init.setStyle("-fx-background-color: #b8d6c8");
        init.setAlignment(CENTER);
        begin.setPadding(new Insets(10,10,10,10));
        begin.setVgap(8);
        begin.setHgap(10);
        
        //second screen formatting
        hbox.setStyle("-fx-background-color: #b8d6c8");//gray #adadad
        
        //Left Grid formatting
        gridLeft.setPadding(new Insets(10, 10, 10, 10));
        gridLeft.setVgap(8);
        gridLeft.setHgap(10);
        
        //ComboBox Dictionary..pls let this work
        cb=new ComboBox<>();
        cb.getItems().addAll(filesLL);
        begin.setConstraints(cb,0,1);
        cb.setEditable(true);
        
        cb2=new ComboBox<>();
        cb2.getItems().addAll(filesLL);
        begin.setConstraints(cb2,0,3);
        cb2.setEditable(true);
        
        //Dictionary Label(Scene 1)
        Label dictInputLabel=new Label("Dictionary input file:");
        begin.setConstraints(dictInputLabel,0,0);
        
        //Dictionary Text field(Scene 1)
        TextField dictInput=new TextField();
        begin.setConstraints(dictInput,0,1);
        
        //File to be checked label(Scene 1)
        Label FileLabel=new Label("Filename for file to be checked:");
        begin.setConstraints(FileLabel,0,2);
        
        //File to be checked input(Scene 1)
        TextField FileInput=new TextField();
        begin.setConstraints(FileInput,0,3);
        
        //Commit button for file imports(Scene 1)
        commit.setText("Import");
        begin.setConstraints(commit,0,5);
        
        //Quit Button (Second Screen)
        quit.setText("Quit");
        gridLeft.setConstraints(quit,0,2);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
                    public void handle(ActionEvent event){
                      System.exit(0);  
                    }
            });
        
        //Quit button (beginning screen)
        quit2.setText("Quit");
        begin.setConstraints(quit2,1,5);
        quit2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
                    public void handle(ActionEvent event){
                      System.exit(0);  
                    }
            });
        
        //Dictionary List Label(Left Grid)
        Label dictListLabel=new Label("Dictionary:");
        gridLeft.setConstraints(dictListLabel,0,0);
        
        //Setting Dictionary List frame (Left Grid)
        ObservableList<String> dictionary=FXCollections.observableArrayList();
        ListView dictList=new ListView(dictionary);
        dictList.setMinSize(200, 350);
        gridLeft.setConstraints(dictList,0,1);
        
        //Right Grid Formatting
        gridRight.setPadding(new Insets(10, 10, 10, 10));
        gridRight.setVgap(8);
        gridRight.setHgap(10);
        
        //Imported File Text area(Right Grid)
        TextArea inputFileText=new TextArea();
        inputFileText.setMinSize(600,550);
        gridRight.setConstraints(inputFileText,0,1);
        
        //Imported File Label(Right Grid)
        Label inputBox=new Label("Imported File:");
        gridRight.setConstraints(inputBox,0,0);
        
        //Label for stats(Right Grid)
        Label statsLabel=new Label("Words Mispelled:");
        gridLeft.setConstraints(statsLabel,0,2);
        
        //Statistics for spellchecker(Left Grid)
        ObservableList<String> statList=FXCollections.observableArrayList();
        ListView stats=new ListView(statList);
        stats.setMinSize(200,100);
        gridLeft.setConstraints(stats,0,3);
        
        //adding components to grids
        gridLeft.getChildren().addAll(dictListLabel, dictList,statsLabel,stats );
        gridRight.getChildren().addAll(inputBox, inputFileText,  quit);
        begin.getChildren().addAll(dictInputLabel, cb, FileLabel, cb2, commit, quit2);
        
        //adding grids to hbox
        hbox.getChildren().addAll(gridLeft, gridRight);
        init.getChildren().addAll(begin);
        
        StackPane root = new StackPane();
        StackPane root2=new StackPane();
        
        root.getChildren().addAll(init);
        root2.getChildren().addAll(hbox);
        
        Scene scene1 = new Scene(root, 400, 200);
        Scene scene2 = new Scene(root2, 900, 650);
        
        primaryStage.setTitle("SpellChecker");
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        //Commit button action event (Left Grid)
        commit.setOnAction(new EventHandler<ActionEvent>() {                   
            @Override
            public void handle(ActionEvent event) {
                try{
                    primaryStage.setScene(scene2);
                    testIn1=cb.hasProperties();
                    testIn2=cb2.hasProperties();
                    System.out.println(testIn1+" "+testIn2);
                    dictLL=new LinkedList();
                                        
                    if(testIn1==true){
                        temp=new File(cb.getValue());
                        Scanner stdin=new Scanner(temp);
                        while(stdin.hasNext()){
                            dictLL.add(stdin.next());
                        }
                    
                        for(int i=0;i<dictLL.size();i++){
                            dictionary.add(dictLL.get(i));
                            
                        }
                    }
                    
                    if(testIn2==true){
                        temp2=new File(cb2.getValue());
                        Scanner stdin2=new Scanner(temp2);
                        for(int j=0;stdin2.hasNextLine();j++){
                            inputFileText.appendText(stdin2.nextLine() + "\n");
                        }
                    }                
                
                }catch (Exception e)
                {
                System.out.println(e);
                };
                
            }
        });
    }
    
    public File[] finder( String dirName){
       File dir = new File(dirName);

       return dir.listFiles(new FilenameFilter() { 
                public boolean accept(File dir, String filename)
                    { return filename.endsWith(".txt"); }
        } );

    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
