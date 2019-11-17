package GameObjects;

public class Bomber extends Alien{
    public Bomber(int x, int y) {
        super(x, y);
        points = 100;
        loadImage("res/Icons/005-airplane.png");
        getImageDimensions();
    }
    public int getScore() {return points;}
    public void move() {

    }
}
