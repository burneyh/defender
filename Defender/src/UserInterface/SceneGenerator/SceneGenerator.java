package UserInterface.SceneGenerator;

import GameObjects.Alien;
import GameObjects.Human;
import GameObjects.MotherShip;
import GameObjects.Projectile;
import UserInterface.MyApplication;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class SceneGenerator extends Scene {
    static private SceneGenerator sceneGenerator = null;

    private int width;
    private int height;
    //private Canvas canvas;
    private Image bgImage;
    private GraphicsContext graphics;

    synchronized static public SceneGenerator getInstance() {
        if (sceneGenerator == null) {
                Group root = new Group();
                sceneGenerator = new SceneGenerator( root);
        }
        return sceneGenerator;
    }

    private SceneGenerator (Group root) {
        super(root);
        width = MyApplication.WIDTH;
        height = MyApplication.HEIGHT;

        Canvas canvas = new Canvas(width, height);
        graphics = canvas.getGraphicsContext2D();
        graphics.setFont(Font.font("Times New Roman", FontWeight.BOLD, 28));
        graphics.setFill(Color.WHITE);

        bgImage = new Image("UserInterface/Icons/Space.jpg");

        root.getChildren().add(canvas);

        createMap(null, null, null);
    }

    public void createMap(MotherShip motherShip, Alien[] aliens, Human[] humans) {
        updateMap(motherShip, aliens, humans, null, 0);
    }

    public void updateMap(MotherShip motherShip, Alien[] aliens, Human[] humans, Projectile[] projectiles, int score) {
        graphics.drawImage(bgImage, 0, 0, width, height);
        graphics.fillText("Score: " + score, 15, 35);
        gameOver();
    }

    public void gameOver(){
        double midWidth = width / 2.0;
        double midHeight = height / 2.0;

        graphics.setFill(Color.BLACK);
        graphics.setFillRule(FillRule.EVEN_ODD);
        graphics.fillRect(midWidth - 100, midHeight - 20, 200, 40);
        graphics.setFill(Color.WHITE);
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);
        graphics.fillText("Game Over", midWidth, midHeight);
    }
}
