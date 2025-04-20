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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class PalestineMartyrs {
	
	private NodeD firstD;
    private NodeDS firstDS;
    private NodeSS firstSS;
	
	public BorderPane bpD ;
	public BorderPane bpL ;
	private int count ; 
	private int marCount;
	private Random random = new Random();
	private Label label = new Label();
	public String filePath;
	
	// for District Screen
	private Button bID = new Button("Insert District");
	private TextField tfID = new TextField();
	private Button bUD = new Button("Update District");
	private TextArea taD = new TextArea();
	private Button bDD = new Button("Delete District");
	private TextField tfDD = new TextField();
	private Button bSD = new Button("Search Districts"); // for showing all districs 
	private TextField tfSD = new TextField();
	private Button bTMD = new Button ("Total Martyr");
	private Button bNMD = new Button("Number of Martyr");
	private Label lM = new Label();
	private Button bLLD = new Button("Load Location in Loc Sc");
	private DatePicker datePickerDis = new DatePicker();
	
	
	// for Location Screen 
	private Label lDL = new Label("         District");
	private	Label lLL = new Label("            Location");
	private	Button bIL = new Button("Insert Location");
	private	TextField tfILL = new TextField();
	private	Button bUL = new Button("Update Location");
	private	TextArea taU = new TextArea();
	private	Button bDL = new Button("Delete Location");
	private	TextField tfDL = new TextField();
	private	Button bSL = new Button("Search Locations");
	private	TextField tfSLL = new TextField();
	private	TextArea taL = new TextArea();
	private	Button bIML = new Button("Insert Martyr");
	private	Button bUML = new Button("Update Martyr");
	private	Button bSML = new Button("Search Martyr");// by part of his name 
	private	TextField tfSML = new TextField();
	private	Button bNL = new Button("  Next >> ");
	private	Button bND = new Button("Next >>");
	private	Button bPD = new Button("<< Prev ");
	
	private TextField tfN = new TextField();
	private TextField tfA = new TextField();
	private TextField tfD = new TextField();
	private TextField tfL = new TextField();
	private TextField tfG = new TextField();
	private DatePicker datePickerLoc = new DatePicker();
	private ComboBox<String> cbSDL = new ComboBox<>();
	private ComboBox<String> cbDDL = new ComboBox<>();
	private ComboBox<String> cbIDL = new ComboBox<>();
	
	
	
	public PalestineMartyrs() {
		cbSDL.setValue("District");
		cbIDL.setValue("District");
		cbDDL.setValue("District");
		count =0;
		marCount = 0;
		bNL.setMaxWidth(150);
		bID.setMaxWidth(150);
		bUD.setMaxWidth(150);
		bDD.setMaxWidth(150);
		bSD.setMaxWidth(150);
		bNMD.setMaxWidth(150);
		bLLD.setMaxWidth(150);
		bTMD.setMaxWidth(150);
		bIL.setMaxWidth(150);
		bUL.setMaxWidth(150);
		bDL.setMaxWidth(150);
		bSL.setMaxWidth(150);
		bIML.setMaxWidth(150);
		bUML.setMaxWidth(150);
		bSML.setMaxWidth(150);
		bNL.setMaxWidth(150);
		
		bpD = new BorderPane();
		bpL = new BorderPane();
		VBox vbD = new VBox(10);
		VBox vbL = new VBox(10);
		vbL.setAlignment(Pos.CENTER);
		vbD.setAlignment(Pos.CENTER);
		
		GridPane gpD = new GridPane();
		gpD.setVgap(10);
		gpD.setHgap(10);
		
		gpD.add(tfID, 0, 0);
		gpD.add(bID,1,0);
		gpD.add(tfDD,0,1);
		gpD.add(bDD,1,1);
		gpD.add(tfSD,0,2);
		gpD.add(bSD,1,2);
		gpD.add(datePickerDis, 0, 3);
		gpD.add(bTMD, 1, 3);
		gpD.setAlignment(Pos.CENTER);
		HBox hbD = new HBox(10);
		hbD.getChildren().addAll(bPD,bUD,bLLD,bND);
		hbD.setAlignment(Pos.CENTER);
		HBox hbox = new HBox(10);
		ImageView i = new ImageView("gaza.jpg");
		i.setFitWidth(350);
		i.setFitHeight(150);
		hbox.getChildren().addAll(i,gpD);
		hbox.setAlignment(Pos.CENTER);
		vbD.getChildren().addAll(hbox,taD,hbD);
		
		
		GridPane gpL = new GridPane();
		gpL.setVgap(10);
		gpL.setHgap(10);
		
		gpL.add(lDL, 0, 0);
		gpL.add(lLL,1,0);
		gpL.add(cbDDL,0,1);
		gpL.add(tfDL,1,1);
		gpL.add(bDL,2,1);
		gpL.add(cbIDL, 0, 2);
		gpL.add(tfILL,1,2);
		gpL.add(bIL,2,2);
		gpL.add(cbSDL,0,3);
		gpL.add(tfSLL,1,3);
		gpL.add(bSL,2,3);
		gpL.add(tfSML,0,4);
		gpL.add(bSML,1,4);
		gpL.add(bIML,2,4);
		
		gpL.setAlignment(Pos.CENTER);
		HBox hbL = new HBox(10);
		hbL.getChildren().addAll(bUL,bUML,bNL);
		hbL.setAlignment(Pos.CENTER);
		vbL.getChildren().addAll(gpL,taL,hbL);
		
		bID.setOnAction(e -> {
		    String disName = tfID.getText();
		    if (disName.isEmpty()) {
		        taD.setText("The TextField is Empty, Please Enter a Name of District.");
		        return;
		    }
		    
		    NodeD current = firstD; //
		   do {
		        District currentDistrict = (District) current.getData();
		        if (currentDistrict.getDistrictName().equalsIgnoreCase(disName)) {
		            taD.setText("District '"+disName+"' already exists.");
		            tfID.clear();
		            return;
		        }
		        current = current.getNext();
		        if (current == firstD)
		            break;
		    } while (current != firstD);
		  
		    District newDistrict = new District(disName);
		    addDistrict(newDistrict); // here call the add district method
		    taD.setText("District '"+disName+"' Added Successfully");
		    tfID.clear();
		});
		
		//District Button
		bDD.setOnAction(e -> deleteDis(tfDD.getText()));
		bSD.setOnAction(e -> searchDis(tfSD.getText()));
		bLLD.setOnAction(e -> loadFirstLocInDis());
		bND.setOnAction(e -> nextDistrict());
		bPD.setOnAction(e -> previousDistrict());
		bUD.setOnAction(e -> updateDis());
		bTMD.setOnAction(e -> totalMartyrByDate(datePickerDis));
		
		//Location Button
		bIML.setOnAction(e -> insertMartyr());
		bUL.setOnAction(e -> updateLoc());
		
		bSL.setOnAction(e -> searchLoc(tfSLL.getText(), cbSDL.getValue()));
		bDL.setOnAction(e -> deleteLoc(tfDL.getText(), cbDDL.getValue()));
		bIL.setOnAction(e -> insertLoc(tfILL.getText(), cbIDL.getValue()));
		bNL.setOnAction(e -> nextLoc());
		bSML.setOnAction(e -> searchMarByName(tfSML.getText()));
		bUML.setOnAction(e -> updateMar());
		
		bpD.setCenter(vbD);
		bpL.setCenter(vbL);
	}
	
	//method for read a data from data.csv
	 public void readCSVFile(String filePath) {
		 try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			 String line;
			 boolean firstLine = true;
			 while ((line = br.readLine()) != null) {
				 if (firstLine) {
					 firstLine = false;
					 continue;
				 }
				 String[] parts = line.split(",");
				 if (parts.length == 6) {
					 String name = parts[0];
					 String date = parts[1];
					 int age = parts[2].isEmpty() ? random.nextInt(100) + 1 : Integer.parseInt(parts[2]);
					 String loc = parts[3];
					 String dis = parts[4];
					 String gender = parts[5];
					 Martyr martyr = new Martyr(name, date, age, loc, dis, gender);
					 Location location = new Location(loc, martyr);
					 District district = new District(dis, location);
					 addDistrict(district);
					 addLocation(location, district);
					 addMartyr(martyr, district, location);     
					 
				 }
			 }
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }
	   
	public void addDistrict(District dis) {
		// here check if the district already exists
		if (firstD != null) {
		    NodeD exNode = findDis(dis.getDistrictName());
		    if (exNode != null) 
		        return;
		}
		
		NodeD newDis = new NodeD(dis);	
		if (firstD == null) {
			// if the list is empty the new node becomes the first node
			firstD = newDis;
			firstD.setNext(firstD);
			firstD.setPrev(firstD);
			count++;
			return;
		}
		
		NodeD current = firstD;
		do {
			District currentDis = (District) current.getData();
			if (dis.getDistrictName().compareToIgnoreCase(currentDis.getDistrictName()) < 0) {
				NodeD prev = current.getPrev();
				prev.setNext(newDis);
				newDis.setPrev(prev);
				newDis.setNext(current);
				current.setPrev(newDis);
				if (current == firstD) {
					firstD = newDis;
				}
				count++;
				return;
			}
			current = current.getNext();
		  } while (current != firstD);
		
		NodeD last = firstD.getPrev();
		last.setNext(newDis);
		newDis.setPrev(last);
		newDis.setNext(firstD);
		firstD.setPrev(newDis);
		count++;
		updateComboBox();
	}

	// method for add location (nodes) to (CDLL)
	public void addLocation(Location location, District district) {
		
	    NodeD currentDis = findDis(district.getDistrictName());
	    if (currentDis == null) {
	        System.out.println("District not found ");
	        return; 
	    }
	    if (findLoc(location.getLocationName(), currentDis.getData()) != null) {
	        System.out.println("Location '" +location.getLocationName()+"' already exists in district: " + district.getDistrictName());
	        return;  // location is already exisit in district 
	    }

	    NodeDS newLoc = new NodeDS(location);
	    NodeDS firstLoc = currentDis.getData().getFirstLoc();

	    if (firstLoc == null) {
	        newLoc.setNext(newLoc); //make it circular
	        currentDis.getData().setFirstLoc(newLoc);
	        System.out.println("Location added successfully : "+location.getLocationName()+" in district: " + district.getDistrictName());
	    } else {
	        NodeDS lastLocationNode = getLastLoc(firstLoc);
	        lastLocationNode.setNext(newLoc); 
	        newLoc.setNext(firstLoc);
	        System.out.println("Location added successfully : "+location.getLocationName()+" in district: " + district.getDistrictName());
	    }
	}

	//method for created a list (nodes) for a martyrs
	public void addMartyr(Martyr martyr, District district, Location location) {
	    if (firstD == null) {
	        System.out.println("No districts found");
	        return;
	    }

	    NodeD currentDis = firstD;
	    do {
	        if (currentDis.getData().getDistrictName().equalsIgnoreCase(district.getDistrictName())) {
	            NodeDS currentLoc = currentDis.getData().getFirstLoc();
	            if (currentLoc == null) {
	                System.out.println("No locations found in this district");
	                return;
	            }
	            do {
	                if (currentLoc.getData().getLocationName().equalsIgnoreCase(location.getLocationName())) {
	                    NodeSS newMar = new NodeSS(martyr);
	                    NodeSS firstMar = currentLoc.getData().getFirstMar();
	                    if (firstMar == null) {
	                        newMar.setNext(newMar); //make it circular
	                        currentLoc.getData().setFirstMar(newMar);
	                    } else {
	                        NodeSS lastMartyrNode = getLastMar(firstMar);
	                        lastMartyrNode.setNext(newMar);
	                        newMar.setNext(firstMar); //make it circular
	                    }
	                    marCount++;
	                    return;
	                }
	                currentLoc = currentLoc.getNext();
	            } while (currentLoc != currentDis.getData().getFirstLoc());
	            System.out.println("Location not found in this district");
	            return;
	        }
	        currentDis = currentDis.getNext();
	    } while (currentDis != firstD);
	    System.out.println("District not found ");
	}

	// method to get the last node in the list of martyrs 
	private NodeSS getLastMar(NodeSS startNode) {
		NodeSS current = startNode;
		while (current.getNext() != startNode) {
			current = current.getNext();
		}
		return current;
	}
	
	// method to find the last location node in the list of locations 
	private NodeDS getLastLoc(NodeDS startNode) {
		NodeDS current = startNode;
		while (current != null && current.getNext() != startNode) {
			current = current.getNext();
		}
		return current; // return the last node
	}
	    
	public void deleteDis(String dis) {
		if (dis.isEmpty()) {
			taD.setText("The TextField is Empty , Please Enter a Name of District .");
			return;
		}
		if (firstD == null) {
			taD.setText("The List is Null");
			return;
		}
		NodeD current = firstD;
		do {
			District currentDistrict = (District) current.getData();
			if (currentDistrict.getDistrictName().equalsIgnoreCase(dis)) {
				if (count == 1)
					firstD = null;    // if found only one node in the list
				else {
					NodeD prev = current.getPrev();
					NodeD next = current.getNext();
					prev.setNext(next);
					next.setPrev(prev);
					if (current == firstD)
						firstD = next; 
				}
				count--;
				taD.setText("District '"+tfDD.getText()+"' Deleted Successfully");
				tfDD.clear();
				updateComboBox();
				return;
			}
			current = current.getNext();
		} while (current != firstD);
		taD.setText("District "+dis+" Not Found");
		tfDD.clear();
	}
    
    public void searchDis(String dis) {  // method for searching about district and then return information for it
        if (dis.isEmpty()) {
            taD.setText("Please enter the name of the district to search ");
            return;
        }

        NodeD districtNode = findDis(dis);
        if (districtNode != null) {
            District district = (District) districtNode.getData();
            calculateMarStatDis(district);
        } else {
            taD.setText("District '" + dis + "' not found ");
        }
    }

    private NodeD findDis(String dis) {  // method for check if district is found 
        NodeD current = firstD;
        do {
            District curDis = (District) current.getData();
            if (curDis.getDistrictName().equalsIgnoreCase(dis)) {
                return current;
            }
            current = current.getNext();
        } while (current != firstD);
        return null; // District not found
    }
    
    public void calculateMarStatDis(District district) {
        int totalMartyrs = 0;
        int maleMartyrs = 0;
        int femaleMartyrs = 0;
        int totalAge = 0;
        Martyr maxMartyr = null;
        String dateWithMostMartyrs = "";
        int maxMartyrCount = 0;
        NodeD currentDis = firstD;

        while (!currentDis.getData().equals(district)) {
            currentDis = currentDis.getNext();
            if (currentDis == firstD) {
                System.out.println("District not found.");
                return;
            }
        }

        NodeDS currentLoc = currentDis.getData().getFirstLoc();
        if (currentLoc == null) {
            taD.setText("No locations in the district.");
            return;
        }

        do {
            Location location = (Location) currentLoc.getData();
            NodeSS currentMar = location.getFirstMar();

            if (currentMar == null) {
                currentLoc = currentLoc.getNext();
                continue;
            }

            do {
                Martyr martyr = (Martyr) currentMar.getData();
                totalMartyrs++;
                totalAge += martyr.getAge();
                if (martyr.getGender().equalsIgnoreCase("M")) 
                    maleMartyrs++;
                 else if (martyr.getGender().equalsIgnoreCase("F")) 
                    femaleMartyrs++;
                
                if (maxMartyr == null || martyr.getDate().compareTo(maxMartyr.getDate()) > 0) 
                    maxMartyr = martyr;
                
                String currentDate = martyr.getDate();
                int currentCount = 1;
                NodeSS tempMar = currentMar.getNext();
                while (tempMar != location.getFirstMar()) {
                    Martyr tempMartyr = (Martyr) tempMar.getData();
                    if (tempMartyr.getDate().equals(currentDate)) {
                        currentCount++;
                    }
                    tempMar = tempMar.getNext();
                }
                if (currentCount > maxMartyrCount) {
                    maxMartyrCount = currentCount;
                    dateWithMostMartyrs = currentDate;
                }

                currentMar = currentMar.getNext();
            } while (currentMar != location.getFirstMar());

            currentLoc = currentLoc.getNext();
        } while (currentLoc != null && currentLoc != currentDis.getData().getFirstLoc());

        double averageAge;
        if (totalMartyrs == 0) 
        	averageAge = 0;
        else 
        	averageAge = (double) totalAge / totalMartyrs; 
	  
        String formatAVG = String.format("%.2f", averageAge);

        // display and print the result
        String result = "District Name: " + district.getDistrictName() + "\n" +
                "Total number of martyrs: " + totalMartyrs + "\n" +
                "Total number of male martyrs: " + maleMartyrs + "\n" +
                "Total number of female martyrs: " + femaleMartyrs + "\n" +
                "Average martyrs age: " + formatAVG + "\n";

        if (!dateWithMostMartyrs.isEmpty()) 
            result += "Date with the largest number of martyrs: " + dateWithMostMartyrs +
                      " (Martyr No: " + maxMartyrCount + ")";
        else 
            result += "No date with martyrs found."; 
        taD.setText(result);
    }

    
    public void nextDistrict() {  // method to show the next District
        if (firstD == null) {
            System.out.println("No districts available.");
            return;
        }
        firstD = firstD.getNext();
        calculateMarStatDis((District) firstD.getData());
    }

    public void previousDistrict() { // method to show the prev District
        if (firstD == null) {
            System.out.println("No districts available ");
            return;
        }
        firstD = firstD.getPrev();
        calculateMarStatDis((District) firstD.getData());
    }

    public int totalMartyrByDate(DatePicker dp) { // method for enter a date and show the martyr in this date
        int totalMartyrs = 0;
        LocalDate selectedDate = dp.getValue();
        if (selectedDate == null) {
            taD.setText("Please Select a Date");
            return 0;
        }  
        String cities = "";
        NodeD currentDis = firstD;

        do {
            NodeDS currentLoc = currentDis.getData().getFirstLoc();
            do {
                NodeSS currenrtMar = currentLoc.getData().getFirstMar();
                do {
                    Martyr martyr = (Martyr) currenrtMar.getData();
                    LocalDate martyrdomDate = LocalDate.parse(martyr.getDate(), DateTimeFormatter.ofPattern("M/d/yyyy"));
                    if (martyrdomDate.equals(selectedDate)) {
                        totalMartyrs++;
                        cities += currentDis.getData().getDistrictName() + "\n";
                    }
                    currenrtMar = currenrtMar.getNext();
                } while (currenrtMar != currentLoc.getData().getFirstMar());          
                currentLoc = currentLoc.getNext();
            } while (currentLoc != currentDis.getData().getFirstLoc());
            currentDis = currentDis.getNext();
        } while (currentDis != firstD);
        
        taD.setText("The Number Of Martyrs martyred on "+selectedDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"))+" is: "+totalMartyrs+"\nCities: \n"+cities);
        
        return totalMartyrs;
    }

    public Stage updateDis() {  // the interface for the update district
        Label lN = new Label("Current District Name:");
        Label lNN = new Label("New District Name:");
        TextField tfCN = new TextField();
        TextField tfNN = new TextField();
        Button UB = new Button("Update");
        
        BorderPane bp = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(lN, 0, 0);
        gridPane.add(tfCN, 1, 0);
        gridPane.add(lNN, 0, 1);
        gridPane.add(tfNN, 1, 1);
        gridPane.add(UB, 1, 2);
        gridPane.setAlignment(Pos.CENTER);
        
        bp.setCenter(gridPane);
        Scene scene = new Scene(bp, 300, 150);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Update District Name");

        UB.setOnAction(event -> {
        	if (tfCN.getText().isEmpty() || tfNN.getText().isEmpty()) {
        		taD.setText("Please enter both the current and new location data.");
        		return;
  	       	}
        	NodeD current = firstD;
        	boolean found = false;
        	while (current.getNext() != firstD) {
        		District district = (District) current.getData();
        		if (district.getDistrictName().equalsIgnoreCase(tfCN.getText())) {
        			taD.setText("The Update is Succesfully : "+district.getDistrictName()+" -> "+tfNN.getText());
        			district.setDistrictName(tfNN.getText());
        			found = true;
        			break; 
        		}
        		current = current.getNext();
        	}
        	    if (!found) 
        	    	taD.setText("District not found.");
        	    stage.close();
        });
        stage.setTitle("Update District Name");
	    stage.show();
	    return stage;
    }

    public void printDis() {  // method for print District in console
        if (firstD == null) {
            System.out.println("No districts.");
            return;
        }
        NodeD current = firstD;
        do {
            System.out.println(current.getData());
            current = current.getNext();
        } while (current != firstD);
        System.out.println(count+"\n"+marCount);
    }
    
    public void loadFirstLocInDis() {
        if (firstD == null) {
            taL.setText("No districts available.");
            return;
        }
        District dis = (District) firstD.getData();
        NodeDS firstLoc = firstD.getData().getFirstLoc();
        
        if (firstLoc == null) {
            taL.setText("No locations in the first district.");
            return;
        }
        
        Location loc = (Location) firstLoc.getData(); 
        cbSDL.setValue(dis.getDistrictName());
        tfSLL.setText(loc.getLocationName());
        searchLoc(loc.getLocationName() ,dis.getDistrictName() ); // called the method that display the information 
    }
    
    public Stage insertMartyr() {  // create a interface for the insert a martyr
 		Stage stage = new Stage();
 		BorderPane bp = new BorderPane();
 		Scene scene = new Scene (bp,500,500);
 		GridPane gp = new GridPane();
 		
 		Text title = new Text("Add A Martyr");
 		title.setFont(new Font(20));
 		Label lN = new Label("Name : ");
 		Label lA = new Label("Age :");
 		Label lD = new Label("District :");
 		Label lL = new Label("Location :");
 		Label lG = new Label("Gender :");
 		Button addB = new Button("Add");
 		Label la = new Label();
 		
 		// grid Pane for sort the window 
 		gp.add(lN, 0, 0);
 		gp.add(tfN, 1,0);
 		gp.add(lA, 0, 1);
 		gp.add(tfA, 1, 1);
 		gp.add(lD, 0, 2);
 		gp.add(tfD, 1, 2);
 		gp.add(lL, 0, 3);
 		gp.add(tfL, 1, 3);
 		gp.add(lG, 0, 4);
 		gp.add(tfG, 1, 4);
 		gp.add(datePickerLoc, 1, 5);
 		gp.setVgap(10);
 		gp.setHgap(10);
 		gp.setAlignment(Pos.CENTER);
 		
 		// file i need to insert a martyr for it
 		File file = new File("C:\\Users\\mhmds\\Desktop\\M7md\\data.csv");
 		addB.setOnAction(e -> addButton(file));  // lambda exeprition for add button
 		
 		VBox vb = new VBox(20);
 		vb.getChildren().addAll(title,gp,addB);
 		vb.setAlignment(Pos.CENTER);
 		
 		bp.setCenter(vb);
 		stage.setScene(scene);
 		stage.setTitle("Insert Martyr");
 		stage.show();
 		return stage;
 	}
 	
 	
 	// method for add button (add new martyr)
 	public void addButton(File file) {
 	    try {
 	        // get martyr information from text fields
 	        String name = tfN.getText();
 	        String age = tfA.getText();
 	        String dis = tfD.getText(); 
 	        String loc = tfL.getText();
 	        String gender = tfG.getText();
 	        LocalDate selectedDate = datePickerLoc.getValue();

 	        if (selectedDate == null) {
 	            taL.setText("Please select a date.");
 	            return;
 	        }

 	        // Check if the text fields are empty
 	        if (name.isEmpty() || age.isEmpty() || dis.isEmpty() || loc.isEmpty() || gender.isEmpty()) {
 	            taL.setText("Enter all information in boxes. Then press Add.");
 	            return;
 	        }

 	        // Check if age is greater than 0
 	        int a = Integer.parseInt(age);
 	        if (a <= 0) {
 	            taL.setText("Invalid age format. Please enter an age greater than 0.");
 	            return;
 	        }

 	        // Check if gender is either "M" or "F"
 	        if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
 	            taL.setText("Invalid gender format. Please enter M for Male or F for Female.");
 	            return;
 	        }

 	        NodeD districtNode = findDis(dis);
 	        if (districtNode == null) {
 	            taL.setText("District '" + dis + "' not found.");
 	            return;
 	        }

 	        // find the location within the district
 	        NodeDS locationNode = findLoc(loc, districtNode.getData());
 	        if (locationNode == null) {
 	            taL.setText("Location '" + loc + "' not found in district '" + dis + "'.");
 	            return;
 	        }

 	        // use the selected date in your Martyr creation logic
 	        String date = selectedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
 	        Martyr martyr = new Martyr(name, date, a, loc, dis, gender);

 	        // call the addMartyr method to add the martyr
 	        District district = new District (dis);
 	        Location location = new Location(loc);
 	        addMartyr(martyr,district, location);

 	        // clear the text fields
 	        tfN.clear();
 	        tfA.clear();
 	        tfD.clear();
 	        tfL.clear();
 	        tfG.clear();
 	        taL.setText("Martyr added successfully.");
 	    } catch (NumberFormatException e) {
 	        taL.setText("Error: " + e.getMessage());
 	    }
 	}

 	public Stage updateLoc() {    // interface for update a location
 	    Label lN = new Label("Current Location Data:");
 	    Label lNN = new Label("New Location Data:");
 	    TextField tfCN = new TextField();
 	    TextField tfNN = new TextField();
 	    Button UB = new Button("Update");

 	    BorderPane bp = new BorderPane();
 	    GridPane gp = new GridPane();
 	    gp.setHgap(10);
 	    gp.setVgap(10);

 	    // Add components to the layout
 	    gp.add(lN, 0, 0);
 	    gp.add(tfCN, 1, 0);
 	    gp.add(lNN, 0, 1);
 	    gp.add(tfNN, 1, 1);
 	    gp.add(UB, 1, 2);
 	    gp.setAlignment(Pos.CENTER);
 	    
 	    bp.setCenter(gp);
 	    Scene scene = new Scene(bp, 300, 150);
 	    Stage stage = new Stage();
 	    stage.setScene(scene);
 	    stage.setTitle("Update Location Data");

 	    UB.setOnAction(event -> {  // lambda expresion for update
 	        String currentName = tfCN.getText();
 	        String newName = tfNN.getText();

 	        if (currentName.isEmpty() || newName.isEmpty()) {
 	            taL.setText("Please enter both the current and new location data.");
 	            return;
 	        }

 	        boolean found = false;
 	        NodeD currentDis = firstD;
 	        if (currentDis == null) {
 	            taL.setText("No districts found.");
 	            return;
 	        }

 	        do {
 	            NodeDS currentLoc = currentDis.getData().getFirstLoc();
 	            if (currentLoc == null) {
 	                taL.setText("No locations found in this district.");
 	               currentDis = currentDis.getNext();
 	                continue;
 	            }
 	            do {
 	                Location location = (Location) currentLoc.getData();
 	                if (location.getLocationName().equalsIgnoreCase(currentName)) {
 	                    location.setLocationName(newName);// location is found
 	                    found = true;
 	                    taL.setText("The Update is Successfully: " + currentName + " -> " + newName);
 	                    stage.close();
 	                    return;
 	                }
 	               currentLoc = currentLoc.getNext();
 	            } while (currentLoc != currentDis.getData().getFirstLoc());
 	        } while (currentDis != firstD && !found);
 	        if (!found) {
 	            taL.setText("Location '" + currentName + "' not found.");
 	        }
 	    });

 	    stage.setTitle("Update Location Data");
 	    stage.show();
 	    return stage;
 	}
 	
 	public void insertLoc(String locName, String disName) {  // method for insert new Martyr
 	    NodeD currentDis = findDis(disName);
 	    if(tfILL.getText().isEmpty() || cbIDL.getValue().isEmpty()) {
 	        taL.setText("The Text's is Empty , Please enter in it ");
 	        return;
 	    }
 	    if (currentDis == null) {
 	       taL.setText("District '" + disName + "' not found.");
 	       tfILL.clear();
 	       return;
 	    }
 	    Location newLoc = new Location(locName);
 	    addLocation(newLoc, currentDis.getData()); // called addLocation method
 	    
 	    taL.setText("Location '" + locName + "' added to district '" + disName + "'.");
 	    tfILL.clear();
 	}
 	
 	public void deleteLoc(String locName, String disName) {
 		try {
 			if (locName.isEmpty() || disName.isEmpty()) {
 				taL.setText("Please enter both the location and district names.");
 				return;
 			}
 			
 			NodeD currenrDis = findDis(disName);
 			if (currenrDis == null) {
 				taL.setText("District '" + disName + "' not found.");
 				return;
 			}
 	    
 			NodeDS currentLoc = currenrDis.getData().getFirstLoc();
 			NodeDS prevLoc = null;
 			boolean found = false;

 			do {
 				if (currentLoc.getData().getLocationName().equalsIgnoreCase(locName)) {
 					found = true;
 					break;
 				}
 				prevLoc = currentLoc;
 				currentLoc = currentLoc.getNext();
 			} while (currentLoc != currenrDis.getData().getFirstLoc());

 			if (!found) {
 				taL.setText("Location '" + locName + "' not found in district '" + disName + "'.");
 				return;
 			}

 			if (prevLoc != null) 
 				prevLoc.setNext(currentLoc.getNext());
 			else {
 				currenrDis.getData().setFirstLoc(currentLoc.getNext());
 			}

 			if (currentLoc.getNext() != null) {
 				NodeDS lastLocation = getLastLoc(currenrDis.getData().getFirstLoc());
 				lastLocation.setNext(currenrDis.getData().getFirstLoc());
 			}

 			tfDL.clear();
 			taL.setText("Location '" + locName + "' deleted from district '" + disName + "'.");
 			
 		}catch(NullPointerException e ) {
 			System.out.println(e.getMessage());
 		}	
 	}	
 	
 	private NodeDS findLoc(String locName, District district) { // method for searching about the location
 	    NodeD dis = findDis(district.getDistrictName());
 	    if (dis == null) {
 	        System.out.println("District '" + district.getDistrictName() + "' not found.");
 	        return null;
 	    }

 	    NodeDS currentLoc = dis.getData().getFirstLoc();
 	    if (currentLoc == null) {
 	        System.out.println("No locations found in district: " + district.getDistrictName());
 	        return null;
 	    }
 	    do {
 	        if (currentLoc.getData().getLocationName().equalsIgnoreCase(locName)) {
 	            return currentLoc;
 	        }
 	       currentLoc = currentLoc.getNext();
 	    } while (currentLoc != dis.getData().getFirstLoc());

 	    System.out.println("Location '" + locName + "' not found in district: " + district.getDistrictName());
 	    return null;
 	}

 	public void searchLoc(String locName, String disName) { // method for display the info for the loc
 	    if (locName.isEmpty() || disName.isEmpty()) {
 	        taL.setText("Please enter both the location and district names.");
 	        return;
 	    }

 	    NodeD currentDis = findDis(disName);
 	    if (currentDis == null) {
 	        taL.setText("District '" + disName + "' not found ");
 	        return;
 	    }

 	    NodeDS locationNode = findLoc(locName, currentDis.getData());
 	    if (locationNode == null) {
 	        taL.setText("Location '" + locName + "' not found in district '" + disName + "'.");
 	        return;
 	    }
 	   calculateMarStaLoc(disName, locName);  // called the method that calculate a totals in location
 	}

 	public void nextLoc() {  // method for Navigate (next) between locations in a district
 	    String districtName = cbSDL.getValue();
 	    String locationName = tfSLL.getText();

 	    NodeD districtNode = findDis(districtName);
 	    if (districtNode == null) {
 	        taL.setText("District '" + districtName + "' not found ");
 	        return;
 	    }

 	    NodeDS currentLocationNode = findLoc(locationName, districtNode.getData());
 	    if (currentLocationNode == null) {
 	        taL.setText("Location '" + locationName + "' not found in district '" + districtName + "'.");
 	        return;
 	    }

 	    currentLocationNode = currentLocationNode.getNext();
 	    if (currentLocationNode == districtNode.getData().getFirstLoc()) 
 	        System.out.println("End of locations reached , now return the begining ");

 	   calculateMarStaLoc(districtName, currentLocationNode.getData().getLocationName()); // called a method that calculate a totals in locations
 	    cbSDL.setValue(districtName);
 	    tfSLL.setText(currentLocationNode.getData().getLocationName());
 	}

 	// this method for calculated a total of martyrs and m,f in a locations 
 	public void calculateMarStaLoc(String dis, String locationName) {
 		try{
 			NodeD currentDis = findDis(dis);
 			if (currentDis == null) {
 				taL.setText("District '" + dis + "' not found ");
 				return;
 			}
 	    
 			NodeDS currentLoc = findLoc(locationName, currentDis.getData());
 			if (currentLoc == null) {
 				taL.setText("Location '" + locationName + "' not found within district '" + dis + "'.");
 				return;
 			}
 			int totalMartyrs = 0;
 			int maleMartyrs = 0;
 			int femaleMartyrs = 0;
 			int totalAge = 0;
 			Martyr oldesMar = null;
 			Martyr youngMar = null;

 			NodeSS currentMar = currentLoc.getData().getFirstMar();
 			do {
 				Martyr martyr = (Martyr) currentMar.getData();
 				totalMartyrs++;
 				if (martyr.getGender().equalsIgnoreCase("M")) 
 					maleMartyrs++;
 				else if (martyr.getGender().equalsIgnoreCase("F")) 
 					femaleMartyrs++;
 	      
 				totalAge += martyr.getAge();    
 				if (oldesMar == null || martyr.getAge() > oldesMar.getAge()) 
 					oldesMar = martyr;
 	       
 				if (youngMar == null || martyr.getAge() < youngMar.getAge()) 
 					youngMar = martyr;
 	       
 				currentMar = currentMar.getNext();
 			} while (currentMar != currentLoc.getData().getFirstMar());
 	    
 			double averageAge;
 			if (totalMartyrs == 0) 
 				averageAge = 0;
 			else 
 				averageAge = (double) totalAge / totalMartyrs; 
 	  
 			// display and print the result
 			String result = "Statistics for Location: " + locationName + " (District: " + dis + ")\n" +
 					"Total number of martyrs: " + totalMartyrs + "\n" +
 					"Total number of male martyrs: " + maleMartyrs + "\n" +
 					"Total number of female martyrs: " + femaleMartyrs + "\n" +
 					"Average martyrs age: " + String.format("%.2f", averageAge) + "\n";
 			if (oldesMar != null) 
 				result += "Oldest martyr: " + oldesMar.getName() + " (Age: " + oldesMar.getAge() + ")\n";
 			if (youngMar != null)
 				result += "Youngest martyr: " + youngMar.getName() + " (Age: " + youngMar.getAge() + ")\n";

 			taL.setText(result);
 		}catch(NullPointerException e) {
 			System.out.println(e.getMessage());
 		}
	}
 	
 	public void searchMarByName(String name) {
 	    if (name.isEmpty()) {
 	        taL.setText("Please enter in a search Text's ");
 	        return;
 	    }
 	    StringBuilder result = new StringBuilder();
 	    NodeD currentDis = firstD;
 	    while (currentDis != null&&currentDis.getNext() !=firstD) {
 	        NodeDS currentLoc = currentDis.getData().getFirstLoc();
 	        while (currentLoc != null && currentLoc.getNext() !=currentDis.getData().getFirstLoc()) {
 	            NodeSS currentMar = currentLoc.getData().getFirstMar();
 	            while (currentMar != null && currentMar.getNext() !=currentLoc.getData().getFirstMar()) {
 	                Martyr martyr = (Martyr) currentMar.getData();
 	                if (martyr.getName().toLowerCase().contains(name.toLowerCase())) {
 	                    result.append("Name : ").append(martyr.getName()).append("\n");
 	                    result.append("Location : ").append(currentLoc.getData().getLocationName()).append("\n");
 	                    result.append("District : ").append(currentDis.getData().getDistrictName()).append("\n\n");
 	                }
 	               currentMar = currentMar.getNext();
 	            }
 	            currentLoc = currentLoc.getNext();
 	        }
 	        currentDis = currentDis.getNext();
 	    }

 	    if (result.length()>0) {
 	        taL.setText("Search Results : \n"+result.toString());
 	    } else {
 	        taL.setText("No Martyr records found");
 	    }
 	}
 	
 	public boolean removeMartyr(String martyrName) {
 	    NodeD currentDis = firstD;
 	    boolean found = false;
 	    do {
 	        NodeDS currentLoc = currentDis.getData().getFirstLoc();
 	        NodeSS prevMar = null;
 	        NodeSS currentMar = currentLoc.getData().getFirstMar();
 	        do {
 	            while (currentMar != currentLoc.getData().getFirstMar()) {
 	                Martyr martyr = (Martyr) currentMar.getData();
 	                if (martyr.getName().equalsIgnoreCase(martyrName)) {
 	                    if (prevMar == null) {
 	                        currentLoc.getData().setFirstMar(currentMar.getNext());
 	                    } else {
 	                        prevMar.setNext(currentMar.getNext());
 	                    }
 	                    found = true;
 	                    break;
 	                }
 	                prevMar = currentMar;
 	                currentMar = currentMar.getNext();
 	            }
 	            if (found) break;
 	            currentLoc = currentLoc.getNext();
 	        } while (currentLoc != null && currentLoc != currentDis.getData().getFirstLoc());
 	        if (found) break;
 	        currentDis = currentDis.getNext();
 	    } while (currentDis != null && currentDis != firstD);
 	    return found;
 	}

 	public Stage updateMar() {
 	    Label lN = new Label("Martyr Name:");
 	    Label lNN = new Label("New Martyr Name:");
 	    TextField tfCN = new TextField();
 	    TextField tfNN = new TextField();
 	    Button UB = new Button("Update");
 	    Button DB = new Button("Delete");

 	    BorderPane bp = new BorderPane();
 	    GridPane gridPane = new GridPane();
 	    gridPane.setHgap(10);
 	    gridPane.setVgap(10);

 	    gridPane.add(lN, 0, 0);
 	    gridPane.add(tfCN, 1, 0);
 	    gridPane.add(lNN, 0, 1);
 	    gridPane.add(tfNN, 1, 1);
 	    gridPane.setAlignment(Pos.CENTER);
 	    HBox hb = new HBox(10);
 	    hb.getChildren().addAll(UB, DB);
 	    hb.setAlignment(Pos.CENTER);
 	    VBox vb = new VBox(10);
 	    vb.getChildren().addAll(gridPane, hb);
 	    vb.setAlignment(Pos.CENTER);

 	    bp.setCenter(vb);
 	    Scene scene = new Scene(bp, 300, 150);
 	    Stage stage = new Stage();
 	    stage.setScene(scene);
 	    stage.setTitle("Update / Delete Martyr");

 	   UB.setOnAction(event -> {
 	        String currentMartyrName = tfCN.getText();
 	        String newMartyrName = tfNN.getText();
 	        if (currentMartyrName.isEmpty() || newMartyrName.isEmpty()) {
 	            taL.setText("Please enter both the current and new martyr names.");
 	            return;
 	        }

 	        NodeD currentDis = firstD;
 	        boolean found = false;
 	        do {
 	            NodeDS currentLoc = currentDis.getData().getFirstLoc();
 	            do {
 	                NodeSS currentMar = currentLoc.getData().getFirstMar();
 	                while (currentMar != currentLoc.getData().getFirstMar()) {
 	                    Martyr martyr = (Martyr) currentMar.getData();
 	                    if (martyr.getName().equalsIgnoreCase(currentMartyrName)) {
 	                        taL.setText("Martyr name updated successfully: " + martyr.getName() + " -> " + newMartyrName);
 	                        martyr.setName(newMartyrName);
 	                        found = true;
 	                        break;
 	                    }
 	                    currentMar = currentMar.getNext();
 	                }
 	                if (found) break;
 	                currentLoc = currentLoc.getNext();
 	            } while (currentLoc != null && currentLoc != currentDis.getData().getFirstLoc());
 	            if (found) break;
 	            currentDis = currentDis.getNext();
 	        } while (currentDis != null && currentDis != firstD);
 	        if (!found) {
 	            taL.setText("Martyr not found.");
 	        }
 	        stage.close();
 	    });

 	  DB.setOnAction(event -> {
 		    String currentMartyrName = tfCN.getText();
 		    if (currentMartyrName.isEmpty()) {
 		        taL.setText("Please enter the martyr name to delete.");
 		        return;
 		    }

 		    NodeD currentDis = firstD;
 		    boolean found = false;
 		    while (currentDis.getNext() != firstD) {
 		        NodeDS currentLoc = currentDis.getData().getFirstLoc();
 		        while (currentLoc != currentDis.getData().getFirstLoc()) {
 		            NodeSS currentMar = currentLoc.getData().getFirstMar();
 		            while (currentMar != currentLoc.getData().getFirstMar()) {
 		                Martyr martyr = (Martyr) currentMar.getData();
 		                if (martyr.getName().equalsIgnoreCase(currentMartyrName)) {
 		                    taL.setText("Martyr '" + martyr.getName() + "' deleted successfully.");

 		                    removeMartyr(currentMar.getData().getName());
 		                    found = true;
 		                    break;
 		                }
 		                currentMar = currentMar.getNext();
 		            }
 		            currentLoc = currentLoc.getNext();
 		        }
 		        currentDis = currentDis.getNext();
 		    }
 		    if (!found) {
 		        taL.setText("Martyr not found.");
 		    }
 		    stage.close();
 		});

 	    stage.setTitle("Update / Delete Martyr Name");
 	    stage.show();
 	    return stage;
 	}
 	
 	private void updateComboBox() {
 	    cbSDL.getItems().clear();
 	    cbDDL.getItems().clear();
 	    cbIDL.getItems().clear();

 	    NodeD currenttt = firstD;
 	    do {
 	        District currentDistrict = (District) currenttt.getData();
 	        cbSDL.getItems().add(currentDistrict.getDistrictName());
 	        cbDDL.getItems().add(currentDistrict.getDistrictName());
 	        cbIDL.getItems().add(currentDistrict.getDistrictName());
 	        currenttt = currenttt.getNext();
 	    } while (currenttt != firstD);
 	}
 	
}