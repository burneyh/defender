package GameEngine;

public class LevelManager {

    private final static int HUMAN_NO_INIT = 5;
    private final static int LANDER_NO_INIT = 5;
    private final static int BOMBER_NO_INIT = 1;
    private final static int BAITER_NO_INIT = 1;

    private static LevelManager levelManager = null;

    private int currentLevel;
    private int numOfHumans;
    private int numOfBaiters;
    private int numOfBombers;
    private int numOfLanders;


    private LevelManager(){
        currentLevel = 1;
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
        currentLevel++;
        increaseAliens();
    }

    public int getLevel(){
        return currentLevel;
    }
}
