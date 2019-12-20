package UserInterface.SceneGenerator;

import Controllers.GameEngine;
import GameObjects.*;
import UserInterface.Menu.MainMenu;
import UserInterface.MyApplication;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class SceneGenerator extends Scene {
    static private SceneGenerator sceneGenerator = null;

    private int width;
    private int height;
    private Image bgImage;
    private Image  bg_black;
    private GraphicsContext graphics;
    private GraphicsContext graphics2;
    private Map map;

    private boolean transitionScreen = false;

    synchronized static public SceneGenerator getInstance() {
        if (sceneGenerator == null) {
                BorderPane root = new BorderPane();
                sceneGenerator = new SceneGenerator( root);
        }
        return sceneGenerator;
    }

    private SceneGenerator (BorderPane root) {
        super(root);
        map = Map.getInstance();

        width = MyApplication.WIDTH;
        height = MyApplication.HEIGHT;

        Canvas canvas = new Canvas(width, height);
        graphics = canvas.getGraphicsContext2D();

        Canvas canvas2 = new Canvas(width/2, Map.HEIGHT);
        graphics2 = canvas2.getGraphicsContext2D();
        graphics2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        graphics2.setFill(Color.WHITE);

        bgImage = new Image("bg_image.jpg");
        bg_black = new Image("black_bg.jpg");
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #ffffff;");
        hbox.getChildren().addAll(Map.getInstance(), canvas2);

        root.setTop(hbox);
        root.setCenter(canvas);
    }

//    public void createMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans) {
//        updateMap(motherShip, aliens, humans, null, 0, 1, 100);
//    }

    public void showGame(){
        MyApplication.setScene(this);
    }

    public void updateMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans,
                          ArrayList<Projectile> projectiles, int score, int totalScore, int levelTarget, int level, int health) {
        map.updateMap(motherShip, aliens, humans, projectiles);

        graphics.drawImage(bgImage, 0, 0, MyApplication.WIDTH, MyApplication.HEIGHT);
        graphics2.drawImage(bg_black, 0, 0,Map.WIDTH, Map.HEIGHT);
        graphics2.fillText("Score: " + score + " / " + levelTarget, 10, 25);
        graphics2.fillText("Health: " + health,  Map.WIDTH/2, 25);
        graphics2.fillText("Level: " + level,Map.WIDTH/2, 85);
        graphics2.fillText("Total Score: " + totalScore,10, 85);

        if(motherShip.getDirection() == MotherShip.moveDirection.RIGHT)
            graphics.drawImage(motherShip.getImage(), motherShip.getX() - 15, motherShip.getY() - 15);
        else
            graphics.drawImage(motherShip.getImage(), motherShip.getX() + 15, motherShip.getY() + 15, -30, -30);

        for(Alien alien : aliens)
            graphics.drawImage(alien.getImage(), alien.getX() - 15, alien.getY() - 15);

        for(Human human : humans)
            graphics.drawImage(human.getImage(), human.getX() - 15, human.getY() - 15);

        if(projectiles != null)
            for(Projectile projectile : projectiles) {
                if(projectile.getDirection() == Projectile.moveDirection.RIGHT)
                    graphics.drawImage(projectile.getImage(), projectile.getX() - 5, projectile.getY() - 5);
                else
                    graphics.drawImage(projectile.getImage(), projectile.getX() + 5, projectile.getY() + 5, -10, -10);
            }
    }

    public void showLevelTransition(int level, int levelTarget) {
        class LevelTransition extends Scene{
            private int width = MyApplication.WIDTH;
            private int height = MyApplication.HEIGHT + Map.HEIGHT;
            private String message = "Level " + level + "\n" +
                    "Target Score: " + levelTarget + "\n" +
                    "Press Enter to continue...";

            private LevelTransition(Group root){
                super(root);

                GraphicsContext graphics;

                Canvas canvas = new Canvas(width, height);
                graphics = canvas.getGraphicsContext2D();

                graphics.setFill(Color.BLACK);
                graphics.fillRect(0, 0, width, height);

                graphics.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
                graphics.setFill(Color.WHITE);
                graphics.setTextAlign(TextAlignment.CENTER);
                graphics.setTextBaseline(VPos.CENTER);
                graphics.fillText(message, width/2.0, height/2.0);

                root.getChildren().add(canvas);
            }
        }

        LevelTransition transition = new LevelTransition(new Group());
        transition.setOnKeyPressed(GameEngine.getInstance());
        MyApplication.setScene(transition);
    }
}

