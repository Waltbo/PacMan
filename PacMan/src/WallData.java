import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class WallData extends Application {

    @Override
    public void start(Stage primaryStage) {


        Loader load = new Loader("playerInfo.txt");
        Scene primaryScene = new Scene(load, 430, 75);


        primaryStage.setTitle("PacMan");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}