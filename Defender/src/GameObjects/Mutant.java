package GameObjects;

public class Mutant extends Alien {


    public Mutant(int x, int y) {
        super(x, y);
        points = 200;
        loadImage("Icons/mutantShip.png");
        getImageDimensions();
        this.move();
    }

    public int getScore() {return points;}

}
