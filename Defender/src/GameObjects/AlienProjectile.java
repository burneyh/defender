package GameObjects;

public class AlienProjectile extends Projectile {


    public AlienProjectile(Coordinate coordinates, moveDirection direction){
        super( coordinates.getX(), coordinates.getY());
        loadImage("Icons/004-rocket-1.png");
        getImageDimensions();
        this.setCoordinates(coordinates);
        this.setDirection(direction);
        damage = 50;
        this.move(direction);
    }

}
