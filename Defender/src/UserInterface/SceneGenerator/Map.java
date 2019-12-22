package UserInterface.SceneGenerator;

import GameObjects.*;
import UserInterface.MyApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

import static UserInterface.SceneGenerator.SceneGenerator.setHalves;

public class Map extends Pane {
    static private Map map = null;

    public static final int WIDTH = MyApplication.WIDTH / 2;
    public static final int HEIGHT = MyApplication.HEIGHT / 4;
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

//    public void createMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans) {
//        updateMap(motherShip, aliens, humans, null);
//    }

    public void updateMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans,
                          ArrayList<PowerUp> powerUps) {
        graphics.drawImage(bgImage, 0, 0, WIDTH, HEIGHT);

        // subtract and add at the x and y coordinates with 1/2 the width and the height of the picture
        // not an arbitrary number

        int appWidth = MyApplication.WIDTH;
        int appHeight = MyApplication.HEIGHT;

        int mapWidth = WIDTH / 3;
        int mapHeight = HEIGHT;

        final int H_WIDTH = 0;
        final int H_HEIGHT = 1;
        int[] halvesContainer = new int[2];

        setHalves( motherShip.getMapImage(), halvesContainer);

        graphics.drawImage(motherShip.getMapImage(),
            0.33 * WIDTH + adjust( motherShip.getX() + halvesContainer[H_WIDTH], appWidth, mapWidth),
            adjust( motherShip.getY() + halvesContainer[H_HEIGHT], appHeight, mapHeight),
            adjust( -2 * halvesContainer[H_WIDTH], appWidth, mapWidth),
            adjust(-2 * halvesContainer[H_HEIGHT], appHeight, mapHeight));


        for (Alien alien : aliens) {
            setHalves(alien.getMapImage(), halvesContainer);
            graphics.drawImage(alien.getMapImage(),
                    0.33 * WIDTH + adjust( alien.getX() - halvesContainer[H_WIDTH], appWidth, mapWidth),
                    adjust(alien.getY() - halvesContainer[H_HEIGHT], appHeight, mapHeight),
                    adjust(2 * halvesContainer[H_WIDTH], appWidth, mapWidth),
                    adjust(2 * halvesContainer[H_HEIGHT], appHeight, mapHeight));
        }

        if(humans.size() > 0)
            setHalves(humans.get(0).getMapImage(), halvesContainer);
        for (Human human : humans)
            graphics.drawImage(human.getMapImage(),
                    0.33 * WIDTH + adjust( human.getX() - halvesContainer[H_WIDTH], appWidth, mapWidth),
                    adjust(human.getY() - halvesContainer[H_HEIGHT], appHeight, mapHeight),
                    adjust(2 * halvesContainer[H_WIDTH], appWidth, mapWidth),
                    adjust(2 * halvesContainer[H_HEIGHT], appHeight, mapHeight));

        if (powerUps != null) {
            for (PowerUp powerUp : powerUps) {
                setHalves(powerUp.getMapImage(), halvesContainer);
                graphics.drawImage(powerUp.getMapImage(),
                        0.33 * WIDTH + adjust( powerUp.getX() - halvesContainer[H_WIDTH], appWidth, mapWidth),
                        adjust( powerUp.getY() - halvesContainer[H_HEIGHT], appHeight, mapHeight),
                        adjust(2 * halvesContainer[H_WIDTH], appWidth, mapWidth),
                        adjust(2 * halvesContainer[H_HEIGHT], appHeight, mapHeight));
            }
        }
        graphics.setStroke(Color.VIOLET);
        graphics.setLineWidth(2);
        graphics.setLineDashes(5);

        double l = 0.33 * WIDTH;
        double r = 0.66 * WIDTH;
        double u = 5;
        double d = HEIGHT - 5;
        int length = 5;

        graphics.strokeLine(l, u, r, u);
        graphics.strokeLine(l, d, r, d);

        graphics.strokeLine(l, u, l, u + length);
        graphics.strokeLine(r, u, r, u + length);

        graphics.strokeLine(l, d, l, d - length);
        graphics.strokeLine(r, d, r, d - length);
    }

    private double adjust(int initLength, int initDim, int newDim){
        return ((double)initLength / initDim) * newDim;
    }
}
