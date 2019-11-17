package UserInterface.Menu;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static com.sun.glass.ui.Cursor.setVisible;

public class MainMenu extends Scene {
    private static MainMenu menuInstance;
    private MainMenu(Pane root){
        super(root);
        // Initialize window width and height
        int windowWidth = 600;
        int windowHeight = 500;

        // Window pane
        root.setPrefSize(windowWidth,windowHeight);

        // Main Menu Background
        Rectangle bg = new Rectangle(windowWidth,windowHeight);
        Color gameCol = Color.rgb(38,6,71);
        bg.setFill(gameCol);

        // Add background to root
        root.getChildren().add(bg);

        // Main Headline
        Text text = new Text("DEFENDER");
        text.setTranslateX(150);
        text.setTranslateY(125);
        text.setFont(Font.font("ARIAL", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);

        text.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            ft.play();
        });


        MenuSection menuSection = new MenuSection();
        root.getChildren().add(menuSection);
    }

    public static MainMenu getInstance(){
        if (menuInstance == null) {
            Pane root = new Pane();
            menuInstance = new MainMenu(root);
        }
        return menuInstance;
    }
}
