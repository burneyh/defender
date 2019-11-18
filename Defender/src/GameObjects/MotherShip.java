package GameObjects;

public class MotherShip extends GameObject{

    private static MotherShip motherShip = null;

    private int health;
    private int score;


    private moveDirection direction;

    private MotherShip(){
        super(300, 250);
        health = 100;
        score = 0;
        direction = moveDirection.RIGHT;

        loadImage("CS319-1C-DE/Defender/res/Icons/001-rocket.png");
        getImageDimensions();
    }
    public void updateScore(int score){
        this.score+= score;
    }

    public void updateHealth(int damage) {
        health  = health - damage;
    }

    public moveDirection getDirection() {
        return direction;
    }

    public int getHealth(){
        return health;
    }

    public static MotherShip getInstance() {
        if (motherShip == null)
            motherShip = new MotherShip();
        return motherShip;
    }

    public void move(moveDirection d){
        switch(direction){
            case UP:
                setY(getY() - this.getSpeed());
                break;
            case DOWN:
                setY(getY() + this.getSpeed());
                break;
            case LEFT:
                setY(getX() + this.getSpeed());
                break;
            case RIGHT:
                setY(getX() - this.getSpeed());
                break;
        }

        if (this.getX() > 600) setX(20);
        if (this.getX() < 0)   setX(580);
        if (this.getY() > 500) setX(10);
        if (this.getY() < 0)   setX(480);

    }

    public Projectile fire(){
        return new ShipProjectile(this.getCoordinates(), this.getDirection());
    }
}
