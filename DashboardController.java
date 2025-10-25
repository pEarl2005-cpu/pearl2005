package main;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DashboardController {

    public void handleDeposit(Stage stage) {
        DepositView depositView = new DepositView();
        depositView.start(stage);
    }

    public void handleWithdraw(Stage stage) {
        WithdrawView withdrawView = new WithdrawView();
        withdrawView.start(stage);
    }

    public void handleViewAccounts(Stage stage) {
        AccountsView accountsView = new AccountsView();
        accountsView.start(stage);
    }

    public void handlePayInterest(Stage stage) {
        InterestView interestView = new InterestView();
        interestView.start(stage);
    }

    public void handleLogout(Stage stage) {
        LoginView loginView = new LoginView();
        loginView.start(stage);
    }
}
