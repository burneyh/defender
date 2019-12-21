package GameObjects;

public class Bomber extends Alien{
    public static final int SCORE = 100;

    public Bomber() {
        super();
        points = 100;
        loadImage("Icons/bomber.png");
        getImageDimensions();
        this.move();
    }
    private int mineCounter = 0;
    public Projectile fire(){
        if (mineCounter >= 30){
            mineCounter = 0;
            return new Mine(this.getX(), this.getY());
        }
        else {
            mineCounter++;
            return null;
        }
    }
    public int getScore() {return points;}

}
