package GameObjects;

public class Bomber extends Alien{
    public Bomber() {
        super();
        points = 100;
        loadImage("Icons/bomber.png");
        getImageDimensions();
        this.move();
    }
    public int getScore() {return points;}

}
