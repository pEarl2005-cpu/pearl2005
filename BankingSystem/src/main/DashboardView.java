package main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DashboardView {

    public void show(Stage stage) {
        Label title = new Label("Dashboard");
        Button accountButton = new Button("Manage Accounts");
        Button customerButton = new Button("Manage Customers");
        Button logoutButton = new Button("Logout");

        VBox layout = new VBox(15, title, accountButton, customerButton, logoutButton);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #e3f2fd;");
        layout.setPrefWidth(400);
        layout.setPrefHeight(300);

        stage.setScene(new Scene(layout));
        stage.setTitle("Dashboard");
        stage.show();
    }

    void start(Stage stage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

