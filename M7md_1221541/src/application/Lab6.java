package application;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;
import java.util.Random;
import javafx.stage.Stage;

public class Lab6 {

    public BorderPane bp = new BorderPane();
    private Queue custQ;
    private Random rand;
    private int simLength;
    private int minute;
    private int totalServed;
    private int totalWait;
    private int maxWait;
    private TextArea outputTextArea;
	
    public Lab6() {
    	Stage stage = new Stage();
    	GridPane gp = new GridPane();
    	gp.setPadding(new Insets(10, 10, 10, 10));
    	gp.setVgap(5);
    	gp.setHgap(5);
    	
    	Label simLengthLabel = new Label("Length of simulation (minutes):");
    	GridPane.setConstraints(simLengthLabel, 0, 0);
    	
    	TextField simLengthField = new TextField();
    	GridPane.setConstraints(simLengthField, 1, 0);

    	Button startButton = new Button("Start Simulation");
    	GridPane.setConstraints(startButton, 0, 1);

    	outputTextArea = new TextArea();
    	outputTextArea.setEditable(false);
    	GridPane.setConstraints(outputTextArea, 0, 2, 2, 1);

    	gp.getChildren().addAll(simLengthLabel, simLengthField, startButton, outputTextArea);
    	gp.setAlignment(Pos.CENTER);
    	startButton.setOnAction(e -> {
    		try {
    			simLength = Integer.parseInt(simLengthField.getText());
    			custQ = new Queue();
    			rand = new Random(System.currentTimeMillis());
    			simulate();
    		} catch (NumberFormatException ex) {
    		}
    	});
    	bp.setCenter(gp);
    	stage.setScene(new Scene(bp, 500, 450));
    	stage.show();

    }

    private void simulate() {
        totalServed = 0;
        totalWait = 0;
        maxWait = 0;
        outputTextArea.clear();

        for (minute = 0; minute < simLength; minute++) {
            if (!custQ.isEmpty()) {
                int customer = (int) custQ.deQueue();
                totalServed++;
                int waitTime = minute - customer;
                totalWait += waitTime;
                if (waitTime > maxWait) {
                    maxWait = waitTime;
                }
            }

            switch (rand.nextInt(4)) {
                case 1:
                    outputTextArea.appendText("Minute " + minute + ": Add 1 customer to the line\n");
                    custQ.enQueue(minute);
                    break;
                case 2:
                    outputTextArea.appendText("Minute " + minute + ": Add 2 customers to the line\n");
                    custQ.enQueue(minute);
                    custQ.enQueue(minute);
                    break;
                default:
                    outputTextArea.appendText("Minute " + minute + ": Do not add any customer to the line\n");
            }
        }

        DecimalFormat fmt = new DecimalFormat("0.##");
        outputTextArea.appendText("\nCustomers served: " + totalServed + "\n");
        outputTextArea.appendText("Average wait: " + fmt.format((double) totalWait / totalServed) + "\n");
        outputTextArea.appendText("Longest wait: " + maxWait + "\n");
    }
}