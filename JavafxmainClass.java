package main;
import javafx.application.Application;
import javafx.stage.Stage;


public class JavafxmainClass extends Application {

    @Override
    public void start(Stage stage) {
        DashboardView dashboard = new DashboardView();
        dashboard.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

