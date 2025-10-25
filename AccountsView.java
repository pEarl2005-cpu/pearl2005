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

public class AccountsView {

    public void start(Stage stage) {
        stage.setTitle("View Accounts");

        Label title = new Label("Customer Accounts");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Table setup
        TableView<Account> table = new TableView<>();

        TableColumn<Account, String> accountCol = new TableColumn<>("Account Number");
        accountCol.setCellValueFactory(cellData -> cellData.getValue().accountNumberProperty());

        TableColumn<Account, Double> balanceCol = new TableColumn<>("Balance (P)");
        balanceCol.setCellValueFactory(cellData -> cellData.getValue().balanceProperty().asObject());

        table.getColumns().addAll(accountCol, balanceCol);
        table.setItems(loadAccounts());

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button refreshButton = new Button("Refresh");
        Button backButton = new Button("Back");

        refreshButton.setOnAction(e -> table.setItems(loadAccounts()));

        backButton.setOnAction(e -> {
            DashboardView dashboard = new DashboardView();
            dashboard.start(stage);
        });

        VBox layout = new VBox(10, title, table, refreshButton, backButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    // Helper method to load accounts into observable list
    private ObservableList<Account> loadAccounts() {
        ObservableList<Account> accountsList = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : BankDatabase.getAccounts().entrySet()) {
            accountsList.add(new Account(entry.getKey(), entry.getValue()));
        }
        return accountsList;
    }
}

