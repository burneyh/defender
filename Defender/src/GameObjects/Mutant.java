package GameObjects;

public class Mutant extends Alien {
    public static final int SCORE = 200;

    public Mutant(int x, int y) {

        super(x, y);
        points = 200;

        loadImage("/Defender/src/UserInterface/Icons/006-mutant.png");
        getImageDimensions();
    }

    public int getScore() {return points;}
    public void move() {

    }
}
