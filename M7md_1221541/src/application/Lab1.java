package application;

import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lab1 {
	
	public BorderPane bp = new BorderPane();
	VBox vb = new VBox(10);
	GridPane gp = new GridPane();
	Button b1 = new Button("Is Palindrom");
	Button b2 = new Button("Binary Search");
	Button b3 = new Button("Count Word");
	Button b4 = new Button("GCD");
	

	
	
	public Lab1(){
		
		b1.setMaxWidth(150);
		b2.setMaxWidth(150);
		b3.setMaxWidth(150);
		b4.setMaxWidth(150);
		
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(b1,b2,b3,b4);
		
		bp.setCenter(vb);
		
		b1.setOnAction(e -> isPalindromStage());
		b2.setOnAction(e -> binarySearchStage());
		b3.setOnAction(e -> countWordStage());
		b4.setOnAction(e -> GCDStage());
		
		bp.setCenter(vb);
		
	}
	
	public Stage isPalindromStage() {
	    Stage stage = new Stage();
	    BorderPane borderPane = new BorderPane(); 
	    Scene scene = new Scene(borderPane, 300, 200); 

	    Label l1 = new Label ("Please enter a Number you need");
	    TextField tf1 = new TextField();
	    tf1.setMaxWidth(200);
	    Button run = new Button("Run");
	    VBox v = new VBox(10);
	    v.setAlignment(Pos.CENTER);
	    Label l = new Label();

	    v.getChildren().addAll(l1,tf1, run, l);
	    run.setOnAction(s -> {
	        String str = tf1.getText(); 
	        
	        
	        if (isPalindrom(str)) {
	            l.setText("TRUE");
	        } else {
	            l.setText("FALSE");
	        }
	        
	    });
	    
	    
	    
	    borderPane.setCenter(v); 

	    stage.setTitle("Is Palindrom");
	    stage.setScene(scene);
	    stage.show();
	    return stage;
	}


	public boolean isPalindrom(String str){
		
		if (str == null || str.isEmpty()) {
			return false; 
		}
		
		if (str.length() ==1 || str.length()==2  && str.charAt(0)== str.charAt(1)){
			return true;
		}
		
		if (str.charAt(0)==str.charAt(str.length()-1)){
			return isPalindrom(str.substring(1,str.length()-1));
		}
		return false;
	}

	public Stage binarySearchStage() {
		
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 300, 200);

        Label l1 = new Label ("Please enter a size you need for a array");
        TextField tfArr = new TextField();
        tfArr.setMaxWidth(150);
        Label l2 = new Label ("Please enter a number you need Search about it");
        TextField tfKey = new TextField();
        tfKey.setMaxWidth(150);
        Button run = new Button("Run");
        VBox v = new VBox(10);
        v.setAlignment(Pos.CENTER);
        Label l = new Label();
        Random random = new Random();
        v.getChildren().addAll(l1,tfArr,l2, tfKey, run, l);
        run.setOnAction(s -> {
            try {
                int arrSize = Integer.parseInt(tfArr.getText());
                int[] arr = new int[arrSize];
               
                int low = 0;
                int high = arrSize - 1; 

                for (int i = 0; i < arrSize; i++) {
                    arr[i] = random.nextInt(10); 
                }

                int key = Integer.parseInt(tfKey.getText());

                int result = binarySearch(arr, key,low,high);
                if (result != -1) {
                    l.setText("Key found at index: " + result);
                } else {
                    l.setText("Key not found");
                }
            } catch (NumberFormatException e) {
                l.setText("Please enter valid integer inputs.");
            }
        });

        borderPane.setCenter(v);

        stage.setTitle("Binary Search");
        stage.setScene(scene);
        stage.show();
        return stage;
    }

	public int binarySearch(int[] arr, int key, int low, int high) {
	   
		if (low <= high) {
	        int mid = (low + high) / 2;

	        if (arr[mid] == key) {
	            return mid;
	        } else if (arr[mid] < key) {
	            return binarySearch(arr, key, mid + 1, high);
	        } else {
	            return binarySearch(arr, key, low, mid - 1);
	        }
	    }
	    return -1; 
	}

	public Stage countWordStage() {
	    Stage stage = new Stage();
	    stage.setTitle("Word Counter");

	    TextField tfStr = new TextField();
	    TextField tfWord = new TextField();
	    Button countButton = new Button("Count");
	    
	    Label lR = new Label();

	    GridPane gridPane = new GridPane();
	    gridPane.setPadding(new Insets(20));
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);

	    gridPane.add(new Label("Input String : "), 0, 0);
	    gridPane.add(tfStr, 1, 0);
	    gridPane.add(new Label("Word to count : "), 0, 1);
	    gridPane.add(tfWord, 1, 1);
	    gridPane.add(countButton, 0, 2, 2, 1);
	    gridPane.add(lR, 0, 3, 2, 1);

	    countButton.setOnAction(e -> {
	        String str = tfStr.getText();
	        String word = tfWord.getText();
	        int count = countWord(str, word);
	        lR.setText("Number of word of ' "+word+" ': "+count);
	    });

	    Scene scene = new Scene(gridPane, 300, 200);
	    stage.setScene(scene);
	    stage.setTitle("Count Word");
	    stage.show();
	    return stage;
	}
	
	public static int countWord(String str, String word) {

        if (str.isEmpty() || word.isEmpty()) {
            return 0;
        }
        
        int index = str.indexOf(word);
        if (index == -1) {
            return 0;
        }
        return 1 + countWord(str.substring(index + word.length()), word);
    }

	
	public Stage GCDStage() {
	    Stage stage = new Stage();
	    TextField tfA = new TextField();
	    TextField tfB = new TextField();
	    Button calculateButton = new Button("Calculate GCD");

	    // Create a label to display the result
	    Label resultLabel = new Label();

	    // Create a new layout for this scene
	    GridPane gp = new GridPane();
	    gp.setPadding(new Insets(20));
	    gp.setHgap(10);
	    gp.setVgap(10);

	    // Add input fields and button to the layout
	    gp.add(new Label("A:"), 0, 0);
	    gp.add(tfA, 1, 0);
	    gp.add(new Label("B:"), 0, 1);
	    gp.add(tfB, 1, 1);
	    gp.add(calculateButton, 0, 2, 2, 1);
	    gp.add(resultLabel, 0, 3, 2, 1);

	    calculateButton.setOnAction(e -> {
	        try {
	            int a = Integer.parseInt(tfA.getText());
	            int b = Integer.parseInt(tfB.getText());
	            int gcd = gcd(a, b);
	            resultLabel.setText("GCD: "+gcd);
	        } catch (NumberFormatException ex) {
	            resultLabel.setText("Please enter valid integers");
	        }
	    });

	    Scene scene = new Scene(gp, 300, 200);
	    stage.setTitle("GCD");
	    stage.setScene(scene);
	    stage.show();
	    return stage;
	}
	
	
	public int gcd(int a , int b){
		if (b==0){
			return a ;
		}
		else{
			return gcd(b,a%b);
		}
	}
}