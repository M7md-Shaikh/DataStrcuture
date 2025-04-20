package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = new TabPane();
			BorderPane borderPane = new BorderPane();
			Tab tl0 = new Tab("Lab 0");
			Tab tl1 = new Tab("Lab 1");
			Tab tl2 = new Tab("Lab 2");
			Tab tl3 = new Tab("Lab 3");
			Tab tl4 = new Tab("Lab 4");
			Tab tl5 = new Tab("Lab 5");
			Tab tl6 = new Tab("Lab 6");
			Tab tl7 = new Tab("Lab 7");
			Tab tl8 = new Tab("Lab 8");
			Tab tl9 = new Tab("Lab 9");

			root.getTabs().addAll(tl0,tl1,tl2,tl3,tl4,tl5,tl6,tl7,tl8,tl9);

			Lab0 lab0 = new Lab0();
			tl0.setContent(lab0.bp);

			Lab1 lab1 =new Lab1();
			tl1.setContent(lab1.bp);

			Lab2 lab2 = new Lab2();
			tl2.setContent(lab2.bp);

			Lab3 lab3 = new Lab3();
			tl3.setContent(lab3.bp);

			Lab4 lab4 = new Lab4();
			tl4.setContent(lab4.bp);

			Lab5 lab5 = new Lab5();
			tl5.setContent(lab5.bp);
			
			Lab6 lab6 = new Lab6();
			tl6.setContent(lab6.bp);

			Scene scene = new Scene(root,700,500);
			primaryStage.setTitle("Comp 242  - Mohammad Sheikh  - 1221541");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}