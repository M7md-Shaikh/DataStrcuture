module Test3 {
	requires javafx.controls;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
