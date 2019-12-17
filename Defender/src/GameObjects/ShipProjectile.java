package GameObjects;

public class ShipProjectile extends Projectile{

    public ShipProjectile(Coordinate coordinates, moveDirection direction){
        super(coordinates.getX(), coordinates.getY());

        loadImage("Icons/004-rocket-1.png");
        this.getImageDimensions();
        this.setCoordinates(coordinates);
        this.setDirection(direction);
        this.move(direction);
    }

    public ShipProjectile(Coordinate coordinates, moveDirection direction, boolean oblique){
        super(coordinates.getX(), coordinates.getY());

        loadImage("Icons/004-rocket-1.png");
        this.getImageDimensions();
        this.setCoordinates(coordinates);
        this.setDirection(direction);
        this.move(direction, oblique);
    }

}
