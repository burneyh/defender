package GameObjects;

public class Mine extends Projectile{
    int points;

    public Mine(int x, int y){
        super(x, y);
        damage = 50;
        loadImage("Icons/mine.png");
        getImageDimensions();
        this.setCoordinates(x,y);
    }
}
