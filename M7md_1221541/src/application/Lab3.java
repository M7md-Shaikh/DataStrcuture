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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


	

public class Lab3 {
	
	static int count = 0;
	BorderPane bp = new BorderPane();
	Button bAdd = new Button("Add");
	Button bS = new Button("Sort");        //  نرتب الليست بالنسبة للعمر
	Button bD = new Button("Delete"); // بندخل العمر هان ولازم نحذف جميع الاعمار اللي فوق هاد العمر 
	Button bAvg = new Button("Average");
	Button bNext = new Button("Next");
	Button bPrev = new Button("Previous");
	
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
	HBox hb = new HBox(10);
	int index =0;
	static Student student ; 
	static DoubleLinkedList l = new DoubleLinkedList();
	
	
	public Lab3() {
		
		bAdd.setMaxWidth(150);
		bS.setMaxWidth(150);
		bD.setMaxWidth(150);
		bAvg.setMaxWidth(150);
		
		hb.getChildren().addAll(bPrev , bNext);
		hb.setAlignment(Pos.CENTER);
		gp.add(lN, 0, 0);
		gp.add(tfN,1,0);
		gp.add(lA,0,1);
		gp.add(tfA,1,1);
		gp.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(lT,gp,hb,bAdd,bS,bD,bAvg,label,listLabel,avgLabel);
		vb.setAlignment(Pos.CENTER);
		bAdd.setOnAction(e -> {				
			
			String name = tfN.getText();
			String age = tfA.getText();
			if(name.isEmpty() || age.isEmpty()) {
				 label.setText("The Text's is Empty , You Should Write in it");
				 return;
			}
			
			File file = new File("C:\\Users\\1221541\\Desktop\\LinkedListStudent.txt");
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
		
		bNext.setOnAction(e -> nextButton());
		bPrev.setOnAction(e -> previousButton());
		
		bAvg.setOnAction(e -> avgButton());
		bNext.setDisable(false);
	    bPrev.setDisable(true);  
	    
		gp.setVgap(10);
		gp.setHgap(10);
		bp.setCenter(vb);
		
	}
	
	public void sort() {
	    if (l.first != null) {
	        boolean swapped;
	        do {
	            swapped = false;
	            Node current = l.first;

	            while (current.next != null) {
	                Student currentStudent = (Student) current.getData();
	                Student nextStudent = (Student) current.next.getData();

	                if (currentStudent.getAge() > nextStudent.getAge()) {
	                	//swap
	                    current.setData(nextStudent);
	                    current.next.setData(currentStudent);
	                    swapped = true;
	                }
	                current = current.next;
	            }
	            if (current.prev != null) {
	                l.last = current.prev;
	            }
	        } while (swapped);
	    }
	    printList();
	}


	
	
	public void readFile(){
		
		File file = new File("C:\\Users\\1221541\\Desktop\\LinkedListStudent.txt");
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
	            return true;
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

	public void deleteButton(int age) {
        count = removeOverAge(age, l.first, count);
        printList();
    }

    private int removeOverAge(int age, Node current, int count) {
        if (current == null) {
            return count;
        }
        Student student = (Student) current.getData();
        if (student.getAge() > age) {
            if (current.prev == null) {
                l.first = current.next;
                if (l.first != null) {
                    l.first.prev = null;
                }
            } else {
                current.prev.next = current.next;
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
            }
            count--;
        }
        return removeOverAge(age, current.next, count);
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
	
	 public void nextButton() {
		 index++;
	     if (index < count) {
	         displayStudentDetails();
	         bPrev.setDisable(false);
	     } else {
	         System.out.println("No more students");
	         index = count - 1;
	         bNext.setDisable(true);
	     }
	 }

	 public void displayStudentDetails() {
		 try {
		    if (count != 0 && index >= 0 && index < count) {
		        Node current = l.first;
		        for (int i = 0; i < index; i++) {
		            current = current.next;
		        }
		        Student student = (Student) current.getData();
		        tfN.setText(student.getName());
		        tfA.setText(String.valueOf(student.getAge()));
		    } else {
		        System.out.println("No students available");
		    }
		 }catch(NullPointerException e) {
			 System.out.println(e.getMessage());
		 }
	 }
	 public void previousButton() {
	        index--;
	        if (index >= 0) {
	            displayStudentDetails();
	            bNext.setDisable(false);
	        } else {
	            index = 0;
	            bPrev.setDisable(true);
	        }
	 }
	
	
}