package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WithdrawView {

    private final WithdrawController controller;

    public WithdrawView() {
        controller = new WithdrawController(); // initialize controller
    }

    public void start(Stage stage) {
        stage.setTitle("Withdraw Funds");

        Label title = new Label("Withdraw Money");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField accountField = new TextField();
        accountField.setPromptText("Enter Account Number");

        TextField amountField = new TextField();
        amountField.setPromptText("Enter Amount to Withdraw");

        Button withdrawButton = new Button("Withdraw");
        Button backButton = new Button("Back");

        Label messageLabel = new Label();

        withdrawButton.setOnAction(e -> {
            String accountNumber = accountField.getText().trim();
            String amountText = amountField.getText().trim();

            if (accountNumber.isEmpty() || amountText.isEmpty()) {
                messageLabel.setText("⚠️ Please fill in all fields.");
                return;
            }

            try {
                double amount = Double.parseDouble(amountText);
                if (amount <= 0) {
                    messageLabel.setText("⚠️ Withdrawal amount must be positive.");
                    return;
                }

                boolean success = controller.withdraw(accountNumber, amount);

                if (success) {
                    messageLabel.setText("✅ Withdrawal successful!");
                    accountField.clear();
                    amountField.clear();
                } else {
                    messageLabel.setText("❌ Account not found or insufficient balance.");
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("⚠️ Invalid amount. Please enter a number.");
            }
        });

        backButton.setOnAction(e -> {
            DashboardView dashboard = new DashboardView();
            dashboard.start(stage);
        });

        VBox layout = new VBox(10, title, accountField, amountField, withdrawButton, backButton, messageLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}

