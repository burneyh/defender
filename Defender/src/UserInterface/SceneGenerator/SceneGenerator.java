package UserInterface.SceneGenerator;

import Controllers.GameEngine;
import GameObjects.*;
import UserInterface.MyApplication;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


import java.util.ArrayList;

public class SceneGenerator extends Scene {
    static private SceneGenerator sceneGenerator = null;

    private int width;
    private int height;
    private Image bgImage;
    private Image bg_black;
    private GraphicsContext gameplayGraphics;
    private GraphicsContext gameStatsGraphics;
    private Map map;

    synchronized static public SceneGenerator getInstance() {
        if (sceneGenerator == null) {
            BorderPane root = new BorderPane();
            sceneGenerator = new SceneGenerator(root);
        }
        return sceneGenerator;
    }

    private SceneGenerator(BorderPane root) {
        super(root);
        map = Map.getInstance();

        width = MyApplication.WIDTH;
        height = MyApplication.HEIGHT;

        Canvas canvas = new Canvas(width, height);
        gameplayGraphics = canvas.getGraphicsContext2D();

        Canvas canvas2 = new Canvas(width / 2, Map.HEIGHT);
        gameStatsGraphics = canvas2.getGraphicsContext2D();
        gameStatsGraphics.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        gameStatsGraphics.setFill(Color.WHITE);

        bgImage = new Image("bg_image.jpg");
        bg_black = new Image("black_bg.jpg");
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #ffffff;");
        hbox.getChildren().addAll(Map.getInstance(), canvas2);

        root.setTop(hbox);
        root.setCenter(canvas);
    }

    public void showGame() {
        MyApplication.setScene(this);
    }

    public void updateMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans,
                          ArrayList<Projectile> projectiles, int score, int totalScore, int levelTarget, int level, int health, ArrayList<PowerUp> powerUps, ArrayList<Explosion> explosions) {
        map.updateMap(motherShip, aliens, humans, powerUps);

        gameplayGraphics.drawImage(bgImage, 0, 0, MyApplication.WIDTH, MyApplication.HEIGHT);
        gameStatsGraphics.drawImage(bg_black, 0, 0, Map.WIDTH, Map.HEIGHT);
        gameStatsGraphics.fillText("Score: " + score + " / " + levelTarget, 10, 25);
        gameStatsGraphics.fillText("Health: " + health, Map.WIDTH / 2, 25);
        gameStatsGraphics.fillText("Level: " + level, Map.WIDTH / 2, 85);
        gameStatsGraphics.fillText("Total Score: " + totalScore, 10, 85);

        final int H_WIDTH = 0;
        final int H_HEIGHT = 1;
        int[] halvesContainer = new int[2];

        if(motherShip.getInvincible()) {
            setHalves(motherShip.getShield(), halvesContainer);
            gameplayGraphics.drawImage(motherShip.getShield(), motherShip.getX() - halvesContainer[H_WIDTH],
                    motherShip.getY() - halvesContainer[H_HEIGHT]);
        }


        setHalves( motherShip.getImage(), halvesContainer);
        if (motherShip.getDirection() == MotherShip.moveDirection.RIGHT)
            gameplayGraphics.drawImage(motherShip.getImage(), motherShip.getX() - halvesContainer[H_WIDTH],
                    motherShip.getY() - halvesContainer[H_HEIGHT]);
        else
            gameplayGraphics.drawImage(motherShip.getImage(), motherShip.getX() + halvesContainer[H_WIDTH]
                    , motherShip.getY() + halvesContainer[H_HEIGHT],
                    -2 * halvesContainer[H_WIDTH], -2 * halvesContainer[H_HEIGHT]);


        for (Alien alien : aliens) {
            setHalves(alien.getImage(), halvesContainer);
            gameplayGraphics.drawImage(alien.getImage(), alien.getX() - halvesContainer[H_WIDTH],
                    alien.getY() - halvesContainer[H_HEIGHT]);
        }


        if(humans.size() > 0)
            setHalves(humans.get(0).getImage(), halvesContainer);
        for (Human human : humans)
            gameplayGraphics.drawImage(human.getImage(), human.getX() - halvesContainer[H_WIDTH],
                    human.getY() - halvesContainer[H_HEIGHT]);

        if (powerUps != null) {
            for (PowerUp powerUp : powerUps) {
                setHalves(powerUp.getImage(), halvesContainer);
                gameplayGraphics.drawImage(powerUp.getImage(), powerUp.getX() - halvesContainer[H_WIDTH],
                        powerUp.getY() - halvesContainer[H_HEIGHT]);
            }
        }

        if (projectiles != null)
            for (Projectile projectile : projectiles) {
                setHalves(projectile.getImage(), halvesContainer);

                if (projectile.getDirection() == Projectile.moveDirection.RIGHT || projectile instanceof Mine)
                    gameplayGraphics.drawImage(projectile.getImage(), projectile.getX() - halvesContainer[H_WIDTH],
                            projectile.getY() - halvesContainer[H_HEIGHT]);
                else
                    gameplayGraphics.drawImage(projectile.getImage(), projectile.getX() + halvesContainer[H_WIDTH],
                            projectile.getY() - halvesContainer[H_HEIGHT],
                            -2 * halvesContainer[H_WIDTH], 2 * halvesContainer[H_HEIGHT]);
            }


        if (explosions != null) {
            if(explosions.size() > 0)
            setHalves(explosions.get(0).getImage(), halvesContainer);
            for (Explosion explosion : explosions) {
                gameplayGraphics.drawImage(explosion.getImage(), explosion.getX() - halvesContainer[H_WIDTH],
                        explosion.getY() - halvesContainer[H_HEIGHT]);
            }
        }
    }

    static void setHalves(Image image, int[] halves) {
        halves[0] = (int) image.getWidth() / 2;
        halves[1] = (int) image.getHeight() / 2;
    }

    public void showLevelTransition(int level, int levelTarget) {
        class LevelTransition extends Scene {
            private int width = MyApplication.WIDTH;
            private int height = MyApplication.HEIGHT + Map.HEIGHT;
            private String message = "Level " + level + "\n" +
                    "Target Score: " + levelTarget + "\n" +
                    "Press Enter to continue...";

            private LevelTransition(Group root) {
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
                graphics.fillText(message, width / 2.0, height / 2.0);

                root.getChildren().add(canvas);
            }
        }

        LevelTransition transition = new LevelTransition(new Group());
        transition.setOnKeyPressed(GameEngine.getInstance());
        MyApplication.setScene(transition);
    }
}

