package UserInterface.Menu;

import UserInterface.MyApplication;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;

public class GameOver extends Scene {
    private static GameOver gameOverInstance;

    private GameOver(Pane root){
        super(root, MyApplication.WIDTH, MyApplication.HEIGHT);
        try{
            Image image = new Image(getClass().getClassLoader().getResource("bg_image.jpg").toString(),
                    MyApplication.WIDTH, MyApplication.HEIGHT, false, false);

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
        Text text = new Text("GAME OVER!");
        text.setTextOrigin(VPos.CENTER);
        text.setX(50);
        text.setY(200);
        text.setFont(Font.font("ARIAL", FontWeight.BOLD, 80));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.06), evt -> text.setVisible(false)),
                new KeyFrame(Duration.seconds( 0.1), evt -> text.setVisible(true)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Back Button
        try{
            Image image = new Image(getClass().getClassLoader().getResource("back-button.png").toString(),
                    20,20, false,false);
            ImageView imageView = new ImageView(image);
            Button back = new Button("", imageView);
            back.setStyle("-fx-background-color:transparent");
            back.setTranslateY(10);
            back.setOnAction(e -> {
                MyApplication.setScene(MainMenu.getInstance());
            });
            root.getChildren().add(back);
        }
        catch (Exception e){
            System.out.println("File Not Found");
        }

    }

    public static GameOver getInstance(){
        if (gameOverInstance == null){
            Pane root = new Pane();
            gameOverInstance = new GameOver(root);
        }
        return gameOverInstance;
    }
}
