package UserInterface;

import UserInterface.SceneGenerator.SceneGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OurApplication extends Application {

    private Scene displayedScene;

    public static void main(String... args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Defenders HAPEG");

        setScene( SceneGenerator.getInstance());
        stage.setScene(displayedScene);
        stage.show();
    }

    public void setScene(Scene scene) {
        this.displayedScene = scene;
    }
}
