package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

	
	

public class Lab2 {
	
	static int count = 0;
	BorderPane bp = new BorderPane();
	Button bAdd = new Button("Add");
	Button bS = new Button("Sort");        //  نرتب الليست بالنسبة للعمر
	Button bD = new Button("Delete"); // بندخل العمر هان ولازم نحذف جميع الاعمار اللي فوق هاد العمر 
	Button bAvg = new Button("Average");
	Button bAC = new Button("Add Course");
	Label lT = new Label("Choose What You Need To Do :) ");
	Label lN = new Label("Student Name : ");
	Label lA = new Label("Student Age : ");
	TextField tfN = new TextField();
	TextField tfA = new TextField();
	GridPane gp = new GridPane();
	VBox vb = new VBox(10);  
	Label label = new Label();
	Label listLabel = new Label();
	Label avgLabel = new Label();
	TextField tfName = new TextField();
	TextField tfCourse = new TextField();
	TextField tfGrade = new TextField();
	static Student student ; 
	static SingleLinkedList l = new SingleLinkedList();
	
	
	
	
	public Lab2() {
		
		
		
		gp.add(lN, 0, 0);
		gp.add(tfN,1,0);
		gp.add(lA,0,1);
		gp.add(tfA,1,1);
		gp.setAlignment(Pos.CENTER);
		
		GridPane gp2 = new GridPane();
		gp2.add(bAdd, 0, 0);
		gp2.add(bS,1,0);
		gp2.add(bD, 0, 1);
		gp2.add(bAvg,1,1);
		gp2.setAlignment(Pos.CENTER);
		gp2.setHgap(10);
		gp2.setVgap(10);
		
		bAdd.setPrefWidth(90);
		bS.setPrefWidth(90);
		bD.setPrefWidth(90);
		bAvg.setPrefWidth(90);
		
		vb.getChildren().addAll(lT,gp,gp2,bAC,label,listLabel,avgLabel);
		vb.setAlignment(Pos.CENTER);
		bAdd.setOnAction(e -> {				
			
			String name = tfN.getText();
			String age = tfA.getText();
			if(name.isEmpty() || age.isEmpty()) {
				 label.setText("The Text's is Empty , You Should Write in it");
				 return;
			}
			
			File file = new File("C:\\Users\\mhmds\\Desktop\\DataStructure\\LinkedListStudent.txt");
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
				writer.write(tfN.getText() + "," + tfA.getText()); 
	            writer.newLine(); 
		        label.setText("'"+tfN.getText()+"' Added Succesfully");
		    	readFile();      
				tfA.clear();
			    tfN.clear();
		        
			}catch (FileNotFoundException a) {
				System.out.println(a.getMessage());
			}catch (NumberFormatException w) {
		        System.out.println( w.getMessage());
			}catch (IOException q) {
				System.out.println(q.getMessage());
			}
			});
		
		bS.setOnAction(e -> {
		listLabel.setText(" ");
		readFile();
		sort();
		});
		
		bD.setOnAction(e -> {
		    String ageText = tfA.getText();
		    if (!ageText.isEmpty()) {
		        int age = Integer.parseInt(ageText);
		        deleteButton(age);
		    } else {
		        label.setText("Error: Please enter an age");
		    }
		});
		
		bAvg.setOnAction(e -> avgButton());
		bAC.setOnAction(e -> addCourse());
		gp.setVgap(10);
		gp.setHgap(10);
		bp.setCenter(vb);
		
	}
	
	public void sort() {
	    if (l.first != null) {
	        boolean swapped;
	        Node current;
	        Node last = null;

	        do {
	            swapped = false;
	            current = l.first;
	            while (current.next != last) {
	                Student currentStudent = (Student) current.getData();
	                Student nextStudent = (Student) current.next.getData();

	                if (currentStudent.getAge() > nextStudent.getAge()) {
	                    Object temp = current.getData();
	                    current.setData(current.next.getData());
	                    current.next.setData(temp);
	                    swapped = true;
	                }
	                current = current.next;
	            }
	            last = current;
	        } while (swapped);
	    }
	    printList();
	}

	
	
	public void readFile(){
		
		File file = new File("C:\\Users\\mhmds\\Desktop\\DataStructure\\LinkedListStudent.txt");
		try (Scanner  scanner = new Scanner(file)){
	        while (scanner.hasNext()) {
	        	String line = scanner.nextLine();
				String [] parts = line.split(",");
				if (parts.length == 2){
					
					String name = parts[0];
					int age = Integer.parseInt(parts[1].trim());
					student = new Student(name,age);
					if (!studentExists(student)) {
			            l.addFirst(student);
			            count++;
			            printList();
			            return;
			            
			        }
					else {
						label.setText("Error: Student with the same name already exists");
					}
				}
				else 
					System.out.println("Error , each line should have all information for a student");
			}
	        printList();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean studentExists(Student s) {
	    Node current = l.first;
	    while (current != null) {
	        if (((Student) current.getData()).getName().equals(s.getName())) {
	            return true; // Student with the same name found
	        }
	        current = current.next;
	    }
	    return false;
	}
	
	
	
	public Label printList() {
	    StringBuilder stringBuilder = new StringBuilder();
	    Node current = l.first;
	    while (current != null) {
	    	Student student = (Student) current.getData();   
	        stringBuilder.append(current.getData()).append(",").append("\n");
	        current = current.getNext();
	    }
	    listLabel.setText(stringBuilder.toString());
	    return listLabel;
	}

	public void deleteButton(int age ){
		
		Node current = l.first;
		Node previous = null;

		while (current != null) {
			Student student = (Student) current.getData();
		    if (student.getAge() > age) {
		        if (previous == null) {
		            l.first = current.next;
		        } else {
		            previous.next = current.next;
		        } 
		        current = current.next;
		    } else {   	
		        previous = current;
		        current = current.next;
		    }
		}
		printList();
	}

	public Label avgButton() {
	    double sum = 0;
	    int countt = 0;

	    Node current = l.first;
	    while (current != null) {
	    	Student student = (Student) current.getData();
	        sum += student.getAge();
	        countt++;
	        current = current.getNext();
	    }
	    if (count == 0) {
	    	avgLabel.setText("0");
	    	return avgLabel;
	    }
	    double summ = sum / countt;
	    avgLabel.setText("The Average is : "+summ);
		return avgLabel;
	   
	}


	public Stage addCourse() {
		Stage stageC = new Stage();
		BorderPane bpCourse = new BorderPane();
		Scene sceneC = new Scene(bpCourse,400,400);
		
		Label lTCourse = new Label("Added Courses For a Student's");
		Label lName = new Label("Student Name : ");
		Label lCourse = new Label("Course : ");
		Label lGrade = new Label("Grade : ");
		Button bC = new Button("Add");
		GridPane gpC = new GridPane();
		VBox vbC = new VBox(10);
		
		gpC.add(lName, 0, 0);
		gpC.add(tfName,1,0);
		gpC.add(lCourse, 0, 1);
		gpC.add(tfCourse,1,1);
		gpC.add(lGrade, 0, 2);
		gpC.add(tfGrade,1,2);
		gpC.setAlignment(Pos.CENTER);
		
		bC.setOnAction(e -> {
			
			
			
		});
		
		vbC.getChildren().addAll(gpC,bC);
		vbC.setAlignment(Pos.CENTER);
		gpC.setVgap(10);
		gpC.setHgap(10);
		
		bpCourse.setCenter(vbC);
		stageC.setTitle("Add Course");
        stageC.setScene(sceneC);
        stageC.show();
        return stageC;
	}

/*	public void buttonAddCourse() {
	    Node current = l.first;
	    while (current != null) {
	        Student student = (Student) current.getData();
	        if (tfName.getText().equals(student.getName())) {
	            String courseName = tfCourse.getText();
	            int grade = Integer.parseInt(tfGrade.getText());
	            Course newCourse = new Course(courseName, grade);
	            if (current.getNextCO() == null) {
	                CNode newCourseNode = new CNode(newCourse);
	                current.setNextCO(newCourseNode);
	            } else {
	                CNode courseNode = current.getNextCO();
	                while (courseNode.getNext() != null) {
	                    courseNode = courseNode.getNext();
	                }
	                courseNode.setNext(new CNode(newCourse));
	            }
	            break;
	        }
	        current = current.getNext();
	    }
	}
*/
}
