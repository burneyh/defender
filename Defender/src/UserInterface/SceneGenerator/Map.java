package UserInterface.SceneGenerator;

import GameObjects.Alien;
import GameObjects.Human;
import GameObjects.MotherShip;
import GameObjects.Projectile;
import UserInterface.MyApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class Map extends Pane {
    static private Map map = null;

    public static final int WIDTH = MyApplication.WIDTH/2;
    public static final int HEIGHT = MyApplication.HEIGHT/4;
    //private Canvas canvas;
    private Image bgImage;
    private GraphicsContext graphics;

    synchronized static public Map getInstance() {
        if (map == null) {
            Pane root = new Pane();
            map = new Map(root);
        }
        return map;
    }

    private Map (Pane root) {
        super(root);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        graphics = canvas.getGraphicsContext2D();
        graphics.setFont(Font.font("Times New Roman", FontWeight.BOLD, 28));
        graphics.setFill(Color.WHITE);

        bgImage = new Image("bg_image.jpg");

        root.getChildren().add(canvas);
    }

    public void createMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans) {
        updateMap(motherShip, aliens, humans, null);
    }

    public void updateMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans,
                          ArrayList<Projectile> projectiles) {
        graphics.drawImage(bgImage, 0, 0, WIDTH, HEIGHT);

        if(motherShip.getDirection() == MotherShip.moveDirection.RIGHT)
            graphics.drawImage(motherShip.getImage(), (motherShip.getX() * WIDTH) / MyApplication.WIDTH - 15, (motherShip.getY() * HEIGHT) / MyApplication.HEIGHT - 15, WIDTH /20, HEIGHT /17);
        else
            graphics.drawImage(motherShip.getImage(), (motherShip.getX() * WIDTH) / MyApplication.WIDTH  + 15, (motherShip.getY() * HEIGHT) / MyApplication.HEIGHT  + 15, -WIDTH /20, -HEIGHT /17);

        for(Alien alien : aliens)
            graphics.drawImage(alien.getImage(), (alien.getX()  * WIDTH) / MyApplication.WIDTH - 15, (alien.getY()* HEIGHT) / MyApplication.HEIGHT - 15, WIDTH /20, HEIGHT /17);

        for(Human human : humans)
            graphics.drawImage(human.getImage(), (human.getX() * WIDTH) / MyApplication.WIDTH - 15, HEIGHT - 15, WIDTH /20, HEIGHT /17);

        if(projectiles != null)
            for(Projectile projectile : projectiles) {
                if(projectile.getDirection() == Projectile.moveDirection.RIGHT)
                    graphics.drawImage(projectile.getImage(), (projectile.getX() * WIDTH) / MyApplication.WIDTH - 5, (projectile.getY()* HEIGHT) / MyApplication.HEIGHT - 5, WIDTH /40, HEIGHT /50);
                else
                    graphics.drawImage(projectile.getImage(), (projectile.getX()* WIDTH) / MyApplication.WIDTH + 5, (projectile.getY()* HEIGHT) / MyApplication.HEIGHT + 5, -WIDTH /40, -HEIGHT /50);
            }
    }
}
