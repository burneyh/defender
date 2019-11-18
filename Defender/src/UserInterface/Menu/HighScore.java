package UserInterface.Menu;

import UserInterface.MyApplication;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class HighScore extends Scene {
    private static HighScore highScoreInstance;
    private HighScore(Pane root) {
        super(root);

        // Initialize window width and height
        int windowWidth = MyApplication.WIDTH;
        int windowHeight = MyApplication.HEIGHT;

        // Window pane
        root.setPrefSize(windowWidth,windowHeight);

//        // Main Menu Background
//        Rectangle bg = new Rectangle(windowWidth,windowHeight);
//        Color gameCol = Color.rgb(38,6,71);
//        bg.setFill(gameCol);
//
//        // Add background to root
//        root.getChildren().add(bg);

        try{
            FileInputStream input = new FileInputStream("CS319-1C-DE/Defender/res/bg_image.jpg");
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
        Text text = new Text("High Scores");
        text.setTextOrigin(VPos.TOP);
        text.setY(10);
        text.setX(150);
        text.setY(this.getHeight()/6);
        text.setFont(Font.font("ARIAL", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);

        // Back Button
        try{
            FileInputStream input = new FileInputStream("CS319-1C-DE/Defender/res/back-button.png");
            Image image = new Image(input, 20,20, false,false);
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

        Text ta = new Text();
        try{
            File input = new File("CS319-1C-DE/Defender/src/UserInterface/TextFiles/highScores.txt");
            FileReader fr = new FileReader(input);
            BufferedReader br = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();
            String st;
            sb.append("NAME ---------- SCORE");
            sb.append("\n");
            while ((st = br.readLine()) != null){
                sb.append(st);
                sb.append('\n');
            }
            ta.setTranslateX(150);
            ta.setY(100);
            ta.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            ta.setFill(Color.WHITE);
            ta.setText(sb.toString());

        }
        catch (Exception e){
            System.out.println("File Not Found");
        }

        root.getChildren().add(ta);
    }

    public static HighScore getInstance(){
        if (highScoreInstance == null) {
            Pane root = new Pane();
            highScoreInstance = new HighScore(root);
        }
        return highScoreInstance;
    }
}
