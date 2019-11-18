package GameEngine;

import java.util.ArrayList;

import CollisionDetector.CollisionDetector;
import GameObjects.*;
import UserInterface.SceneGenerator.SceneGenerator;

public class GameEngine {

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
    }

    public static GameEngine getInstance(){
        if (gameEngine == null)
            gameEngine = new GameEngine();
        return gameEngine;
    }

    public void createUniverse(){
        sceneGenerator.createMap(motherShip, aliens, humans);
        gameEngine.refreshFrame();
    }

    public void refreshFrame(){
        if (isPaused) return;

        collisionDetector.checkAllCollisions(motherShip, aliens, humans, projectiles);

        if (!motherShip.isAlive())
            gameOver();

        ArrayList<Alien> tempAliens = new ArrayList<>();
        ArrayList<Projectile>  tempProjectiles = new ArrayList<>();
        ArrayList<Human> tempHumans = new ArrayList<>();

        //remove dead aliens
        for (Alien alien : aliens) {
            if (alien.isAlive())
                tempAliens.add(alien);
            score += alien.getScore();
        }

        //remove projectile
        for (Projectile projectile : projectiles)
            if (projectile.isAlive())
                tempProjectiles.add(projectile);

        //remove mutated humans and add mutants
        for (Human human : humans) {
            if (human.isAlive())
                tempHumans.add(human);
            else {
                tempAliens.add(new Mutant(human.getX(), human.getY()));
                score -= Mutant.SCORE;
            }
        }

        //repopulate original lists
        aliens = tempAliens;
        humans = tempHumans;
        projectiles = tempProjectiles;


        sceneGenerator.updateMap(motherShip, aliens, humans, projectiles, score);
    }

    private void gameOver(){

    }
}