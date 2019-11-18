package GameObjects;

public class AlienProjectile extends Projectile {
    public AlienProjectile(){}

    public AlienProjectile(Coordinate coordinates, moveDirection direction){
        super( coordinates.getX(), coordinates.getY());
        loadImage("res/Icons/004-rocket-1.png");
        getImageDimensions();
        damage = 50;
        this.move(direction);
    }

}
