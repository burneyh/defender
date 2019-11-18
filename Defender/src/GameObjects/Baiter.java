package GameObjects;

public class Baiter extends Alien{

    public Baiter() {
        super();
        points = 50;
        loadImage("Icons/monster.png");
        getImageDimensions();
        this.move();
        setSpeed(3);

    }
    public int getScore() {return points;}


}
