package UserInterface.Menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize window width and height
        int windowWidth = 600;
        int windowHeight = 500;

        // Window pane
        Pane root = new Pane();
        root.setPrefSize(windowWidth,windowHeight);

        // Main Menu Background
        Rectangle bg = new Rectangle(windowWidth,windowHeight);
        bg.setFill(Color.NAVY);

        // Add background to root
        root.getChildren().add(bg);

        // Add root to scene and show
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public void show(String[] args){
        launch(args);
    }
}
