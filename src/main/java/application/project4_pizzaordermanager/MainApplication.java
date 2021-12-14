package application.project4_pizzaordermanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Driver to launch the Window for the main application.
 *
 * @author Yuchen Wei, Denghao Sun
 */

public class MainApplication extends Application {

    /**
     * Load the MainView and show the window.
     * 
     * @param stage that the main scene is going to show.
     * @throws IOException when the fxml file is not found.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("RU Pizzeria Ordering System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main class to launch the application.
     * 
     * @param args that are not supposed to exist.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
