package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AccountView {

    public void show(Stage stage) {
        Label title = new Label("Account Management");

        Label accountNumberLabel = new Label("Account Number:");
        TextField accountNumberField = new TextField();

        Label accountNameLabel = new Label("Account Holder:");
        TextField accountNameField = new TextField();

        Label balanceLabel = new Label("Initial Balance:");
        TextField balanceField = new TextField();

        Button addButton = new Button("Add Account");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));
        grid.addRow(0, title);
        grid.addRow(1, accountNumberLabel, accountNumberField);
        grid.addRow(2, accountNameLabel, accountNameField);
        grid.addRow(3, balanceLabel, balanceField);

        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 1, 4);

        Scene scene = new Scene(grid, 450, 300);
        stage.setTitle("Account Management");
        stage.setScene(scene);
        stage.show();

    }
}

