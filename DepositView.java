package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DepositView {

    private final DepositController controller; // link to controller

    public DepositView() {
        controller = new DepositController(); // initialize controller
    }

    public void start(Stage stage) {
        stage.setTitle("Deposit Funds");

        Label title = new Label("Deposit Money");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField accountField = new TextField();
        accountField.setPromptText("Enter Account Number");

        TextField amountField = new TextField();
        amountField.setPromptText("Enter Amount to Deposit");

        Button depositButton = new Button("Deposit");
        Button backButton = new Button("Back");

        Label messageLabel = new Label();

        depositButton.setOnAction(e -> {
            String accountNumber = accountField.getText().trim();
            String amountText = amountField.getText().trim();

            if (accountNumber.isEmpty() || amountText.isEmpty()) {
                messageLabel.setText("⚠️ Please fill in all fields.");
                return;
            }

            try {
                double amount = Double.parseDouble(amountText);
                if (amount <= 0) {
                    messageLabel.setText("⚠️ Deposit amount must be positive.");
                    return;
                }

                // Call the controller to perform the deposit
                boolean success = controller.deposit(accountNumber, amount);

                if (success) {
                    messageLabel.setText("✅ Deposit successful!");
                    accountField.clear();
                    amountField.clear();
                } else {
                    messageLabel.setText("❌ Account not found. Try again.");
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("⚠️ Invalid amount. Please enter a number.");
            }
        });

        backButton.setOnAction(e -> {
            // Go back to main dashboard or login screen
            DashboardView dashboard = new DashboardView();
            dashboard.start(stage);
        });

        VBox layout = new VBox(10, title, accountField, amountField, depositButton, backButton, messageLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}

