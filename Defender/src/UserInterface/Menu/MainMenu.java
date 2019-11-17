package UserInterface.Menu;

import UserInterface.MyApplication;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainMenu extends Scene {
    private static MainMenu menuInstance;
    private MainMenu(Pane root){
        super(root,MyApplication.WIDTH, MyApplication.HEIGHT );
        // Initialize window width and height
        int windowWidth = MyApplication.WIDTH;
        int windowHeight = MyApplication.HEIGHT;

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
        text.setTextOrigin(VPos.TOP);
        text.setX(this.getWidth() / 2 - text.getLayoutBounds().getWidth() * 2.5);
        text.setY(this.getHeight()/6);
        text.setFont(Font.font("ARIAL", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);


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
