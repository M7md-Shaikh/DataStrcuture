package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	
	Drive drive = new Drive();
	
    
    @Override
    public void start(Stage primaryStage) {
        try {
            MenuItem newMenuItem = new MenuItem("New");
            MenuItem saveMenuItem = new MenuItem("Save");
            MenuItem openMenuItem = new MenuItem("Open");

            newMenuItem.setOnAction(e -> drive.handleNew(primaryStage));
            saveMenuItem.setOnAction(e -> drive.handleSave(primaryStage));
            openMenuItem.setOnAction(e -> drive.handleOpen(primaryStage));

            Menu fileMenu = new Menu("File");
            fileMenu.getItems().addAll(newMenuItem, saveMenuItem, openMenuItem);

            MenuBar menuBar = new MenuBar();
            menuBar.getMenus().add(fileMenu);

            VBox vBox = new VBox();
            TabPane tabPane = new TabPane();
            BorderPane borderPane = new BorderPane();

            Tab districtTab = new Tab("District");
            Tab locationTab = new Tab("Location");
            Tab martyrTab = new Tab("Martyr");

            districtTab.setContent(drive.bpD);
            locationTab.setContent(drive.bpL);
            martyrTab.setContent(drive.bpM);

            tabPane.getTabs().addAll(districtTab, locationTab, martyrTab);

            borderPane.setTop(menuBar);
            borderPane.setCenter(tabPane);
            borderPane.setBottom(vBox);

            Scene scene = new Scene(borderPane, 700, 500);

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
