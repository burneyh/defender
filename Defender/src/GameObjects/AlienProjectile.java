package GameObjects;

public class AlienProjectile extends Projectile {

    public static final double ALIEN_FIRE_RATE = 0.16;

    public AlienProjectile(int x, int y){
        super( x, y);
    }
    public AlienProjectile(Coordinate coordinates, moveDirection direction){
        super( coordinates.getX(), coordinates.getY());
        damage = 25;
        loadImage("Icons/AlienProjectile.png");
        getImageDimensions();
        this.setCoordinates(coordinates);
        this.setDirection(direction);
        this.move(direction);
    }

}
