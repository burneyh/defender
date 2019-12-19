package Controllers;

import GameObjects.*;
import UserInterface.Menu.GameOver;
import UserInterface.Menu.HighScore;
import UserInterface.Menu.PauseMenu;
import UserInterface.MyApplication;
import UserInterface.SceneGenerator.SceneGenerator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class GameEngine implements EventHandler<KeyEvent> {

    private static GameEngine gameEngine = null;

    private Timeline sceneRefresher = null;
    private Timeline levelRefresher = null;

    private ArrayList<PowerUp> powerUps;
    private ArrayList<Alien> aliens;
    private ArrayList<Projectile>  projectiles;
    private ArrayList<Human> humans;
    private MotherShip motherShip;
    private LevelManager levelManager;
    private CollisionDetector collisionDetector;
    private SceneGenerator sceneGenerator;
    private boolean isPaused;
    private int score = 0;

    //movement and fire
    private boolean isFire = false;
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isLeft = false;
    private boolean isRight = false;

    private GameEngine(){
        powerUps = new ArrayList<>();
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

        sceneGenerator.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e){
                switch(e.getCode()){
                    case SPACE:
                        isFire = false;
                        break;
                    case UP:
                        isUp = false;
                        break;
                    case DOWN:
                        isDown = false;
                        break;
                    case LEFT:
                        isLeft = false;
                        break;
                    case RIGHT:
                        isRight =false;
                        break;
                    case ESCAPE:
                        sceneRefresher.pause();
                        levelRefresher.pause();
                        MyApplication.setScene(PauseMenu.getInstance());
                        break;
                }
            }

        });

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
        //counter to limit number of ship projectiles
        int count[] =new int [1];
        count[0] = 10;
        //start timer
        if(sceneRefresher == null) {
            sceneRefresher = new Timeline(new KeyFrame(
                    Duration.millis(16), e ->
                    refreshFrame(count)
            ));
            sceneRefresher.setCycleCount(Animation.INDEFINITE);
        }

        if(levelRefresher == null) {
            levelRefresher = new Timeline( new KeyFrame(
                    Duration.millis(10000), e -> {
                        System.out.println("HERE");
                        nextLevel();
            }
            ));
            levelRefresher.setCycleCount(Animation.INDEFINITE);
        }

        levelRefresher.play();
        sceneRefresher.play();
    }


    private void dropPowerUp() {
        Random random = new Random();
        //powerUps.clear();

        int option = random.nextInt(6);

        switch (option) {
            case 0:
                powerUps.add(new PowerUp(random.nextInt(600), 10, PowerUp.Type.REFILL_HEALTH));
                break;
            case 1:
                powerUps.add(new PowerUp(random.nextInt(600), 10, PowerUp.Type.SHIELD));
                break;
            case 2:
                powerUps.add(new PowerUp(random.nextInt(600), 10, PowerUp.Type.TRIPLE_SHOT));
                break;
            case 3:
                powerUps.add(new PowerUp(random.nextInt(600), 10, PowerUp.Type.EMPOWERED_SHOT));
                break;
            case 4:
                powerUps.add(new PowerUp(random.nextInt(600), 10, PowerUp.Type.EXPLOSIVE_SHOT));
                break;
            case 5:
                powerUps.add(new PowerUp(random.nextInt(600), 10, PowerUp.Type.FROST));
                break;
        }
    }

    private synchronized void refreshFrame(int[]count){
        if (isPaused) {
            MyApplication.setScene(PauseMenu.getInstance());
            sceneRefresher.stop();
            levelRefresher.stop();
            return;
        };

        motherShip.move(isUp, isDown, isLeft,isRight);
        //firing issue

        if(isFire && count[0] > 7){
            projectiles.addAll(motherShip.fire());
            count[0] = 0;
        }
        else
            count[0]++;

        collisionDetector.checkAllCollisions(motherShip, aliens, humans, projectiles, powerUps);

        if (!motherShip.isAlive()) {
            sceneRefresher.stop();
            levelRefresher.stop();
            gameOver();
            return;
        }

        if (motherShip.getPowerUp() != null) {
            switch (motherShip.getPowerUp().getType()) {
                case REFILL_HEALTH:
                    motherShip.refillHealth();
                    break;
                case SHIELD:
                    motherShip.setInvincible(true);
                    break;
                case TRIPLE_SHOT: //Needs fixing or changing
                    break;
                case EMPOWERED_SHOT:
                    break;
                case EXPLOSIVE_SHOT:
                    break;
                case FROST: //Implemented. Remove in production
                    break;
            }
        }

        ArrayList<Alien> tempAliens = new ArrayList<>();
        ArrayList<Projectile>  tempProjectiles = new ArrayList<>();
        ArrayList<Human> tempHumans = new ArrayList<>();
        ArrayList<PowerUp> tempPowerUps = new ArrayList<>();

        //remove dead power-ups and move live ones
        for (PowerUp powerUp : powerUps) {
            if (powerUp.isAlive()) {
                tempPowerUps.add(powerUp);
                powerUp.move();
            }
        }

        //remove dead aliens
        for (Alien alien : aliens) {
            if (alien.isAlive()) {
                tempAliens.add(alien);
                if ((motherShip.getPowerUp() == null) || (motherShip.getPowerUp().getType() != PowerUp.Type.FROST))
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
                if (!(projectile instanceof Mine))
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
        powerUps = tempPowerUps;


        sceneGenerator.updateMap(motherShip, aliens, humans, projectiles, score, levelManager.getLevel(), motherShip.getHealth(), powerUps);
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

        System.out.println("PowerUps");
        dropPowerUp();

        aliens = tempAliens;
        humans = tempHumans;
        projectiles = tempProjectiles;

        System.out.println("Next Level Done");
    }

    private void gameOver(){
        // high score
        try{
            InputStream inputStream = getClass().getResourceAsStream("/TextFiles/highScores.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            ArrayList<Integer> scores = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            String st;
            while ((st = br.readLine()) != null) {
                Integer i = Integer.parseInt(st.substring(17, st.length()));
                scores.add(i);
                String string = st.substring(0,st.indexOf("-"));
                names.add(string.replace(" ",""));
            }
            inputStream.close();
            br.close();

            if (scores.size() == 0){
                scores.add(score);
                names.add(HighScore.getInstance(false).getUsername());
            }
            if (scores.get(scores.size()-1) > score){
                scores.add(scores.size(), score);
                names.add(names.size(), HighScore.getInstance(false).getUsername());
            }
            for (int i = 0; i < scores.size() && i <= 10; i++){
                if (score > scores.get(i)){
                    scores.add(i, score);
                    names.add(i, HighScore.getInstance(false).getUsername());
                    break;
                }
            }

            FileWriter fr = new FileWriter("res/TextFiles/highScores.txt");
            for(int i = 0; i < scores.size() && i <= 10; i++){
                String str = names.get(i) + " ---------- " + scores.get(i) + "\n";
                fr.write(str);
            }
            fr.flush();
            fr.close();

            FileWriter fr1 = new FileWriter("out/production/Defender/TextFiles/highScores.txt");
            for(int i = 0; i < scores.size(); i++){
                String str = names.get(i) + " ---------- " + scores.get(i) + "\n";
                fr1.write(str);
            }
            fr1.flush();
            fr1.close();
        }
        catch (Exception e){
            System.out.println("File Not Found in GameEngine");
        }


        MotherShip.renew();
        LevelManager.renew();
        gameEngine = new GameEngine();      //renewing gameEngine as well

        MyApplication.setScene(GameOver.getInstance());
    }

    @Override
    public void handle(KeyEvent e){

        switch(e.getCode()){
            case SPACE:
                isFire = true;
                break;
            case UP:
                isUp = true;
                break;
            case DOWN:
                isDown = true;
                break;
            case LEFT:
                isLeft = true;
                break;
            case RIGHT:
                isRight =true;
                break;
            case ESCAPE:
                sceneRefresher.pause();
                levelRefresher.pause();
                MyApplication.setScene(PauseMenu.getInstance());
                break;
        }


    }
}