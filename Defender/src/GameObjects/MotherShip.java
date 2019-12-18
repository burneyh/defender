package GameObjects;

import java.util.ArrayList;

public class MotherShip extends GameObject{

    private static MotherShip motherShip = null;

    private int health;
    private int score;
    private PowerUp powerUp = null;


    private moveDirection direction;

    private MotherShip(){
        super(100, 250);
        health = 100;
        score = 0;
        direction = moveDirection.RIGHT;

        loadImage("Icons/001-rocket.png");
        getImageDimensions();
    }
    public void updateScore(int score){
        this.score+= score;
    }

    public void updateHealth(int damage) {
        health  = health - damage;
    }

    public void refillHealth() {
        health = 100;
    }

    public moveDirection getDirection() {
        return direction;
    }

    public int getHealth(){
        return health;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public static MotherShip getInstance() {
        if (motherShip == null)
            motherShip = new MotherShip();
        return motherShip;
    }

    public static void renew() {
        motherShip = null;
    }

    public void move(moveDirection d){
        switch(d){
            case UP:
                setY(getY() - this.getSpeed());
                break;
            case DOWN:
                setY(getY() + this.getSpeed());
                break;
            case LEFT:
                setX(getX() - this.getSpeed());
                direction = moveDirection.LEFT;
                break;
            case RIGHT:
                setX(getX() + this.getSpeed());
                direction = moveDirection.RIGHT;
                break;
        }

        if (this.getX() > 600) setX(20);
        if (this.getX() < 0)   setX(580);
        if (this.getY() > 500) setY(490);
        if (this.getY() < 0)   setY(10);

    }

    public ArrayList<Projectile> fire(){
        Projectile p1, p2, p3;
        ArrayList<Projectile> newProjectiles = new ArrayList<>();
        int x = this.getCoordinates().getX();
        int y = this.getCoordinates().getY();
        Coordinate ship_Coordinate = new Coordinate(x,y);
        moveDirection newDirection = this.getDirection();
        p1 = new ShipProjectile(ship_Coordinate, newDirection, 2);
        //if (powerUp.getType() == PowerUp.Type.TRIPLE_SHOP){
            p2 = new ShipProjectile(ship_Coordinate, newDirection, 0);
            p3 = new ShipProjectile(ship_Coordinate, newDirection, 1);
        //}
        newProjectiles.add(p1);
        newProjectiles.add(p2);
        newProjectiles.add(p3);
        return newProjectiles;
    }
}
