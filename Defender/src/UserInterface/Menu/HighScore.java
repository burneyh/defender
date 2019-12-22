package UserInterface.Menu;

import UserInterface.MyApplication;
import UserInterface.SceneGenerator.Map;
import javafx.geometry.Insets;
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

import java.io.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class HighScore extends Scene {
    private static HighScore highScoreInstance;
    private String username;
    private HighScore(Pane root, boolean isPause) {
        super(root);
        username = "";
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
        Text text = new Text("High Scores");
        text.setTextOrigin(VPos.TOP);
        text.setY(10);
        text.setX(windowHeight/2 - 100);
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

        HBox hbox = new HBox();
        VBox vbox_name = new VBox();
        VBox vbox_score = new VBox();
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File("Defender/res/TextFiles/highScores.txt")));
            ArrayList<Integer> scores = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            String st;
            while ((st = br.readLine()) != null) {
                if (st.replace(" ","").length() > 0) {
                    Integer i = Integer.parseInt(st.substring(st.lastIndexOf(" ") + 1));
                    scores.add(i);
                    String string = st.substring(0, st.indexOf(" "));
                    names.add(string.replace(" ", ""));
                }
            }
            //br.close();

            vbox_name.setPadding(new Insets(30));
            vbox_name.setSpacing(8);
            Text title = new Text("NAME");
            title.setFill(Color.WHITE);
            title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
            vbox_name.getChildren().add(title);
            for (int i = 0; i < names.size(); i++){
                Text temp = new Text(names.get(i));
                temp.setFill(Color.WHITE);
                temp.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                vbox_name.getChildren().add(temp);
            }

            vbox_score.setPadding(new Insets(30));
            vbox_score.setSpacing(8);
            Text title2 = new Text("SCORE");
            title2.setFont(Font.font("Arial", FontWeight.BOLD, 30));
            title2.setFill(Color.WHITE);
            vbox_score.getChildren().add(title2);
            for (int i = 0; i < scores.size(); i++){
                Text temp = new Text(Integer.toString(scores.get(i)));
                temp.setFill(Color.WHITE);
                temp.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                vbox_score.getChildren().add(temp);
            }

            hbox.getChildren().addAll(vbox_name, vbox_score);
            hbox.setLayoutX(MyApplication.WIDTH/2-160);
            hbox.setLayoutY(MyApplication.HEIGHT/2 - 200);
        }
        catch (Exception e){
            System.out.println("File Not Found in HighScore");
        }

        root.getChildren().add(hbox);
    }

    public static HighScore getInstance(boolean isPause){
        if (highScoreInstance == null) {
            Pane root = new Pane();
            highScoreInstance = new HighScore(root, isPause);
        }
        return highScoreInstance;
    }

    public static void setInstance(){
        highScoreInstance = null;
    }

    public void setUsername(String userName){
        this.username = userName;
    }

    public String getUsername(){
        return username;
    }
}
