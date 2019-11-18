package GameEngine;

public class LevelManager {

    private static LevelManager levelManager = null;

    private int currentLevel;
    private int numOfHumans;
    private int numOfBaiters;
    private int numOfBombers;
    private int numOfLanders;
    private int numOfMutants;


    private LevelManager(){
        currentLevel = 1;
        numOfHumans = 5;
        numOfLanders = 8;
        numOfBombers = 1;
        numOfMutants = 1;
    }

    public static LevelManager getInstance(){
        if (levelManager == null)
            levelManager = new LevelManager();
        return levelManager;
    }

    public void increaseAliens(){
        numOfHumans += (int)(currentLevel * 0.3);
        numOfLanders += (int)(currentLevel * 1.27);
        numOfBombers += (int)(currentLevel * 0.3);
        numOfMutants += (int)(currentLevel * 0.3);
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

    public int getNumOfMutants() {
        return numOfMutants;
    }

    public void incrementLevel() {
        currentLevel++;
    }
}
