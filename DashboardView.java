package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView {

    private final DashboardController controller;

    public DashboardView() {
        controller = new DashboardController();
    }

    public void start(Stage stage) {
        Label title = new Label("ACCESS BANK DASHBOARD");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: darkblue;");

        Button depositButton = new Button("Deposit Funds");
        Button withdrawButton = new Button("Withdraw Funds");
        Button viewAccountsButton = new Button("View Accounts");
        Button payInterestButton = new Button("Pay Interest");
        Button logoutButton = new Button("Logout");

        depositButton.setMinWidth(200);
        withdrawButton.setMinWidth(200);
        viewAccountsButton.setMinWidth(200);
        payInterestButton.setMinWidth(200);
        logoutButton.setMinWidth(200);

        VBox layout = new VBox(15, title, depositButton, withdrawButton, viewAccountsButton, payInterestButton, logoutButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30; -fx-background-color: brown;");

        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Bank Dashboard");
        stage.show();

        // Button actions (connect to controller)
        depositButton.setOnAction(e -> {
    DepositView depositView = new DepositView();
    depositView.start(stage);
});

        withdrawButton.setOnAction(e -> {
    WithdrawView withdrawView = new WithdrawView();
    withdrawView.start(stage);
});

        
viewAccountsButton.setOnAction(e -> {
    AccountsView accountsView = new AccountsView();
    accountsView.start(stage);
});

       
payInterestButton.setOnAction(e -> {
    PayInterestView payInterestView = new PayInterestView();
    payInterestView.start(stage);
});

        logoutButton.setOnAction(e -> controller.handleLogout(stage));
    }
}


