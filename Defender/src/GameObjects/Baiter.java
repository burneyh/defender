package GameObjects;

public class Baiter extends Alien{

    public static final int SCORE = 50;

    public Baiter() {
        super();
        points = SCORE;
        loadImage("Icons/monster.png");
        getImageDimensions();
        this.move();
        setSpeed(3);

    }
    public int getScore() {return points;}


}
