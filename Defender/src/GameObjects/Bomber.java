package GameObjects;

public class Bomber extends Alien{
    public Bomber() {
        super();
        points = 100;
        loadImage("res/Icons/005-airplane.png");
        getImageDimensions();
    }
    public int getScore() {return points;}
    public void move() {

    }
}
