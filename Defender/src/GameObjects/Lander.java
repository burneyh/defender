package GameObjects;

import java.util.Random;

public class Lander extends Alien {

    public Lander() {
        super(((int)(Math.random() * 500)), (int)(Math.random()) * 400);
        points = 150;
        loadImage("res/Icons/002-lander.png");
        getImageDimensions();
    }

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
