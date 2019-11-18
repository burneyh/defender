package GameObjects;

public class Mine extends Projectile{
    int points;

    public Mine(Coordinate coordinates){
        super(coordinates.getX(), coordinates.getY());
        damage = 50;
    }
}
