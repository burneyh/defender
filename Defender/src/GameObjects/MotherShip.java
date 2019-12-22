package GameObjects;

import UserInterface.MyApplication;

import java.util.ArrayList;

public class MotherShip extends GameObject{

    private static MotherShip motherShip = null;
    private static final int startX = 100;
    private static final int startY = 250;

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

    public void resetPos(){
        setX(startX);
        setY(startY);
    };

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

    public void move(boolean isUp, boolean isDown, boolean isLeft, boolean isRight){
        if(isUp)
            setY(getY() - this.getSpeed());

        if(isDown)
            setY(getY() + this.getSpeed());

        if(isLeft){
            setX(getX() - this.getSpeed());
            direction = moveDirection.LEFT;
        }

        if(isRight){
            setX(getX() + this.getSpeed());
            direction = moveDirection.RIGHT;
        }

        if (this.getX() > MyApplication.WIDTH) setX(MyApplication.WIDTH);
        if (this.getX() < 0)   setX(0);
        if (this.getY() > MyApplication.HEIGHT) setY(MyApplication.HEIGHT);
        if (this.getY() < 0)   setY(10);

    }

    public ArrayList<Projectile> fire(){
        Projectile p1, p2, p3;
        ArrayList<Projectile> newProjectiles = new ArrayList<>();
        int x = this.getCoordinates().getX();
        int y = this.getCoordinates().getY();
        Coordinate ship_Coordinate = new Coordinate(x,y);
        moveDirection newDirection = this.getDirection();

        if(powerUp != null && powerUp.getType() == PowerUp.Type.EMPOWERED_SHOT){
            p1 = new ShipProjectile(ship_Coordinate, newDirection, 2);
            p1.loadImage("Icons/empoweredProjectile.png");
        }
        else if(powerUp != null && powerUp.getType() == PowerUp.Type.EXPLOSIVE_SHOT)
            p1 = new ShipProjectile(ship_Coordinate, newDirection, 2, true);
        else
            p1 = new ShipProjectile(ship_Coordinate, newDirection, 2);

        if(powerUp != null && powerUp.getType() == PowerUp.Type.EMPOWERED_SHOT)
            p1.setInvincible(true);

        if (powerUp != null && powerUp.getType() == PowerUp.Type.TRIPLE_SHOT){
            p2 = new ShipProjectile(ship_Coordinate, newDirection, 0);
            p3 = new ShipProjectile(ship_Coordinate, newDirection, 1);
            newProjectiles.add(p2);
            newProjectiles.add(p3);
        }

        newProjectiles.add(p1);



        return newProjectiles;
    }
}
