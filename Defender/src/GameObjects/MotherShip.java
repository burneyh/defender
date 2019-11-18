package GameObjects;

public class MotherShip extends GameObject{

    private static MotherShip motherShip = null;

    private int health;
    private int score;
    public static enum moveDirection {LEFT, RIGHT, UP, DOWN};
    private moveDirection direction;

    private MotherShip(){
        super(300, 250);
        health = 100;
        score = 0;
        direction = moveDirection.RIGHT;

        loadImage("res/Icons/001-rocket.png");
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
        // TODO
    }

    public void fire(){
        // TODO
    }
}
