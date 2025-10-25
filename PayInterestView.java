package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Map;

public class PayInterestView {

    public void start(Stage stage) {
        stage.setTitle("Pay Interest to Accounts");

        Label title = new Label("Pay Interest");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TableView<Account> table = new TableView<>();

        TableColumn<Account, String> accountCol = new TableColumn<>("Account Number");
        accountCol.setCellValueFactory(cell -> cell.getValue().accountNumberProperty());

        TableColumn<Account, Double> balanceCol = new TableColumn<>("Balance (P)");
        balanceCol.setCellValueFactory(cell -> cell.getValue().balanceProperty().asObject());

        table.getColumns().addAll(accountCol, balanceCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(loadAccounts());

        TextField rateField = new TextField();
        rateField.setPromptText("Enter interest rate (%)");

        Label messageLabel = new Label();

        Button applyButton = new Button("Apply Interest");
        Button refreshButton = new Button("Refresh");
        Button backButton = new Button("Back");

        applyButton.setOnAction(e -> {
            String rateText = rateField.getText().trim();

            if (rateText.isEmpty()) {
                messageLabel.setText("⚠️ Please enter an interest rate.");
                return;
            }

            try {
                double rate = Double.parseDouble(rateText);
                if (rate <= 0) {
                    messageLabel.setText("⚠️ Interest rate must be positive.");
                    return;
                }

                // Apply interest to all accounts
                BankDatabase.applyInterest(rate);
                table.setItems(loadAccounts()); // refresh table
                messageLabel.setText("✅ Interest of " + rate + "% applied successfully!");
                rateField.clear();

            } catch (NumberFormatException ex) {
                messageLabel.setText("⚠️ Invalid rate. Please enter a number.");
            }
        });

        refreshButton.setOnAction(e -> table.setItems(loadAccounts()));

        backButton.setOnAction(e -> {
            DashboardView dashboard = new DashboardView();
            dashboard.start(stage);
        });

        VBox layout = new VBox(10, title, table, rateField, applyButton, refreshButton, backButton, messageLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 500, 450);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Account> loadAccounts() {
        ObservableList<Account> accountsList = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : BankDatabase.getAccounts().entrySet()) {
            accountsList.add(new Account(entry.getKey(), entry.getValue()));
        }
        return accountsList;
    }
}

