package UserInterface.Menu;

import UserInterface.MyApplication;
import UserInterface.SceneGenerator.Map;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainMenu extends Scene {
    private static MainMenu menuInstance;
    private MainMenu(Pane root){
        super(root,MyApplication.WIDTH, MyApplication.HEIGHT  + Map.HEIGHT);
        // Initialize window width and height
        int windowWidth = MyApplication.WIDTH;
        int windowHeight = MyApplication.HEIGHT + Map.HEIGHT;

        // Window pane
        root.setPrefSize(windowWidth,windowHeight);

        // Add background to root
        try{
            Image image = new Image(getClass().getClassLoader().getResource("bg_image.jpg").toString(),
                    MyApplication.WIDTH, MyApplication.HEIGHT + Map.HEIGHT, false, false);

            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root.setBackground(background);
        }
        catch (NullPointerException e){
            System.out.println("Resource not found on " + "bg_image_jpg");
        }


        // Main Headline
        Text text = new Text("DEFENDER");
        text.setTextOrigin(VPos.TOP);
        text.setX(this.getWidth() / 2 - text.getLayoutBounds().getWidth() * 2.5);
        text.setY(this.getHeight()/6);
        text.setFont(Font.font("ARIAL", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);


        MenuSection menuSection = new MenuSection(false);
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
