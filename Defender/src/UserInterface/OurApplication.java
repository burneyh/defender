package UserInterface;

import UserInterface.Menu.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OurApplication extends Application {
    private static Scene displayedScene;
    public final static int globalWidth = 500;
    public final static int globalHeight = 600;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("DEFENDER");
        displayedScene = MainMenu.getInstance();
        stage.setScene(displayedScene);
        stage.show();
    }

    public static void setScene(Scene scene) {
        OurApplication..setScene(scene);
    }
}
