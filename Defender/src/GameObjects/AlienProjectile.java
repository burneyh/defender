package GameObjects;

public class AlienProjectile extends Projectile {


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
