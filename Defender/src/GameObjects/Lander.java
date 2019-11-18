package GameObjects;

import java.util.Random;

public class Lander extends Alien {

//    public Lander() {
//        super(((int)(Math.random() * 500)), (int)(Math.random()) * 400);
//        points = 150;
//        loadImage("res/Icons/002-lander.png");
//        getImageDimensions();
//    }

    public static final int SCORE = 200;

    public Lander() {
        super();
        points = 150;
        loadImage("Icons/002-lander.png");
        getImageDimensions();
        this.move();
    }
    public int getScore() {return points;}

}
