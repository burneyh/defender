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

    // for the explosive shots
    public ShipProjectile(Coordinate coordinates, moveDirection direction, int oblique, boolean explosive){
        super(coordinates.getX(), coordinates.getY());
        super.setExplosive(explosive);

        loadImage("Icons/tesla.png");
        this.getImageDimensions();
        this.setCoordinates(coordinates);
        this.setDirection(direction);
        this.move(direction);
        this.oblique = oblique;
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

    public void move(moveDirection direction){
            switch(direction){
                case LEFT:
                    this.setX(this.getX() - this.getSpeed());
                    break;
                case RIGHT:
                    this.setX(this.getX() + this.getSpeed());
                    break;
            }

            switch (oblique) {
                case 0:
                    this.setY(this.getY() - this.getSpeed()/2);
                    break;
                case 1:
                    this.setY(this.getY() + this.getSpeed()/2);
                    break;
                default:
                    break;
            }


            if (this.getX() > 600 || this.getX() < 0 || this.getY() > 500 || this.getY() < 0)
                this.kill();

        }

}
