package main;

import javafx.stage.Stage;

public class LoginController {

    // Simulated authentication (you can connect this to a model later)
    public boolean handleLogin(String username, String password) {
        return username.equals("pearl") && password.equals("1234");
    }

    // ✅ Open the Dashboard screen after successful login
    public void openDashboard(Stage dashboardStage) {
        DashboardView dashboard = new DashboardView();
        dashboard.start(dashboardStage);
    }
}

