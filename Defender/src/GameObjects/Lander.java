package GameObjects;

import java.util.Random;

public class Lander extends Alien {

    public static final int SCORE = 150;

//    public Lander() {
//        super(((int)(Math.random() * 500)), (int)(Math.random()) * 400);
//        points = 150;
//        loadImage("res/Icons/002-lander.png");
//        getImageDimensions();
//    }

    public Lander() {
        super();
        points = SCORE;
        loadImage("Icons/002-lander.png");
        getImageDimensions();
        this.move();
    }
    public int getScore() {return points;}

}
