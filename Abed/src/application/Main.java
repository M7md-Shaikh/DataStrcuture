
 package application;
	
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    Helpers helpers = new Helpers();


    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        Group group = new Group();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(130, 130, 130, 130));
        pane.setHgap(10.5);
        pane.setVgap(10.5);
        pane.setStyle("-fx-background-color: 	#8bf1a4;\r\n");


        BackgroundFill fill = new BackgroundFill(Color.rgb(194, 194, 214), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(fill);


        Button random = new Button("Random Number");
        random.setFont(Font.font(14));
        random.setBackground(background);
        random.setTextFill(Color.rgb(40, 40, 62));
        random.setPrefSize(150, 50);
        pane.add(random, 1, 1);
        random.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
				+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
				+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
				+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n" + "-fx-font-weight: bold;");
        Button file = new Button("Read File");
        file.setFont(Font.font(14));
        file.setBackground(background);
        file.setTextFill(Color.rgb(40, 40, 62));
        file.setPrefSize(150, 50);
        pane.add(file, 1, 0);
        file.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
				+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
				+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
				+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n" + "-fx-font-weight: bold;");

        Button cancel = new Button("Cancel");
        cancel.setFont(Font.font(14));
        cancel.setBackground(background);
        cancel.setTextFill(Color.rgb(40, 40, 62));
        cancel.setPrefSize(150, 50);
        pane.add(cancel, 1, 2);
        cancel.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
				+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
				+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
				+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n" + "-fx-font-weight: bold;");

        group.getChildren().addAll(pane);

        random.setOnAction(e -> {
            Stage randomStage = new Stage();
            GridPane pane2 = new GridPane();
            pane2.setStyle("-fx-background-color: 	#8bf1a4;\r\n");
            Group group1 = new Group();
            pane2.setPadding(new Insets(100, 130, 100, 130));
            pane2.setHgap(15.5);
            pane2.setVgap(15.5);

            Label numberOfLED = new Label("Enter Number of Leds");
            numberOfLED.setFont(Font.font(16));
            numberOfLED.setTextFill(Color.rgb(40, 40, 62));
            pane2.add(numberOfLED, 0, 0);

            TextField numberOfLEDT = new TextField();
            numberOfLEDT.setFont(Font.font(14));
            numberOfLEDT.setPrefWidth(100);
            pane2.add(numberOfLEDT, 1, 0);
            numberOfLEDT.setStyle("        -fx-background-radius:100;\r\n");

            Button run = new Button("Run");
            run.setTextFill(Color.rgb(40, 40, 62));
            run.setFont(Font.font(16));
            GridPane.setHalignment(run, HPos.CENTER);
            run.setPrefSize(150, 50);
            run.setBackground(background);
            pane2.add(run, 1, 2);
            run.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
    				+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
    				+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
    				+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
    				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n" + "-fx-font-weight: bold;");

            Button btCancel = new Button("Cancel");
            btCancel.setTextFill(Color.rgb(40, 40, 62));
            btCancel.setFont(Font.font(16));
            btCancel.setPrefSize(150, 50);
            btCancel.setBackground(background);
            pane2.add(btCancel, 2, 2);
            btCancel.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
    				+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
    				+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
    				+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
    				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n" + "-fx-font-weight: bold;");


            run.setOnAction(d -> {
                int c = Integer.parseInt(numberOfLEDT.getText().trim());
                helpers.random(c);
                Stage stage3 = new Stage();

                Group group2 = printSample(helpers.random);


                Scene scene = new Scene(group2);
                scene.setFill(new LinearGradient(
                        0, 0, 1, 1, true,                      //sizing
                        CycleMethod.NO_CYCLE,                  //cycling
                        new Stop(0, Color.web("#8bf1a4")),     //colors
                        new Stop(1, Color.web("#8bf1a4")))
                		
                );
                stage3.setTitle("MaX Number");
                stage3.setScene(scene);
                stage3.show();


            });

            btCancel.setOnAction(d -> {
                randomStage.close();
            });

            group1.getChildren().addAll(pane2);
            Scene scene = new Scene(group1);
            scene.setFill(new LinearGradient(
                    0, 0, 1, 1, true,                      //sizing
                    CycleMethod.NO_CYCLE,                  //cycling
                    new Stop(0, Color.web("#8bf1a4")),     //colors
                    new Stop(1, Color.web("#8bf1a4")))
            );
            randomStage.setTitle("Random Number");
            randomStage.setScene(scene);
            randomStage.show();

        });


        file.setOnAction(f -> {
            helpers.readFile();
            Stage stage3 = new Stage();
            Group group2 =
                    printSample(helpers.ledsBoard);


            Scene scene = new Scene(group2);
            scene.setFill(new LinearGradient(
                    0, 0, 1, 1, true,                      //sizing
                    CycleMethod.NO_CYCLE,                  //cycling
                    new Stop(0, Color.web("#8bf1a4")),     //colors
                    new Stop(1, Color.web("#8bf1a4")))
            );
            stage3.setTitle("MaX Number");
            stage3.setScene(scene);
            stage3.show();


        });
        cancel.setOnAction(g -> {
            System.exit(0);
        });


        Scene scene = new Scene(group);
        scene.setFill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#c1c1d7")),     //colors
                new Stop(1, Color.web("#3c3c5d")))
        );
        primaryStage.setScene(scene);
        primaryStage.setTitle("Max Number Of Led Light");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


    public Group printDynamic(LedsBoard ledsBoards) {
        Group group = new Group();
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        Label labelsPower[] = new Label[ledsBoards.getPower().size()];
        Label labelsLED[] = new Label[ledsBoards.getLeds().size()];

        for (int i = 0; i < labelsLED.length; i++) {
            //this is the limit of my interface
            if (i < 15) {
                //fill vbox1
                labelsLED[i] = new Label(ledsBoards.getLeds().get(i).getIndex() + "");

                labelsLED[i].setFont(Font.font("Verdana", 15));
                vBox.getChildren().add(labelsLED[i]);

                //fill vbox2
                labelsPower[i] = new Label(ledsBoards.getPower().get(i).getIndex() + "");
                labelsPower[i].setFont(Font.font("Verdana", 15));
                hBox.getChildren().add(labelsPower[i]);

            } else {
                break;
            }
        }
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: 	#8bf1a4;\r\n");

        int dynamicTable[][] = helpers.dynamicTable;
        char arrowTable[][] = helpers.arrowTable;

        for (int i = 0; i < dynamicTable.length; i++) {
            for (int j = 0; j < dynamicTable[i].length; j++) {
                gridPane.add(new Label(dynamicTable[i][j] + "" + arrowTable[i][j] + ""), i, j);
            }
        }

        vBox.setSpacing(30);
        vBox.setLayoutX(10);
        vBox.setLayoutY(100);
        hBox.setSpacing(50);
        hBox.setLayoutY(20);
        hBox.setLayoutX(95);
        vBox.setStyle("-fx-background-color: 	#8bf1a4;\r\n");
        hBox.setStyle("-fx-background-color: 	#8bf1a4;\r\n");

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        for (int i = 0; i <= ledsBoards.getLeds().size(); i++) {
            ColumnConstraints column = new ColumnConstraints(40);
            column.setPrefWidth(50);
            gridPane.getColumnConstraints().add(column);
        }

        for (int i = 0; i <= ledsBoards.getPower().size(); i++) {
            RowConstraints row = new RowConstraints(40);
            gridPane.getRowConstraints().add(row);
        }
        gridPane.setLayoutX(20);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setLayoutY(30);
        gridPane.setGridLinesVisible(true);

        group.getChildren().addAll(gridPane, vBox, hBox);
        
        return group;
    }

    public Group printSample(LedsBoard ledsBoard) {
        Group group2 = new Group();
        Label label = new Label("Max Number Of Led Light");
        TextField textField = new TextField();
        textField.setVisible(true);
        textField.setStyle("        -fx-background-radius:100;\r\n");
        label.setLayoutX(10);
        label.setLayoutY(10);
        textField.setLayoutX(200);
        textField.setLayoutY(10);
        Button table = new Button("Dynamic Table");
        table.setLayoutX(250);
        table.setLayoutY(650);
        table.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
				+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
				+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
				+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n" + "-fx-font-weight: bold;");

        //this will contain two VBox and each VBox have labels with array element
        HBox hBox = new HBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();

        Button buttonPower[] = new Button[ledsBoard.getPower().size()];

        Button buttonLED[] = new Button[ledsBoard.getLeds().size()];
        textField.setText(helpers.solveLedsBoard(ledsBoard) + "" + helpers.print(helpers.arrowTable, ledsBoard.getLeds(), ledsBoard.getPower().size(), ledsBoard.getLeds().size()));

        for (int i = 0; i < buttonLED.length; i++) {
            //this is the limit of my interface
            if (i < 12) {
                //fill vbox1
                buttonLED[i] = new Button(ledsBoard.getLeds().get(i).getIndex() + "");
                buttonLED[i].setFont(Font.font("Verdana", 15));
                Image image = new Image(getClass().getResourceAsStream("led.png"));
                ImageView imageView = new ImageView(image);
                buttonLED[i].setGraphic(imageView);
                imageView.setFitHeight(40);
                imageView.setFitWidth(40);
                buttonLED[i].setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
                vBox1.getChildren().add(buttonLED[i]);


                //fill vbox2
                buttonPower[i] = new Button(ledsBoard.getPower().get(i).getIndex() + "");
                buttonPower[i].setFont(Font.font("Verdana", 15));
                Image image1 = new Image(getClass().getResourceAsStream("power.png"));
                ImageView imageView1 = new ImageView(image1);
                buttonPower[i].setGraphic(imageView1);
                imageView1.setFitHeight(40);
                imageView1.setFitWidth(40);
                buttonPower[i].setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
                vBox2.getChildren().add(buttonPower[i]);


            } else {
                break;
            }
        }


        for (int i = 0; i < helpers.ledLight.size(); i++) {
            for (int j = 0; j < buttonLED.length; j++) {
                int varu = Integer.parseInt(buttonLED[j].getText());
                if (helpers.ledLight.get(i) == varu) {

                    Image image = new Image(getClass().getResourceAsStream("ledOn.png"));
                    ImageView imageView = new ImageView(image);
                    buttonLED[j].setGraphic(imageView);
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);

                }
            }
        }
        for (int i = 0; i < helpers.ledLight.size(); i++) {
            for (int j = 0; j < buttonPower.length; j++) {
                int varu = Integer.parseInt(buttonPower[j].getText());
                if (helpers.ledLight.get(i) == varu) {

                    Image image = new Image(getClass().getResourceAsStream("PowerOn.png"));
                    ImageView imageView = new ImageView(image);
                    buttonPower[j].setGraphic(imageView);
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);

                }
            }
        }
            vBox1.setSpacing(10);
            vBox2.setSpacing(10);
            hBox.setSpacing(180);
            hBox.setLayoutX(40);
            hBox.setLayoutY(60);
            hBox.getChildren().addAll(vBox1, vBox2);
            group2.getChildren().addAll(hBox, label, textField, table);

            table.setOnAction(r -> {
                Stage stage6 = new Stage();
                Group group3;
                group3 = printDynamic(ledsBoard);

                Scene scene1 = new Scene(group3);
                scene1.setFill(new LinearGradient(
                        0, 0, 1, 1, true,                      //sizing
                        CycleMethod.NO_CYCLE,                  //cycling
                        new Stop(0, Color.web("#8bf1a4")),     //colors
                        new Stop(1, Color.web("#8bf1a4")))
                );
                stage6.setTitle("Random Number");
                stage6.setScene(scene1);
                stage6.show();
            });

            return group2;
        }
    }


