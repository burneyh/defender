package UserInterface;

import Controllers.GameEngine;
import UserInterface.Menu.MainMenu;
import UserInterface.SceneGenerator.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApplication extends Application {
    private static Stage primaryStage;
    public final static int WIDTH = 700;
    public final static int HEIGHT = 500;
    public static GameEngine ge = GameEngine.getInstance();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        stage.setTitle("DEFENDER");
        setScene( MainMenu.getInstance());
        primaryStage.show();
    }

    public static void setScene(Scene scene) {
        primaryStage.setScene(scene);
    }
}
