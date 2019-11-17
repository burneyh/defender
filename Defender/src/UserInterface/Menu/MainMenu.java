package UserInterface.Menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        Color gameCol = Color.rgb(38,6,71);
        bg.setFill(gameCol);

        // Add background to root
        root.getChildren().add(bg);

        Text text = new Text("DEFENDER");
        text.setTranslateX(150);
        text.setTranslateY(125);
        text.setFont(Font.font("ARIAL", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);

        MenuSection mb = new MenuSection();
        root.getChildren().add(mb);

        // Add root to scene and show
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public void show(String[] args){
        launch(args);
    }
}
