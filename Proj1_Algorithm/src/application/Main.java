package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: 	#f5f5dc;\r\n");

		ImageView image = new ImageView(
				"https://img.icons8.com/external-wanicon-flat-wanicon/512/external-problem-solving-business-administration-wanicon-flat-wanicon.png");
		image.setFitHeight(270);
		image.setFitWidth(380);
		pane.getChildren().add(image);
		image.setTranslateX(470);
		image.setTranslateY(20);

		Label l0 = new Label("Enter coins number:");
		l0.setFont(new Font(20));
		pane.getChildren().add(l0);
		l0.setTranslateX(30);
		l0.setTranslateY(10);

		TextField t0 = new TextField();
		t0.setPrefSize(200, 40);
		pane.getChildren().add(t0);
		t0.setTranslateX(30);
		t0.setTranslateY(50);
		t0.setStyle("        -fx-background-radius:100;\r\n");

		Label l1 = new Label("Enter the coins (with space between them):");
		l1.setFont(new Font(20));
		pane.getChildren().add(l1);
		l1.setTranslateX(30);
		l1.setTranslateY(120);

		TextField t1 = new TextField();
		t1.setPrefSize(200, 40);
		pane.getChildren().add(t1);
		t1.setTranslateX(30);
		t1.setTranslateY(160);
		t1.setStyle("        -fx-background-radius:100;\r\n");

		Button b = new Button("Solve");
		pane.getChildren().add(b);
		b.setPrefSize(100, 50);
		b.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
				+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
				+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
				+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n" + "-fx-font-weight: bold;");
		b.setTranslateX(50);
		b.setTranslateY(235);

		Label line0 = new Label(
				"-----------------------------------------------------------------------------------------------------------------");
		line0.setFont(new Font(20));
		pane.getChildren().add(line0);
		line0.setTranslateX(0);
		line0.setTranslateY(290);

		Label line1 = new Label("|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|");
		line1.setFont(new Font(20));
		pane.getChildren().add(line1);
		line1.setTranslateX(400);
		line1.setTranslateY(305);

		Label l2 = new Label("Max ammount:");
		l2.setFont(new Font(20));
		pane.getChildren().add(l2);
		l2.setTranslateX(20);
		l2.setTranslateY(310);

		Label l3 = new Label("Player #1 coins:");
		l3.setFont(new Font(20));
		pane.getChildren().add(l3);
		l3.setTranslateX(20);
		l3.setTranslateY(380);

		Label l4 = new Label("The DP table:");
		l4.setFont(new Font(20));
		pane.getChildren().add(l4);
		l4.setTranslateX(20);
		l4.setTranslateY(455);

		TextArea ta0 = new TextArea();
		ta0.setPrefSize(350, 10);
		ta0.setFont(new Font(15));
		pane.getChildren().add(ta0);
		ta0.setTranslateX(20);
		ta0.setTranslateY(335);
		ta0.setStyle("-fx-background-color: 	#f5f5dc;\r\n");
		ta0.setEditable(false);

		TextArea ta1 = new TextArea();
		ta1.setPrefSize(350, 10);
		ta1.setFont(new Font(15));
		pane.getChildren().add(ta1);
		ta1.setTranslateX(20);
		ta1.setTranslateY(410);
		ta1.setStyle("-fx-background-color: 	#f5f5dc;\r\n");
		ta1.setEditable(false);

		TextArea ta2 = new TextArea();
		ta2.setPrefSize(350, 200);
		ta2.setFont(new Font(15));
		pane.getChildren().add(ta2);
		ta2.setTranslateX(20);
		ta2.setTranslateY(480);
		ta2.setStyle("-fx-background-color: 	#f5f5dc;\r\n");
		ta2.setEditable(false);

		b.setOnAction(e -> {
			if (t0.getText() == "" || t1.getText() == "") {
				Stage stage = new Stage();
				Label l = new Label("You miss something");
				l.setFont(new Font(15));
				Button b1 = new Button("Ok");
				b1.setPrefSize(50, 20);
				b1.setAlignment(Pos.CENTER);
				b1.setOnAction(s -> {
					stage.close();
				});
				BorderPane pane1 = new BorderPane();
				pane1.setTop(l);
				pane1.setBottom(b1);
				pane1.setAlignment(l, Pos.CENTER);
				pane1.setAlignment(b1, Pos.CENTER);
				Scene s = new Scene(pane1, 300, 100);
				stage.setScene(s);
				stage.setTitle("Error");
				stage.show();
				t0.clear();
				t1.clear();
			} else {
				if (Integer.parseInt(t0.getText()) % 2 != 0 || Integer.parseInt(t0.getText()) <= 0) {
					Stage stage = new Stage();
					Label l = new Label("The number is odd enter an even number");
					l.setFont(new Font(15));
					Button b1 = new Button("Ok");
					b1.setPrefSize(50, 20);
					b1.setAlignment(Pos.CENTER);
					b1.setOnAction(s -> {
						stage.close();
					});
					BorderPane pane1 = new BorderPane();
					pane1.setTop(l);
					pane1.setBottom(b1);
					pane1.setAlignment(l, Pos.CENTER);
					pane1.setAlignment(b1, Pos.CENTER);
					Scene s = new Scene(pane1, 300, 100);
					stage.setScene(s);
					stage.setTitle("Error");
					stage.show();
					t0.clear();
					t1.clear();
				} else {

					String[] s = t1.getText().trim().split(" ");

					if (s.length != Integer.parseInt(t0.getText())) {
						Stage stage = new Stage();
						Label l = new Label("Enter the same number of coins that you choose");
						l.setFont(new Font(15));
						Button b2 = new Button("Ok");
						b2.setPrefSize(50, 20);
						b2.setAlignment(Pos.CENTER);
						b2.setOnAction(r -> {
							stage.close();
						});
						BorderPane pane2 = new BorderPane();
						pane2.setTop(l);
						pane2.setBottom(b2);
						pane2.setAlignment(l, Pos.CENTER);
						pane2.setAlignment(b2, Pos.CENTER);
						Scene s2 = new Scene(pane2, 330, 100);
						stage.setScene(s2);
						stage.setTitle("Error");
						stage.show();
						t0.clear();
						t1.clear();
					} else {

						String[] st = t1.getText().trim().split(" ");
						int[] arr = new int[st.length];
						for (int i = 0; i < arr.length; i++) {
							if (st[i] == "")
								break;
							arr[i] = Integer.parseInt(st[i]);
						}

						Object[][] game = getTable(arr);

						Label l = new Label(String.valueOf(game[0][arr.length - 1].first));
						l.setFont(new Font(20));
						ta0.setText(l.getText());

						String table = "";

						for (int i = 0; i < game.length; i++) {
							for (int j = 0; j < game[i].length; j++) {
								table += String.valueOf(game[i][j].first + "\t");
							}
							table += "\n";
						}

						Label tl = new Label(table);
						tl.setFont(new Font(20));
						ta2.setText(tl.getText());

						int[] playerCoins = getSeq(arr, game);

						String coins = "";
						for (int i = 0; i < playerCoins.length; i = i + 2) {
							coins += String.valueOf("(" + playerCoins[i] + ") ");
						}

						Label cl = new Label(coins);
						cl.setFont(new Font(20));
						ta1.setText(cl.getText());

						Button b2 = new Button("Simulate");
						pane.getChildren().add(b2);
						b2.setPrefSize(100, 50);
						b2.setStyle(" -fx-padding: 8 15 15 15;\r\n"
								+ "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
								+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
								+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8,"
								+ "#d8a0d8," + "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
								+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
								+ "-fx-font-weight: bold;");
						b2.setTranslateX(190);
						b2.setTranslateY(235);

						b2.setOnAction(a -> {

							Label player1 = new Label("Player #1");
							player1.setFont(new Font(30));
							pane.getChildren().add(player1);
							player1.setTranslateX(570);
							player1.setTranslateY(330);

							Label player2 = new Label("Player #2");
							player2.setFont(new Font(30));
							pane.getChildren().add(player2);
							player2.setTranslateX(570);
							player2.setTranslateY(590);

							StackPane[] stack = new StackPane[arr.length];

							for (int i = 0; i < stack.length; i++) {
								stack[i] = new StackPane();
								Circle circle = new Circle(20);
								circle.setFill(Color.WHITE);
								circle.setStroke(Color.BLACK);
								Label label = new Label(String.valueOf(arr[i]));
								stack[i].getChildren().add(circle);
								stack[i].getChildren().add(label);
								pane.getChildren().add(stack[i]);
								stack[i].setTranslateX(430 + (i * 60));
								stack[i].setTranslateY(460);
							}
							

							int[] array = getSeq2(arr, game);
							int[] player1Indexes = new int[arr.length / 2];
							int[] player2Indexes = new int[arr.length / 2];

							for (int i = 0, j = 0, k = 0; i < playerCoins.length; i++) {
								if (i % 2 == 0) {
									player1Indexes[j] = array[i];
									j++;
								} else {
									player2Indexes[k] = array[i];
									k++;
								}
							}

							SequentialTransition seqT = new SequentialTransition();

							TranslateTransition[] tt = new TranslateTransition[arr.length];

							for (int i = 0, j = 0, k = 0; i < stack.length; i++) {
								if (i % 2 == 0) {
									tt[i] = new TranslateTransition();
									tt[i] = new TranslateTransition(Duration.seconds(1.5), stack[player1Indexes[j]]);
									tt[i].setFromX(430 + (player1Indexes[j] * 60));
									tt[i].setFromY(460);
									tt[i].setByY(-35);
									tt[i].setCycleCount(1);
									seqT.getChildren().add(tt[i]);
									j++;
								} else {
									tt[i] = new TranslateTransition();
									tt[i] = new TranslateTransition(Duration.seconds(1.5), stack[player2Indexes[k]]);
									tt[i].setFromX(430 + (player2Indexes[k] * 60));
									tt[i].setFromY(460);
									tt[i].setByY(35);
									tt[i].setCycleCount(1);
									seqT.getChildren().add(tt[i]);
									k++;
								}

							}

							seqT.play();

						});

					}
				}
			}

		});

		Scene root = new Scene(pane, 900, 700);
		Stage stage = new Stage();
		stage.setScene(root);
		stage.setTitle("Project #1");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Object[][] getTable(int[] arr) {
		int n = arr.length;

		Object[][] game = new Object[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				game[i][j] = new Object(0, 0);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					game[i][j] = new Object(arr[i], 0);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			// to track the sequence of moves
			game[i][i].pick = i;
		}

		for (int i = n; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				if (j > i) {
					if (game[i + 1][j].second + arr[i] > game[i][j - 1].second + arr[j]) {
						game[i][j].first = game[i + 1][j].second + arr[i];
						game[i][j].second = game[i + 1][j].first;
						game[i][j].pick = i;
					} else {
						game[i][j].first = game[i][j - 1].second + arr[j];
						game[i][j].second = game[i][j - 1].first;
						game[i][j].pick = j;
					}

				}
			}
		}
		return game;
	}

	public static int[] getSeq(int arr[], Object moves[][]) {
		int i = 0;
		int j = arr.length - 1;
		int step;
		int[] plays = new int[arr.length];
		for (int k = 0; k < arr.length; k++) {
			step = moves[i][j].pick;
			// this is the value of pick and its index
			plays[k] = arr[step];
			if (step <= i) {
				i = i + 1;
			} else {
				j = j - 1;
			}
		}
		return plays;
	}

	public static int[] getSeq2(int arr[], Object moves[][]) {
		int i = 0;
		int j = arr.length - 1;
		int step;
		int[] plays = new int[arr.length];
		for (int k = 0; k < arr.length; k++) {
			step = moves[i][j].pick;
			// this is the value of pick and its index
			plays[k] = step;
			if (step <= i) {
				i = i + 1;
			} else {
				j = j - 1;
			}
		}
		return plays;
	}

}
