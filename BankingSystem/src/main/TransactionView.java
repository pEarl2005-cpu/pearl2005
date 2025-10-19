package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TransactionView {

    public void show(Stage stage) {
        Label title = new Label("Transaction Management");

        Label accountNumberLabel = new Label("Account Number:");
        TextField accountNumberField = new TextField();

        Label typeLabel = new Label("Transaction Type:");
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Deposit", "Withdrawal");

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();

        Button processButton = new Button("Process Transaction");
        Button backButton = new Button("Back");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, title);
        grid.addRow(1, accountNumberLabel, accountNumberField);
        grid.addRow(2, typeLabel, typeBox);
        grid.addRow(3, amountLabel, amountField);

        HBox buttonBox = new HBox(10, processButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 1, 4);

        Scene scene = new Scene(grid, 450, 300);
        stage.setTitle("Transaction Management");
        stage.setScene(scene);
        stage.show();

        // No logic here; only GUI display
    }
}

