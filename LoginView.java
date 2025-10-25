package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    private final LoginController controller;

    public LoginView() {
        controller = new LoginController();
    }

    public void start(Stage stage) {
        Label title = new Label("WELCOME TO ACCESS BANK");
        title.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Button cancelButton = new Button("Cancel");

        VBox layout = new VBox(10, title, usernameField, passwordField, loginButton, cancelButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #8B4513;");

        Scene scene = new Scene(layout, 350, 300);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();

        // ✅ Login button action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            boolean success = controller.handleLogin(username, password);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
                stage.close(); // close login window

                // ✅ Open dashboard
                Stage dashboardStage = new Stage();
                controller.openDashboard(dashboardStage);
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            }
        });

        cancelButton.setOnAction(e -> {
            usernameField.clear();
            passwordField.clear();
        });
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


