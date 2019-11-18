package GameEngine;

public class LevelManager {

    private static LevelManager levelManager = null;

    private int currentLevel;
    private int numOfHumans;
    private int numOfBaiters;
    private int numOfBombers;
    private int numOfLanders;


    private LevelManager(){
        currentLevel = 1;
        numOfHumans = 5;
        numOfLanders = 8;
        numOfBombers = 1;
    }

    public static LevelManager getInstance(){
        if (levelManager == null)
            levelManager = new LevelManager();
        return levelManager;
    }

    public void increaseAliens(){
        numOfHumans += (int)(currentLevel * 0.3);
        numOfBombers += (int)(currentLevel * 0.3);
        numOfLanders += (int)(currentLevel * 1.27);
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
    }
}
