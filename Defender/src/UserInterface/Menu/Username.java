package UserInterface.Menu;

import Controllers.GameEngine;
import UserInterface.MyApplication;
import UserInterface.SceneGenerator.Map;
import UserInterface.SceneGenerator.SceneGenerator;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.*;

public class Username extends Scene {
    private static Username UsernameInstance;
    private Username(Pane root, boolean isPause) {
        super(root);
        VBox menu1 = new VBox(10);
        menu1.setTranslateX(210);
        menu1.setTranslateY(150);

        // Initialize window width and height
        int windowWidth = MyApplication.WIDTH;
        int windowHeight = MyApplication.HEIGHT + Map.HEIGHT;

        // Window pane
        root.setPrefSize(windowWidth,windowHeight);

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
        Text text = new Text("Select Username");
        text.setTextOrigin(VPos.TOP);
        text.setY(10);
        text.setX(windowHeight/2 - 155);
        text.setY(65);
        text.setFont(Font.font("ARIAL", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);

        // Back Button
        try{
            Image image = new Image(getClass().getClassLoader().getResource("back-button.png").toString(),
                    20,20, false,false);
            ImageView imageView = new ImageView(image);
            Button back = new Button("", imageView);
            back.setStyle("-fx-background-color:transparent");
            back.setTranslateY(10);
            back.setOnAction(e -> {
                if (isPause){
                    MyApplication.setScene(PauseMenu.getInstance());
                }
                else{
                    MyApplication.setScene(MainMenu.getInstance());
                }
            });
            root.getChildren().add(back);
        }
        catch (Exception e){
            System.out.println("File Not Found");
        }

        TextField usernameField = new TextField("Enter Username");
        // set alignment of text
        usernameField.setAlignment(Pos.CENTER);

        MenuButton aContinue = new MenuButton("Continue");
        aContinue.setOnMouseClicked(event -> {
            // Set username here
            MyApplication.setScene(SceneGenerator.getInstance());
            GameEngine.getInstance().createUniverse();
            HighScore.getInstance(false).setUsername(usernameField.getText());
        });
        menu1.setTranslateX(windowHeight/2-50);
        menu1.getChildren().addAll(usernameField, aContinue);
        root.getChildren().add(menu1);
    }

    public static Username getInstance(boolean isPause){
        if (UsernameInstance == null) {
            Pane root = new Pane();
            UsernameInstance = new Username(root, isPause);
        }
        return UsernameInstance;
    }
    public static void setInstance(){
        UsernameInstance = null;
    }

}