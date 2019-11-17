package GameObjects;

public class Lander extends Alien {
    public Lander(int x, int y) {
        super(x, y);
        points = 150;
        loadImage("res/Icons/002-lander.png");
        getImageDimensions();
    }
    public int getScore() {return points;}
    public void move() {

    }
}
