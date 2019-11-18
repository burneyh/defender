package GameObjects;

public class Baiter extends Alien{

    public Baiter() {
        super();
        points = 50;
        loadImage("res/Icons/monster.png");
        getImageDimensions();
    }
    public int getScore() {return points;}

    public void move(){

    }
}
