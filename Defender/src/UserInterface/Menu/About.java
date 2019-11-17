package UserInterface.Menu;

import UserInterface.MyApplication;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    private About(Pane root) {
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
            // https://www.flaticon.com/authors/freepik
            FileInputStream input = new FileInputStream("res/back-button.png"); // TODO
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            Button back = new Button("", imageView);
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
            File input = new File("src/UserInterface/TextFiles/about.txt");
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
            ta.setFont(new Font("Arial", 16));
            ta.setFill(Color.WHITE);
            ta.setText(sb.toString());

        }
        catch (Exception e){
            System.out.println("File Not Found");
        }

        root.getChildren().add(ta);
    }

    public static About getInstance(){
        if (aboutInstance == null) {
            Pane root = new Pane();
            aboutInstance = new About(root);
        }
        return aboutInstance;
    }
}
