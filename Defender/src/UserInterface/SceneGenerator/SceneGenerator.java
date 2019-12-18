package UserInterface.SceneGenerator;

import GameObjects.*;
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
    private GraphicsContext graphics;
    private GraphicsContext graphics2;

    synchronized static public SceneGenerator getInstance() {
        if (sceneGenerator == null) {
                BorderPane root = new BorderPane();
                sceneGenerator = new SceneGenerator( root);
        }
        return sceneGenerator;
    }

    private SceneGenerator (BorderPane root) {
        super(root);
        width = MyApplication.WIDTH;
        height = MyApplication.HEIGHT;

        Canvas canvas = new Canvas(width, height);
        graphics = canvas.getGraphicsContext2D();

        Canvas canvas2 = new Canvas(width/2, Map.HEIGHT);
        graphics2 = canvas2.getGraphicsContext2D();
        graphics2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        graphics2.setFill(Color.WHITE);

        bgImage = new Image("bg_image.jpg");
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #000080;");
        hbox.getChildren().addAll(Map.getInstance(), canvas2);

        root.setTop(hbox);
        root.setCenter(canvas);
    }

    public void createMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans) {
        updateMap(motherShip, aliens, humans, null, 0, 1, 100);
    }

    public void updateMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans,
                          ArrayList<Projectile> projectiles, int score, int level, int health) {
        graphics.drawImage(bgImage, 0, 0, MyApplication.WIDTH, MyApplication.HEIGHT);
        graphics2.drawImage(bgImage, 0, 0,Map.WIDTH, Map.HEIGHT);;
        graphics2.fillText("Score: " + score, 10, 25);
        graphics2.fillText("Level: " + level, Map.WIDTH/2, 25);
        graphics2.fillText("Health: " + health, 10, 85);
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
}
