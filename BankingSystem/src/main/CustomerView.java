package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CustomerView {

    public void show(Stage stage) {
        Label title = new Label("Customer Management");

        Label idLabel = new Label("Customer ID:");
        TextField idField = new TextField();

        Label nameLabel = new Label("Full Name:");
        TextField nameField = new TextField();

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, title);
        grid.addRow(1, idLabel, idField);
        grid.addRow(2, nameLabel, nameField);
        grid.addRow(3, phoneLabel, phoneField);
        grid.addRow(4, emailLabel, emailField);

        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 1, 5);

        Scene scene = new Scene(grid, 450, 350);
        stage.setTitle("Customer Management");
        stage.setScene(scene);
        stage.show();
    }
}

