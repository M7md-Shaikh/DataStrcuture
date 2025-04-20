package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
public class Lab4 {

    public BorderPane bp = new BorderPane();
    private Cursor cursor = new Cursor();
    private TextField headerTextField = new TextField();
    private TextField nameTextField = new TextField();
    private TextField ageTextField = new TextField();
    private Label headerLabel = new Label("Header:");
    private Label nameLabel = new Label("Student Name:");
    private Label ageLabel = new Label("Student Age:");
    private GridPane gridPane = new GridPane();
    private Button createListButton = new Button("Create List");
    private Button addNodeButton = new Button("Add Node");
    private Button printButton = new Button("Print");
    private Label statusLabel = new Label();

    public Lab4() {
        gridPane.add(headerLabel, 0, 0);
        gridPane.add(headerTextField, 1, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameTextField, 1, 1);
        gridPane.add(ageLabel, 0, 2);
        gridPane.add(ageTextField, 1, 2);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(gridPane, createListButton, addNodeButton, printButton, statusLabel);
        
        createListButton.setOnAction(e -> createList());
        addNodeButton.setOnAction(e -> addNode());
        printButton.setOnAction(e -> printPrev());

        bp.setCenter(vbox);
    }

    private void createList() {
        int header = cursor.createList();
        if (header != 0) {
            statusLabel.setText("List Created Successfully");
        } else {
            statusLabel.setText("Failed to create list");
        }
    }

    private void addNode() {
        try {
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            int header = Integer.parseInt(headerTextField.getText());
            cursor.addFirst(header, new Student(name, age));
            statusLabel.setText("Node Added Successfully");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid input. Please enter valid integer values.");
        }
    }

    private void printPrev() {
        try {
            int header = Integer.parseInt(headerTextField.getText());
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            String result = cursor.printPrev(new Student(name, age), header);
            statusLabel.setText(result);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid input. Please enter valid integer values.");
        }
    }
}