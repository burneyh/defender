package GameEngine;

import java.util.ArrayList;
import java.util.Calendar;

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

    private Timeline sceneRefresher = null;
    private Timeline levelRefresher = null;

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
        if(sceneRefresher == null) {
            sceneRefresher = new Timeline(new KeyFrame(
                    Duration.millis(16), e ->
                    refreshFrame()
            ));
            sceneRefresher.setCycleCount(Animation.INDEFINITE);
        }

        if(levelRefresher == null) {
            levelRefresher = new Timeline( new KeyFrame(
                    Duration.millis(60000), e -> {
                        System.out.println("HERE");
                        nextLevel();
            }
            ));
            levelRefresher.setCycleCount(Animation.INDEFINITE);
        }

        levelRefresher.play();
        sceneRefresher.play();
    }

    private synchronized void refreshFrame(){
        if (isPaused) {
            MyApplication.setScene(PauseMenu.getInstance());
            sceneRefresher.stop();
            levelRefresher.stop();
            return;
        };

        collisionDetector.checkAllCollisions(motherShip, aliens, humans, projectiles);

        if (!motherShip.isAlive()) {
            sceneRefresher.stop();
            levelRefresher.stop();
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

    private synchronized void nextLevel() {
        levelManager.incrementLevel();

        ArrayList<Alien> tempAliens = new ArrayList<>();
        ArrayList<Projectile>  tempProjectiles = new ArrayList<>();
        ArrayList<Human> tempHumans = new ArrayList<>();

        for (int i = 0; i < levelManager.getNumOfLanders(); i++) {
            tempAliens.add(new Lander());
        }
        System.out.println("Lander done");

        for (int i = 0; i < levelManager.getNumOfBaiters(); i++) {
            tempAliens.add(new Baiter());
        }

        System.out.println("Baiter done");
        for (int i = 0; i < levelManager.getNumOfBombers(); i++) {
            tempAliens.add(new Bomber());
        }

        System.out.println("Human done");
        for (int i = 0; i < levelManager.getNumOfHumans(); i++) {
            tempHumans.add(new Human());
        }

        aliens = tempAliens;
        humans = tempHumans;
        projectiles = tempProjectiles;

        System.out.println("Next Level Done");
    }

    private void gameOver(){
        // high score
        MotherShip.renew();
        LevelManager.renew();
        gameEngine = new GameEngine();      //renewing gameEngine as well
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