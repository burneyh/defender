package UserInterface.Menu;

import UserInterface.MyApplication;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;

public class Help extends Scene {
    private static Help helpInstance;
    private Help(Pane root) {
        super(root);

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

        // Back Button
        try{
            // https://www.flaticon.com/authors/freepik
            FileInputStream input = new FileInputStream("res/back-button.png"); // TODO
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            Button back = new Button("Go Back");
            back.setOnAction(e -> {
                MyApplication.setScene(MainMenu.getInstance());
            });
            root.getChildren().add(back);
        }
        catch (Exception e){
            System.out.println("File Not Found");
        }

        // TODO
    }

    public static Help getInstance(){
        if (helpInstance == null) {
            Pane root = new Pane();
            helpInstance = new Help(root);
        }
        return helpInstance;
    }
}
