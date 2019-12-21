package Controllers;

import GameObjects.Baiter;
import GameObjects.Bomber;
import GameObjects.Lander;

public class LevelManager {

    private final static int HUMAN_NO_INIT = 5;
    private final static int LANDER_NO_INIT = 5;
    private final static int BOMBER_NO_INIT = 1;
    private final static int BAITER_NO_INIT = 1;

    private final static double PERCENTAGE_OF_TOTAL_SCORE = 0.75;

    private static LevelManager levelManager = null;

    private int currentLevel;
    private int numOfHumans;
    private int numOfBaiters;
    private int numOfBombers;
    private int numOfLanders;

    private int levelTarget;

    private LevelManager(){
        currentLevel = 0;
        numOfHumans = HUMAN_NO_INIT;
        numOfLanders = LANDER_NO_INIT;
        numOfBombers = BOMBER_NO_INIT;
        numOfBaiters = BAITER_NO_INIT;
    }

    public static void renew(){
        levelManager = null;
    }

    public static LevelManager getInstance(){
        if (levelManager == null)
            levelManager = new LevelManager();

        return levelManager;
    }

    private void increaseAliens(){
        numOfHumans = HUMAN_NO_INIT + (int)(currentLevel * 0.8);
        numOfBombers = BOMBER_NO_INIT + (int)(currentLevel * 1.3);
        numOfLanders = LANDER_NO_INIT + (int)(currentLevel * 0.8);
        numOfBaiters = BAITER_NO_INIT + (int)(currentLevel * 0.8);
    }

    public int getLevelTarget() {
        return levelTarget;
    }

    public int getNumOfHumans() {
        return numOfHumans;
    }

    public int getNumOfBaiters() {
        return numOfBaiters;
    }

    public int getNumOfBombers() {
        return numOfBombers;
    }

    public int getNumOfLanders() {
        return numOfLanders;
    }

    public void incrementLevel() {
        if (currentLevel++ != 0)
            increaseAliens();

        int totalPossibleScore = numOfBaiters * Baiter.SCORE +
                numOfBombers * Bomber.SCORE +
                numOfLanders * Lander.SCORE;

        levelTarget = (int)(PERCENTAGE_OF_TOTAL_SCORE * totalPossibleScore);

        // truncating the part not divisible by 50
        levelTarget -= levelTarget % 50;
    }

    public int getLevel(){
        return currentLevel;
    }
}
