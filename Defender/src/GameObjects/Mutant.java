package GameObjects;

public class Mutant extends Alien {
    public static final int SCORE = 200;

    public Mutant(int x, int y) {
        super(x, y);
        points = SCORE;
        loadImage("Icons/mutantShip.png");
        getImageDimensions();
        this.move();
    }

    public int getScore() {return points;}

}
