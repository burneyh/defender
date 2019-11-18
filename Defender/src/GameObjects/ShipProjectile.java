package GameObjects;

public class ShipProjectile extends Projectile{

    public ShipProjectile(Coordinate coordinates, moveDirection direction){
        this.setCoordinates(coordinates);
        this.setDirection(direction);
    }

}
