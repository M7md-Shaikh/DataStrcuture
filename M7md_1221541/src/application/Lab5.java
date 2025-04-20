package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Lab5 {

	public BorderPane bp = new BorderPane();
	public Lab5() {
		 
		Stage stage = new Stage();
		
		VBox vBox = new VBox(12);
		
		Button btQ1 = new Button("Question 1");
		Button btQ2 = new Button("Question 2");
		
		vBox.getChildren().addAll(btQ1, btQ2);
		
		btQ1.setOnAction(e -> q1Stage());
		
		btQ2.setOnAction(e -> q2Stage());

		bp.setCenter(vBox);
		vBox.setAlignment(Pos.CENTER);
		stage.setScene(new Scene(bp, 500, 550));
		stage.show();
	}

    public void q1Stage() {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();

        Label lbsString = new Label("Enter a mathematical equation to show the postfix for it :");
        TextField tfString = new TextField();

        Label lbResult = new Label("Result :");
        TextField tfResult = new TextField();
        tfResult.setEditable(false);

        Button btSubmit = new Button("Submit");

        btSubmit.setOnAction(e -> {
            if (tfString.getText().isEmpty()) {
                tfResult.setText("You myst enter the mathematical equation first .");
            } else {
                tfResult.setText(" ");
                String postFix = this.infixToPostfix(tfString.getText());
                tfResult.setText(postFix);
            }
        });


        gridPane.add(lbsString, 0, 0);
        gridPane.add(tfString, 1, 0);
        gridPane.add(btSubmit, 2, 0);
        gridPane.add(lbResult, 0, 1);
        gridPane.add(tfResult, 1, 1);

        gridPane.setVgap(15);
        gridPane.setHgap(15);
        gridPane.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(gridPane, 900, 550));
        stage.show();
    }

    public void q2Stage() {
        Stage stage = new Stage();
        GridPane gp = new GridPane();

        Label lbString = new Label("Please enter the parentheses to check if it valid or Not :");
        TextField tfString = new TextField();
        Button btSubmit = new Button("Submit.");
        Label lbResult = new Label();

        btSubmit.setOnAction(e -> {
            if (tfString.getText().isEmpty()) {
                lbResult.setText("You must enter the parentheses first !");
            } else {
                lbResult.setText(" ");

                boolean b = isBalanced(tfString.getText());

                if (b) {
                    lbResult.setText("It's valid.");
                } else {
                    lbResult.setText("It's Not valid.");
                }
            }
        });

        lbResult.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 17));
        lbResult.setAlignment(Pos.CENTER);


        gp.add(lbString, 0, 0);
        gp.add(tfString, 1, 0);
        gp.add(btSubmit, 2, 0);
        gp.add(lbResult, 1, 1);
        

        gp.setAlignment(Pos.CENTER);
        gp.setHgap(15);
        gp.setVgap(15);

        stage.setScene(new Scene(gp, 650, 500));
        stage.show();
    }

    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack operatorStack = new Stack();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            if (Character.isDigit(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                operatorStack.push(ch);
            } else if (ch == ')') {
                while (!operatorStack.isEmpty() && (char) operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.pop(); 
            } else {
                while (!operatorStack.isEmpty() && precedence((char) operatorStack.peek()) >= precedence(ch)) {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.push(ch);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }

        return postfix.toString();
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1; 
        }
    }

    public static boolean isBalanced(String parentheses) {
        Stack stack = new Stack();

        for (int i = 0; i < parentheses.length(); i++) {
            char ch = parentheses.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false; 
                } else {
                    char top = (char) stack.pop();
                    if ((ch == ')' && top != '(') ||
                            (ch == '}' && top != '{') ||
                            (ch == ']' && top != '[')) {
                        return false; 
                    }
                }
            }
        }

        return stack.isEmpty();
    }
}


