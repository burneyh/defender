package GameObjects;

public class Mutant extends Alien {


    public Mutant(int x, int y) {

        super(x, y);
        points = 200;

//        loadImage(null);
//
//        getImageDimensions();
    }

    public int getScore() {return points;}

}
