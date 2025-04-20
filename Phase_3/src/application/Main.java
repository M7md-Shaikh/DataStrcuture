package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

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

            Tab dateTab = new Tab("Date");
            Tab martyrTab = new Tab("Martyr");

            tabPane.getTabs().addAll(dateTab, martyrTab);

            borderPane.setTop(menuBar);
            borderPane.setCenter(tabPane);
            borderPane.setBottom(vBox);

            dateTab.setContent(drive.bpD);
            martyrTab.setContent(drive.bpM);

            Image wallpaperImage = new Image("file:C:/Users/mhmds/Pictures/Saved Pictures/Gaza-War.jpg");
            
            BackgroundImage backgroundImage = new BackgroundImage(
                    wallpaperImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );

            Background background = new Background(backgroundImage);

            borderPane.setBackground(background);

            Scene scene = new Scene(borderPane, 800, 600);

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
