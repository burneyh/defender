package UserInterface.Menu;

import UserInterface.MyApplication;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;

public class PauseMenu extends Scene {
    private static PauseMenu pauseMenuInstance;
    private PauseMenu(Pane root){
        super(root,MyApplication.WIDTH, MyApplication.HEIGHT );

        try{
            FileInputStream input = new FileInputStream("res/bg_image.jpg");

            // create a image
            Image image = new Image(input, MyApplication.WIDTH, MyApplication.HEIGHT, false, false);

            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            root.setBackground(background);
        }
        catch(Exception e){

        }


        // Main Headline
        Text text = new Text("DEFENDER");
        text.setTextOrigin(VPos.TOP);
        text.setX(this.getWidth() / 2 - text.getLayoutBounds().getWidth() * 2.5);
        text.setY(this.getHeight()/6);
        text.setFont(Font.font("ARIAL", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);


        MenuSection menuSection = new MenuSection(true);
        root.getChildren().add(menuSection);
    }

    public static PauseMenu getInstance(){
        if (pauseMenuInstance == null) {
            Pane root = new Pane();
            pauseMenuInstance = new PauseMenu(root);
        }
        return pauseMenuInstance;
    }

}
