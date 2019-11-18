package UserInterface.Menu;

import UserInterface.MyApplication;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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

public class About extends Scene {
    private static About aboutInstance;
    private About(Pane root, boolean isPause) {
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
        Text text = new Text("About");
        text.setTextOrigin(VPos.TOP);
        text.setY(10);
        text.setX(200);
        text.setY(this.getHeight()/6);
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

        Text ta = new Text();
        try{
            File input = new File(getClass().getClassLoader().getResource("TextFiles/about.txt").getFile());
            FileReader fr = new FileReader(input);
            BufferedReader br = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();
            String st;
            while ((st = br.readLine()) != null){
                sb.append(st);
                sb.append('\n');
            }
            ta.setTranslateX(15);
            ta.setY(75);
            ta.setFont(Font.font("Times New Roman",16));
            ta.setFill(Color.WHITE);
            ta.setText(sb.toString());

        }
        catch (Exception e){
            System.out.println("File not found!");
        }

        root.getChildren().add(ta);
    }

    public static About getInstance(boolean isPause){
        if (aboutInstance == null) {
            Pane root = new Pane();
            aboutInstance = new About(root, isPause);
        }
        return aboutInstance;
    }
}
