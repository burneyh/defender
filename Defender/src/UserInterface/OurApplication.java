package UserInterface;

import UserInterface.Menu.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OurApplication extends Application {
    private Scene displayedScene;
    public static int globalWidth = 500;
    public static int globalHeight = 600;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("DEFENDER");
        setScene(MainMenu.getInstance());
        stage.setScene(displayedScene);
        stage.show();
    }

    public void setScene(Scene scene) {
        this.displayedScene = scene;
    }
}
