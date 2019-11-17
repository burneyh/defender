package GameObjects;

public class MotherShip extends GameObject{

    private static MotherShip motherShip = null;

    private int health;
    private int score;
    private int direction;
    private int left;
    private int right;
    private int up;
    private int down;

    private MotherShip(){
        super(300, 250);
        health = 100;
        score = 0;
        direction = 1;
        left = 0;
        right =0;
        up = 0;
        down = 0;

        loadImage("res/Icons/001-rocket.png");
        getImageDimensions();
    }
    public void updateScore(int score){
        this.score+= score;
    }

    public void updateHealth(int damage) {
        health  = health - damage;
    }

    public int getDirection() {
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
}
