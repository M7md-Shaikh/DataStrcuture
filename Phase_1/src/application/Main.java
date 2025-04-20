package application;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

    public FileChooser fileChooser = new FileChooser(); 
    static PalestineMartyrs pm = new PalestineMartyrs();
    Label l = new Label();

    @Override
    public void start(Stage primaryStage) {
        try {
        	
        	Button chB = new Button("Load File");
        	VBox vb = new VBox();
            TabPane tabPane = new TabPane();
            BorderPane borderPane = new BorderPane();
            
            Tab districtTab = new Tab("District");
            Tab locationTab = new Tab("Location");

            // set content for each tab
            districtTab.setContent(pm.bpD);
            locationTab.setContent(pm.bpL);

            tabPane.getTabs().addAll(districtTab, locationTab);
            vb.getChildren().addAll(chB,l);
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            fileChooser.setTitle("Open CSV File");
			fileChooser.setInitialDirectory(new File("C:\\Users\\mhmds\\Desktop\\M7md"));  // select File Location
            
            if (selectedFile != null) { 
            	l.setText("Selected File : "+ selectedFile);
            	pm.readCSVFile(selectedFile.getAbsolutePath());
            } else {
                l.setText("No file selected.");
            }
            
            chB.setOnAction(e -> {            	 
         
            	fileChooser.setTitle("Open File");  // title for window
            	fileChooser.setInitialDirectory(new File("C:\\Users\\mhmds\\Desktop\\M7md"));  // select File Location
            	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File","*.csv"));  // the kind of files
            	File selectedFilee = fileChooser.showOpenDialog(primaryStage);
    			if (selectedFilee != null) { 
    				l.setText("Selected File : "+ selectedFilee);
    				pm.readCSVFile(selectedFilee.getAbsolutePath());
    			} else {
    				l.setText("No file selected");
    	        }
    			pm.printDis();
            });
            
            pm.printDis();
            pm.bpD.setBottom(vb);
            Scene scene = new Scene(tabPane, 700, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Palestine Martyrs Application");
            primaryStage.show();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
    	launch(args);
    }
}
