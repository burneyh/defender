package GameObjects;

public class ShipProjectile extends Projectile{

    public ShipProjectile(Coordinate coordinates, moveDirection direction){
        super(coordinates.getX(), coordinates.getY());

        loadImage("Icons/004-rocket-1.png");
        this.getImageDimensions();
        this.setCoordinates(coordinates);
        this.setDirection(direction);
        this.move(direction);
        oblique = 2;
    }

    public ShipProjectile(Coordinate coordinates, moveDirection direction, int oblique){
        super(coordinates.getX(), coordinates.getY());

        loadImage("Icons/004-rocket-1.png");
        this.getImageDimensions();
        this.setCoordinates(coordinates);
        this.setDirection(direction);
        this.move(direction);
        this.oblique = oblique;
    }

}
