package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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

	Random random = new Random();
	private FileChooser fileChooser = new FileChooser();
	AVLTree tree = new AVLTree();
	Hash hashTable = new Hash(11); 
	private Stack preEnt = new Stack();
	private Queue nextEnt = new LinkedList();
	private HashEntry currEnt = null;

	// For Date Screen
	public BorderPane bpD;
	private Button bID = new Button("Insert Date");
	private Button bUD = new Button("Update Date");
	private Button bDD = new Button("Delete Date");
	private Button bSD = new Button("Search Date");
	public TextArea taD = new TextArea();
	private Button bND = new Button("Next >>");
	private Button bPD = new Button("<< Previous");
	private Button bPDD = new Button("Print Date");

	private ComboBox<String> cbDDD = new ComboBox<>();
	private ComboBox<String> cbSDD = new ComboBox<>();
	private ComboBox<String> cbUDD = new ComboBox<>();

	private DatePicker dpIDD = new DatePicker();
	private DatePicker dpUDD = new DatePicker();

	// For Martyr Screen
	public BorderPane bpM = new BorderPane();
	private Button bIM = new Button("Insert Martyr");
	private Button bDM = new Button("Delete Martyr"); 
	private ComboBox<String> cbDM = new ComboBox<>();
	private Button bUM = new Button("Update Martyr");
	private TableView<Martyr> orderTable = new TableView<>();
	private ObservableList<Martyr> orderData = FXCollections.observableArrayList();	
	private Button bPMM = new Button("Print Tree");
	private Button bSMM = new Button("Show Tree");
	private ComboBox<String> cbUM = new ComboBox<>();
	TextField tfNNM = new TextField();


	// for insert martyr
	private TextField tfN = new TextField();
	private TextField tfA = new TextField();
	private ComboBox<String> cbD = new ComboBox<>();
	private ComboBox<String> cbL = new ComboBox<>();;
	private TextField tfG = new TextField();

	// Update Martyr
	private ComboBox<String> cbUDM = new ComboBox<>();;
	private ComboBox<String> cbULM = new ComboBox<>();;
	private TextField tfNewAge = new TextField();

	private TextArea taM = new TextArea();


	public Drive() {
		dpIDD.setMaxWidth(150);
		taD.setMaxWidth(700);
		orderTable.setMaxWidth(700);
		orderTable.setMaxHeight(280);
		cbDM.setMaxWidth(150);
		cbDM.setValue("Martyr");

		/*** Date ***/

		cbDDD.setValue("Date");
		cbDDD.setMaxWidth(150);
		cbSDD.setValue("Date");
		cbSDD.setMaxWidth(150);

		bND.setMaxWidth(150);
		bID.setMaxWidth(150);
		bUD.setMaxWidth(150);
		bDD.setMaxWidth(150);
		bSD.setMaxWidth(150);
		bPD.setMaxWidth(150);
		bPDD.setMaxWidth(150);

		bpD = new BorderPane();
		VBox vbL = new VBox(10);
		vbL.setAlignment(Pos.CENTER);

		GridPane gpL = new GridPane();
		gpL.setVgap(10);
		gpL.setHgap(10);

		gpL.add(cbDDD, 1, 1);
		gpL.add(bDD, 2, 1);
		gpL.add(cbSDD, 1, 2);
		gpL.add(bSD, 2, 2);
		gpL.add(dpIDD, 1, 3);
		gpL.add(bID, 2, 3);

		gpL.setAlignment(Pos.CENTER);
		HBox hbL = new HBox(10);
		hbL.getChildren().addAll(bPD, bUD, bPDD, bND);
		hbL.setAlignment(Pos.CENTER);
		vbL.getChildren().addAll(gpL, taD, hbL);

		bID.setOnAction(e -> insertDate());
		bUD.setOnAction(e -> updateDateScreen());
		bDD.setOnAction(e -> warningDeDate());
		bPDD.setOnAction(e -> printHashTable(false));
		bND.setOnAction(e -> navigateNext());
		bPD.setOnAction(e -> navigatePrevious());
		bSD.setOnAction(e -> searchDate(cbSDD.getValue()));
		bDM.setOnAction(e -> warningDeMartyr());


		bpD.setCenter(vbL);

		/*** Martyr ***/

		bIM.setMaxWidth(150);
		bUM.setMaxWidth(150);
		bDM.setMaxWidth(150);
		bPMM.setMaxWidth(150);
		bSMM.setMaxWidth(150);

		orderTable.getColumns().addAll(
				createTableColumn("Name", "name"),
				createTableColumn("Date", "date"),
				createTableColumn("Age", "age"),
				createTableColumn("Location", "location"),
				createTableColumn("District", "district"),
				createTableColumn("Gender", "gender")
				);

		orderTable.setItems(orderData);

		GridPane gp = new GridPane();
		gp.add(cbDM, 0, 0);
		gp.add(bDM, 1, 0);
		gp.add(bIM, 0, 1);
		gp.add(bUM, 1, 1);
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(10);
		gp.setHgap(10);
		HBox hb = new HBox(10);
		hb.getChildren().addAll( bPMM, bSMM);
		hb.setAlignment(Pos.CENTER);

		bIM.setOnAction(e -> insertMartyr());
		bUM.setOnAction(e -> updateMar());
		bSMM.setOnAction(e -> {
			String selectedDate = cbSDD.getValue();

			if (selectedDate != null) {
				AVLTree tree = null;

				for (HashEntry entry : hashTable.table) {
					if (entry != null && entry.getStatus() == 'F' && entry.getKey().equals(selectedDate)) {
						tree = entry.getTree();
						break;
					}
				}

				if (tree != null) {
					int size = tree.getSizeOfTree();
					int height = tree.getHeightOfTree();

					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Tree Size and Height for " + selectedDate);
					alert.setHeaderText(null);
					alert.setContentText("Size of the tree: " + size + "\nHeight of the tree: " + height);
					alert.showAndWait();
				} else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Tree Not Found");
					alert.setHeaderText(null);
					alert.setContentText("No AVL tree exists for the selected date: " + selectedDate);
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("No Date Selected");
				alert.setHeaderText(null);
				alert.setContentText("Please select a date from the ComboBox.");
				alert.showAndWait();
			}
		});

		bPMM.setOnAction(e -> printTree());

		VBox vb = new VBox(10);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(gp, orderTable, hb);

		updateComboBoxes();

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
		File selectedFile = fileChooser.showOpenDialog(primaryStage);
		if (selectedFile != null) {
			try {
				readCSVFile(selectedFile.getAbsolutePath());
				updateComboBoxes();
				initializeQueue();
				updateDisComboBoxes();
				updateLocComboBox(cbD.getValue());
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
					application.Date date = new application.Date(year, month, day);
					int age = parts[2].isEmpty() ? random.nextInt(100) + 1 : Integer.parseInt(parts[2]);
					String loc = parts[3];
					String dis = parts[4];
					String gender = parts[5];
					Martyr martyr = new Martyr(name, date, age, loc, dis, gender);

					hashTable.insert(date.toString(), martyr);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertDate() {
	    LocalDate selectedD = dpIDD.getValue();
	    if (selectedD != null) {
	        try {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	            String formattedDate = selectedD.format(formatter);

	            application.Date date = new application.Date(selectedD.getYear(), selectedD.getMonthValue(), selectedD.getDayOfMonth());

	            Martyr martyr = new Martyr();

	            hashTable.insert(formattedDate, martyr);

	            taD.appendText("Inserted Date: " + formattedDate + "\n");
	            System.out.println(hashTable.countD);
	            updateComboBoxes();
	        } catch (Exception e) {
	            taD.appendText("Error: An unexpected error occurred.\n");
	            System.out.println(e.getMessage());	        }
	    } else {
	        taD.appendText("Error: No date selected.\n");
	    }
	}


	private void updateComboBoxes() {
		cbDDD.getItems().clear(); 
		cbSDD.getItems().clear(); 
		cbUDD.getItems().clear(); 


		String[] allDates = hashTable.getAllDates(); 

		for (String date : allDates) {
			if (!cbDDD.getItems().contains(date)) {
				cbDDD.getItems().add(date.toString());
			}
			if (!cbSDD.getItems().contains(date)) {
				cbSDD.getItems().add(date.toString());
			}
			if (!cbUDD.getItems().contains(date)) {
				cbUDD.getItems().add(date.toString());
			}
		}
	}

	public Stage updateDateScreen() {
		Label lN = new Label("Current Date :");
		Label lNN = new Label("New Date :");
		Button UB = new Button("Update");
		cbUDD.setValue("Date");
		cbUDD.setMaxWidth(150);

		BorderPane bp = new BorderPane();
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		gridPane.add(lN, 0, 0);
		gridPane.add(cbUDD, 1, 0);
		gridPane.add(lNN, 0, 1);
		gridPane.add(dpUDD, 1, 1);
		gridPane.add(UB, 1, 2);
		gridPane.setAlignment(Pos.CENTER);

		bp.setCenter(gridPane);
		Scene scene = new Scene(bp, 300, 150);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Update Date");

		UB.setOnAction(event ->{
			warningUpDate();
			stage.close();
		});

		stage.setTitle("Update Date");
		stage.show();
		return stage;
	}

	public void updateDate(String oldDate, String newDate) {
		application.Date old = new application.Date(oldDate);
		application.Date newD = new application.Date(newDate);
		if (hashTable.contains(old.toString())) {
			AVLTree oldTree = hashTable.get(old.toString());

			traverseAndInsert(oldTree.getRoot(), newD.toString());

			hashTable.remove(old.toString());

			taD.setText("The Date " + oldDate + " Was Updated to -> " + newDate);
			updateComboBoxes();
		} else {
			taD.setText("Old date not found in the hash table.");
		}
	}

	public Stage warningUpDate() {
		Label label = new Label("Are you sure you want to update the Date ?");
		Button bY = new Button("Yes");
		Button bN = new Button("No");
		bY.setMaxWidth(150);
		bN.setMaxWidth(150);


		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp, 300, 100);
		Stage stage = new Stage();
		stage.setScene(scene);


		bY.setOnAction(event -> {
			LocalDate newDate = dpUDD.getValue(); 
			String formattedNewDate = newDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			updateDate(cbUDD.getValue(), formattedNewDate);
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
		stage.setTitle("Warning");
		stage.show();
		return stage;
	}

	private void traverseAndInsert(Node node, String newDate) {
		if (node != null) {
			Martyr martyr = node.getMartyr();
			hashTable.insert(newDate, martyr); 

			traverseAndInsert(node.getLeft(), newDate);
			traverseAndInsert(node.getRight(), newDate);
		}
	}


	public void deleteDate(String date) {
		try {
			if (hashTable.contains(date)) {
				hashTable.remove(date);
				updateComboBoxes();
			} else {
				taD.appendText("Date not found in the hash table.");
			}
		} catch (Exception e) {
			taD.appendText("Invalid date format or error occurred: " + e.getMessage());
		}
	}



	public Stage warningDeDate() {
		Label label = new Label("Are you sure you want to Delete the Date ?");
		Button bY = new Button("Yes");
		Button bN = new Button("No");
		bY.setMaxWidth(150);
		bN.setMaxWidth(150);


		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp, 300, 100);
		Stage stage = new Stage();
		stage.setScene(scene);


		bY.setOnAction(event -> {
			deleteDate(cbDDD.getValue());
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
		stage.setTitle("Warning");
		stage.show();
		return stage;
	}

	public void printHashTable(boolean withEm) {
		for (int i = 0; i < hashTable.table.length; i++) {
			HashEntry entry = hashTable.table[i];
			if (entry != null && (withEm || entry.getStatus() == 'F')) {
				taD.appendText(i + " -> " + entry.getKey() + " -> " + entry.getTree().getRoot().getMartyr()+"\n");
			} else if (withEm) {
				taD.appendText(i + " -> Empty\n");
			}
		}
		taD.appendText("Total Martyrs: " + hashTable.getTotalMartyrs()+"\n");
		taD.appendText("Total Inserts: " + hashTable.countD);
	}

	private void initializeQueue() {
		for (HashEntry entry : hashTable.table) {
			if (entry != null && entry.getStatus() == 'F') {
				nextEnt.add(entry);
			}
		}
	}

	public void navigateNext() {
		if (currEnt != null) {
			preEnt.push(currEnt);
		}
		while (!nextEnt.isEmpty()) {
			currEnt = (HashEntry) nextEnt.poll();
			if (currEnt.getStatus() == 'F') {
				updateUIWithEntryInfo(currEnt);
				loadMartyrsByDate(cbSDD.getValue());
				return;
			}
		}
		taD.appendText("No more entries available.\n");
	}

	public void navigatePrevious() {
		if (!preEnt.isEmpty()) {
			nextEnt.add(currEnt);
			while (!preEnt.isEmpty()) {
				currEnt = (HashEntry) preEnt.pop();
				if (currEnt.getStatus() == 'F') {
					updateUIWithEntryInfo(currEnt);
					loadMartyrsByDate(cbSDD.getValue());
					return;
				}
			}
		} else {
			taD.appendText("You are at the beginning. No previous entries available.\n");
		}
	}

	private void updateUIWithEntryInfo(HashEntry entry) {
		if (entry != null) {
			taD.setText("Current Date: " + entry.getKey() + "\n");
			cbSDD.setValue(entry.getKey());
			displayDateInfo(entry.getKey().toString());
		} else {
			taD.appendText("No entry to display.\n");
		}
	}

	private void displayDateInfo(String date) {
		AVLTree tree = hashTable.get(date);
		if (tree != null) {
			int totalMartyrs = countMartyrs(tree.getRoot());
			int totalAge = calculateTotalAge(tree.getRoot());
			double averageAge = totalAge / (double) totalMartyrs;

			taD.appendText("Total Martyrs: " + totalMartyrs + "\n");
			taD.appendText("Average Age: " + averageAge + "\n");

			String maxDistrict = tree.getDisWithMaxMar(tree.getRoot());
			String maxLocation = tree.getLocWithMaxMar(tree.getRoot());
			taD.appendText("District with maximum martyrs: " + maxDistrict + "\n");
			taD.appendText("Location with maximum martyrs: " + maxLocation + "\n");
		} else {
			taD.appendText("No information available for the selected date.\n");
		}
	}

	private int countMartyrs(Node node) {
		if (node == null) {
			return 0;
		}
		return 1 + countMartyrs(node.getLeft()) + countMartyrs(node.getRight());
	}

	private int calculateTotalAge(Node node) {
		if (node == null) {
			return 0;
		}
		return node.getMartyr().getAge() + calculateTotalAge(node.getLeft()) + calculateTotalAge(node.getRight());
	}

	public void searchDate(String date) {
		try {
			if (hashTable.contains(date)) {
				AVLTree tree = hashTable.get(date);
				taD.setText("Martyrs' Summary for Date: " + date + "\n\n");
				displayDateInfo(date);
				loadMartyrsByDate(date);
			} else {
				taD.setText("No record found for the date: " + date);
			}
		} catch (Exception e) {
			taD.setText("Invalid date format or error occurred: " + e.getMessage());
		}
	}


	public void loadMartyrsByDate(String date) {
		if (date == null || date.isEmpty()) {
			return;
		}
		orderData.clear();
		cbDM.getItems().clear();
		cbUM.getItems().clear();

		AVLTree tree = hashTable.get(date);

		if (tree != null) {
			loadMartyrsFromTree(tree.getRoot());
			sortByAge();
		} else {
			System.out.println("No martyrs found for the selected date: " + date);
		}
	}


	private void loadMartyrsFromTree(Node node) {
		if (node != null) {

			orderData.add(node.getMartyr());
			cbDM.getItems().add(node.getMartyr().getName());
			cbUM.getItems().add(node.getMartyr().getName());
			loadMartyrsFromTree(node.getLeft());
			loadMartyrsFromTree(node.getRight());
		}
	}




	//************************************************** MARTYR SCREEN **************************************************************




	private TableColumn<Martyr, String> createTableColumn(String title, String property) {
		TableColumn<Martyr, String> col = new TableColumn<>(title);
		col.setCellValueFactory(new PropertyValueFactory<>(property));
		return col;
	}

	public Stage insertMartyr() {
		cbD.setValue("District");
		cbL.setValue("Location");
		cbD.setMaxWidth(200);
		cbL.setMaxWidth(200);

		Stage stage = new Stage();
		BorderPane bp = new BorderPane();
		Scene scene = new Scene (bp,500,500);
		VBox vbox = new VBox(20);
		vbox.setAlignment(Pos.CENTER);

		Text title = new Text("Add A Martyr");
		title.setFont(new Font(20));

		GridPane gp = new GridPane();
		gp.setVgap(10);
		gp.setHgap(10);
		gp.setAlignment(Pos.CENTER);

		Label lN = new Label("Name: ");
		Label lA = new Label("Age: ");
		Label lD = new Label("District: ");
		Label lL = new Label("Location: ");
		Label lG = new Label("Gender: ");
		Button addB = new Button("Add");
		RadioButton rbM = new RadioButton("M");
		RadioButton rbF = new RadioButton("F");
		ToggleGroup genderGroup = new ToggleGroup();
		rbM.setToggleGroup(genderGroup);
		rbF.setToggleGroup(genderGroup);

		HBox hb = new HBox(20);
		hb.getChildren().addAll(rbM,rbF);

		gp.add(lN, 0, 0);
		gp.add(tfN, 1, 0);
		gp.add(lA, 0, 1);
		gp.add(tfA, 1, 1);
		gp.add(lD, 0, 2);
		gp.add(cbD, 1, 2);
		gp.add(lL, 0, 3);
		gp.add(cbL, 1, 3);
		gp.add(lG, 0, 4);
		gp.add(hb, 1, 4);


		File file = new File("C:\\Users\\mhmds\\Desktop\\DataStructure\\DataStructure Projects\\data.csv");
		addB.setOnAction(e -> addButton(file ,genderGroup )); 

		vbox.getChildren().addAll(title, gp, addB);
		bp.setCenter(vbox);
		stage.setScene(scene);
		stage.setTitle("Insert Martyr");
		stage.show();
		return stage;
	}

	public void addButton(File file, ToggleGroup genderGroup) {
		try {
			String name = tfN.getText();
			String age = tfA.getText();
			String dis = cbD.getValue();
			String loc = cbL.getValue();

			if (cbSDD.getValue() == null) {
				System.out.println("Please select a date.");
				return;
			}

			if (name.isEmpty() || age.isEmpty() || dis.isEmpty() || loc.isEmpty() || genderGroup.getSelectedToggle() == null) {
				System.out.println("Enter all information in boxes. Then press Add.");
				return;
			}

			int a = Integer.parseInt(age);
			if (a <= 0) {
				System.out.println("Invalid age format. Please enter an age greater than 0.");
				return;
			}

			String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
			String[] dateParts = cbSDD.getValue().split("/");
			int month = Integer.parseInt(dateParts[0]);
			int day = Integer.parseInt(dateParts[1]);
			int year = Integer.parseInt(dateParts[2]);
			Date date = new Date(year, month, day); // Assuming Date class has this constructor
			Martyr martyr = new Martyr(name, date, a, loc, dis, gender);


			hashTable.insert(date.toString(), martyr);
			updateDisComboBoxes();
			loadMartyrsByDate(cbSDD.getValue());

			orderTable.setItems(orderData);

			tfN.clear();
			tfA.clear();
			cbD.setValue("District");
			cbL.setValue("Location");
			genderGroup.selectToggle(null);
			System.out.println("Martyr added successfully.");

			writeMartyrToFile(file, martyr);

		} catch (NumberFormatException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}


	private void writeMartyrToFile(File file, Martyr martyr) {
		try (FileWriter writer = new FileWriter(file, true)) {
			writer.write(martyr.getName() + "," +martyr.getDate()+ "," + martyr.getAge() + "," + martyr.getDistrict() + "," +
					martyr.getLocation() + "," + martyr.getGender()+ "\n");
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

	private void updateDisComboBoxes() {
		cbD.getItems().clear();
		cbUDM.getItems().clear();

		String[] allDistricts = hashTable.getAllDistricts();
		for (String district : allDistricts) {
			cbD.getItems().add(district);
			cbUDM.getItems().add(district);
		}

		cbD.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				updateLocComboBox(newValue);
			}
		});

		cbUDM.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				updateLocComboBox(newValue);
			}
		});
	}


	private void updateLocComboBox(String selectedDistrict) {
		cbL.getItems().clear();		
		cbULM.getItems().clear();

		String[] locationsInDistrict = hashTable.getLocationsInDistrict(selectedDistrict);
		for (String location : locationsInDistrict) {
			cbL.getItems().add(location);
			cbULM.getItems().add(location);
		}
	}

	public void deleteMartyr(String martyrName) {
		if (martyrName == null || martyrName.isEmpty()) {
			System.out.println("No martyr selected for deletion.");
			return;
		}

		for (HashEntry entry : hashTable.table) {
			if (entry != null && entry.getStatus() == 'F') {
				AVLTree tree = entry.getTree();
				if (tree != null) {
					tree.delete(martyrName); 
					if (tree.getRoot() == null) {
						hashTable.remove(entry.getKey());
					}
				}
			}
		}

		loadMartyrsByDate(cbSDD.getValue());
	}

	public Stage warningDeMartyr() {
		Label label = new Label("Are you sure you want to delete the selected Martyr?");
		Button bY = new Button("Yes");
		Button bN = new Button("No");
		bY.setMaxWidth(150);
		bN.setMaxWidth(150);

		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp, 300, 100);
		Stage stage = new Stage();
		stage.setScene(scene);

		bY.setOnAction(event -> {
			deleteMartyr(cbDM.getValue());
			stage.close();
		});

		HBox hb = new HBox(10);
		hb.getChildren().addAll(bY, bN);
		hb.setAlignment(Pos.CENTER);

		VBox vb = new VBox(10);
		vb.getChildren().addAll(label, hb);
		vb.setAlignment(Pos.CENTER);

		bN.setOnAction(e -> stage.close());

		bp.setCenter(vb);
		stage.setTitle("Warning");
		stage.show();
		return stage;
	}


	public Stage updateMar() {
		try {
			cbUDM.setMaxWidth(200);
			cbULM.setMaxWidth(200);
			cbUM.setMaxWidth(200);
			cbUM.setValue("Martyr");
			cbUDM.setValue("District");
			cbULM.setValue("Location");

			Stage stage = new Stage();
			BorderPane bp = new BorderPane();
			Scene scene = new Scene(bp, 400, 300); 
			VBox vbox = new VBox(20);
			vbox.setAlignment(Pos.CENTER);
			Label lCN = new Label("Martyr Name:");
			Label lNN = new Label("New Martyr Name:");
			Label lNA = new Label("New Age:");
			Label lNL = new Label("New Location:");
			Label lND = new Label("New District:");
			Label lNG = new Label("New Gender:");
			Text title = new Text("Update Martyr");
			title.setFont(new Font(20));

			GridPane gp = new GridPane();
			gp.setVgap(10);
			gp.setHgap(10);
			gp.setAlignment(Pos.CENTER);

			Button upM = new Button("Update");
			RadioButton rbM = new RadioButton("M");
			RadioButton rbF = new RadioButton("F");
			ToggleGroup genderGroup = new ToggleGroup();
			rbM.setToggleGroup(genderGroup);
			rbF.setToggleGroup(genderGroup);

			HBox hb2 = new HBox(20);
			hb2.getChildren().addAll(rbM,rbF);

			HBox hb = new HBox(20);
			hb.getChildren().addAll(upM);

			gp.add(lCN, 0, 0);
			gp.add(cbUM, 1, 0);
			gp.add(lNN, 0, 1);
			gp.add(tfNNM, 1, 1);
			gp.add(lNA, 0, 2);
			gp.add(tfNewAge, 1, 2);
			gp.add(lND, 0, 3);
			gp.add(cbUDM, 1, 3);
			gp.add(lNL, 0, 4);
			gp.add(cbULM, 1, 4);
			gp.add(lNG, 0, 5);
			gp.add(hb2, 1, 5);

			upM.setOnAction(e -> {
				String currentName = cbUM.getValue();
				String newName = tfNNM.getText();
				int newAge = Integer.parseInt(tfNewAge.getText());
				String newLocation = cbULM.getValue();
				String newDistrict = cbUDM.getValue();
				String newGender = ((RadioButton) genderGroup.getSelectedToggle()).getText();

				warningUpMartyr(currentName, newName, newAge, newLocation, newDistrict, newGender);
				loadMartyrsByDate(cbSDD.getValue());
			});

			vbox.getChildren().addAll(title, gp, upM);
			bp.setCenter(vbox);
			stage.setScene(scene);
			stage.setTitle("Update Martyr");
			stage.show();
			return stage;
		}catch(NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Stage warningUpMartyr(String currentName, String newName, int newAge, String newLocation, String newDistrict, String newGender) {
		Label label = new Label("Are you sure you want to update the information for the selected Martyr?");
		Button bY = new Button("Yes");
		Button bN = new Button("No");
		bY.setMaxWidth(150);
		bN.setMaxWidth(150);

		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp, 400, 150); 
		Stage stage = new Stage();
		stage.setScene(scene);

		bY.setOnAction(event -> {
			updateMartyr(currentName, newName, newAge, newLocation, newDistrict, newGender);
			loadMartyrsByDate(cbSDD.getValue());
			stage.close();
		});

		HBox hb = new HBox(10);
		hb.getChildren().addAll(bY, bN);
		hb.setAlignment(Pos.CENTER);

		VBox vb = new VBox(10);
		vb.getChildren().addAll(label, hb);
		vb.setAlignment(Pos.CENTER);

		bN.setOnAction(e -> stage.close());

		bp.setCenter(vb);
		stage.setTitle("Warning");
		stage.show();
		return stage;
	}


	public void updateMartyr(String currentMartyrName, String newName, int newAge, String newLocation, String newDistrict, String newGender) {
		if (currentMartyrName == null || currentMartyrName.isEmpty()) {
			System.out.println("Invalid martyr name provided for update.");
			return;
		}

		String selectedDate = cbSDD.getValue();
		if (selectedDate == null || selectedDate.isEmpty()) {
			System.out.println("Please select a date.");
			return;
		}

		boolean martyrUpdated = false;
		for (HashEntry entry : hashTable.table) {
			if (entry != null && entry.getStatus() == 'F' && entry.getKey().equals(selectedDate)) {
				AVLTree tree = entry.getTree();
				if (tree != null) {
					martyrUpdated = updateMartyrInTree(tree.getRoot(), currentMartyrName, newName, newAge, newLocation, newDistrict, newGender);
				}
			}
		}

		if (martyrUpdated) {
			System.out.println("Martyr information updated successfully.");
		} else {
			System.out.println("Martyr not found.");
		}

		loadMartyrsByDate(selectedDate);
	}

	private boolean updateMartyrInTree(Node node, String currentMartyrName, String newName, int newAge, String newLocation, String newDistrict, String newGender) {
		if (node == null) {
			return false;
		}

		boolean updatedInLeft = updateMartyrInTree(node.getLeft(), currentMartyrName, newName, newAge, newLocation, newDistrict, newGender);
		boolean updatedInRight = updateMartyrInTree(node.getRight(), currentMartyrName, newName, newAge, newLocation, newDistrict, newGender);

		if (node.getMartyr().getName().equalsIgnoreCase(currentMartyrName)) {
			Martyr martyr = node.getMartyr();
			martyr.setName(newName);
			martyr.setAge(newAge);
			martyr.setLocation(newLocation);
			martyr.setDistrict(newDistrict);
			martyr.setGender(newGender);
			return true;
		}

		return updatedInLeft || updatedInRight;
	}

	public Stage printTree() {
		Stage stage = new Stage();
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp, 500, 200);

		TextArea taM = new TextArea();
		taM.setEditable(false);

		String selectedDate = cbSDD.getValue();
		if (selectedDate != null && !selectedDate.isEmpty()) {

			for (HashEntry entry : hashTable.table) {
				if (entry != null && entry.getStatus() == 'F' && entry.getKey().equals(selectedDate)) {
					tree = entry.getTree();
					break;
				}
			}

			if (tree != null) {
				StringBuilder treeString = new StringBuilder();
				printTreeLevelByLevel(tree.getRoot(), treeString);
				taM.setText(treeString.toString());
			} else {
				taM.setText("No tree found for the selected date.");
			}
		} else {
			taM.setText("Please select a date.");
		}

		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(taM);
		bp.setCenter(vbox);

		stage.setScene(scene);
		stage.setTitle("Print Tree");
		stage.show();
		return stage;
	}


	private void printTreeLevelByLevel(Node root, StringBuilder sb) {
		if (root == null) {
			sb.append("Tree is empty.");
			return;
		}

		Queue queue = new LinkedList();
		queue.add(root);

		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			StringBuilder levelString = new StringBuilder();

			for (int i = 0; i < levelSize; i++) {
				Node current = (Node) queue.poll();
				if (current != null) {
					levelString.insert(0, current.getMartyr().getName() + " \n "); // insert at the beginning for right-to-left printing
					if (current.getLeft() != null) {
						queue.add(current.getLeft());
					}
					if (current.getRight() != null) {
						queue.add(current.getRight());
					}
				}
			}

			sb.append(levelString.toString().trim()).append("\n");
		}
	}

	private void sortByAge() {
		Heap heap = new Heap(orderData.size());

		for (Martyr martyr : orderData) {
			heap.insert(martyr);
		}

		orderData.clear();

		while (!heap.isEmpty()) {
			orderData.add(heap.removeMax());
		}
	}

	private Martyr getMartyrByAge(int age) {
		for (int i = 0; i < orderData.size(); i++) {
			Martyr martyr = orderData.get(i);
			if (martyr.getAge() == age) {
				return martyr;
			}
		}
		return null;
	}

}
