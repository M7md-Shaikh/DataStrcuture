package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
	private File file;

	private Compress compress;

	@Override
	public void start(Stage stage) throws IOException {

		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10, 10, 70, 10));

		Label welcomeLabel = new Label("File Compression");
		welcomeLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		welcomeLabel.setTextFill(Color.DARKGRAY);

		borderPane.setTop(welcomeLabel);
		BorderPane.setAlignment(welcomeLabel, Pos.TOP_CENTER);

		HBox hBoxOperations = new HBox(50);
		HBox hBoxCompress = new HBox(10);
		HBox hBoxDeCompress = new HBox(10);

		Label compressLabel = new Label("Choose a file to be compressed:");
		Button compressButton = new Button("Compress file");
		hBoxCompress.getChildren().addAll(compressLabel, compressButton);

		Label deCompressLabel = new Label("Choose a file to be deCompressed:");
		Button deCompressButton = new Button("Decompress file");

		hBoxDeCompress.getChildren().addAll(deCompressLabel, deCompressButton);

		hBoxOperations.getChildren().addAll(hBoxCompress, hBoxDeCompress);

		VBox vBox = new VBox(10);

		Label outputLabel = new Label("output");

		Button stat = new Button("Statistics");
		stat.setOnAction(actionEvent -> {
			if (compress != null) {

				outputLabel.setText("some statistics about the compression: " + compress.stat.toString());
//            System.out.println(compress.stat);
			}

			else
				outputLabel.setText("Please do Compression operation first");
		});

		Button header = new Button("Header info");
		header.setOnAction(actionEvent -> {
			if (compress != null)
				headerInfo().show();
			else
				outputLabel.setText("Please do Compression operation first");
		});

		vBox.getChildren().addAll(new Label(""), hBoxOperations, stat, header, outputLabel);

		// borderPane.setCenter(FXCollections.observableArrayList());

		borderPane.setLeft(vBox);

		/////////////
		// ----Compress-----

		compressButton.setOnAction(actionEvent -> {
			Stage stage1 = fileStage(outputLabel, 'c', borderPane);
			stage1.setTitle("Compression");
			stage1.show();

		});

		/////////////

		// ----DeCompress-----

		deCompressButton.setOnAction(actionEvent -> {
			Stage stage1 = fileStage(outputLabel, 'd', borderPane); // d: means decompression

			stage1.setTitle("Decompression");
			stage1.show();

		});

		/////////////

		Scene scene = new Scene(borderPane, 850, 650);
		stage.setTitle("Huffman Compression");
		stage.setScene(scene);
		stage.show();
	}

	private Stage fileStage(Label outputLabel, char operation, BorderPane borderPane) {

		Stage inputFileStage = new Stage();
		inputFileStage.setTitle("File chooser");

		Label inputFileLabel = new Label("Please Choose the file");
		inputFileLabel.setFont(Font.font("Verdana", 20));

		BorderPane inputFileBorderPane = new BorderPane();
		inputFileBorderPane.setPadding(new Insets(15));

		inputFileBorderPane.setTop(inputFileLabel);

		Button fileButton = new Button("Browse the file");
		inputFileBorderPane.setCenter(fileButton);

		BorderPane.setAlignment(inputFileLabel, Pos.TOP_CENTER);
		BorderPane.setAlignment(fileButton, Pos.CENTER);

		Scene fileScene = new Scene(inputFileBorderPane, 400, 300);
		inputFileStage.setScene(fileScene);

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open the File");

		fileButton.setOnAction(actionEvent -> {

			if (operation == 'c') {

			} else {
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Huf Files", "*.huf"));
			}

			File file = fileChooser.showOpenDialog(inputFileStage);

			if (file != null) {

				this.file = file;
				// linesArrayList.clear();

				if (operation == 'c') {

					CreateHuffman create = new CreateHuffman();

					try {
						create.readFile(file); // get the frequency
						create.buildHuffManTree(); // build the huffman tree

						compress = new Compress(create.getTable(), create.getRoot()); // create.getTable(): the huffman
																						// codes table

						compress.compress(file);
						fillTable(borderPane);
						outputLabel.setText("The compression for the file '" + file.getName() + "' is done");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						Decompress decompress = new Decompress();
						decompress.decompress(this.file);

						outputLabel.setText("The Decompression for the file '" + file.getName() + "' is done\n"
								+ "The new File is: '" + decompress.getNewDeCompressedFile().getName()
								+ "', in the same directory");

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// CreateHuffman.readFile(file, linesArrayList);
				inputFileStage.close();
			}
		});

		return inputFileStage;
	}

	public void fillTable(BorderPane borderPane) {
		ArrayList<Huffman> data = new ArrayList<>();
		ObservableList<Huffman> dataList;

		for (int i = 0; i < compress.table.length; i++) {
			data.add(compress.table[i]);
		}
		dataList = FXCollections.observableArrayList(data);
		TableView<Huffman> myDataTable = new TableView<>();
		myDataTable.setEditable(true);
		// name of column for display
		TableColumn<Huffman, Integer> ascii = new TableColumn<>("ascii(char)");
		ascii.setMinWidth(50);
		ascii.setCellValueFactory(new PropertyValueFactory<>("ascii"));
		ascii.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		TableColumn<Huffman, String> huffman = new TableColumn<>("huffman code");
		huffman.setMinWidth(50);
		huffman.setCellValueFactory(new PropertyValueFactory<>("huffman"));

		TableColumn<Huffman, Integer> repetition = new TableColumn<>("repetition");
		repetition.setMinWidth(50);
		repetition.setCellValueFactory(new PropertyValueFactory<>("repetition"));
		repetition.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		TableColumn<Huffman, Integer> length = new TableColumn<>("length of code");
		length.setMinWidth(50);
		length.setCellValueFactory(new PropertyValueFactory<>("length"));
		length.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		myDataTable.setItems(dataList);

		myDataTable.getColumns().addAll(ascii, huffman, repetition, length);
		myDataTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		VBox vBox = new VBox(5);
		Label label = new Label("Below the huffman codes of each char:");
		vBox.getChildren().addAll(label, myDataTable);
		borderPane.setBottom(vBox);
		BorderPane.setAlignment(vBox, Pos.BOTTOM_CENTER);

	}

	public Stage headerInfo() {
		String s = "";
		for (int i = 0; i < compress.list.size(); i++) {

			if (i == 0)
				s += "\n" + "extension length: " + (compress.list.get(i) + 1);

			else
				s += "\n" + compress.list.get(i);

		}
		BorderPane borderPane = new BorderPane();

		Label welcomeLabel = new Label("Welcome to Header data");
		welcomeLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		welcomeLabel.setTextFill(Color.DARKGRAY);

		borderPane.setTop(welcomeLabel);
		BorderPane.setAlignment(welcomeLabel, Pos.TOP_CENTER);
		Label output = new Label("");
		output.setText(s);
		borderPane.setCenter(output);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPadding(new Insets(10));

		scrollPane.setContent(borderPane);

		Stage stage = new Stage();
		Scene scene = new Scene(scrollPane, 500, 400);
		stage.setScene(scene);
		stage.setTitle("Header");

		return stage;

	}

	public static void main(String[] args) {
		launch();
	}

}