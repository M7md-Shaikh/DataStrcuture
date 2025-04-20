package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PalestinianMartyrs extends Application {

	Button insB ;
	Button delB ;
	Button seaB ;
	Button disB ;
	Button chB;
	Label response = new Label();
	Label addLabel =new Label();
	Label deleteLabel = new Label();
	static int n = 0; 
	static Martyr [] martyrArr = new Martyr[16];
	FileChooser fileCh ;
	TextField tfN = new TextField();
	TextField tfA = new TextField();
	TextField tfE = new TextField();
	TextField tfD = new TextField();
	TextField tfG = new TextField();
	File fileP ;
	Random random = new Random();
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//here we use Border Pane , and decliration a Grid pane 
		BorderPane bp = new BorderPane();  
		Scene scene = new Scene (bp,500,700);
		GridPane gp = new GridPane();   // Grid Pane for sort a button
		Text top = new Text(" Please Choose What You Need To Do :)\n\n " );
		top.setFont(new Font(15));
		Label l = new Label();
		
		BorderPane.setAlignment(top, Pos.CENTER);

		// button in main interface
		insB = new Button("Insert Martyr");
		delB = new Button("Delete Martyr");
		seaB = new Button("Martyr Search ");
		disB = new Button("Display Martyr Number");
		chB = new Button("Choose File");
		fileCh = new FileChooser();
		
		// sort a buttons 
		insB.setMinSize(140,10);
		delB.setMinSize(140,10);
		seaB.setMinSize(140,10);
		disB.setMinSize(100,10);
		chB.setMinSize(100,10);
		
		// here use lambda expression to set an action handler for the buttons (called another windows)
		insB.setOnAction(e -> insertMartyr());
		delB.setOnAction(e -> deleteMartyr());
		seaB.setOnAction(e -> martyrSearch());
		disB.setOnAction(e -> displayMartyrCount());		
		
		gp.add(insB, 0, 0);
		gp.add(delB, 1, 0);
		gp.add(seaB, 0, 1);
		gp.add(disB, 1, 1);
		gp.setVgap(10);
		gp.setHgap(10);
		gp.setAlignment(Pos.CENTER);
		
		try {
		
			// here use fileChooser for choose a file i need
		chB.setOnAction(e -> {
			
			fileCh.setTitle("Open File");  // title for window
			fileCh.setInitialDirectory(new File("C:\\Users\\mhmds\\Desktop\\M7md"));  // select File Location
			fileCh.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File","*.csv"));  // the kind of files
			File fileP = fileCh.showOpenDialog(stage); 

		
			if (fileP != null) {
				readCSVFile(fileP);  // called a csv method
	            l.setText("Selected File: "+ fileP.getPath());
	        } else {
	            l.setText("No file selected.");
	         }
			
			
		});
		
		ImageView i = new ImageView("martyr.jpg");
		i.setFitWidth(500);
		i.setFitHeight(400);
		VBox vb = new VBox(10);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(top,gp,chB);
		bp.setTop(i);
		bp.setCenter(vb);
		bp.setBottom(l);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Palestinian Martyrs");
		
		}catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	// method for resize the list by doubling the list size. 
	public Martyr[] checkArr(Martyr[] arr) {
		int a = arr.length;
	    int capacity = a * 2;  // double the size
	    Martyr[] newArr = new Martyr[capacity];
	    for (int i = 0; i < arr.length; i++) {
	        newArr[i] = arr[i];
	    }
	    return newArr; // return a new array
	}
	
	// method for read a file.CSV 
	private void readCSVFile(File file) {
		
		// we can use here (FileInputStrea) but i prefer use BufferedReader 
		  try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			  String line;
		      boolean firstLine = true;
		        
		      // this while until don't read the first Line and store it (because it headder) 
		      while ((line = br.readLine()) != null) {
		          if (firstLine) {
		              firstLine = false;
		              continue;
		          }

		          // split the information by (,)
		          String[] parts = line.split(",");
		          if (parts.length == 5) {
		              String name = parts[0];
		              int age;
		              
		              //here because a file have some martyr without age , i put the age randomly
		              if (parts[1].isEmpty())   
		                  age = random.nextInt(100) + 1; 
		               else 
		            	  age = Integer.parseInt(parts[1]);
		             
		              String eventLocation = parts[2];
		              String date = parts[3];
		              String gender = parts[4];

		              Martyr martyr = new Martyr(name, age, eventLocation, date, gender);
		              // here check if the size of array is full , 
		              if (n != martyrArr.length ) {
		                  martyrArr[n] = martyr;
		                  n++;
		              } else {
		                  // call the checkArr function and assign the new array to martyrArr
		                  martyrArr = checkArr(martyrArr);
		                  martyrArr[n] = martyr;
		                  n++;
		              }
		              
		          } else {
		        	  System.out.println("Invalid line format: " + line);
		            }
		        }
		    } catch (FileNotFoundException e) {
		        System.out.println(e.getMessage());
		    } catch (NumberFormatException e) {
		        System.out.println( e.getMessage());
		    } catch (IOException e) {
		    	System.out.println(e.getMessage());
			}
		}

	
	// this method for created a interface for a insert martyr
	public Stage insertMartyr() {
	
		Stage stage = new Stage();
		BorderPane bp = new BorderPane(); // use border Pane
		Scene scene = new Scene (bp,500,500);
		GridPane gp = new GridPane();
		
		Text title = new Text("Add A Martyr");
		title.setFont(new Font(20));
		Label lN = new Label("Name : ");
		Label lA = new Label("Age :");
		Label lE = new Label("Event Location :");
		Label lD = new Label("Date of Death :");
		Label lG = new Label("Gender :");
		Button addB = new Button("Add");
		Label la = new Label();
		
		// grid Pane for sort the window 
		gp.add(lN, 0, 0);
		gp.add(tfN, 1,0);
		gp.add(lA, 0, 1);
		gp.add(tfA, 1, 1);
		gp.add(lE, 0, 2);
		gp.add(tfE, 1, 2);
		gp.add(lD, 0, 3);
		gp.add(tfD, 1, 3);
		gp.add(lG, 0, 4);
		gp.add(tfG, 1, 4);
		gp.setVgap(10);
		gp.setHgap(10);
		gp.setAlignment(Pos.CENTER);
		
		// file i need to insert a martyr for it
		File file = new File("C:\\Users\\mhmds\\Desktop\\M7md\\data.csv");
		addB.setOnAction(e -> addButton(file));  // lambda exeprition for add button
		
		VBox vb = new VBox(20);
		vb.getChildren().addAll(title,gp,addB,addLabel);
		vb.setAlignment(Pos.CENTER);
		
		bp.setCenter(vb);
		stage.setScene(scene);
		stage.setTitle("Insert Martyr");
		stage.show();
		return stage;
	}
	
	
	// method for add button (add new martyr)
	public Label addButton(File file) {
	  try {

		String name = tfN.getText();
		String age = tfA.getText();
		String event = tfE.getText();
		String date = tfD.getText();
		String gender = tfG.getText();
		
		
		// check if the texts field is empty
		if(name.isEmpty() || age.isEmpty() || event.isEmpty() || date.isEmpty() || gender.isEmpty()) {
			addLabel.setText("Enter All information in boxes. Then press Add.");
 	        return addLabel;
		}else {
			
			String[] parts = date.split("/");  // for split a date
			
			// check if textField for date have 3 number (day/month/year)  
			if (parts.length != 3) {
                addLabel.setText("Invalid date format , Please use the format: day/month/year");
                return addLabel;
            }
			 
			int day = Integer.parseInt(parts[0]);
	    	int month = Integer.parseInt(parts[1]);
	    	int year = Integer.parseInt(parts[2]);
			
	    	// check if the list have a martyr in past
			for (int i = 0; i < n; i++) {
			    String s = martyrArr[i].getName();
			    if (name.equalsIgnoreCase(s)) {
			        addLabel.setText("Invalid name format, this name is already in the list.");
			        return addLabel;
			    }
			}
        	
        	// check if the year in the present or past not in future and a logical year
        	if (year > 2024 || year < 1900) {
                addLabel.setText("Wrong , Please enter a Logical year");
                return addLabel;
            }
        	
        	  // check if a month of date isnt wrong 
            if (month >12 || month <1) {
                addLabel.setText("Wrong , The large Number of months is 12");
                return addLabel;
            }
            
            // check if a day of date isnt wrong 
            if (day >31  || day < 1) {
                addLabel.setText("Wrong , The large days Number of months is 31");
                return addLabel;
            }
        	
            // check if age isnt 0 or less
        	int a = Integer.parseInt(age);
        	if (a <= 0){
        		addLabel.setText("Invaild age format , Please the Age should be large than 0 ");
        		return addLabel;
        	}
        	
        	// check if gender just male or female (M | F)
        	if((!gender.equals("M")) && (!gender.equals("F"))) {
        		addLabel.setText("Invaild gender format , Please The Gender should be M : Male || F : Female ");
        		return addLabel;
        	}
        	
			int ages=Integer.parseInt(tfA.getText());
			Martyr martyr = new Martyr(name,ages,event,date,gender);
			
			// here for write a martyr in the file
			 try (FileOutputStream fos = new FileOutputStream(file, true);
		                DataOutputStream dos = new DataOutputStream(fos)){
		            dos.writeUTF(tfN.getText()+","+tfA.getText()+","+tfE.getText()+","+tfD.getText()+","+tfG.getText());
		            dos.writeUTF("\n");
		            martyrArr [n]=martyr;  // here for adding a new martyr to array 
		            n++;
			 }catch (FileNotFoundException e) {
			        System.out.println(e.getMessage());
			 } catch (IOException e) {
				 	System.out.println(e.getMessage());
			}
		}
		
		// clear the textsfields
		tfN.clear();
		tfA.clear();
		tfE.clear();
		tfD.clear();
		tfG.clear();
		addLabel.setText("File Added Martyr Successfully");
		return addLabel;
		
	  }catch (NumberFormatException e) {
		  addLabel.setText(e.getMessage());
		  }
      return addLabel;
	}
	
	// hete a method for delete martyr window , and until sort the window
	public Stage deleteMartyr() {
	    Stage stage = new Stage();
	    StackPane sp = new StackPane();
	    Scene scene = new Scene(sp, 500, 120);

	    Label l = new Label("Enter a Name of Martyr");
	    TextField tf = new TextField();
	    Button b = new Button("Delete");
	    Label label = new Label();

	    HBox hb = new HBox(10);
	    hb.getChildren().addAll(l, tf);
	    hb.setAlignment(Pos.CENTER);

	    // file path , file i need to delete a martyr from it
	    File file = new File("C:\\Users\\mhmds\\Desktop\\M7md\\data.csv");

	    // use lambda
	    b.setOnAction(e -> {
	        String name = tf.getText();
	        Martyr martyr = new Martyr();
	        boolean martyrFound = false;

	        for (int i = 0; i < n; i++) {
	            if (martyrArr[i].getName().equalsIgnoreCase(name)) {  // check if name is found 
	                martyrFound = true;
	                for (int j = i; j < n - 1; j++) {
	                    martyrArr[j] = martyrArr[j + 1];        
	                }
	                martyrArr[n - 1] = null; 
	                n--;                                // بنمسح الشهيد من الاري وبعدها بنحط الشهيد اللي بعده محله
	                tf.clear();             // clear textField
	                label.setText("Martyr '" + name + "' deleted successfully.");  // Message

	                // update the file outside the loop
	                try (BufferedReader br = new BufferedReader(new FileReader(file));
	                     BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {

	                    String line;
	                    while ((line = br.readLine()) != null) {
	                        String[] parts = line.split(",");
	                        if (parts.length >= 5 && !parts[0].equalsIgnoreCase(name)) {
	                            bw.write(line);
	                            bw.newLine();
	                        }
	                    }

	                    for (int k = 0; k < n; k++) {
	                        // check if the array is not null before accessing its properties
	                        if (martyrArr[k] != null) {
	                            bw.write(martyrArr[k].getName() + "," + martyrArr[k].getAge() + "," +
	                                    martyrArr[k].getEventLoc() + "," + martyrArr[k].getDate() + "," + martyrArr[k].getGender());
	                            bw.newLine();
	                        }
	                    }

	                } catch (FileNotFoundException ex) {
	                    System.out.println(ex.getMessage());
	                } catch (IOException ex) {
	                    System.out.println(ex.getMessage());
	                }
	                break;
	            }
	        }

	        if (!martyrFound)
	            label.setText("Error: Martyr '" + name + "' not found.");
	    });

	    VBox vb = new VBox(10);
	    vb.getChildren().addAll(hb, b, label);
	    vb.setAlignment(Pos.CENTER);

	    sp.getChildren().add(vb);
	    stage.setScene(scene);
	    stage.setTitle("Delete Martyr");
	    stage.show();
	    return stage;
	}


	// here for martyr Search window , enter the name and then give u a information for a martyr u enter
	public Stage martyrSearch() {
		Stage stage = new Stage();
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp,500,300);
		
		TextField tf = new TextField();
		Button b= new Button("Search");
		HBox hb = new HBox(10);
		hb.setAlignment(Pos.CENTER);
		Label l = new Label("Enter Name You Need To Search :");
		Label label = new Label();
		hb.getChildren().addAll(l,tf,b);
		VBox vb = new VBox(15);
		vb.getChildren().addAll(hb,label);
		vb.setAlignment(Pos.CENTER);
		
		b.setOnAction(e -> {
		    boolean martyrFound = false;

		    for (int i = 0; i < n; i++) {
		    	// check if a Array isnt null , and if the name was entered is found in array
		        if (martyrArr[i] != null && tf.getText().equalsIgnoreCase(martyrArr[i].getName())) {
		            martyrFound = true;
		            label.setText("Martyr Information:\n"+
		                    "Name : "+martyrArr[i].getName() + "\n" +
		                    "Age : "+martyrArr[i].getAge() + "\n" +
		                    "Event Location : "+martyrArr[i].getEventLoc()+"\n"+
		                    "Date of Death : "+martyrArr[i].getDate()+"\n" +
		                    "Gender : "+martyrArr[i].getGender());
		            break; // exit from the loop
		        }
		    }

		    if (!martyrFound) {
		        label.setText("Martyr with name '" + tf.getText() + "' not found.");
		    }
		});
		
		bp.setCenter(vb);
		stage.setScene(scene);
		stage.setTitle("Martyr search");
		stage.show();
		return stage;
	
	}
	
	
	// this method for display martyr count by date/gender/Age
	public Stage displayMartyrCount() {
	    Stage stage = new Stage();
	    BorderPane bp = new BorderPane();
	    Scene scene = new Scene(bp, 500, 600);

	    // create combo box until choose what u need 
	    ComboBox<String> CBox = new ComboBox<>();
	    CBox.getItems().addAll("Age", "Gender", "Date");   // items 
	    CBox.setValue("Select Choice");
	    

	    Button b = new Button("Display Martyr Count");
	    VBox vb = new VBox(15);
	    Label label = new Label();
	    vb.getChildren().addAll(CBox, b, label);
	    vb.setAlignment(Pos.CENTER);

	    // use lambda 
	    b.setOnAction(e -> {
	    	int countM = 0, countF = 0;   // for gender
	    	String selectedChoice = CBox.getValue();

	    	// check if selected in CBox
	    	if (selectedChoice != null) {
	    		// if u choose a gender (need a number of martyrs by gender)
	    	    if (selectedChoice.equals("Gender")) {
	    	        for (int i = 0; i < martyrArr.length; i++) {
	    	            Martyr martyr = martyrArr[i];
	    	            if (martyr != null) {
	    	                String s = martyr.getGender();
	    	                if (s != null) {                    // check if getGender isnt null
	    	                    if (s.equals("M")) {
	    	                        countM++;                   // increase a Male count
	    	                    } else if (s.equals("F")) {
	    	                        countF++;                   // increase a Female count
	    	                    }
	    	                }
	    	            }
	    	        }
	    	        label.setText("Male count : "+countM+"\nFemale count : "+countF);
	    	    }
	    	    // if u choose a age (need the number of martyr by age)
	    	    else if(selectedChoice.equals("Age")) {
	    	    	int SAge = 0 , MAge =0 , LAge = 0, XAge=0 , XLAge=0;  
	    	    	 for (int i = 0; i < martyrArr.length; i++) {
		    	            Martyr martyr = martyrArr[i];
		    	            if (martyr != null) {                        // check if martyr doesn't null
		    	            	int age = martyr.getAge();
		    	            	if (age!= 0) {							 // check if age isnt 0
		    	            		if (age >=1&& age<18) 
		    	            			SAge++;							 // increase SAge 
		    	            		else if(age >= 18 && age < 25)
		    	            			MAge++;							 // increase MAge
		    	            		else if(age >=25 && age <40)
		    	            			LAge++;							 // increase LAge
		    	            		else if (age >= 40 && age <60)
		    	            			XAge++;							 // increase XAge
		    	            		else 
		    	            			XLAge++; 						 // increase XLAge
		    	            	}
		    	            }
	    	    	 }
	    	    	 label.setText("Age 1-18 : "+SAge+"\nAge 18-25 : "+MAge+"\nAge 25-40 : "
	    	    	 +LAge+"\nAge 40-60 : "+XAge+"\nAge Older than 60 : "+XLAge);                 // print it 
	    	   
	    	    // check if u choose a Date (Number of martyrs by date)
	    	    }else if (selectedChoice.equals("Date")) {
	    	        int[] yearCounts = new int[n]; 

	    	        for (int i = 0; i < martyrArr.length; i++) {
	    	            Martyr martyr = martyrArr[i];

	    	            if (martyr != null) {
	    	                String date = martyr.getDate();

	    	                if (!date.isEmpty()) {
	    	                    int year = Integer.parseInt(date.split("/")[2]); // extract year
	    	                    yearCounts[year]++;
	    	                }
	    	            }
	    	        }
	    	        
	    	     // for build the label text with counts for each year
	    	        StringBuilder labelText = new StringBuilder("Martyr Count by Year:\n");
	    	        for (int year = 0; year <yearCounts.length; year++) {
	    	            int count = yearCounts[year];
	    	            if (count > 0) {
	    	                labelText.append("Year ").append(year).append(": ").append(count).append(" martyr").append(count > 1 ? "s" : "").append("\n");
	    	            }
	    	        }
	    	        label.setText(labelText.toString());
	    	    }
	    	    else {
	    	        label.setText("Invalid choice for counting");
	    	    }
	    	    
	    	} else {
	    	    label.setText("Please select a choice");
	    	}
	    });

	    bp.setCenter(vb);
	    stage.setScene(scene);
	    stage.setTitle("Display Martyr Count by Category");
	    stage.show();
	    return stage;
	}
	
	public static void main(String[] args) {

		launch(args);
	}
	

}
