import java.awt.Color;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

public class project7 extends Application {
    File temp,temp2;
    boolean testIn1=false;
    boolean testIn2=false;
    static LinkedList<String> dictLL, statsLL;
    String s1,s2;
	static ComboBox<String> cb,cb2;
	static HashTable ht;
	static File hashDict;
	static File testFile;
	static Vector<String> wordList;
	static int wrong=0;
	static ObservableList<String> statList;
	static ObservableList<String> dictionary;
	static ListView stats;
            
    @Override
    public void start(Stage primaryStage)throws Exception {
        HBox hbox=new HBox();
        HBox init=new HBox();
        Button commit = new Button();
        Button quit=new Button();
        Button quit2=new Button();
        Button check=new Button();
        Button back=new Button();
        GridPane gridLeft=new GridPane();
        GridPane gridRight=new GridPane();
        GridPane begin=new GridPane();
        StackPane pane1 = new StackPane();
        StackPane pane2=new StackPane();
        Scene scene1 = new Scene(pane1, 400, 200);
        Scene scene2 = new Scene(pane2, 900, 650);
        
        
        //File Filter and finder
        File[] files;
        files=finder("/home/awelts/Documents/LinearDataStructures Projects/project7-hashtables/");
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
        hbox.setStyle("-fx-background-color: #b8d6c8");//gray:#adadad lt.blue:#b8d6c8 blue:#359aff
        
        //Left Grid formatting
        gridLeft.setPadding(new Insets(10, 10, 10, 10));
        gridLeft.setVgap(8);
        gridLeft.setHgap(10);
        
        //ComboBox Dictionary..pls let this work
        cb=new ComboBox<>();
        cb.getItems().addAll(filesLL);
        begin.setConstraints(cb,0,1);
        cb.setEditable(true);
        //cb.setValue("SpellCheckDictionary.txt");
	
        cb2=new ComboBox<>();
        cb2.getItems().addAll(filesLL);
        begin.setConstraints(cb2,0,3);
        cb2.setEditable(true);
        
        //Dictionary Label(Scene 1)
        Label dictInputLabel=new Label("Dictionary input file:");
        begin.setConstraints(dictInputLabel,0,0);
                
        //File to be checked label(Scene 1)
        Label FileLabel=new Label("Filename for file to be checked:");
        begin.setConstraints(FileLabel,0,2);
  
        //Commit button for file imports(Scene 1)
        commit.setText("Import");
        begin.setConstraints(commit,0,5);
        
        //Quit Button (Second Screen)
        quit.setText("Quit");
        gridLeft.setConstraints(quit,0,2);
        quit.setTranslateX(150);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
                    public void handle(ActionEvent event){
                      System.exit(0);  
                    }
            });
        
        //Quit button (beginning screen)
        quit2.setText("Quit");
        begin.setConstraints(quit2,0,5);
        quit2.setTranslateX(70);
        quit2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
                    public void handle(ActionEvent event){
                      System.exit(0);  
                    }
            });
        
        //Dictionary List Label(Left Grid)
		Label dictListLabel=new Label("Dictionary: ");
        gridLeft.setConstraints(dictListLabel,0,0);
        
        //Setting Dictionary List frame (Left Grid)
        dictionary=FXCollections.observableArrayList();
        ListView dictList=new ListView(dictionary);
        dictList.setMinSize(200, 350);
        gridLeft.setConstraints(dictList,0,1);
        
        //Right Grid Formatting
        gridRight.setPadding(new Insets(10, 10, 10, 10));
        gridRight.setVgap(8);
        gridRight.setHgap(10);
        
        //Imported File Text area(Right Grid)
        TextArea inputFileText=new TextArea();
        inputFileText.setMinSize(600,580);
		gridRight.setConstraints(inputFileText,0,1);
		inputFileText.setWrapText(true);
 	        
        //Imported File Label(Right Grid)
        Label inputBox=new Label("Imported File:");
        gridRight.setConstraints(inputBox,0,0);
        
        //Label for stats(Right Grid)
        Label statsLabel=new Label("Words Mispelled:");
        gridLeft.setConstraints(statsLabel,0,2);
        
        //Statistics for spellchecker(Left Grid)
    	statList=FXCollections.observableArrayList();
        stats=new ListView(statList);
        stats.setMinSize(200,100);
		gridLeft.setConstraints(stats,0,3);
		

        //Checker button for second scene
        check.setText("Check for errors");
        gridRight.setConstraints(check,0,2);
        
        
        //Back button to file selection
        back.setText("Back to file selection");
        gridRight.setConstraints(back,0,2);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
                    public void handle(ActionEvent event){
                      primaryStage.setScene(scene1);
                    }
            });
        
        
        //adding components to grids
        gridLeft.getChildren().addAll(dictListLabel, dictList,statsLabel,stats );
        gridRight.getChildren().addAll(inputBox, inputFileText, back, quit);
        begin.getChildren().addAll(dictInputLabel, cb, FileLabel, cb2, commit, quit2);
    
        //adding grids to hbox
        hbox.getChildren().addAll(gridLeft, gridRight);
        init.getChildren().addAll(begin);
                
        //setting stackpanes
        pane1.getChildren().addAll(init);
        pane2.getChildren().addAll(hbox);
        
        //setting stage
        primaryStage.setTitle("SpellChecker");
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        //Commit button action event (Left Grid)
        commit.setOnAction(new EventHandler<ActionEvent>() {                   
            @Override
            public void handle(ActionEvent event) {
                try{
					wrong=0;
					inputFileText.clear();
                    dictionary.clear();
					wordList.clear();
					statList.clear();
					statsLL=new LinkedList();
					dictLL=new LinkedList();
					loadNewDictionaryFile(cb.getValue());
					loadTestFile(cb2.getValue());
					
					primaryStage.setScene(scene2);
                                        
                    temp2=new File(cb2.getValue());
                    Scanner in2=new Scanner(temp2);
                    for(int j=0;j!=wordList.size();j++){
						if(wordList.get(j).equals("\n")){
							inputFileText.appendText(wordList.get(j));
						}
						else{
							inputFileText.appendText((wordList.get(j))+" ");
						}
						
					}
					
					for(int i=0; i<statsLL.size();i++){
						if(statsLL.get(i).equals("\n") || statsLL.get(i).equals("\t") || statsLL.get(i).equals(" ")){
							statsLL.remove(i);
						}
					}

					for(int i=0; i<statsLL.size();i++){
						statList.add(statsLL.get(i));
						if(statList.get(i).equals("\n") || statList.get(i).equals("\t") || statList.get(i).equals(" ")){
							statList.remove(i);
						}					
					}
					             
                
                }catch (Exception e)
                {
                 e.printStackTrace();
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
    
    
    public static void main(String[] args) throws Exception {
		
		wordList = new Vector<String>();
		launch(args);
		
		
	}
	
	public static void loadTestFile(String name) throws Exception{
		
		testFile = new File(name);
		Scanner in= new Scanner(testFile);
		String[] userInput;
		
		while (in.hasNextLine()){
			userInput=in.nextLine().split(" ");
			//wordList.add(userInput);
			for (int i=0; i<userInput.length;i++){
				wordList.add(userInput[i]);
			}
			wordList.add("\n");
			
		}

		System.out.printf("%d entries read%n", wordList.size());
		statsLL.clear();
		for (int i=0; i < wordList.size(); i++){
			System.out.printf("%s_",wordList.get(i));
					
			if (!ht.contains(wordList.get(i)) && !wordList.get(i).equals("\n")){
				if (!wordList.get(i).equals(" ") || !wordList.get(i).equals("\n"))
				{
					statsLL.add(ht.preprocess(wordList.get(i)));
					wrong++;
				}
			}
			//else System.out.println(wordList.get(i)+" was found");
		}
		System.out.println("There were "+wrong+" errors found");
		in.close();
		
	}
	
	public static void loadNewDictionaryFile(String fname) throws Exception{
		hashDict= new File(fname);
		if (hashDict.canRead())
			ReadDictionaryFile();
	}
	public static void ReadDictionaryFile() throws Exception{
		//Vector<String> temp = new Vector<String>();
		Scanner in = new Scanner(hashDict);
		
		while (in.hasNext())
			dictLL.add(in.next());
		in.close();
		ht = new HashTable(dictLL.size());

		for(int i=0;i<dictLL.size();i++){
			dictionary.add(dictLL.get(i));
			
		}
		
		for (int i=0; i < dictLL.size(); ++i){
			ht.insert(dictLL.get(i));
		}
		System.out.printf("%d entries%n", ht.Count());
	}

}
