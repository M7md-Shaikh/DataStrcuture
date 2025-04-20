package application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Lab0  {
	
	ArrayList<Student> studentList  = new ArrayList<>();
	public BorderPane bp = new BorderPane();
	GridPane gp ;
	Label lN = new Label("Student Name : ");
	Label lA = new Label("Student Age : ");
	Label lG = new Label("Student Grade : ");
	Label lAd = new Label("Student Address : ");
	TextField tfN = new TextField();
	TextField tfA = new TextField();
	TextField tfG = new TextField();
	TextField tfC = new TextField();
	TextField tfCi = new TextField();
	TextField tfS = new TextField();
	Button pre = new Button("Previous");
	Button next = new Button("Next");
	int index = 0;
	boolean isLastLine = false;
	 boolean firstLine = true;
	
	
	public Lab0() {
			
			bp = new BorderPane();
			gp = new GridPane();
			
			gp.setHgap(10);
			gp.setVgap(10);
			gp.setAlignment(Pos.CENTER);

			
			gp.add(lN, 0, 0);
			gp.add(tfN,1,0);
			gp.add(lA, 0, 1);
			gp.add(tfA,1,1);
			gp.add(lG, 0, 2);
			gp.add(tfG,1,2);
			gp.add(lAd, 0, 3);
			gp.add(tfC,1,3);
			gp.add(tfCi, 2, 3);
			gp.add(tfS,3,3);
			
			HBox hb = new HBox(15);
			hb.getChildren().addAll(pre,next);
			hb.setAlignment(Pos.CENTER);
			
	        readFile(); 
			next.setOnAction(e -> nextButton());
			pre.setOnAction(e -> previousButton());
	        displayStudentDetails(); 
			
			VBox vb = new VBox(20); 
			vb.getChildren().addAll(gp,hb);
			vb.setAlignment(Pos.CENTER);
		
			 next.setDisable(false);
		     pre.setDisable(true);  
		        
			bp.setCenter(vb);
			
	}

	
	public void readFile(){
		File file = new File("C:\\Users\\mhmds\\Desktop\\DataStructure\\M7md_1221541\\StudentList.txt");
		try (BufferedReader br= new BufferedReader(new FileReader(file))){
			
			String line;
		     
	        while ((line = br.readLine()) != null) {
				String [] parts = line.split(",");
				if (parts.length == 6){
					
					String name = parts[0];
					int age = Integer.parseInt(parts[1].trim());
					Double grade = Double.parseDouble(parts[2].trim());
					String country = parts[3];
					String city = parts[4];
					String street = parts[5];
					
					Address address =  new Address(country , city , street);
					Student student = new Student(name , age , grade , address );
					studentList.add(student);
				}
				else System.out.println("Error , each line should have all information for a student");
				
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	 public void nextButton() {
		 index++;
	     if (index < studentList.size()) {
	         displayStudentDetails();
	         pre.setDisable(false);
	     } else {
	         System.out.println("No more students");
	         index = studentList.size() - 1;
	         next.setDisable(true);
	     }
	 }

	 public void displayStudentDetails() {
	     if (!studentList.isEmpty() && index >= 0 && index < studentList.size()) {
	         Student currentStudent = studentList.get(index);
	         tfN.setText(currentStudent.getName());
	         tfA.setText(String.valueOf(currentStudent.getAge()));
	         tfG.setText(String.valueOf(currentStudent.getGrade()));
	         tfC.setText(currentStudent.getAddress().getCountry());
	         tfCi.setText(currentStudent.getAddress().getCity());
	         tfS.setText(currentStudent.getAddress().getStreet());
	     } else {
	         System.out.println("No students available");
	         }
	    }
	 
	 public void previousButton() {
	        index--;
	        if (index >= 0) {
	            displayStudentDetails();
	            next.setDisable(false);
	        } else {
	            index = 0;
	            pre.setDisable(true);
	        }
	 }
	
}
