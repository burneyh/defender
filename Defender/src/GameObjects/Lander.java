package GameObjects;

import java.util.Random;

public class Lander extends Alien {

    public static final int SCORE = 150;

    public Lander() {
        super();
        points = SCORE;
        loadImage("Icons/002-lander.png");
        getImageDimensions();
        this.move();
    }
    public int getScore() {return points;}

}
