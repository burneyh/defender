package UserInterface;

import UserInterface.Menu.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApplication extends Application {
    private static Stage primaryStage;
    public final static int WIDTH = 500;
    public final static int HEIGHT = 600;
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
