package Controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import GameObjects.*;
import UserInterface.Menu.GameOver;
import UserInterface.Menu.HighScore;
import UserInterface.Menu.PauseMenu;
import UserInterface.Menu.Username;
import UserInterface.MyApplication;
import UserInterface.SceneGenerator.Map;
import UserInterface.SceneGenerator.SceneGenerator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class GameEngine implements EventHandler<KeyEvent> {

    private static GameEngine gameEngine = null;

    private Timeline sceneRefresher = null;

    private ArrayList<Alien> aliens;
    private ArrayList<Projectile>  projectiles;
    private ArrayList<Human> humans;
    private MotherShip motherShip;
    private LevelManager levelManager;
    private CollisionDetector collisionDetector;
    private SceneGenerator sceneGenerator;
    private boolean levelTransitionState;
    private int score = 0;

    private GameEngine(){
        levelTransitionState = false;

        //instantiate singletons
        motherShip = MotherShip.getInstance();
        levelManager = LevelManager.getInstance();
        collisionDetector = CollisionDetector.getInstance();
        sceneGenerator = SceneGenerator.getInstance();

        sceneGenerator.setOnKeyPressed(this);
    }

    public static GameEngine getInstance(){
        if (gameEngine == null)
            gameEngine = new GameEngine();
        return gameEngine;
    }

    public void createUniverse(){
        if(levelManager.getLevel() > 0) {
            gameOver( false);
        }

        // the gameEngine part is very important do not remove
        // serious oop problem
        // for extra inquiries contact Gledis :)
        gameEngine.nextLevel();
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

        sceneRefresher.play();
    }

    private synchronized void refreshFrame(){
        collisionDetector.checkAllCollisions(motherShip, aliens, humans, projectiles);

        if (!motherShip.isAlive()) {
            sceneRefresher.stop();
            gameOver( true);
            return;
        }

        ArrayList<Alien> tempAliens = new ArrayList<>();
        ArrayList<Projectile>  tempProjectiles = new ArrayList<>();
        ArrayList<Human> tempHumans = new ArrayList<>();

        // calculate bias
        int midScreen = MyApplication.WIDTH / 2;
        int perifScreen = (int)(0.2 * MyApplication.WIDTH);
        double biasPerc;
        if(motherShip.getDirection() == MotherShip.moveDirection.RIGHT &&
            motherShip.getX() >= midScreen)
            biasPerc = -(double)(motherShip.getX() - midScreen) / (midScreen - perifScreen);
        else if(motherShip.getDirection() == MotherShip.moveDirection.LEFT &&
            motherShip.getX() < midScreen)
            biasPerc = (double)(midScreen - motherShip.getX()) / (midScreen - perifScreen);
        else
            biasPerc = 0;

        int bias = (int)(motherShip.getSpeed() * biasPerc);

        motherShip.applyBias( bias);

        //remove dead aliens and apply bias
        for (Alien alien : aliens) {
            if (alien.isAlive()) {
                alien.applyBias( bias);
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

        //remove projectile and apply bias
        for (Projectile projectile : projectiles)
            if (projectile.isAlive()) {
                projectile.applyBias( bias);
                tempProjectiles.add(projectile);
                if (!(projectile instanceof Mine))
                    projectile.move(projectile.getDirection());
            }

        //remove mutated humans and add mutants
        for (Human human : humans) {
            if (human.isAlive()) {
                human.applyBias( bias);
                tempHumans.add(human);

            }
            else {
                tempAliens.add(new Mutant(human.getX(), human.getY()));
                score -= Lander.SCORE;
            }
        }

        if(score >= levelManager.getLevelTarget()){
            sceneRefresher.stop();
            nextLevel();
            return;
        }

        //repopulate original lists
        aliens = tempAliens;
        humans = tempHumans;
        projectiles = tempProjectiles;

        sceneGenerator.updateMap(motherShip, aliens, humans, projectiles, score, levelManager.getLevelTarget(),
                levelManager.getLevel(), motherShip.getHealth());
    }

    private void nextLevel() {
        motherShip.resetPos();

        score = 0;
        levelManager.incrementLevel();

        System.out.println("New level: " + levelManager.getLevel());

        levelTransitionState = true;
        sceneGenerator.showLevelTransition(levelManager.getLevel(), levelManager.getLevelTarget());

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

    // gameFinished is false when user presses play from the pause menu
    private void gameOver( boolean gameFinished){
        if(gameFinished)
            recordHighScore();

        MotherShip.renew();
        LevelManager.renew();
        setInstance();
        gameEngine = getInstance();

        if(gameFinished)
            MyApplication.setScene(GameOver.getInstance());
    }

    private void recordHighScore(){
        // high score
        try{
            InputStream inputStream = getClass().getResourceAsStream("/TextFiles/highScores.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            ArrayList<Integer> scores = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            String st;
            while ((st = br.readLine()) != null) {
                Integer i = Integer.parseInt(st.substring(st.lastIndexOf("-")+2));
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
            for(int i = 0; i < scores.size() && i <= 10; i++){
                String str = names.get(i) + " ---------- " + scores.get(i) + "\n";
                fr1.write(str);
            }
            fr1.flush();
            fr1.close();

            HighScore.setInstance();
        }
        catch (Exception e){
            System.out.println("File Not Found in GameEngine");
        }
    }

    public static void setInstance(){
        gameEngine = null;
    }


    @Override
    public void handle(KeyEvent event) {
        if(levelTransitionState){
            if(event.getCode() == KeyCode.ENTER) {
                levelTransitionState = false;
                sceneGenerator.showGame();
                gameEngine.refresh();
            }
            return;
        }

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
                sceneRefresher.pause();
                MyApplication.setScene(PauseMenu.getInstance());
                break;
        }
    }
}