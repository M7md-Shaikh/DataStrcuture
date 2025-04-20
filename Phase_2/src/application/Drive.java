package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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

public class Drive {
	
	private DateNode currentSelectedDate;
	private LocNode currentLocation;
    private Queue locationQueue;
    private Stack previousLocations;	
	
    DisNav disNav = new DisNav();
    
    DateTree dateTree = new DateTree();
	public DisTree disTree = new DisTree();
	private FileChooser fileChooser = new FileChooser();
    private LocTree locTree = new LocTree();    
    private Random random;
    public int countDis = 0;
    public int count = 0;
    
    // For District Screen 
    
    public BorderPane bpD ;
    TextField tfNND = new TextField();
	private Button bID = new Button("Insert District");
	private TextField tfID = new TextField();
	private Button bUD = new Button("Update District");
	private TextArea taD = new TextArea();
	private Button bDD = new Button("Delete District");
	private Button bSD = new Button("Search Districts"); 
	private TextField tfSD = new TextField();
	private Button bNMD = new Button("Number of Martyr");
	private Label lM = new Label();
	private TextField tfSDD = new TextField();

	
	private	Button bND = new Button("Next >>");
	private	Button bPD = new Button("<< Prev ");
	
	private ComboBox<String> cbDDD = new ComboBox<>();
	private ComboBox<String> cbUDD= new ComboBox<>();

	
	
    // For Location Screen 

    private TextField tfNNL = new TextField();
	public BorderPane bpL ;
	private Label lDL = new Label("         District");
	private	Label lLL = new Label("            Location");
	private	Button bIL = new Button("Insert Location");
	private	TextField tfILL = new TextField();
	private	Button bUL = new Button("Update Location");
	private	Button bDL = new Button("Delete Location");
	private	TextArea taL = new TextArea();
	private	Button bNL = new Button("  Next >> ");
	private	Button bPL = new Button(" << Previous ");

    ComboBox<String> cbLoc= new ComboBox<>();
	private ComboBox<String> cbDDL = new ComboBox<>();
	private ComboBox<String> cbIDL = new ComboBox<>();
	private ComboBox<String> cbDLL = new ComboBox<>();
	private ComboBox<String> cbDis = new ComboBox<>();

	
	
    // For Martyr Screen 
	public BorderPane bpM = new BorderPane();
	private	Button bIM = new Button("Insert Martyr");
	private	Button bSM = new Button("Search Martyr");// by part of his name 
	private	TextField tfSM = new TextField();
	private	Button bUM = new Button("Update Martyr");
	public TextArea taM = new TextArea();
	private	Button bNM = new Button("Next >>");
	private	Button bPM = new Button("<< Previous");
	private ComboBox<String> cbUM= new ComboBox<>();


	//  for insert martyr
	private TextField tfN = new TextField();
	private TextField tfA = new TextField();
	private TextField tfD = new TextField();
	private TextField tfL = new TextField();
	private TextField tfG = new TextField();
	private DatePicker datePickerMar = new DatePicker();
	
    
	
	public Drive() {
    	
		currentSelectedDate = null;
		bID.setMaxWidth(150);
		bUD.setMaxWidth(150);
		bDD.setMaxWidth(150);
		bSD.setMaxWidth(150);
		bNMD.setMaxWidth(150);

		cbDDD.setValue("District");
		cbDDD.setMaxWidth(150);
		
		bpD = new BorderPane();
		VBox vbD = new VBox(10);
		vbD.setAlignment(Pos.CENTER);
		
		GridPane gpD = new GridPane();
		gpD.setVgap(10);
		gpD.setHgap(10);
		
		gpD.add(tfID, 0, 0);
		gpD.add(bID,1,0);
		gpD.add(cbDDD,0,1);
		gpD.add(bDD,1,1);
		gpD.add(tfSDD, 0, 2);
		gpD.setAlignment(Pos.CENTER);
		HBox hbD = new HBox(10);
		hbD.getChildren().addAll(bPD,bUD,bND);
		hbD.setAlignment(Pos.CENTER);
		HBox hbox = new HBox(10);
		ImageView i = new ImageView("gaza.jpg");
		i.setFitWidth(350);
		i.setFitHeight(150);
		hbox.getChildren().addAll(i,gpD);
		hbox.setAlignment(Pos.CENTER);
		vbD.getChildren().addAll(hbox,taD,hbD);
		
		bID.setOnAction(e -> insertDis(tfID.getText()));
		bUD.setOnAction(e -> updateDis());
		bDD.setOnAction(e -> warningDeDis());
		
		bND.setOnAction(e -> navigateNext());
		
		bPD.setOnAction(e -> navigatePrevious());
		bpD.setCenter(vbD);
	
		
		/*** Location ***/
		
		cbIDL.setValue("District");
		cbDDL.setValue("District");
		cbDLL.setValue("Location");
		cbDLL.setMaxWidth(150);
		
		bNL.setMaxWidth(150);
		bIL.setMaxWidth(150);
		bUL.setMaxWidth(150);
		bDL.setMaxWidth(150);
		bPL.setMaxWidth(150);
		
		bpL = new BorderPane();
		VBox vbL = new VBox(10);
		vbL.setAlignment(Pos.CENTER);
		
		GridPane gpL = new GridPane();
		gpL.setVgap(10);
		gpL.setHgap(10);
		
		gpL.add(lDL, 0, 0);
		gpL.add(lLL,1,0);
		gpL.add(cbDDL,0,1);
		gpL.add(cbDLL,1,1);
		gpL.add(bDL,2,1);
		gpL.add(cbIDL, 0, 2);
		gpL.add(tfILL,1,2);
		gpL.add(bIL,2,2);

		gpL.setAlignment(Pos.CENTER);
		HBox hbL = new HBox(10);
		hbL.getChildren().addAll(bPL,bUL,bNL);
		hbL.setAlignment(Pos.CENTER);
		vbL.getChildren().addAll(gpL,taL,hbL);
		
		cbDDL.setOnAction(e -> {
		    String selectedDistrict = cbDDL.getValue();
		    updateLocBox(selectedDistrict, cbDLL);
		});
		
		bIL.setOnAction(e -> insertLoc(cbIDL.getValue() , tfILL.getText()));
		
		bUL.setOnAction(e -> updateLoc());
		
		bNL.setOnAction(e -> navigateNextLocation());
		bPL.setOnAction(e -> navigatePreviousLocation());
		
		bDL.setOnAction(e -> warningDeLoc());
		bpL.setCenter(vbL);
		
		/*** Martyr ***/ 
		
		bIM.setMaxWidth(150);
		bUM.setMaxWidth(150);
		bSM.setMaxWidth(150);
		bNM.setMaxWidth(150);
		bPM.setMaxWidth(150);
		
		GridPane gp = new GridPane();
		gp.add(tfSM, 0, 0);
		gp.add(bSM, 1, 0);
		gp.add(bIM, 0, 1);
		gp.add(bUM, 1, 1);
 		gp.setAlignment(Pos.CENTER);
 		gp.setVgap(10);
 		gp.setHgap(10);
 		HBox hb = new HBox(10);
 		hb.getChildren().addAll(bPM,bNM);
 		hb.setAlignment(Pos.CENTER);
 		
 		VBox vb = new VBox(10);
 		vb.setAlignment(Pos.CENTER);
 		vb.getChildren().addAll(gp,taM,hb);
		
		bIM.setOnAction(e -> insertMartyr());
		bUM.setOnAction(e -> updateMar());
		bSM.setOnAction(e -> searchMarByName(tfSM.getText() , disTree.getRoot()));
		
		bNM.setOnAction(e -> navigateNextDate());

		bPM.setOnAction(e -> navigatePreviousDate());

		
		bpM.setCenter(vb);

	}	
	
    public void handleNew(Stage primaryStage) {
        fileChooser.setTitle("Create New CSV File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File newFile = fileChooser.showSaveDialog(primaryStage);
        if (newFile != null) {
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    public void handleSave(Stage primaryStage) {
        File selectedFile = fileChooser.showSaveDialog(primaryStage);
        if (selectedFile != null) {
            try {
                FileWriter fileWriter = new FileWriter(selectedFile);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    

    public void handleOpen(Stage primaryStage) {
    	DisTree d = new DisTree();
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            try {
                readCSVFile(selectedFile.getAbsolutePath());
                print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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
                    String[] dateParts = parts[1].split("/");
                    int month = Integer.parseInt(dateParts[0]);
                    int day = Integer.parseInt(dateParts[1]);
                    int year = Integer.parseInt(dateParts[2]);
                    Date date = new Date(year, month, day);
                    random = new Random();
                    int age = parts[2].isEmpty() ? random.nextInt(100) + 1 : Integer.parseInt(parts[2]);
                    String loc = parts[3];
                    String dis = parts[4];
                    String gender = parts[5];
                    Martyr martyr = new Martyr(name, date, age, loc, dis, gender);
                    Location location = new Location(loc);
                    District district = new District(dis, location);
                    DateNode dateNode = new DateNode(date);
                    addDis(district);
                    addLoc(location, district);
                    addDa(date,location,martyr);
                    addMartyr(martyr,dateNode);

                }
            }
            disNav.pushLeftNodes(disTree.root);
            updateDistrictComboBoxes();
            
        } catch (FileNotFoundException e) {
        	System.out.println(e.getMessage());
        } catch (IOException e) {
        	System.out.println(e.getMessage());
        }
    }
    
    
    public void addDis(District dis) {
        DisNode existingNode = findDis(dis.getDistrictName());
        if (existingNode != null) {
            taD.setText("District already exists: " + dis.getDistrictName() +"\n");
            return; 
        }
        disTree.root = addDistrict(disTree.root, dis);
        taD.setText("District added successfully: " + dis.getDistrictName()+"\n");
    }

    private DisNode addDistrict(DisNode current, District dis) {
        if (current == null) {
        	countDis++;
            return new DisNode(dis);
        }

        int compareResult = dis.getDistrictName().compareToIgnoreCase(current.getData().getDistrictName());
        if (compareResult < 0) {
            current.setLeft(addDistrict(current.getLeft(), dis));
        } else if (compareResult > 0) {
            current.setRight(addDistrict(current.getRight(), dis));
        }
        return current;
    }

    public DisNode findDis(String districtName) {
        return findDistrictNode(disTree.root, districtName);
    }

    public DisNode findDistrictNode(DisNode node, String districtName) {
        if (node == null) {
            taD.setText("District Not Found" + districtName +"\n");
            return null;
        }
        int compareResult = districtName.compareToIgnoreCase(node.getData().getDistrictName());

        if (compareResult == 0) {
            return node;
        } else if (compareResult < 0) {
            return findDistrictNode(node.getLeft(), districtName);
        } else {
            return findDistrictNode(node.getRight(), districtName);
        }
    }
    
    
    
    public void insertDis(String dis) {
	    String districtName = dis.trim();

	    if (!districtName.isEmpty()) {
	        District district = new District(districtName);
	        DisNode dn= new DisNode(district);
	        
	        DisNode districtNode = findDis(districtName);
	        if (districtNode == null) {
		        addDis(district);
	            updateDistrictComboBoxes();
		        taD.setText("District '" + districtName + "' added successfully."+"\n");
	        } else {
	        	taD.setText("Failed to add district '" + districtName + "'."+"\n");
	        }
	    } else {
	    	taD.setText("Please enter a valid district name."+"\n");
	    }
	}
    
    public Stage warningDeDis() {
	    Label label = new Label("Are you sure you want to Delete the district ?");
	    Button bY = new Button("Yes");
	    Button bN = new Button("No");
	    bY.setMaxWidth(150);
	    bN.setMaxWidth(150);

	    
	    BorderPane bp = new BorderPane();
	    Scene scene = new Scene(bp, 300, 100);
	    Stage stage = new Stage();
	    stage.setScene(scene);
	    

	    bY.setOnAction(event -> {
	    	deleteDistrict(cbDDD.getValue());
	    	stage.close();
	    });
	    
	    HBox hb = new HBox(10);
	    hb.getChildren().addAll(bY,bN);
	    hb.setAlignment(Pos.CENTER);
	    
	    VBox vb = new VBox(10);
	    vb.getChildren().addAll(label,hb);
	    vb.setAlignment(Pos.CENTER);
	    
	    bN.setOnAction(e -> stage.close());
	    
	    bp.setCenter(vb);
	    tfNND.clear();
	    stage.setTitle("Warning");
	    stage.show();
	    return stage;
	}
    
    
    public Stage updateDis() {
	    Label lN = new Label("Current District Name:");
	    Label lNN = new Label("New District Name:");
	    Button UB = new Button("Update");
	    cbUDD.setValue("District");
	    cbUDD.setMaxWidth(150);
	    
	    BorderPane bp = new BorderPane();
	    GridPane gridPane = new GridPane();
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);

	    gridPane.add(lN, 0, 0);
	    gridPane.add(cbUDD, 1, 0);
	    gridPane.add(lNN, 0, 1);
	    gridPane.add(tfNND, 1, 1);
	    gridPane.add(UB, 1, 2);
	    gridPane.setAlignment(Pos.CENTER);

	    bp.setCenter(gridPane);
	    Scene scene = new Scene(bp, 300, 150);
	    Stage stage = new Stage();
	    stage.setScene(scene);
	    stage.setTitle("Update District Name");

	    UB.setOnAction(event -> warningUpDis());
	    
	    stage.setTitle("Update District Name");
	    stage.show();
	    return stage;
	}
  
    public Stage warningUpDis() {
	    Label label = new Label("Are you sure you want to update the district name?");
	    Button bY = new Button("Yes");
	    Button bN = new Button("No");
	    bY.setMaxWidth(150);
	    bN.setMaxWidth(150);

	    
	    BorderPane bp = new BorderPane();
	    Scene scene = new Scene(bp, 300, 100);
	    Stage stage = new Stage();
	    stage.setScene(scene);
	    

	    bY.setOnAction(event -> {
	        if (cbUDD.getValue().isEmpty() || tfNND.getText().isEmpty()) {
	            taD.setText("Please enter both the current and new district names.");
	            return;
	        }

	        DisNode current = findDis(cbUDD.getValue());
	        if (current != null) {
	            District district = current.getData();
	            taD.setText("The Update is Successfully: " + district.getDistrictName() + " -> " + tfNND.getText());
	            district.setDistrictName(tfNND.getText());
	            updateDistrictComboBoxes();
	        } else {
	            taD.setText("District not found.");
	        }

	        stage.close();
	    });
	    
	    HBox hb = new HBox(10);
	    hb.getChildren().addAll(bY,bN);
	    hb.setAlignment(Pos.CENTER);
	    
	    VBox vb = new VBox(10);
	    vb.getChildren().addAll(label,hb);
	    vb.setAlignment(Pos.CENTER);
	    
	    bN.setOnAction(e -> stage.close());
	    
	    bp.setCenter(vb);
	    tfNND.clear();
	    stage.setTitle("Warning");
	    stage.show();
	    return stage;
	}
    
    private void updateDistrictComboBoxes() {
        cbDDD.getItems().clear();
        cbIDL.getItems().clear();
        cbDDL.getItems().clear();
        cbDis.getItems().clear();
        cbUDD.getItems().clear();

        traverseDistricts(disTree.getRoot());
    }

    private void traverseDistricts(DisNode node) {
        if (node != null) {
            if (!cbDDD.getItems().contains(node.getData().getDistrictName())) {
                cbDDD.getItems().add(node.getData().getDistrictName());
            }
            if (!cbDDL.getItems().contains(node.getData().getDistrictName())) {
                cbDDL.getItems().add(node.getData().getDistrictName());
            }
            if (!cbIDL.getItems().contains(node.getData().getDistrictName())) {
                cbIDL.getItems().add(node.getData().getDistrictName());
            }
            
            if (!cbDis.getItems().contains(node.getData().getDistrictName())) {
            	cbDis.getItems().add(node.getData().getDistrictName());
            }
            
            if (!cbUDD.getItems().contains(node.getData().getDistrictName())) {
            	cbUDD.getItems().add(node.getData().getDistrictName());
            }

            traverseDistricts(node.getLeft());
            traverseDistricts(node.getRight());
        }
    }


    public void deleteDistrict(String districtName) {
        DisNode districtNode = findDis(districtName);
        if (districtNode != null) {
            disTree.root = deleteDistrictNode(disTree.root, districtName);
            updateDistrictComboBoxes();
            taD.setText("District '" + districtName + "' has been deleted."+"\n");
        } 
    }

    private DisNode deleteDistrictNode(DisNode node, String districtName) {
        if (node == null) {
            return null;
        }

        int compareResult = districtName.compareToIgnoreCase(node.getData().getDistrictName());
        
        if (compareResult == 0) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }

            DisNode successor = findMinimum(node.getRight());
            node.getData().setDistrictName(successor.getData().getDistrictName());
            node.setRight(deleteDistrictNode(node.getRight(), successor.getData().getDistrictName()));
            return node;
        }
        
        if (compareResult < 0) {
            node.setLeft(deleteDistrictNode(node.getLeft(), districtName));
        } 
        else {
            node.setRight(deleteDistrictNode(node.getRight(), districtName));
        }
        return node;
    }

    private DisNode findMinimum(DisNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
 


    private void loadLoc(String selectedDistrict) {
    	DisNode districtNode = findDis(selectedDistrict);  // Assuming findDis method exists
    	if (districtNode != null) {
    		currentLocation = districtNode.getLocTree().getRoot();
    		locationQueue = new Queue(100);  // Reset the queue
    		previousLocations = new Stack(100);  // Reset the stack
    		if (currentLocation != null) {
    			locationQueue.enQueue(currentLocation);
    		}
    		updateUIWithLocationInfo(currentLocation);
    		updateWithLocationInfo(currentLocation);
    	}
    }


    public void navigateNext() {
    	DisNode nextDistrict = disNav.next();
    	if (nextDistrict != null) {
    		tfSDD.setText(nextDistrict.getData().getDistrictName());
    		updateUIWithDistrictInfo(nextDistrict);
    		loadLoc(nextDistrict.getData().getDistrictName());
    	} else {
    		taD.setText("No more districts available.\n");
    	}
    }


    public void navigatePrevious() {
    	DisNode previousDistrict = disNav.previous(); 
    	if (previousDistrict != null) {
    		tfSDD.setText(previousDistrict.getData().getDistrictName());
    		updateUIWithDistrictInfo(previousDistrict);
    		loadLoc(previousDistrict.getData().getDistrictName());
    	} else {
    		taD.setText("You are at the beginning. No previous districts available.\n");
    	}
    }


    private void updateUIWithDistrictInfo(DisNode districtNode) {
    	District district = districtNode.getData();
     
    	int totalMartyrs = countMartyrsInDistrict(district); 
    	taD.setText("District Name: " + district.getDistrictName() + "\n");
    	//taD.appendText("Total Martyrs: " + totalMartyrs + "\n");

    	loadLoc(district.getDistrictName());
    }
    
    private int countMartyrsInDistrict(District district) {
        DisNode districtNode = findDis(district.getDistrictName());
        if (districtNode == null || districtNode.getLocTree() == null) {
            return 0;
        }
        return countMartyrsInLocation(districtNode.getLocTree().getRoot());
    }

    private int countMartyrsInLocation(LocNode locationNode) {
        if (locationNode == null) {
            return 0;
        }
        int totalMartyrs = countMartyrsInDate(locationNode.getData().getRootDate());
        totalMartyrs += countMartyrsInLocation(locationNode.getLeft());
        totalMartyrs += countMartyrsInLocation(locationNode.getRight());
        return totalMartyrs;
    }

    private int countMartyrsInDate(DateNode dateNode) {
        if (dateNode == null) {
            return 0;
        }
        int totalMartyrs = countMartyrs(dateNode);
        totalMartyrs += countMartyrsInDate(dateNode.getLeft());
        totalMartyrs += countMartyrsInDate(dateNode.getRight());
        return totalMartyrs;
    }

    
    /********************************************* Location **********************************************/ 
    
    
    
    public void addLoc(Location location, District district) {
        DisNode districtNode = findDis(district.getDistrictName());
        if (districtNode == null) {
        	taL.setText("District not found: " + district.getDistrictName());
            return;
        }

        LocNode locationNode = findLoc(location.getLocationName());
        if (locationNode != null) {
            taL.setText("Location '" + location.getLocationName() + "' already exists in district: " + district.getDistrictName());
            return; 
        }

        locTree = districtNode.getLocTree();
        if (locTree == null) {
        	locTree = new LocTree();
            districtNode.getLocTree().setRoot(locationNode);
        }

        LocNode newLocationNode = new LocNode(location);
        addLocation(locTree, newLocationNode);
    }

    private void addLocation(LocTree locationTree, LocNode newNode) {
        if (locationTree.getRoot() == null) {
            locationTree.setRoot(newNode);
            taL.setText("Location added successfully: " + newNode.getData().getLocationName());
            return;
        }
        addLocRec(locationTree.getRoot(), newNode);
    }

    public void addLocRec(LocNode currentNode, LocNode newNode) {
        int compareResult = newNode.getData().getLocationName().compareToIgnoreCase(currentNode.getData().getLocationName());

        if (compareResult < 0) {
            if (currentNode.getLeft() == null) {
                currentNode.setLeft(newNode);
                tfILL.clear();
                taL.setText("Location added successfully: " + newNode.getData().getLocationName());
            } else {
            	addLocRec(currentNode.getLeft(), newNode);
            }
        } else if (compareResult > 0) {
            if (currentNode.getRight() == null) {
                currentNode.setRight(newNode);
                tfILL.clear();
                taL.setText("Location added successfully: " + newNode.getData().getLocationName());
            } else {
            	addLocRec(currentNode.getRight(), newNode);
            }
        }
   }

    // Method to search for a location node in the tree
    public LocNode findLoc(String locationName) {
    	if (locTree.getRoot() == null) {
            return null;
        }
        return findLocation(locTree.getRoot(), locationName);
    }

    // Recursive method to search for a location node
    private LocNode findLocation(LocNode currentNode, String locationName) {
        if (currentNode == null || currentNode.getData().getLocationName().equalsIgnoreCase(locationName)) {
            return currentNode;
        }

        if (locationName.compareToIgnoreCase(currentNode.getData().getLocationName()) < 0) {
            return findLocation(currentNode.getLeft(), locationName);
        }
        return findLocation(currentNode.getRight(), locationName);
    }
   
    
    public void insertLoc(String districtName, String locationName) {
        Location newLoc = new Location(locationName);
        
        DisNode disNode = findDis(districtName);
        if (disNode != null) {
            locTree = disNode.getLocTree();
            if (locTree == null) {
            	locTree = new LocTree();
                disNode.getLocTree().setRoot(currentLocation);;
            }
            
            LocNode locationNode = findLoc(newLoc.getLocationName());
            if (locationNode != null) {
                System.out.println("Location '" + newLoc.getLocationName() + "' already exists in district: " + disNode.getData().getDistrictName());
                taL.setText("Location '" + newLoc.getLocationName() + "' already exists in district: " + disNode.getData().getDistrictName());
                return;  
            }
            
            addLocation(locTree, new LocNode(newLoc));
        } 
    }
    
    public void deleteLoc(String locationName) {
        LocNode locationNode = findLoc(locationName);
        if (locationNode == null) {
            taL.setText("Location not found.");
            return;
        }
        
        if (locationNode.getLeft() == null && locationNode.getRight() == null) {
            deleteLocNoChildren(locationNode);
        } else if (locationNode.getLeft() == null || locationNode.getRight() == null) {
            deleteLocOneChild(locationNode);
        } else {
            deleteLocTwoChildren(locationNode);
        }
	    String selectedDistrict = cbDDL.getValue();
	    updateLocBox(selectedDistrict , cbDLL);
    }

    private void deleteLocNoChildren(LocNode locationNode) {
        LocNode parent = findParent(locationNode);
        
        if (parent != null) {
            if (parent.getLeft() == locationNode) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else {
            locTree.setRoot(null);
        }
    }

    private void deleteLocOneChild(LocNode locationNode) {
        LocNode parent = findParent(locationNode);
        
        LocNode child = (locationNode.getLeft() != null) ? locationNode.getLeft() : locationNode.getRight();
        
        if (parent != null) {
            if (parent.getLeft() == locationNode) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        } else {
            locTree.setRoot(child);
        }
    }

    private void deleteLocTwoChildren(LocNode locationNode) {
        LocNode successor = findSuccessor(locationNode.getRight());
        
        locationNode.getData().setLocationName(successor.getData().getLocationName());
        
        deleteLocOneChild(successor);
    }

    private LocNode findParent(LocNode node) {
        LocNode parent = null;
        LocNode current = locTree.getRoot();
        
        while (current != null) {
            int compareResult = node.getData().getLocationName().compareToIgnoreCase(current.getData().getLocationName());
            
            if (compareResult == 0) {
                break; 
            } else {
                parent = current;
                if (compareResult < 0) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }
        }
        
        return parent;
    }

    private LocNode findSuccessor(LocNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public Stage warningDeLoc() {
	    Label label = new Label("Are you sure you want to Delete the Location ?");
	    Button bY = new Button("Yes");
	    Button bN = new Button("No");
	    bY.setMaxWidth(150);
	    bN.setMaxWidth(150);

	    
	    BorderPane bp = new BorderPane();
	    Scene scene = new Scene(bp, 300, 100);
	    Stage stage = new Stage();
	    stage.setScene(scene);
	    

	    bY.setOnAction(event -> {
	    	deleteLoc(cbDLL.getValue());
	    	stage.close();
	    	cbDDL.setValue("District");
	    	cbDLL.setValue("Location");
	    });
	    
	    HBox hb = new HBox(10);
	    hb.getChildren().addAll(bY,bN);
	    hb.setAlignment(Pos.CENTER);
	    
	    VBox vb = new VBox(10);
	    vb.getChildren().addAll(label,hb);
	    vb.setAlignment(Pos.CENTER);
	    
	    bN.setOnAction(e -> stage.close());
	    
	    bp.setCenter(vb);
	    tfNND.clear();
	    stage.setTitle("Warning");
	    stage.show();
	    return stage;
	}
    
    
    public Stage updateLoc() {
        Label lDN = new Label("District:");
        Label lLN = new Label("Current Location:");
        Label lNN = new Label("New Location:");

        Button UB = new Button("Update");
        
        cbDis.setMaxWidth(150);
        cbLoc.setMaxWidth(150);
        
        cbDis.setValue("District");
        cbLoc.setValue("Location");

        updateDistrictComboBoxes();

        cbDis.setOnAction(event -> {
            String selectedDistrict = cbDis.getValue();
            if (selectedDistrict != null) {
                updateLocBox(selectedDistrict, cbLoc);
            }
        });

        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);

        gp.add(lDN, 0, 0);
        gp.add(cbDis, 1, 0);
        gp.add(lLN, 0, 1);
        gp.add(cbLoc, 1, 1);
        gp.add(lNN, 0, 2);
        gp.add(tfNNL, 1, 2);
        gp.add(UB, 1, 3);
        gp.setAlignment(Pos.CENTER);

        bp.setCenter(gp);
        Scene scene = new Scene(bp, 300, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Update Location Data");

        UB.setOnAction(event -> warningLoc());
        
        tfNNL.clear();
        stage.setTitle("Update Location Data");
        stage.show();
        return stage;
    }  
    
    public Stage warningLoc() {
	    Label label = new Label("Are you sure you want to Update the Location Name ?");
	    Button bY = new Button("Yes");
	    Button bN = new Button("No");
	    bY.setMaxWidth(150);
	    bN.setMaxWidth(150);

	    
	    BorderPane bp = new BorderPane();
	    Scene scene = new Scene(bp, 300, 100);
	    Stage stage = new Stage();
	    stage.setScene(scene);
	    

	    bY.setOnAction(event -> {  
            String selectedDistrict = cbDis.getValue();
            String currentLocation = cbLoc.getValue();
            String newName = tfNNL.getText();

            if (selectedDistrict == null || currentLocation == null || newName.isEmpty()) {
                taL.setText("Please select both the district and current location, and enter the new location name.");
                return;
            }

            LocNode locationNode = findLoc(currentLocation);
            if (locationNode != null) {
                locationNode.getData().setLocationName(newName);
                taL.setText("The Update is Successfully: " + currentLocation + " -> " + newName);
                stage.close();
            } else {
                taL.setText("Location '" + currentLocation + "' not found in district '" + selectedDistrict + "'.");
            }
        });
	    
	    HBox hb = new HBox(10);
	    hb.getChildren().addAll(bY,bN);
	    hb.setAlignment(Pos.CENTER);
	    
	    VBox vb = new VBox(10);
	    vb.getChildren().addAll(label,hb);
	    vb.setAlignment(Pos.CENTER);
	    
	    bN.setOnAction(e -> stage.close());
	    
	    bp.setCenter(vb);
	    tfNND.clear();
	    stage.setTitle("Warning");
	    stage.show();
	    return stage;
	}
    
    public void updateLocBox(String districtName, ComboBox<String> locationComboBox) {
        locationComboBox.getItems().clear();
        DisNode districtNode = findDis(districtName);
        if (districtNode != null) {
            locTree = districtNode.getLocTree();
            if (locTree != null) {
                locBox(locTree.getRoot(), locationComboBox);
            }
        }
    }

    public void locBox(LocNode node, ComboBox<String> locationComboBox) {
        if (node != null) {
            locationComboBox.getItems().add(node.getData().getLocationName());
            locBox(node.getLeft(), locationComboBox);
            locBox(node.getRight(), locationComboBox);
        }
    }

    private void navigateNextLocation() {
    	try {
        if (!locationQueue.isEmpty()) {
            if (currentLocation != null) {
                previousLocations.push(currentLocation);
            }
            currentLocation = (LocNode) locationQueue.deQueue();
            if (currentLocation.getLeft() != null) {
                locationQueue.enQueue(currentLocation.getLeft());
            }
            if (currentLocation.getRight() != null) {
                locationQueue.enQueue(currentLocation.getRight());
            }
            updateUIWithLocationInfo(currentLocation);
            updateWithLocationInfo(currentLocation);            
        } else {
            taL.setText("No more locations available.\n");
        }
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }

    private void navigatePreviousLocation() {
        if (!previousLocations.isEmpty()) {
            currentLocation = (LocNode) previousLocations.pop();
            updateUIWithLocationInfo(currentLocation);
            updateWithLocationInfo(currentLocation);            
        } else {
            taL.setText("You are at the beginning. No previous locations available.\n");
        }
    }

    private void updateUIWithLocationInfo(LocNode locationNode) {
        if (locationNode == null) return;

        DateNode earliestDateNode = findEarliestDateWithMartyrs(locationNode.getDateTree().getRoot());
        Date earliestDate = (earliestDateNode != null) ? earliestDateNode.getData() : null;

        DateNode latestDateNode = findLatestDateWithMartyrs(locationNode.getDateTree().getRoot());
        Date latestDate = (latestDateNode != null) ? latestDateNode.getData() : null;

        DateNode maxMartyrsDateNode = findDateWithMaxMartyrs(locationNode.getDateTree().getRoot());
        Date maxMartyrsDate = (maxMartyrsDateNode != null) ? maxMartyrsDateNode.getData() : null;

        taL.setText("Location Name: " + locationNode.getData().getLocationName() + "\n");
        taL.appendText("Earliest Date with Martyrs: " + (earliestDate != null ? earliestDate : "N/A") + "\n");
        taL.appendText("Latest Date with Martyrs: " + (latestDate != null ? latestDate : "N/A") + "\n");
        taL.appendText("Date with Max Martyrs: " + (maxMartyrsDate != null ? maxMartyrsDate : "N/A") + "\n");
    }

    private DateNode findLatestDateWithMartyrs(DateNode dateNode) {
        if (dateNode == null) return null;

        DateNode latestNode = null;

        if (countMartyrs(dateNode) > 0) {
            latestNode = dateNode;
        }

        DateNode right = findLatestDateWithMartyrs(dateNode.getRight());
        DateNode left = findLatestDateWithMartyrs(dateNode.getLeft());

        if (right != null && (latestNode == null || right.getData().compareTo(latestNode.getData()) > 0)) {
            latestNode = right;
        }
        if (left != null && (latestNode == null || left.getData().compareTo(latestNode.getData()) > 0)) {
            latestNode = left;
        }

        return latestNode;
    }

    private DateNode findEarliestDateWithMartyrs(DateNode dateNode) {
        if (dateNode == null) return null;

        DateNode earliestNode = null;

        if (countMartyrs(dateNode) > 0) {
            earliestNode = dateNode;
        }

        DateNode right = findEarliestDateWithMartyrs(dateNode.getRight());
        DateNode left = findEarliestDateWithMartyrs(dateNode.getLeft());

        if (right != null && (earliestNode == null || right.getData().compareTo(earliestNode.getData()) < 0)) {
            earliestNode = right;
        }
        if (left != null && (earliestNode == null || left.getData().compareTo(earliestNode.getData()) < 0)) {
            earliestNode = left;
        }

        return earliestNode;
    }

    private DateNode findDateWithMaxMartyrs(DateNode dateNode) {
        return findDateWithMaxMartyrsHelper(dateNode, null, 0);
    }

    private DateNode findDateWithMaxMartyrsHelper(DateNode dateNode, DateNode maxNode, int maxCount) {
        if (dateNode == null) return maxNode;

        int currentCount = countMartyrs(dateNode);
        if (currentCount > maxCount) {
            maxCount = currentCount;
            maxNode = dateNode;
        }

        DateNode right = findDateWithMaxMartyrsHelper(dateNode.getRight(), maxNode, maxCount);
        DateNode left = findDateWithMaxMartyrsHelper(dateNode.getLeft(), maxNode, maxCount);

        if (right != null && countMartyrs(right) > maxCount) {
            maxNode = right;
            maxCount = countMartyrs(right);
        }
        if (left != null && countMartyrs(left) > maxCount) {
            maxNode = left;
            maxCount = countMartyrs(left);
        }

        return maxNode;
    }
   
    
    
    /*********************************************   Date  **********************************************/ 

    
    
    
    public void addDa(Date date, Location location, Martyr martyr) {
        DateNode newDateNode = new DateNode(date);
        LocNode locNode = findLoc(location.getLocationName());
        if (locNode == null) {
            taL.setText("Location not found: " + location.getLocationName());
            return;
        }

        dateTree = locNode.getDateTree();
        if (dateTree == null) {
            dateTree = new DateTree();
            locNode.getDateTree().setRoot(newDateNode);
            dateTree.setRoot(newDateNode);
        }

        DateNode dateNode = findDa(date, dateTree);
        if (dateNode != null) {
            taL.setText("Date '" + date + "' already exists in Location: " + location.getLocationName());
            return;
        }
        
        MarNode newMarNode = new MarNode(martyr);
        newDateNode.setHead(newMarNode);
        addDate(dateTree, newDateNode);

    }

    
    private void addDate(DateTree dateTree, DateNode newNode) {
    	if (dateTree.getRoot() == null) {
    		dateTree.setRoot(newNode);
    		taM.setText("Date added successfully: " + newNode.getData());
    	} else {
    		addDateRec(dateTree.getRoot(), newNode);
    	}
    }


    private void addDateRec(DateNode currentNode, DateNode newNode) {
        int compareResult = newNode.getData().compareTo(currentNode.getData());

        if (compareResult < 0) {
            if (currentNode.getLeft() == null) {
                currentNode.setLeft(newNode);
                taM.setText("Date added successfully: " + newNode.getData());
            } else {
                addDateRec(currentNode.getLeft(), newNode);
            }
        } else if (compareResult > 0) {
            if (currentNode.getRight() == null) {
                currentNode.setRight(newNode);
                taM.setText("Date added successfully: " + newNode.getData());
            } else {
                addDateRec(currentNode.getRight(), newNode);
            }
        }
    }

    public DateNode findDa(Date date, DateTree dateTree) {
        return findDate(date, dateTree.getRoot());
    }

    public DateNode findDate(Date date, DateNode currentNode) {
        if (currentNode == null) {
            return null;
        }

        int compareResult = date.compareTo(currentNode.getData());

        if (compareResult == 0) {
            return currentNode;
        } else if (compareResult < 0) {
            return findDate(date, currentNode.getLeft());
        } else {
            return findDate(date, currentNode.getRight());
        }
    }

    public void addMartyr(Martyr martyr, DateNode dateNode) {
        MarNode newNode = new MarNode(martyr);
        if (dateNode.getHead() == null) {
        	count++;
            dateNode.setHead(newNode);
        } else {
            MarNode current = dateNode.getHead();
            while (current.getNext() != null) {
                current = current.getNext();
            }
        	count++;
            current.setNext(newNode);
        }
    }

    public int countMartyrs(DateNode dateNode) {
        int totalCount = 0;
        MarNode current = dateNode.getHead();
        while (current != null) {
            totalCount++;
            current = current.getNext();
        }
        return totalCount;
    }
    
    public void print() {
    	System.out.println(countDis);
    	System.out.println(count);
    }
    
    public void printTree(DisNode node) {
        if (node != null) {
            printTree(node.getLeft());
            System.out.println("District: " + node.getData().getDistrictName());
            printTree(node.getRight());
        }
    }
    

	public Stage insertMartyr() {  
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
 		gp.add(datePickerMar, 1, 5);
 		gp.setVgap(10);
 		gp.setHgap(10);
 		gp.setAlignment(Pos.CENTER);
 		gp.setVgap(10);
 		gp.setHgap(10);
 		
 		File file = new File("C:\\Users\\mhmds\\Desktop\\DataStructure\\DataStructure Projects\\data_2.csv");
 		addB.setOnAction(e -> addButton(file)); 
 		
 		VBox vbI = new VBox(20);
 		vbI.getChildren().addAll(title,gp,addB);
 		vbI.setAlignment(Pos.CENTER);
 		
 		bp.setCenter(vbI);
 		stage.setScene(scene);
 		stage.setTitle("Insert Martyr");
 		stage.show();
 		return stage;
 	}
 	
	 	public void addButton(File file) {
	 	    try {
	 	        String name = tfN.getText();
	 	        String age = tfA.getText();
	 	        String dis = tfD.getText(); 
	 	        String loc = tfL.getText();
	 	        String gender = tfG.getText();
	 	        LocalDate selectedDate = datePickerMar.getValue();
	 	        District diss = new District();
	 	        Location locc = new Location();

	 	        if (selectedDate == null) {
	 	            taM.setText("Please select a date.");
	 	            return;
	 	        }

	 	        if (name.isEmpty() || age.isEmpty() || dis.isEmpty() || loc.isEmpty() || gender.isEmpty()) {
	 	            taM.setText("Enter all information in boxes. Then press Add.");
	 	            return;
	 	        }

	 	        int a = Integer.parseInt(age);
	 	        if (a <= 0) {
	 	            taM.setText("Invalid age format. Please enter an age greater than 0.");
	 	            return;
	 	        }

	 	        if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
	 	            taM.setText("Invalid gender format. Please enter M for Male or F for Female.");
	 	            return;
	 	        }
	 	        
	 	        DisNode districtNode = findDis(dis);
	 	        if (districtNode == null) {
	 	            taM.setText("District '" + dis + "' not found.");
	 	            return;
	 	        }

	 	        LocNode locationNode = findLoc( districtNode.getLocTree().getRoot().getData().getLocationName());
	 	        if (locationNode == null) {
	 	            taM.setText("Location '" + loc + "' not found in district '" + dis + "'.");
	 	            return;
	 	        }

	 	       String dateString = selectedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	 	       String[] dateParts = dateString.split("/");
	 	       int month = Integer.parseInt(dateParts[0]);
	 	       int day = Integer.parseInt(dateParts[1]);
	 	       int year = Integer.parseInt(dateParts[2]);
	 	       Date date = new Date(year, month, day);
	 	       Martyr martyr = new Martyr(name, date, a, loc, dis, gender);

	 	        District district = new District (dis);
	 	        Location location = new Location(loc);
	 	        DateNode dateNode= new DateNode();
	 	        addMartyr(martyr ,dateNode);

	 	        tfN.clear();
	 	        tfA.clear();
	 	        tfD.clear();
	 	        tfL.clear();
	 	        tfG.clear();
	 	        taM.setText("Martyr added successfully.");
	 	        
	 	        writeMartyrToFile(file, martyr);

	 	    } catch (NumberFormatException e) {
	 	        taM.setText("Error: " + e.getMessage());
	 	    }
	 	}
	 	
	 	private void writeMartyrToFile(File file, Martyr martyr) {
	 	    try (FileWriter writer = new FileWriter(file, true)) {
	 	        writer.write(martyr.getName() + "," + martyr.getAge() + "," + martyr.getDistrict() + "," +
	 	                martyr.getLocation() + "," + martyr.getGender() + "," + martyr.getDate() + "\n");
	 	    } catch (IOException e) {
	 	        taM.setText("Error writing to file: " + e.getMessage());
	 	    }
	 	}
	 	
	 	public Stage updateMar() {
	 		cbUM.setValue("Martyr Name");
	 	    Label lN = new Label("Martyr Name:");
	 	    Label lNN = new Label("New Martyr Name:");
	 	    TextField tfNN = new TextField();
	 	    Button UB = new Button("Update");
	 	    Button DB = new Button("Delete");

	 	    BorderPane bp = new BorderPane();
	 	    GridPane gridPane = new GridPane();
	 	    gridPane.setHgap(10);
	 	    gridPane.setVgap(10);

	 	    gridPane.add(lN, 0, 0);
	 	    gridPane.add(cbUM, 1, 0);
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
	 	        String currentMartyrName = cbUM.getValue();
	 	        String newMartyrName = tfNN.getText();
	 	        if (currentMartyrName.isEmpty() || newMartyrName.isEmpty()) {
	 	            taM.setText("Please enter both the current and new martyr names.");
	 	            return;
	 	        }

	 	        DisNode currentDis = disTree.root;
	 	        boolean found = false;
	 	        while (currentDis != null) {
	 	            LocNode currentLoc = currentDis.getData().getRootLoc();
	 	            while (currentLoc != null) {
	 	                DateNode currentDate = currentLoc.getData().getRootDate();
	 	                while (currentDate != null) {
	 	                    MarNode currentMar = currentDate.getData().getMartyr();
	 	                    while (currentMar != null) {
	 	                        Martyr martyr = currentMar.getMartyr();
	 	                        if (martyr.getName().equalsIgnoreCase(currentMartyrName)) {
	 	                            taM.setText("Martyr name updated successfully: " + martyr.getName() + " -> " + newMartyrName);
	 	                            martyr.setName(newMartyrName);
	 	                            found = true;
	 	                            break;
	 	                        }
	 	                        currentMar = currentMar.getNext();
	 	                    }
	 	                    if (found) break;
	 	                    currentDate = currentDate.getRight();
	 	                }
	 	                if (found) break;
	 	                currentLoc = currentLoc.getRight();
	 	            }
	 	            if (found) break;
	 	            currentDis = currentDis.getRight();
	 	        }
	 	        if (!found) {
	 	            taM.setText("Martyr not found.");
	 	        }
	 	        stage.close();
	 	    });

	 	    DB.setOnAction(event -> {
	 	        String currentMartyrName = cbUM.getValue();
	 	        if (currentMartyrName.isEmpty()) {
	 	            taM.setText("Please enter the martyr name to delete.");
	 	            return;
	 	        }

	 	        DisNode currentDis = disTree.root;
	 	        boolean found = false;
	 	        while (currentDis != null) {
	 	            LocNode currentLoc = currentDis.getData().getRootLoc();
	 	            while (currentLoc != null) {
	 	                DateNode currentDate = currentLoc.getData().getRootDate();
	 	                while (currentDate != null) {
	 	                    MarNode currentMar = currentDate.getData().getMartyr();
	 	                    while (currentMar != null) {
	 	                        Martyr martyr = currentMar.getMartyr();
	 	                        if (martyr.getName().equalsIgnoreCase(currentMartyrName)) {
	 	                            taM.setText("Martyr '" + martyr.getName() + "' deleted successfully.");

	 	                            // Assuming you have a method to remove the martyr from the data structure
	 	                            removeMartyr(currentMartyrName);

	 	                            found = true;
	 	                            break;
	 	                        }
	 	                        currentMar = currentMar.getNext();
	 	                    }
	 	                    if (found) break;
	 	                    currentDate = currentDate.getRight();
	 	                }
	 	                if (found) break;
	 	                currentLoc = currentLoc.getRight();
	 	            }
	 	            if (found) break;
	 	            currentDis = currentDis.getRight();
	 	        }
	 	        if (!found) {
	 	            taM.setText("Martyr not found.");
	 	        }
	 	        stage.close();
	 	    });

	 	    stage.setTitle("Update / Delete Martyr Name");
	 	    stage.show();
	 	    return stage;
	 	}
	 	
	 	public boolean removeMartyr(String martyrName) {
	 	    DisNode currentDis = disTree.root;
	 	    boolean found = false;
	 	    while (currentDis != null) {
	 	        LocNode currentLoc = currentDis.getData().getRootLoc();
	 	        while (currentLoc != null) {
	 	            DateNode currentDate = currentLoc.getData().getRootDate();
	 	            while (currentDate != null) {
	 	                MarNode prevMar = null;
	 	                MarNode currentMar = currentDate.getData().getMartyr();
	 	                while (currentMar != null) {
	 	                    Martyr martyr = currentMar.getMartyr();
	 	                    if (martyr.getName().equalsIgnoreCase(martyrName)) {
	 	                        if (prevMar == null) {
	 	                            currentDate.getData().setMartyr(currentMar.getNext());
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
	 	                currentDate = currentDate.getRight();
	 	            }
	 	            if (found) break;
	 	            currentLoc = currentLoc.getRight();
	 	        }
	 	        if (found) break;
	 	        currentDis = currentDis.getRight();
	 	    }
	 	    return found;
	 	}
	 	
	 	
	 	public void searchMarByName(String partialName, DisNode disNode) {
	 	    if (disNode == null) {
	 	        return;
	 	    }
	 	    LocNode locNode = disNode.getLocTree().getRoot();
	 	    searchMartyrsByNameInLoc(partialName, locNode);

	 	    searchMarByName(partialName, disNode.getLeft());

	 	    searchMarByName(partialName, disNode.getRight());
	 	}
	 	
	 	
	 	private void searchMartyrsByNameInLoc(String partialName, LocNode locNode) {
	 	    if (locNode == null) {
	 	        System.out.println("Location node is null.");
	 	        return;
	 	    }

	 	    DateNode dateNode = locNode.getDateTree().getRoot();
	 	    searchMartyrsByNameInDate(partialName, dateNode, locNode);
	 	}

	 	private void searchMartyrsByNameInDate(String partialName, DateNode dateNode, LocNode locNode) {
	 	    if (dateNode == null) {
	 	        return;
	 	    }

	 	    boolean martyrsFound = false; 
	 	    MarNode marNode = dateNode.getHead(); 
	 	    while (marNode != null) {
	 	        if (marNode.getMartyr() != null && marNode.getMartyr().getName().toLowerCase().contains(partialName.toLowerCase())) {
	 	            martyrsFound = true;
		 	    	taM.clear();
	 	            taM.appendText("Martyr Found:\n");
	 	            taM.appendText("Name: " + marNode.getMartyr().getName() + "\n");
	 	            taM.appendText("Date: " + dateNode.getData() + "\n");
	 	            taM.appendText("Location: " + locNode.getData().getLocationName() + "\n");
	 	            taM.appendText("-----------------------------\n");
	 	        }
	 	        marNode = marNode.getNext();
	 	    }

	 	    if (!martyrsFound) {
	 	    	taM.clear();
	 	        taM.appendText("No martyrs found with the provided name.\n");
	 	    }
	 	}


	 	private void updateWithLocationInfo(LocNode locationNode) {
	 	    if (locationNode == null) return;

	 	    taM.clear();

	 	    taM.appendText("Location Name: " + locationNode.getData().getLocationName() + "\n");

	 	    displayDatesWithMartyrs(locationNode.getDateTree().getRoot());
	 	}

	 	private void displayDatesWithMartyrs(DateNode dateNode) {
	 	    if (dateNode == null) return;

	 	    taM.appendText("Date: " + dateNode.getData() + "\n");

	 	    displayMartyrs(dateNode.getHead());
	 	    comboMar(dateNode.getHead());
	 	    displayDatesWithMartyrs(dateNode.getLeft());

	 	    displayDatesWithMartyrs(dateNode.getRight());
	 	}
	 	
	 	public void comboMar(MarNode node) {
	 		while (node != null) {
	 	        cbUM.getItems().add(node.getMartyr().getName()); // Add martyr name to ComboBox
	 	       node = node.getNext();
	 	    }
	 	}

	 	private void displayMartyrs(MarNode marNode) {
	 	    while (marNode != null) {
	 	        taM.appendText("Name: " + marNode.getMartyr().getName() + "\n");
	 	        taM.appendText("Age: " + marNode.getMartyr().getAge() + "\n");
	 	        
	 	        taM.appendText("----------------------------------"+"\n");

	 	        marNode = marNode.getNext();
	 	    }
	 	}

	 	public void navigateNextDate() {
	 	    if (currentLocation == null) {
	 	        taM.setText("No location selected.");
	 	        return;
	 	    }

	 	    if (currentSelectedDate == null) {
	 	        taM.setText("No date selected.");
	 	        return;
	 	    }

	 	    DateNode nextDate = findNextDateInLocation(currentLocation.getDateTree().getRoot(), currentSelectedDate);
	 	    if (nextDate != null) {
	 	        currentSelectedDate = nextDate;
	 	        displayDateInfo(currentSelectedDate);
	 	    } else {
	 	        taM.setText("No more dates available.");
	 	    }
	 	}

	 	public void navigatePreviousDate() {
	 	    if (currentLocation == null) {
	 	        taM.setText("No location selected.");
	 	        return;
	 	    }

	 	    if (currentSelectedDate == null) {
	 	        taM.setText("No date selected.");
	 	        return;
	 	    }

	 	    DateNode previousDate = findPreviousDateInLocation(currentLocation.getDateTree().getRoot(), currentSelectedDate);
	 	    if (previousDate != null) {
	 	        currentSelectedDate = previousDate;
	 	        displayDateInfo(currentSelectedDate);
	 	    } else {
	 	        taM.setText("You are at the beginning. No previous dates available.");
	 	    }
	 	}

	 	private DateNode findNextDateInLocation(DateNode currentNode, DateNode date) {
	 	    if (currentNode == null) {
	 	        return null;
	 	    }

	 	    DateNode nextDate = findNextDateInLocation(currentNode.getLeft(), date);
	 	    if (nextDate == null && currentNode.getData().compareTo(date.getData()) > 0) {
	 	        nextDate = currentNode;
	 	    }
	 	    if (nextDate == null) {
	 	        nextDate = findNextDateInLocation(currentNode.getRight(), date);
	 	    }
	 	    return nextDate;
	 	}

	 	private DateNode findPreviousDateInLocation(DateNode currentNode, DateNode date) {
	 	    if (currentNode == null) {
	 	        return null;
	 	    }

	 	    DateNode previousDate = findPreviousDateInLocation(currentNode.getRight(), date);
	 	    if (previousDate == null && currentNode.getData().compareTo(date.getData()) < 0) {
	 	        previousDate = currentNode;
	 	    }
	 	    if (previousDate == null) {
	 	        previousDate = findPreviousDateInLocation(currentNode.getLeft(), date);
	 	    }
	 	    return previousDate;
	 	}


	 	private void sortMartyrsByNameInDate(DateNode dateNode) {
	 	    if (dateNode == null) {
	 	        return;
	 	    }

	 	    MarNode current = dateNode.getHead();
	 	    MarNode sorted = null;

	 	    while (current != null) {
	 	        MarNode next = current.getNext();
	 	        sorted = insertIntoSorted(sorted, current);
	 	        current = next;
	 	    }

	 	    dateNode.setHead(sorted);
	 	}

	 	private MarNode insertIntoSorted(MarNode sortedHead, MarNode newNode) {
	 	    if (sortedHead == null || sortedHead.getMartyr().getName().compareTo(newNode.getMartyr().getName()) >= 0) {
	 	        newNode.setNext(sortedHead);
	 	        return newNode;
	 	    }

	 	    MarNode current = sortedHead;
	 	    while (current.getNext() != null && current.getNext().getMartyr().getName().compareTo(newNode.getMartyr().getName()) < 0) {
	 	        current = current.getNext();
	 	    }

	 	    newNode.setNext(current.getNext());
	 	    current.setNext(newNode);

	 	    return sortedHead;
	 	}

	 	private void displayDateInfo(DateNode dateNode) {
	 	    if (dateNode == null) {
	 	        System.out.println("No date selected.");
	 	        return;
	 	    }

	 	    double averageAge = calculateAverageAge(dateNode);
	 	    Martyr youngestMartyr = findYoungestMartyr(dateNode);
	 	    Martyr oldestMartyr = findOldestMartyr(dateNode);

	 	    taM.setText("Date: " + dateNode.getData());
	 	    taM.setText("Average Martyr Age: " + averageAge);
	 	    taM.setText("Youngest Martyr: " + youngestMartyr.getName() + ", Age: " + youngestMartyr.getAge());
	 	    taM.setText("Oldest Martyr: " + oldestMartyr.getName() + ", Age: " + oldestMartyr.getAge());

	 	    taM.setText("Martyrs List:");
	 	    MarNode current = dateNode.getHead();
	 	    while (current != null) {
	 	    	taM.setText("Name: " + current.getMartyr().getName() + ", Age: " + current.getMartyr().getAge());
	 	        current = current.getNext();
	 	    }
	 	}

	 	private double calculateAverageAge(DateNode dateNode) {
	 	    int totalAge = 0;
	 	    int martyrCount = 0;
	 	    MarNode current = dateNode.getHead();
	 	    while (current != null) {
	 	        totalAge += current.getMartyr().getAge();
	 	        martyrCount++;
	 	        current = current.getNext();
	 	    }
	 	    return (martyrCount > 0) ? ((double) totalAge / martyrCount) : 0.0;
	 	}

	 	private Martyr findYoungestMartyr(DateNode dateNode) {
	 	    int minAge = Integer.MAX_VALUE;
	 	    Martyr youngestMartyr = null;
	 	    MarNode current = dateNode.getHead();
	 	    while (current != null) {
	 	        if (current.getMartyr().getAge() < minAge) {
	 	            minAge = current.getMartyr().getAge();
	 	            youngestMartyr = current.getMartyr();
	 	        }
	 	        current = current.getNext();
	 	    }
	 	    return youngestMartyr;
	 	}

	 	private Martyr findOldestMartyr(DateNode dateNode) {
	 	    int maxAge = Integer.MIN_VALUE;
	 	    Martyr oldestMartyr = null;
	 	    MarNode current = dateNode.getHead();
	 	    while (current != null) {
	 	        if (current.getMartyr().getAge() > maxAge) {
	 	            maxAge = current.getMartyr().getAge();
	 	            oldestMartyr = current.getMartyr();
	 	        }
	 	        current = current.getNext();
	 	    }
	 	    return oldestMartyr;
	 	}
	 	
}