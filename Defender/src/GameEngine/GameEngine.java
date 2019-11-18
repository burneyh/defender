package GameEngine;

import java.util.ArrayList;
import java.util.Random;

import CollisionDetector.CollisionDetector;
import GameObjects.*;
import UserInterface.Menu.GameOver;
import UserInterface.Menu.PauseMenu;
import UserInterface.MyApplication;
import UserInterface.SceneGenerator.SceneGenerator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class GameEngine implements EventHandler<KeyEvent> {

    private static GameEngine gameEngine = null;

    private ArrayList<Alien> aliens;
    private ArrayList<Projectile>  projectiles;
    private ArrayList<Human> humans;
    private MotherShip motherShip;
    private LevelManager levelManager;
    private CollisionDetector collisionDetector;
    private SceneGenerator sceneGenerator;
    private boolean isPaused;
    private int score = 0;

    private GameEngine(){
        aliens = new ArrayList<>();
        projectiles = new ArrayList<>();
        humans = new ArrayList<>();

        //instantiate singletons
        motherShip = MotherShip.getInstance();
        levelManager = LevelManager.getInstance();
        collisionDetector = CollisionDetector.getInstance();
        sceneGenerator = SceneGenerator.getInstance();
        isPaused = false;

        for (int i = 0; i < levelManager.getNumOfLanders(); i++) {
            aliens.add(new Lander());
        }

        for (int i = 0; i < levelManager.getNumOfBaiters(); i++) {
            aliens.add(new Baiter());
        }

        for (int i = 0; i < levelManager.getNumOfBombers(); i++) {
            aliens.add(new Bomber());
        }

        for (int i = 0; i < levelManager.getNumOfHumans(); i++) {
            humans.add(new Human());
        }

        sceneGenerator.setOnKeyPressed(this);

    }

    public static GameEngine getInstance(){
        if (gameEngine == null)
            gameEngine = new GameEngine();
        return gameEngine;
    }

    public void createUniverse(){
        sceneGenerator.createMap(motherShip, aliens, humans);
        gameEngine.refresh();
    }

    public void refresh(){
        //start timer
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(16),
                e -> refreshFrame()
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void refreshFrame(){
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(60000),
                e -> nextLevel()
        ));
        timeline.play();

        if (isPaused) {
            MyApplication.setScene(PauseMenu.getInstance());
        };

        collisionDetector.checkAllCollisions(motherShip, aliens, humans, projectiles);

        if (!motherShip.isAlive()) {
            gameOver();
            return;
        }

        ArrayList<Alien> tempAliens = new ArrayList<>();
        ArrayList<Projectile>  tempProjectiles = new ArrayList<>();
        ArrayList<Human> tempHumans = new ArrayList<>();

        //remove dead aliens
        for (Alien alien : aliens) {
            if (alien.isAlive()) {
                tempAliens.add(alien);
                alien.move();
            }
            else {
                score += alien.getScore();
            }
        }

        Random rand = new Random();
        if(tempAliens.size() > 0) {
            int i = rand.nextInt(tempAliens.size());
            Projectile projectile2 = tempAliens.get(i).fire();
            if (projectile2 != null)
                tempProjectiles.add(projectile2);
        }

        //remove projectile
        for (Projectile projectile : projectiles)
            if (projectile.isAlive()) {
                tempProjectiles.add(projectile);
                projectile.move(projectile.getDirection());
            }

        //remove mutated humans and add mutants
        for (Human human : humans) {
            if (human.isAlive()) {
                tempHumans.add(human);

            }
            else {
                tempAliens.add(new Mutant(human.getX(), human.getY()));
                score -= Lander.SCORE;
            }
        }

        //repopulate original lists
        aliens = tempAliens;
        humans = tempHumans;
        projectiles = tempProjectiles;


        sceneGenerator.updateMap(motherShip, aliens, humans, projectiles, score);
    }

    private void nextLevel() {
        levelManager.incrementLevel();
        levelManager.increaseAliens();

        ArrayList<Alien> tempAliens = new ArrayList<>();
        ArrayList<Projectile>  tempProjectiles = new ArrayList<>();
        ArrayList<Human> tempHumans = new ArrayList<>();

        for (int i = 0; i < levelManager.getNumOfLanders(); i++) {
            aliens.add(new Lander());
        }

        for (int i = 0; i < levelManager.getNumOfBaiters(); i++) {
            aliens.add(new Baiter());
        }

        for (int i = 0; i < levelManager.getNumOfBombers(); i++) {
            aliens.add(new Bomber());
        }

        for (int i = 0; i < levelManager.getNumOfHumans(); i++) {
            humans.add(new Human());
        }

        aliens = tempAliens;
        humans = tempHumans;
        projectiles = tempProjectiles;

    }

    private void gameOver(){

        MyApplication.setScene(GameOver.getInstance());
    }

    @Override
    public void handle(KeyEvent event) {
        switch(event.getCode()){
            case SPACE:
                projectiles.add(motherShip.fire());
                break;
            case UP:
                motherShip.move(MotherShip.moveDirection.UP);
                break;
            case DOWN:
                motherShip.move(MotherShip.moveDirection.DOWN);
                break;
            case LEFT:
                motherShip.move(MotherShip.moveDirection.LEFT);
                break;
            case RIGHT:
                motherShip.move(MotherShip.moveDirection.RIGHT);
                break;
            case ESCAPE:
                MyApplication.setScene(PauseMenu.getInstance());
                break;
        }
    }
}